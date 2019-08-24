package com.spring.dao;


import com.spring.pojo.MenuQueryObject;
import com.spring.pojo.SysMenu;

import java.util.List;

public interface SysMenuMapper {

    List<SysMenu> getAllMenuInfo(SysMenu sysMenu);

    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    int getAllMenuCount(MenuQueryObject menuQueryObject);

    List getAllMenuList(MenuQueryObject menuQueryObject);
}