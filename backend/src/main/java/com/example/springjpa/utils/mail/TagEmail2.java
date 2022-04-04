package com.example.springjpa.utils.mail;


public class TagEmail2 extends MailTagBuilder {

    private static final HtmlFileEnum HTML_FILE_ENUM = HtmlFileEnum.EMAIL2;

    public TagEmail2(String url){
        super(HTML_FILE_ENUM);
        tags.put(MailTags.MAIL2.getValue(), url);

    }
}
