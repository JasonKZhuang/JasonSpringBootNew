package com.zkz.email.tools;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Properties;

@Slf4j
public class FetchProperties {

    public static Properties fetchProperties(String resourceName){
        Properties properties = new Properties();
        String fileName = "classpath:" + resourceName;
        try {
            File file = ResourceUtils.getFile(fileName);
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return properties;
    }
}
