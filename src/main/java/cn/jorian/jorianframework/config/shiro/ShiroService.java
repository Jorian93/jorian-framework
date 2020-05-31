package cn.jorian.jorianframework.config.shiro;

import cn.jorian.jorianframework.core.system.entity.SysResource;
import cn.jorian.jorianframework.core.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: jorian
 * @Date: 2019/5/8 15:53
 * @Description:
 */
@Service
@Component
public class ShiroService {

    @Autowired
    ResourceService resourceService;


    public Map<String, String> getFilterChainDefinitionMap() {
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 需要鉴权的
        List<String[]> permsList = new LinkedList<>();
        // 直接放行的
        List<String[]> anonList = new LinkedList<>();

        List<SysResource> resources = resourceService.list();;
        if(resources!=null){
            for (SysResource resource : resources) {
                if(!StringUtils.isEmpty(resource.getPath()) && !StringUtils.isEmpty(resource.getPermission())){
                    if(!"".equals(resource.getPermission().trim())) {
                        //判断是否需要权限验证
                        if(resource.getIsVerify()){
                            // {"/system/user/list/**","perms[syste:user:list:*]"}
                            permsList.add(0,new String[]{resource.getPath()+"/**","perms["+resource.getPermission()+":*]"});
                        }else{
                            // {"/system/user/list/**","anon"}
                            anonList.add(0,new String[]{resource.getPath()+"/**","anon"});
                        }
                    }
                }
                // 有点多余，本身就是所有的数据的list
                // iterationAllResourceInToFilter(resource,permsList,anonList);
            }
        }
        for (String[] strings : anonList) {
            filterChainDefinitionMap.put(strings[0],strings[1]);
        }

        for (String[] strings : permsList) {
            filterChainDefinitionMap.put(strings[0],strings[1]);
        }
        return filterChainDefinitionMap;
    }

    // public void iterationAllResourceInToFilter(SysResource resource,List<String[]> permsList, List<String[]> anonList){
    //     if(resource.getChildren()!=null && resource.getChildren().size()>0){
    //         for (SysResource v : resource.getChildren()) {
    //             if(!StringUtils.isEmpty(v.getPath()) && !StringUtils.isEmpty(v.getPermission())){
    //                 if(v.getIsVerify()){
    //                     permsList.add(0,new String[]{v.getPath()+"/**","perms["+v.getPermission()+":*]"});
    //                 }else{
    //                     anonList.add(0,new String[]{v.getPath()+"/**","anon"});
    //                 }
    //                 iterationAllResourceInToFilter(v,permsList,anonList);
    //             }
    //         }
    //     }
    // }
}
