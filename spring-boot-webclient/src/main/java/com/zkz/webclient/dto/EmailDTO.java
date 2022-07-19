package com.zkz.webclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private Sender sender;
    private List<Receiver> to;
    private String subject;
    private int templateId;
    private Params params;
}

