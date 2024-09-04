package com.wesleypi.timesheet.exception;

public class TimeoutUserConnectionException extends RuntimeException {
    public TimeoutUserConnectionException(String message) {
        super(message);
    }
}
