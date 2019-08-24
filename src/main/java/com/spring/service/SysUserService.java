package com.spring.service;

import com.spring.pojo.SysUser;
import com.spring.pojo.UserQueryObject;
import com.spring.utils.PageResult;

import java.util.List;

public interface SysUserService {

    SysUser getSysUserByusername(String username);

    SysUser selectByPrimaryKey(Integer id);

    List<SysUser> getAllSysUserInfo(SysUser sysUser);

    PageResult getAlluser(UserQueryObject userQueryObject);
}
