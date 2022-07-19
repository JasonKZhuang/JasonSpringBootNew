package com.zkz.webclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params{
    String logoURL;
    String appName;
    String verifyAddress;
}