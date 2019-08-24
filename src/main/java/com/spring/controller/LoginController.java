package com.spring.controller;


import com.spring.pojo.SysUser;
import com.spring.response.ResponseBean;
import com.spring.response.UnicomResponseEnums;
import com.spring.utils.CreateImageCode;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户登录功能
 */
@Controller
@RequestMapping(value = "PersonLogin")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login")
    public String UserLogin(){
        return "login";
    }

    /**
     * 生成验证码
     */
    @RequestMapping("/getcheckcode")
    public void getCheckCode(HttpServletResponse response, HttpSession session)throws IOException {
        CreateImageCode vCode = new CreateImageCode(116,36,4,10);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
    }

    /**
     * 校验用户登录
     * @param request
     * @return
     */
    @RequestMapping(value = "CheckLogin")
    @ResponseBody
    public ResponseBean<SysUser> CheckSysUserLogin(HttpServletRequest request){
        logger.info("登陆验证处理开始");
        long start = System.currentTimeMillis();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        try{
            if (StringUtils.isEmpty(username)){
                logger.error("用户名为空！");
                return new ResponseBean<>(false,null, UnicomResponseEnums.SYSUSER_EMPTY);
            }
            if (StringUtils.isEmpty(password)){
                logger.error("密码为空");
                return new ResponseBean<>(false,null,UnicomResponseEnums.PASSWORD_EMPTY);
            }
            if (StringUtils.isEmpty(code)){
                logger.error("验证码为空");
                return new ResponseBean<>(false,null,UnicomResponseEnums.CODE_EMPTY);
            }
            String sessionCode = (String) request.getSession().getAttribute("code");
            if (!code.toLowerCase().equals(sessionCode)){
                logger.error("验证码错误");
                return new ResponseBean<>(false,null,UnicomResponseEnums.CODE_INCORRECT);
            }

            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            if (currentUser.isAuthenticated()){
                return new ResponseBean<>(true,new SysUser());
            }
            return new ResponseBean<>(false,null,UnicomResponseEnums.LOGIN_INCORRECT);
        } catch (IncorrectCredentialsException ice) {
            logger.error("登陆验证失败,原因:用户名或密码不匹配");
            return new ResponseBean<>(false,null,UnicomResponseEnums.LOGIN_INCORRECT);
        }catch (AccountException e){
            logger.error("登陆验证失败,原因:用户名或密码不匹配");
            return new ResponseBean<>(false,null,UnicomResponseEnums.LOGIN_INCORRECT);
        }catch (Exception e) {
            logger.error("登陆验证失败,原因:系统登陆异常", e);
            return new ResponseBean<>(false,null,UnicomResponseEnums.LOGIN_INCORRECT);
        } finally {
            logger.info("登陆验证处理结束,用时" + (System.currentTimeMillis() - start) + "毫秒");
        }
    }
    /**
     * 用户退出
     */
    @RequestMapping("/Logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }

}
