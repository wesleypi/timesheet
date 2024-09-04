package com.wesleypi.timesheet.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Unauthorized user");
    }
}
