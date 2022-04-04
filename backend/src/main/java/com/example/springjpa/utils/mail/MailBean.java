package com.example.springjpa.utils.mail;

public class MailBean {

    private final String body;
    private final String subject;

    public MailBean(String body, String subject) {
        this.body = body;
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "MailBean{" +
                "body='" + body + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
