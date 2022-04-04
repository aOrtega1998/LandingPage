package com.example.springjpa.utils.mail;


public class TagEmail4 extends MailTagBuilder {

    private static final HtmlFileEnum HTML_FILE_ENUM = HtmlFileEnum.EMAIL4;

    public TagEmail4(String url){
        super(HTML_FILE_ENUM);
        tags.put(MailTags.MAIL4.getValue(), url);

    }
}
