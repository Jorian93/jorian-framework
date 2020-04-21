package cn.jorian.jorianframework.common.annotation;

import java.lang.annotation.*;

/**
 * @author jorian
 * @version 2019/4/27/17:36
 * 系统日志
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JLog {
    String value();
}
