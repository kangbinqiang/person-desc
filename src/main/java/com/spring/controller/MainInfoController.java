package com.spring.controller;

import com.spring.pojo.MenuQueryObject;
import com.spring.pojo.SysMenu;
import com.spring.service.SysMenuService;
import com.spring.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "MainInfo")
public class MainInfoController {

    @Autowired
    private SysMenuService sysMenuService;
    //配置页面路径
    @RequestMapping(value = "index")
    public String turnToIndex(){
        return "index";
    }
    @RequestMapping(value = "user")
    public String turnToUser(){
        return "user";
    }
    @RequestMapping(value = "role")
    public String turnToRole(){
        return "role";
    }
    @RequestMapping(value = "main")
    public String turnToMain(){
        return "main";
    }
    @RequestMapping(value = "menu")
    public String turnToMenu(){
        return "menu";
    }
    @RequestMapping(value = "task")
    public String turnToTask(){
        return "task";
    }
    @RequestMapping(value = "adduser")
    public String addUser(){
        return "adduser";
    }



    @RequestMapping(value = "getMenuInfo")
    @ResponseBody
    public List<SysMenu> getAllMenuInfo(SysMenu sysMenu){
        return sysMenuService.getAllMenuInfo(sysMenu);
    }

    @RequestMapping(value = "getMenuList")
    @ResponseBody
    public PageResult getMenuList(MenuQueryObject menuQueryObject){
        return sysMenuService.getAllMenuList(menuQueryObject);
    }
}
