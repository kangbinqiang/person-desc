package com.spring.service.impl;

import com.spring.dao.SysMenuMapper;
import com.spring.pojo.MenuQueryObject;
import com.spring.pojo.SysMenu;
import com.spring.service.SysMenuService;
import com.spring.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getAllMenuInfo(SysMenu sysMenu) {
        List<SysMenu> resultList = new ArrayList<>();
        List<SysMenu> list = sysMenuMapper.getAllMenuInfo(sysMenu);
        for (SysMenu temp : list) {
            if (temp.getParentId() == 0) {
                //查找它的子菜单
                List<SysMenu> chilList = new ArrayList<>();
                for(SysMenu child : list) {
                    if (temp.getMenuId() == child.getParentId()) {
                        chilList.add(child);
                    }
                }
                temp.setChildMenu(chilList);
                resultList.add(temp);
            }
        }
        return resultList;
    }

    @Override
    public PageResult getAllMenuList(MenuQueryObject menuQueryObject) {
        int count = sysMenuMapper.getAllMenuCount(menuQueryObject);
        List list = sysMenuMapper.getAllMenuList(menuQueryObject);
        if(count == 0){
            return new PageResult(0, Collections.emptyList());
        }
        return new PageResult(count,list);
    }
}
