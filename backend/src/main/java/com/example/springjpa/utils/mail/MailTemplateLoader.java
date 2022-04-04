package com.example.springjpa.utils.mail;

import com.example.springjpa.service.UsuariosServiceImpl;
import com.example.springjpa.utils.logging.FsLogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class MailTemplateLoader {

    private static final Logger log = FsLogManager.getLogger(MailTemplateLoader.class);

    private static final String RESOURCE_FOLDER = "mailtemplates";
    private static final String HTML_EXTENSION = ".html";
    private static final String TXT_EXTENSION = "_SUBJECT.txt";


    public static MailBean generateBody( MailTagBuilder mailTagBuilder){
        String preSubject = loadSubject( mailTagBuilder.getHTML_FILE_ENUM());
        log.debug(" preSubject = [{}]",  preSubject);
        String transformedSubject = mailTagBuilder.replaceTags(preSubject);
        log.debug(" transformedSubject = [{}]", transformedSubject);
        String preBody = loadHtmlBody( mailTagBuilder.getHTML_FILE_ENUM());
        log.debug(" preBody = [{}]", preBody);
        String transformedBody = mailTagBuilder.replaceTags(preBody);
        log.debug(" transformedBody = [{}]", transformedBody);
        return new MailBean(transformedBody, transformedSubject);
    }

    private static String loadHtmlBody( HtmlFileEnum htmlFileEnum) {
        ClassLoader classLoader = MailTemplateLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FOLDER +
                File.separator + htmlFileEnum.name() + HTML_EXTENSION);
        String body = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        log.debug(" body found:\n{}",  body);
        return body;
    }

    private static String loadSubject(HtmlFileEnum htmlFileEnum) {
        ClassLoader classLoader = MailTemplateLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(RESOURCE_FOLDER +
                File.separator + htmlFileEnum.name() + TXT_EXTENSION);
        String body = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        log.debug("body found:\n{}",  body);
        return body;
    }

}
