package cn.jorian.jorianframework.common.model;

import lombok.Data;

import java.util.List;

/**
 * @Auther: jorian
 * @Date: 2019/4/27 01:53
 * @Description:
 */
@Data
public class ElementTree {
    private String id;
    private String lable;
    private List<ElementTree> Children;
}
