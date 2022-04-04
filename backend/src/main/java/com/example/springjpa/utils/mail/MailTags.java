package com.example.springjpa.utils.mail;

public enum MailTags {
    MAIL1("%MAIL1%"),
    MAIL2("%MAIL2%"),
    MAIL3("%MAIL3%"),
    MAIL4("%MAIL4%");

    private final String value;

    MailTags(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
