package cn.jorian.jorianframework.common.model;

import ch.qos.logback.classic.db.names.TableName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author: jorian
 * @Date: 2019/4/17 15:47
 * @Description:
 */

public class RedisDB {

    public static final String APP_NAME = "jframework:";
    public static final String TABLE_USER = "userinfo:";
    public static final String TABLE2_MAIL = "mailinfo:";

    public static String getDBKey(String tableName){


        return APP_NAME+tableName;

    } 

}
