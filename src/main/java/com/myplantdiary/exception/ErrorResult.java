package com.myplantdiary.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    private String status;
    private String error;
    private String message;
}
