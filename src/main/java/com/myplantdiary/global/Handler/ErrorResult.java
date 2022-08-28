package com.myplantdiary.global.Handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {
    private int statusCode;
    private HttpStatus responseMessage;
    private String error;
}
