package com.spring.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Mail {

    private String to;
    private String verifyCode;

    public Mail(String to) {
        this.to = to;
    }
}
