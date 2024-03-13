package org.example.accesspolicymanagementapi.exceptionhandler.responsebodies;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityNotFoundExceptionBody {
    @JsonProperty("timestamp")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private String errorMessages;

    public EntityNotFoundExceptionBody(HttpStatusCode status, EntityNotFoundException exception) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessages = exception.getMessage();
    }
}
