package com.srnpr.zapcom.basesupport;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.srnpr.zapcom.baseclass.BaseClass;
import com.srnpr.zapcom.baseface.IBaseInstance;
import com.srnpr.zapcom.topdo.TopConst;

public class MailSupport extends BaseClass implements IBaseInstance {

	public final static MailSupport INSTANCE = new MailSupport();

	public boolean sendMail(String sReceive, String sTitle, String sContent) {
		return sendMail(sReceive, sTitle, sContent, null);
	}

	public boolean sendMail(String sReceive, String sTitle, String sContent,
			File[] files) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

		// 设定mail server
		senderImpl.setHost(bConfig("zapcom.mail_host"));
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = senderImpl.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式 为true时发送附件 可以设置html格式
		MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(mailMessage, true,
					TopConst.CONST_BASE_ENCODING);

			// 设置收件人，寄件人
			messageHelper.setTo(sReceive);
			messageHelper.setFrom(bConfig("zapcom.mail_name"));
			messageHelper.setSubject(sTitle);
			// true 表示启动HTML格式的邮件
			messageHelper.setText(sContent, true);

			if (files != null) {

				for (File file : files) {
					FileSystemResource fileSystemResource = new FileSystemResource(
							file);
					messageHelper.addAttachment(file.getName(),
							fileSystemResource);
				}

			}

		} catch (MessagingException e) {

			e.printStackTrace();
		}
		senderImpl.setUsername(bConfig("zapcom.mail_name")); // 根据自己的情况,设置username
		senderImpl.setPassword(bConfig("zapcom.mail_pass")); // 根据自己的情况,
																// 设置password
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.timeout", "25000");
		senderImpl.setJavaMailProperties(prop);
		// 发送邮件
		senderImpl.send(mailMessage);

		return true;

	}

}
