package com.myplantdiary.global.Handler;

import com.myplantdiary.global.exception.PlantException;
import com.myplantdiary.global.exception.PostException;
import com.myplantdiary.global.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
@CrossOrigin
public class ExControllerAdvice {

    @ExceptionHandler(UserException.class)
    public ErrorResult handleUserException(Exception e){
        return new ErrorResult(400, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(PostException.class)
    public ErrorResult handlePostException(Exception e){
        return new ErrorResult(400, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(PlantException.class)
    public ErrorResult handlePlantException(Exception e){
        return new ErrorResult(400, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResult handleException(Exception e){
        log.error(e.getMessage());
        return new ErrorResult(500, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResult handleHttpException(Exception e) {
        return new ErrorResult(400, HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
