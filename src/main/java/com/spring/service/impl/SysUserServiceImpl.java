package com.spring.service.impl;

import com.spring.dao.SysUserMapper;
import com.spring.pojo.SysUser;
import com.spring.pojo.UserQueryObject;
import com.spring.service.SysUserService;
import com.spring.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public SysUser getSysUserByusername(String username) {
        return sysUserMapper.getSysUserByusername(username);
    }

    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        return sysUser;
    }

    @Override
    public List<SysUser> getAllSysUserInfo(SysUser sysUser) {
        List<SysUser> list = new ArrayList<>();
        list = sysUserMapper.getAllSysUserInfo(sysUser);
        return list;
    }

    @Override
    public PageResult getAlluser(UserQueryObject userQueryObject) {
        int count = sysUserMapper.getAllUserCount(userQueryObject);
        List list = sysUserMapper.getAllUserList(userQueryObject);
        if(count == 0){
            return new PageResult(0, Collections.emptyList());
        }
        return new PageResult(count,list);
    }
}
