package com.example.springjpa.exception;

public enum ErroresEnum {



    //Errores generales (1000 - 1099)

    NAME_NULL("1004", "null name"),
    SURNAME_NULL("1005", "null surname"),
    EMAIL_NULL("1008","null email"),
    EMAIL_ALREADY_EXISTS("1009","email already exists");


    private final String code;
    private final String value;

    ErroresEnum(String code, String value){
        this.code = code;
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }


}
