package com.dxc.barry.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BookstoreAdvice {

    @ExceptionHandler(BookExistsException.class)
    public final ResponseEntity<ErrorResponse> handleBookExistsException(BookExistsException exception) {
        final ErrorResponse errorResponse = getErrorResponse(HttpStatus.CONFLICT, exception);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException exception) {
        final ErrorResponse errorResponse = getErrorResponse(HttpStatus.NOT_FOUND, exception);

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        final List<String> errorList = fieldErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), errorList);

        log.warn("Validation errors : {} , Parameters : {}", errorList, exception.getBindingResult().getTarget());

        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    private final ErrorResponse getErrorResponse(HttpStatus httpStatus, Exception exception) {
        final ErrorResponse errorResponse = new ErrorResponse(httpStatus, LocalDateTime.now(), Collections.singletonList(exception.getMessage()));

        log.warn(errorResponse.getMessage().getFirst());

        return errorResponse;
    }

}
