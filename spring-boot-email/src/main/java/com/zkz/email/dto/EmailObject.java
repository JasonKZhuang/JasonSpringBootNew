package com.zkz.email.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailObject {
    private String receiver;
    private String sender;
    private String subject;
    private String textBody;
    private String attachmentName;
    private String pathToAttachment;
    private String template;
    private Map<String, Object> properties;

}
