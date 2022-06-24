package com.zkz.email.service;

import com.zkz.email.configuration.AppProperties;
import com.zkz.email.dto.EmailObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Created by Jason Zhuang on 17/11/21.
 */
@Slf4j
@Service
public class AppService {

    @Autowired
    AppProperties appProperties;

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    SimpleMailMessage template;

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    /**
     * @param email
     */
    public void sendSimpleMessage(EmailObject email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(appProperties.getMailUsername());
            message.setTo(email.getReceiver());
            message.setSubject(email.getSubject());
            message.setText(email.getTextBody());
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param email
     */
    public void sendMessageWithAttachment(EmailObject email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(appProperties.getMailUsername());
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getSubject());
            helper.setText(email.getTextBody());

            FileSystemResource file = new FileSystemResource(new File(email.getPathToAttachment()));
            helper.addAttachment(file.getFilename(), file);
//            ClassPathResource myFile = new ClassPathResource(email.getAttachmentName());
//            helper.addAttachment(email.getAttachmentName(), file);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param email
     */
    public void sendHtmlMessage(EmailObject email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // Enable the multipart flag!
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom(appProperties.getMailUsername());
            helper.setTo(email.getReceiver());
            helper.setSubject(email.getSubject());

            Context context = new Context();
            context.setVariables(email.getProperties());
            String html = springTemplateEngine.process(email.getTemplate(), context);
            helper.setText(html, true);
//            log.info("Sending email: {} with html body: {}", email, html);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
