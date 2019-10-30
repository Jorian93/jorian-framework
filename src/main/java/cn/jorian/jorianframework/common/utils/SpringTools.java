package cn.jorian.jorianframework.common.utils;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @author jorian
 * @version 2019/4/28/9:46
 */
public class SpringTools {


    @Autowired
    private static ApplicationContext applicationContext;
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip==null || "".equals(ip.trim()) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if("0.0.0.0".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip) || "localhost".equals(ip) || "127.0.0.1".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 判断请求是否是Ajax
     * @param request
     * @return
     */
    public static boolean ajax(HttpServletRequest request){
        String accept = request.getHeader("accept");
        return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
    }

    /**
     * 获取bean的名字
     * @return
     */
    public static List<String> getBeanNames(){
        String[] strings = applicationContext.getBeanDefinitionNames();
        List<String> list = new ArrayList<>();
        for (String str : strings) {
            if (str.contains(".")) {
                continue;
            }
            Class<?> clazz = getClass(str);
            if (clazz.isAnnotationPresent(Service.class) && str.toLowerCase().contains("service")) {
                list.add(str);
            }
        }
        Collections.sort(list);// 2018.07.26修改排序
        return list;
    }

    /**
     * 获取方法名字
     * @param name
     * @return
     */
    public static Set<String> getBeansMethods(String name){
        Class<?> clazz = getClass(name);
        Method[] methods = clazz.getDeclaredMethods();

        Set<String> names = new HashSet<>();
        Arrays.asList(methods).forEach(m -> {
            int b = m.getModifiers();// public 1 static 8 final 16
//			if (b == 1 || b == 9 || b == 17 || b == 25) {
            if (Modifier.isPublic(b)) { // 2018.07.26修改public方法的判断
                Class<?>[] classes = m.getParameterTypes();
                if (classes.length == 0) {
                    names.add(m.getName());
                }
            }
        });
        return names;
    }

    private static Class<?> getClass(String name) {
        Object object = applicationContext.getBean(name);
        Class<?> clazz = object.getClass();
        if (AopUtils.isAopProxy(object)) {
            clazz = clazz.getSuperclass();
        }
        return clazz;
    }

}
