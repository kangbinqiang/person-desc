package com.spring.activity;


import org.activiti.engine.EngineServices;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(value = "createActiviti")
@Controller
public class InitActivityData {
    private static final Logger logger = LoggerFactory.getLogger(InitActivityData.class);
    @Autowired
    private EngineServices processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    //部署流程
    @RequestMapping("/buildFlow")
    @ResponseBody
    public void deployProcess(){
        logger.info("部署流程");
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder builder = repositoryService.createDeployment();
        builder.addClasspathResource("askforleave.bpmn");
        builder.deploy();
    }


    @RequestMapping("/startAskForLeave")
    @ResponseBody
    public String firstDemo() {
        logger.info("启动请假流程");
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("askforleave");
        return "请假流程创建成功";
    }

    @RequestMapping("/queryFlow")
    @ResponseBody
    public void queryTask(){
        logger.info("查询流程");
        TaskService taskService = processEngine.getTaskService();
        //根据assignee(节点接受人)查询任务
        String assignee = "student";
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();
        int size = tasks.size();
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
        }
        //首次运行的时候这个没有输出，因为第一次运行的时候扫描act_ru_task的表里面是空的，但第一次运行完成之后里面会添加一条记录，之后每次运行里面都会添加一条记录
        for (Task task : tasks) {
            System.out.println("taskId=" +"流程任务节点信息ID："+ task.getId() +
                    ",taskName:" +"流程任务节点名称ID：" +task.getName() +
                    ",assignee:" + "流程任务节点接受人："+task.getAssignee() +
                    ",createTime:" +"流程任务节点创建时间："+ task.getCreateTime());
        }
    }

    @RequestMapping("/examineFlow")
    @ResponseBody
    public void examineFlow(HttpServletRequest request){
        logger.info("处理流程");
        String taskId =  request.getParameter("taskId");
        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskId);
    }

}
