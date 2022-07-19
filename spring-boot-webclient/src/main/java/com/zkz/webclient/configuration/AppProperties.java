package com.zkz.webclient.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jason Zhuang on 17/11/21.
 */
@Configuration
@RequiredArgsConstructor
@Data
public class AppProperties {
    // common parts
//    @Value("${spring.profiles.active}")
//    private String activeProfile;

    // business parts
    @Value("${customized.application.name}")
    private String customizedApplicationName;

    @Value("${customized.application.description}")
    private String customizedApplicationDescription;
}
