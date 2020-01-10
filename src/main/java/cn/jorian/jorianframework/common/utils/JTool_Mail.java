package cn.jorian.jorianframework.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/***
 * @author jorian
 * 邮件工具
 */
@Service
public class JTool_Mail {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String serverMail;


	/**
	 * 多收件人
	 * @param toUser
	 * @param subject
	 * @param text
	 */
	public  void sendMail(List<String> toUser, String subject, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(serverMail);
			helper.setTo(toUser.toArray(new String[toUser.size()]));
			helper.setSubject(subject);
			helper.setText(text, true);

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 单一收件人
	 * @param toUser
	 * @param subject
	 * @param text
	 * @throws MessagingException
	 */
	public void sendMail(String toUser, String subject, String text) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(serverMail);
		helper.setTo(toUser);
		helper.setSubject(subject);
		helper.setText(text, true);

		javaMailSender.send(message);
	}
}
