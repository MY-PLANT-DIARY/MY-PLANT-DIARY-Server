package com.myplantdiary.global.Handler;

import com.myplantdiary.global.exception.PostException;
import com.myplantdiary.global.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ErrorResult handleUserException(Exception e){
        return new ErrorResult("400", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(PostException.class)
    public ErrorResult handlePostException(Exception e){
        return new ErrorResult("400", HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(Exception e){
        return new ErrorResult("500", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
