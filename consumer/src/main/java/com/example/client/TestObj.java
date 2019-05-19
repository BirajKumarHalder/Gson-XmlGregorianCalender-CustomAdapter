package com.example.client;

import javax.xml.datatype.XMLGregorianCalendar;

public class TestObj {
    private XMLGregorianCalendar date;
    private String message;

    public XMLGregorianCalendar getDate() {
        return date;
    }

    public void setDate(XMLGregorianCalendar date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}