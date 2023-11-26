package com.renan.backfistdecision.domain.exception;

import com.renan.backfistdecision.domain.exception.model.ErrorDetail;
import com.renan.backfistdecision.domain.exception.model.ErrorsDetails;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleResourceNotFound(MethodArgumentNotValidException rnfe) {

        List<ErrorsDetails> list = new ArrayList<>();
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        rnfe.getAllErrors().forEach( error -> {
            list.add( new ErrorsDetails(error.getDefaultMessage(), error.getCode()));
        });

        errorDetail.setDevelopersError(list);

        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(EntityNotFoundException rnfe, HttpServletRequest request) {

        List<ErrorsDetails> list = new ArrayList<>();
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());

        list.add( new ErrorsDetails(rnfe.getMessage(), request.getRequestURI()));

        errorDetail.setDevelopersError(list);

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleResourceNotFound(DataIntegrityViolationException rnfe, HttpServletRequest request) {

        List<ErrorsDetails> list = new ArrayList<>();
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        if (rnfe.getMessage().contains("Unique index or primary key violation")) {
            if (rnfe.getMessage().contains("PUBLIC.NUMBER_TABLE")) {
                list.add( new ErrorsDetails("Numero é um campo unico, valor repitido não é permitido", rnfe.getMessage()));
            } else {
                list.add( new ErrorsDetails("CPF é um campo unico, valor repitido não é permitido", rnfe.getMessage()));
            }
        } else {
            list.add( new ErrorsDetails(rnfe.getMessage(), request.getRequestURI()));
        }


        errorDetail.setDevelopersError(list);

        return new ResponseEntity<>(errorDetail, null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
