package com.wesleypi.timesheet.models;

import lombok.Data;

@Data
public class PasswordRecovery {
    private Long id;
    private Long userId;
    private String code;
}