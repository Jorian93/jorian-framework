package cn.jorian.jorianframework.common.factory;

/**
 * @author: jorian
 * @date: 2020/4/20 21:40
 * @description: this is  description for the class
 */
public class JLogFactory {
    public static final String SAVE_TO_MYSQL = "log.mysql";
    public static final String SAVE_TO_MONGODB = "log.mongodb";
    public static final String SAVE_TO_ELSEARCH = "log.elsearch";

    /**
     *
     * @param logType 1."mysql"  2."mongodb"  3."elsearch"
     * @return
     */
    public static JLogService generateLogService(String logType){
        if(JLogFactory.SAVE_TO_MYSQL.equals(logType)){
            return new JLogToAnywhereByMq();
        }

        return null;
    }





}
