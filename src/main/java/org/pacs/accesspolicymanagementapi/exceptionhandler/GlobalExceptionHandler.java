package org.pacs.accesspolicymanagementapi.exceptionhandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.pacs.accesspolicymanagementapi.exceptionhandler.responsebodies.*;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

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

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<WebClientResponseExceptionBody>
    handleWebClientResponseException(WebClientResponseException exception){

        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        WebClientResponseExceptionBody body =
                new WebClientResponseExceptionBody(exception.getResponseBodyAsString());

        return new ResponseEntity<>(body,status);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationExceptionBody>
    handleValidationException(ValidationException exception){

        HttpStatusCode status = HttpStatus.BAD_REQUEST;

        ValidationExceptionBody body =
                new ValidationExceptionBody(status,exception);

        return new ResponseEntity<>(body,status);
    }

}
