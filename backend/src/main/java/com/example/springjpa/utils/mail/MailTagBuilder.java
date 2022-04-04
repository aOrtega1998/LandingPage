package com.example.springjpa.utils.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MailTagBuilder {

    private static final Pattern p = Pattern.compile("%(\\w+)%");
    final Map<String, String> tags = new HashMap<>();
    private final HtmlFileEnum HTML_FILE_ENUM;

    public MailTagBuilder(HtmlFileEnum HTML_FILE_ENUM) {
        this.HTML_FILE_ENUM = HTML_FILE_ENUM;
    }

    public String replaceTags(String text) {
        Matcher m = p.matcher(text);
        boolean result = m.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            do {
                m.appendReplacement(sb, Matcher.quoteReplacement(tags.getOrDefault(m.group(0), "")));
                result = m.find();
            } while (result);
            m.appendTail(sb);
            text = sb.toString();
        }
        return text;
    }

    public HtmlFileEnum getHTML_FILE_ENUM() {
        return HTML_FILE_ENUM;
    }

}
