package com.spring.service;

import com.spring.pojo.MenuQueryObject;
import com.spring.pojo.SysMenu;
import com.spring.utils.PageResult;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> getAllMenuInfo(SysMenu sysMenu);

    PageResult getAllMenuList(MenuQueryObject menuQueryObject);
}
