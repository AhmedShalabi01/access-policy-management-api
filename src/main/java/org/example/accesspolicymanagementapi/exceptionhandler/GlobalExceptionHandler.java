package org.example.accesspolicymanagementapi.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.example.accesspolicymanagementapi.exceptionhandler.responsebodies.ConstraintViolationExceptionResponseBody;
import org.example.accesspolicymanagementapi.exceptionhandler.responsebodies.DuplicateKeyExceptionResponseBody;
import org.example.accesspolicymanagementapi.exceptionhandler.responsebodies.EntityNotFoundExceptionBody;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ConstraintViolationExceptionResponseBody>
    handleFieldsValidationException(ConstraintViolationException exception) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ConstraintViolationExceptionResponseBody body =
                new ConstraintViolationExceptionResponseBody(status, exception);

        return new ResponseEntity<>(body, status);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<DuplicateKeyExceptionResponseBody>
    handleFieldsDuplicateKeyException(DuplicateKeyException exception) {

        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        DuplicateKeyExceptionResponseBody body =
                new DuplicateKeyExceptionResponseBody(status,exception);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundExceptionBody>
    handleEntityNotFoundException(EntityNotFoundException exception){

        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        EntityNotFoundExceptionBody body =
                new EntityNotFoundExceptionBody(status, exception);

        return new ResponseEntity<>(body,status);
    }

}
