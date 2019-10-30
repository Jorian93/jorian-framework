package cn.jorian.jorianframework.core.mail.service.impl;

import cn.jorian.jorianframework.core.mail.entity.MailTo;
import cn.jorian.jorianframework.core.mail.mapper.MailToMapper;
import cn.jorian.jorianframework.core.mail.service.MailToService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jorian
 * @since 2019-10-30
 */
@Service
public class MailToServiceImpl extends ServiceImpl<MailToMapper, MailTo> implements MailToService {

}
