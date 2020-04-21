package cn.jorian.jorianframework.config.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: jorian
 * @date: 2019/11/19 13:46
 * @description: this is  description for the class
 */
@Configuration
public class RabbitMQProviderConfig {
    public static final String TOPIC_QUEUE_LOG_MYSQL = "topic.queue.log.mysql";
    public static final String TOPIC_QUEUE_LOG_MONGODB = "topic.queue.log.mongodb";
    public static final String TOPIC_QUEUE_LOG_ELSEARCH = "topic.queue.log.elsearch";

    public static final String TOPIC_EXCHANGE = "topic.jexchange.log";

    /**
     * Topic模式，定义3个队列
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_LOG_MYSQL);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_LOG_MONGODB);
    }
    @Bean
    public Queue topicQueue3() {
        return new Queue(TOPIC_QUEUE_LOG_ELSEARCH);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /***
     * 给队列绑定路由key
     * @return
     */
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("log.mysql");
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("log.mongodb");
    }
    @Bean
    public Binding topicBinding3() {
        return BindingBuilder.bind(topicQueue3()).to(topicExchange()).with("log.elsearch");
    }

}
