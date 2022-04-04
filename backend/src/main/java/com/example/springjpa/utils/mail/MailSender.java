package com.example.springjpa.utils.mail;


import javax.mail.MessagingException;
import java.util.List;

public interface MailSender {
    void send(String to, String cc, String subject, String body) throws MessagingException;

    void send(String to, String[] cc, String subject, String body) throws MessagingException;

    void sendMassEmail(List<String> listTo, String cc, String subject, String body) throws MessagingException;
}
