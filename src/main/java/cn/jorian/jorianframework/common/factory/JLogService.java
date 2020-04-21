package cn.jorian.jorianframework.common.factory;

import cn.jorian.jorianframework.core.system.entity.SysLog;

/**
 * @author: jorian
 * @date: 2020/4/20 21:38
 * @description: this is  description for the class
 */
public interface JLogService {
    /**
     * 保存日志
     * @param sysLog SAVE_TO_MYSQL  routingKey = "log.mysql";
     *     SAVE_TO_MONGODB  routingKey= "log.mongodb";
     *     SAVE_TO_ELSEARCH routingKey= "log.elsearch";
     *     SAVE_TO_Remote routingKey= "log.Remote";
     */
    void save(SysLog sysLog, String routingKey);
}
