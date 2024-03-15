package org.example.accesspolicymanagementapi.exceptionhandler.responsebodies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationExceptionBody {

    @JsonProperty("timestamp")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private String errorMessage;


    public ValidationExceptionBody(HttpStatusCode status, ValidationException exception) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessage = exception.getMessage();
    }
}