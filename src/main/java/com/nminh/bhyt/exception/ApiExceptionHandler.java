package com.nminh.bhyt.exception;

import com.nminh.bhyt.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponseDto<Object>> handlerAllError(Exception ex){
        ResponseDto<Object> response = new ResponseDto<>();
        response.setCode("E_0001");
        response.setMessage("Error Unknown");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ResponseDto<Object>> handlerNotfoundException(Exception ex){
        ResponseDto<Object> response = new ResponseDto<>();
        response.setCode("R_404");
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    ResponseEntity<ResponseDto<Object>> handlerBadRequestException(Exception ex){
        ResponseDto<Object> response = new ResponseDto<>();
        response.setCode("R_400");
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponseDto<Object>> handlerMethodArgumentNotValidException(Exception ex){
        ResponseDto<Object> response = new ResponseDto<>();
        response.setCode("R_400");
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseDto<Object>> handleBindException(BindException ex) {
        ResponseDto<Object> response = new ResponseDto<>();
        response.setCode("R_400");
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
