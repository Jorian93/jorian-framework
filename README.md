演示地址：http://jorianye.cn:8088/framework-web  
测试账户：test  
密码：test123
## 重要提示！！
由于许多人没有elasticsearch和rabbitmq,所以特别建立了branch v1.0,v1.0去除了es,mq相关代码，但是功能保持不变，需要纯净版的请拉branch v1.0,
# jorian-framework特点  
1.轻量级   
2.集成shiro+jwt完成登录校验，权限控制，粒度为url级别  
3.集成mybatis-plus及代码生成器  
4.使用自定义注释完成日志收集,消息队列异步完成存储  
5.包含有文件中心,邮件中心,监控中心等基础功能  
6.集成了rabbitmq,elasticsearch ,适用于学习和轻量级项目开发  


# 所用技术栈  
1.SpringBoot 2.0  
2.Shiro 1.3  
3.Mybatis-Plus 3.X + MP代码生成器  
4.JWT 3.0
5.redis
6.rabbitmq
7.elasticsearch

# 使用环境准备  
1.JDK1.8  
2.nginx 用于配置图片服务器  
3.mysql5.7
4.redis3.x
5.rabbitmq
6.elasticsearch   

# 使用步骤  
1.克隆项目  
2.新建mysql数据库jorian-framework，导入目录下sql文件夹内的jorian-framework.sql   
3.修改resources下的applicatiion-dev,修改redis和数据库连接,rabbitmq,Elasticsearch配置为自己的   
4.启动项目即可
    

# 相关图片
1.  
  ![image1](https://github.com/Jorian93/hello-word/blob/master/images/2019-05-29_150510.png)   
2.  
  ![image2](https://github.com/Jorian93/hello-word/blob/master/images/2019-05-29_150552.png)   
3.  
  ![image3](https://github.com/Jorian93/hello-word/blob/master/images/2019-05-29_150617.png)   
# 联系我  
如有任何疑问，请通过以下方式联系我：  
* 邮件(1977474361@qq.com ; jorianye@163.com)    
* qq交流群: 177154143  



