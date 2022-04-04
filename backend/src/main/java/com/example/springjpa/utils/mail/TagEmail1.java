package com.example.springjpa.utils.mail;


public class TagEmail1 extends MailTagBuilder {

    private static final HtmlFileEnum HTML_FILE_ENUM = HtmlFileEnum.EMAIL1;

    public TagEmail1(String url){
        super(HTML_FILE_ENUM);
        tags.put(MailTags.MAIL1.getValue(), url);

    }
}
