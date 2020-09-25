package se.lexicon.mattias.library.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ExceptionResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private LocalDateTime timeStamp;

    private final Integer status;
    private final String error;
    private String message;
    private String details;

    public ExceptionResponse(LocalDateTime timeStamp, Integer status, String error, String message, String details) {
        super();
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
