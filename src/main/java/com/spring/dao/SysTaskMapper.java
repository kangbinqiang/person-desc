package com.spring.dao;


import com.spring.pojo.SysTask;
import com.spring.pojo.TaskQueryObject;

import java.util.List;

public interface SysTaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysTask record);

    int insertSelective(SysTask record);

    SysTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysTask record);

    int updateByPrimaryKey(SysTask record);

    int getAllTaskCount(TaskQueryObject taskQueryObject);

    List getAllTaskList(TaskQueryObject taskQueryObject);
}