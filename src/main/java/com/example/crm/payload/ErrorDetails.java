package com.example.crm.payload;

import java.util.Date;

public class ErrorDetails {

    private Date date;
    private String message;
    public ErrorDetails(String eMessage, Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
