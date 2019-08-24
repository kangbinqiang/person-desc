package com.spring.controller;

import com.spring.pojo.MenuQueryObject;
import com.spring.pojo.SysUser;
import com.spring.pojo.UserQueryObject;
import com.spring.response.ResponseBean;
import com.spring.service.SysUserService;
import com.spring.utils.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "Sysuser")
public class SysUserController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "getAllSysUserInfo")
    @ResponseBody
    public ResponseBean<List<SysUser>> getAllSysUserInfo(SysUser sysUser){
        List<SysUser> list = sysUserService.getAllSysUserInfo(sysUser);
        ResponseBean sysUserResponseBean = new ResponseBean(true,list);
        return sysUserResponseBean;
    }
    @RequestMapping(value = "getAllList")
    @ResponseBody
    public PageResult getAlluser(UserQueryObject userQueryObject){
        return sysUserService.getAlluser(userQueryObject);
    }

}
