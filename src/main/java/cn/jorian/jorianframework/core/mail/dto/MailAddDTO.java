package cn.jorian.jorianframework.core.mail.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: jorian
 * @date: 2019/10/30 16:52
 * @description: this is the class description
 */
@Data
public class MailAddDTO {

    @NotBlank(message = "收件人不能为空")
    private String toUsers;
    /**
     * 标题
     */
    @NotBlank(message = "主题不能为空")
    private String subject;

    /**
     * 正文
     */
    @NotBlank(message = "内容不能为空")
    private String content;

}
