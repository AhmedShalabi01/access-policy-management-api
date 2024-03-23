package org.example.accesspolicymanagementapi.exceptionhandler.responsebodies;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DuplicateKeyExceptionResponseBody {

    @JsonProperty("timestamp")
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime dateTime;
    @JsonProperty("status")
    private final Integer status;
    @JsonProperty("message")
    private String errorMessages;


    public DuplicateKeyExceptionResponseBody(HttpStatusCode status, DuplicateKeyException exception) {
        this.dateTime = LocalDateTime.now();
        this.status = status.value();
        this.errorMessages = exception.getMessage();
    }

}
