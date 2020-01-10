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
public class JTool_Spring {

    @Autowired
    private static ApplicationContext applicationContext;


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
        //修改排序
        Collections.sort(list);
        return list;
    }

    /**
     * 获取类的方法名集合
     * @param name
     * @return
     */
    public static Set<String>  getBeansMethods(String name){
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

    /**
     * 根据名称获取类
     * @param name
     * @return
     */
    private static Class<?> getClass(String name) {
        Object object = applicationContext.getBean(name);
        Class<?> clazz = object.getClass();
        if (AopUtils.isAopProxy(object)) {
            clazz = clazz.getSuperclass();
        }
        return clazz;
    }

}
