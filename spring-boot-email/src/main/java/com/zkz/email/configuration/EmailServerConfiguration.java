package com.zkz.email.configuration;

import com.zkz.email.tools.FetchProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailServerConfiguration {

    @Autowired
    private AppProperties appProperties;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(appProperties.getMailHost());
        mailSender.setPort(appProperties.getMailPort());
        mailSender.setUsername(appProperties.getMailUsername());
        mailSender.setPassword(appProperties.getMailPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
//        Properties p = FetchProperties.fetchProperties("templates/email.html");
//        System.out.println(p.toString());

        message.setText("<html>" +
                "<body>" +
                "Here is a cat picture! <img src='cid:id101'/><body></html>" +
                "This is the test email template for your email:\n%s\n");
        return message;
    }
}
