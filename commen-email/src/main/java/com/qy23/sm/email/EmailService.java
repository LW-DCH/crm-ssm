package com.qy23.sm.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName EmailService
 * @Author 刘伟
 * @Date 2020/10/22 14:12
 * @Description
 * @Version 1.0
 **/
@Component
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${mail.subject1}")
    private String subject1;
    @Value("${mail.from}")
    private String from;


    @Async
    public void sendMail(String to, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            helper.setFrom("刘伟<18752018930@163.com>");
            helper.setTo(to);
            helper.setSubject("新员工信息");
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
