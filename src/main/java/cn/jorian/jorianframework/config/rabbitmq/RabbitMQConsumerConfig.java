package cn.jorian.jorianframework.config.rabbitmq;

import cn.jorian.jorianframework.core.system.entity.SysLog;
import cn.jorian.jorianframework.core.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author: jorian
 * @date: 2019/11/19 13:34
 * @description: this is  description for the class
 */
@Slf4j
@Component
public class RabbitMQConsumerConfig {

    @Autowired
    LogService logService;

    // queues是指要监听的队列的名字,监听队列1,保存日志到mysql
    @RabbitListener(queues = RabbitMQProviderConfig.TOPIC_QUEUE_LOG_MYSQL)
    public void receiveTopic1(SysLog sysLog) {
        logService.save(sysLog);
        log.info("【消息队列:{}监听到消息】-消息负载:{}" ,RabbitMQProviderConfig.TOPIC_QUEUE_LOG_MONGODB,sysLog.toString());
    }
    // 监听队列2，保存日志到mongodb
    @RabbitListener(queues = RabbitMQProviderConfig.TOPIC_QUEUE_LOG_MONGODB)
    public void receiveTopic2(SysLog sysLog) {
        log.info("【消息队列:{}监听到消息】-消息负载:{}" ,RabbitMQProviderConfig.TOPIC_QUEUE_LOG_MONGODB,sysLog.toString());
        logService.save(sysLog);
    }
    // 监听队列3，保存日志到ES
    @RabbitListener(queues = RabbitMQProviderConfig.TOPIC_QUEUE_LOG_ELSEARCH)
    public void receiveTopic3(SysLog sysLog) {
        log.info("【消息队列:{}监听到消息】--- 消息负载:{}" ,RabbitMQProviderConfig.TOPIC_QUEUE_LOG_MONGODB,sysLog.toString());
        logService.save(sysLog);
    }


}
