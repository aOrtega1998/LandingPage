package com.example.springjpa.utils.mail;


public class TagEmail3 extends MailTagBuilder {

    private static final HtmlFileEnum HTML_FILE_ENUM = HtmlFileEnum.EMAIL3;

    public TagEmail3(String url){
        super(HTML_FILE_ENUM);
        tags.put(MailTags.MAIL3.getValue(), url);

    }
}
