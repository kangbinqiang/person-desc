package com.spring.service;

import com.spring.pojo.TaskQueryObject;
import com.spring.utils.PageResult;

import java.io.InputStream;

public interface SysTaskService {

    PageResult getAllTaskList(TaskQueryObject taskQueryObject);
    InputStream imgs(String processId);
}
