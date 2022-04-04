package com.example.springjpa.exception;


import org.springframework.http.HttpStatus;


public class LandingPageException extends Exception{

    private String entityName;
    private String errorKey;
    private String defaultMessage;
    private HttpStatus status;

    public LandingPageException(Exception e) {
        super(e);
    }

    public LandingPageException(String message){
        super(message);
    }

    public LandingPageException(String message, String entityName, String errorKey, String defaultMessage){
        super(message);
        this.defaultMessage = defaultMessage;
        this.errorKey = errorKey;
        this.entityName = entityName;
    }

    public LandingPageException(ErroresEnum invalidCode) {
        this.errorKey = invalidCode.getCode();
        this.defaultMessage = invalidCode.getValue();

    }

    public LandingPageException(HttpStatus status) {
        this.status=status;
    }


    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
