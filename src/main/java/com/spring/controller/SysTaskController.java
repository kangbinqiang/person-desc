package com.spring.controller;

import com.spring.pojo.TaskQueryObject;
import com.spring.service.SysTaskService;
import com.spring.utils.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;

@Controller
@RequestMapping(value = "Task")
public class SysTaskController {

    private static final Logger logger = LoggerFactory.getLogger(SysTaskController.class);

    @Autowired
    private SysTaskService sysTaskService;

    @Autowired
    private SysTaskService userTaskService;

    @RequestMapping(value = "getTaskList")
    @ResponseBody
    public PageResult getTaskList(TaskQueryObject taskQueryObject) {
        return sysTaskService.getAllTaskList(taskQueryObject);
    }

    @RequestMapping("getImages")
    public void getCode(HttpServletRequest req, HttpServletResponse res){
        logger.info("流程跟踪信息");
        String processId = req.getParameter("processId");
        InputStream in = null;
        byte[] b=new byte[1024];
        try {
            ServletOutputStream sos = res.getOutputStream();
            in = userTaskService.imgs(processId);
            //文件名
            String src3="askforleave.png";
            res.setHeader("Content-Disposition","attachment;filename="+URLEncoder
                    .encode(src3,"UTF-8"));
            int i=in.read(b, 0, b.length);
            while(i!=-1){
                sos.write(b, 0, i);
                i=in.read(b,0,b.length);
            }
            in.close();
            sos.close();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}