package cn.jorian.jorianframework.core.mail.service.impl;

import cn.jorian.jorianframework.common.utils.JTool_Mail;
import cn.jorian.jorianframework.core.account.service.AccountService;
import cn.jorian.jorianframework.core.mail.dto.MailAddDTO;
import cn.jorian.jorianframework.core.mail.dto.MailFindDTO;
import cn.jorian.jorianframework.core.mail.entity.Mail;
import cn.jorian.jorianframework.core.mail.entity.MailTo;
import cn.jorian.jorianframework.core.mail.mapper.MailMapper;
import cn.jorian.jorianframework.core.mail.service.MailService;
import cn.jorian.jorianframework.core.mail.service.MailToService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-10-30
 */
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements MailService {


    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    @Autowired
    private JTool_Mail jToolMail;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MailToService mailToService;

    @Autowired
    private DataSourceTransactionManager transactionManager;


    @Override
    @Transactional
    public void saveMail(MailAddDTO addDTO) {
        Mail mail = new Mail();
        BeanUtils.copyProperties(addDTO,mail);
        String toUsers = mail.getToUsers().trim();
        if (StringUtils.isBlank(toUsers)) {
            throw new IllegalArgumentException("收件人不能为空");
        }
        //过滤收件人
        toUsers = toUsers.replace(" ", "");
        toUsers = toUsers.replace("；", ";");
        String[] tousers = toUsers.split(";");

        List<String> toUserss = Arrays.asList(tousers);
        toUserss = toUserss.stream().filter(u -> !StringUtils.isBlank(u)).map(u -> u.trim()).collect(Collectors.toList());
        mail.setUserId("blog-main-sender");
        String userid = accountService.getCurrentUser().getId();
        if(userid!=null){
            mail.setUserId(userid);
        }
        mail.setCreateTime(LocalDateTime.now());
        mail.setUpdateTime(LocalDateTime.now());
        this.save(mail);
        toUserss.forEach(u -> {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            // explicitly setting the transaction name is something that can only be done programmatically
            def.setName("hh");
            int status = 0;
            TransactionStatus statuss = transactionManager.getTransaction(def);//定义点位？
            try {
                jToolMail.sendMail(u, mail.getSubject(), mail.getContent());
                status = 1;
            } catch (Exception e) {
                log.error("发送邮件失败", e);
                transactionManager.rollback(statuss);//手动回滚
            }
            MailTo mailTo = MailTo.builder()
                    .mailId(mail.getId())
                    .toUser(u)
                    .status(status)
                    .build();
            mailTo.setCreateTime(LocalDateTime.now());
            mailTo.setUpdateTime(LocalDateTime.now());
            mailToService.save(mailTo);
        });

    }

    @Override
    public IPage<Mail> listMails(MailFindDTO mailFindDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.orderByDesc(mailFindDTO.getSort());
        return this.page(new Page<>(mailFindDTO.getPage(),mailFindDTO.getLimit()),queryWrapper);

    }

}
