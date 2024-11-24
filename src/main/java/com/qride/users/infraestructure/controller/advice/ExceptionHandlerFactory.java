package com.qride.users.infraestructure.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.apache.velocity.exception.ResourceNotFoundException;
import com.qride.users.application.dto.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionHandlerFactory {

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<BaseResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return BaseResponse.builder()
                .message(exception.getMostSpecificCause().getLocalizedMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .status(409)
                .success(false)
                .build().apply();
    }

    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<BaseResponse> handleResponseStatusException(ResponseStatusException exception) {
        return BaseResponse.builder()
                .message(exception.getMostSpecificCause().getLocalizedMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .status(HttpStatus.NOT_FOUND.value())
                .success(false)
                .build().apply();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<BaseResponse> handleEntityNotFoundException(EntityNotFoundException exception) {
        return BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .status(404)
                .success(false)
                .build().apply();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<BaseResponse> handleResponseStatusException(ResourceNotFoundException exception) {
        return BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .status(404)
                .success(false)
                .build().apply();
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException exception) {
        return BaseResponse.builder()
                .message(exception.getLocalizedMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .status(500)
                .success(false)
                .build().apply();
    }
}
