package com.spring.dao;


import com.spring.pojo.SysUser;
import com.spring.pojo.UserQueryObject;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> getAllSysUserInfo(SysUser sysUser);

    SysUser getSysUserByusername(String username);

    int getAllUserCount(UserQueryObject userQueryObject);

    List getAllUserList(UserQueryObject userQueryObject);
}