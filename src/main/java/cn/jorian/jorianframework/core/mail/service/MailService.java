package cn.jorian.jorianframework.core.mail.service;

import cn.jorian.jorianframework.core.mail.dto.MailAddDTO;
import cn.jorian.jorianframework.core.mail.dto.MailFindDTO;
import cn.jorian.jorianframework.core.mail.entity.Mail;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jorian
 * @since 2019-10-30
 */


public interface MailService extends IService<Mail> {

    /**
     * 保存邮件
     * @param addDTO
     */
    void saveMail(MailAddDTO addDTO);

    /**
     * 获取邮件列表
     * @param mailFindDTO
     * @return
     */
    IPage<Mail> listMails(MailFindDTO mailFindDTO);
}
