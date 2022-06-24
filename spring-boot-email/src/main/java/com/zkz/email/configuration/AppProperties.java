package com.zkz.email.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jason Zhuang on 17/11/21.
 * https://support.microsoft.com/en-us/office/pop-imap-and-smtp-settings-for-outlook-com-d088b986-291d-42b8-9564-9c414e2aa040
 */
@Configuration
@RequiredArgsConstructor
@Data
public class AppProperties {
    // common parts
    //@Value("${spring.profiles.active}")
    //private String activeProfile;

    // business parts
    @Value("${customized.application.name}")
    private String customizedApplicationName;

    @Value("${customized.application.description}")
    private String customizedApplicationDescription;

    // system parts
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.default-encoding}")
    private String mailDefaultEncoding;

}
