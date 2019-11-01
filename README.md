**FaceChina2.0-server简介**
`项目特点`
FaceChina2.0-server是一个轻量级的，前后端分离的Java快速开发平台

`项目结构`
faceChina-server
├─db  项目SQL语句
│
├─src 
│  └─main
│      ├─java
│      │  └─com.spring
│      │      ├─activity 流程
│      │      ├─common 公共
│      │      ├─controller 控制
│      │      ├─dao 持久
│      │      ├─model 模型
│      │      ├─pojo 实体
│      │      ├─producer MQ
│      │      ├─response 相应
│      │      ├─service 业务
│      │      ├─utils 工具
│      └─resources
│          ├─key 密钥文件
│          ├─mapping 映射文件
│          ├─processes 流程引擎文件
│          ├─static 静态文件
│          └─templates 模板文件

`项目技术资料`
    核心框架：Spring Boot 
    安全框架：Apache Shiro 
    持久层框架：MyBatis 
    流程引擎：Activity 
    数据库连接池：Druid
    日志：log4j
    搜索引擎：solr
    页面交互：LayUI
    数据库：MYSQL
    定时器：Quartz 



