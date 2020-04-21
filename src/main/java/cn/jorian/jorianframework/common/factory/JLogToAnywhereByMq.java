package cn.jorian.jorianframework.common.factory;

import cn.jorian.jorianframework.config.rabbitmq.RabbitMQProviderConfig;
import cn.jorian.jorianframework.core.system.entity.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: jorian
 * @date: 2020/4/20 21:43
 * @description: record log into database
 */
@Slf4j
@Component
public class JLogToAnywhereByMq implements JLogService {
    @Autowired
    AmqpTemplate amqpTemplate;
    @Override
    public void save(SysLog sysLog, String routingKey) {
        this.amqpTemplate.convertAndSend(RabbitMQProviderConfig.TOPIC_EXCHANGE, routingKey, sysLog);
        log.info("【已发送消息至路由{}-routingKey:{}】", RabbitMQProviderConfig.TOPIC_EXCHANGE,routingKey);
    }
}
