### 演示地址：http://jorianye.cn:8088/framework-web  

测试账户：test  
密码：test123

# jorian-framework特点  

------

1.轻量级，快速启动，基础功能完善，可直接用于中小型项目开发
2.集成shiro+jwt完成权限控制，粒度为url级别  
3.集成mybatis-plus及代码生成器  
4.统一日志处理，使用自定义注释@log完成日志收集, 使用消息队列异步完成存储    5.统一异常处理  6.统一系统响应  7.集成了Rabbitmq,ElasticSearch等中间件    8.系统预设文件中心，监控中心，邮件中心，定时任务管理等功能

# 所用技术栈  

------

1.SpringBoot 2.x 
2.Shiro 1.3  
3.Mybatis-Plus 3.x + MP代码生成器  
4.JWT 3.0
5.Redis
6.RabbitMQ
7.Elasticsearch

# 项目架构

------



# 使用环境准备  

------

1.JDK 8  
2.Nginx (用于配置图片服务器 )
3.Mysql5.7
4.Redis3.x
5.RabbitMQ
6.Elasticsearch   

# 使用步骤  

------

1.克隆项目  
2.新建mysql数据库jorian-framework，并导入目录下sql文件夹内的jorian-framework.sql   
3.找到resources下的applicatiion-dev.yml, 修改其中的Redis、Database、rabbitmq、Elasticsearch相关配置   
4.启动项目访问首页
    

# 相关图片

------



1.  
  ![image1](https://github.com/Jorian93/hello-word/blob/master/images/2019-05-29_150510.png)   
2.  
  ![image2](https://github.com/Jorian93/hello-word/blob/master/images/2019-05-29_150552.png)   
3.  
  ![image3](https://github.com/Jorian93/hello-word/blob/master/images/2019-05-29_150617.png)   
# 联系作者  

------

如果有任何疑问，请通过以下方式联系我：  
* 邮件( jorianye@163.com)  
* 作者QQ: 1977474361 
* QQ交流群: 177154143  



