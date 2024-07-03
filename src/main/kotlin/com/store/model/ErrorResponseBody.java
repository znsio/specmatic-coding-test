package com.store.model;

import java.time.LocalDateTime;

public class ErrorResponseBody {
    public final LocalDateTime timestamp;
    public final int status;
    public final String error;
    public final String path;

    public ErrorResponseBody(LocalDateTime timestamp, int status, String error, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }
}
