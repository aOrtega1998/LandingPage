package com.example.springjpa.exception;


public class LandingPageInvalidParamException extends LandingPageException {

    public LandingPageInvalidParamException(Exception e) {
        super(e);
    }

    public LandingPageInvalidParamException(String message) {
        super(message);
    }

    public LandingPageInvalidParamException(String message, String entityName, String errorKey, String defaultMessage) {
        super(message, entityName, errorKey, defaultMessage);
    }

}
