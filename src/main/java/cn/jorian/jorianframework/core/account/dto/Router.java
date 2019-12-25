package cn.jorian.jorianframework.core.account.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: jorian
 * @Date: 2019/5/1 08:33
 * @Description:
 *
 *                   * {
 *                   *     path: '/system',
 *                   *     component: Layout,
 *                   *     redirect: '/system/user',
 *                   *     name: 'System',
 *                   *     meta: { title: '系统管理', icon: 'system' ,role: ['admin', 'editor'] },
 *                   *     children: [
 *                   *       {
 *                   *         path: 'user',
 *                   *         name: 'User',
 *                   *         component: () => import('@/views/system/user/index'),
 *                   *         meta: { title: '用户管理', icon: 'user' ,role: ['admin', 'editor'] }
 *                   *       },
 *                   *
 *                   *
 *
 */
@Data
public class Router {
    /**
     * 访问路径
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 重定向
     */
    private String redirect;
    /**
     * 名称
     */
    private String name;
    private Map<String,String > meta;
    private Boolean hidden;
    private List<Router> children;


}


