package com.myplantdiary.global.Handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String status;
    private HttpStatus message;
    private String error;
}
