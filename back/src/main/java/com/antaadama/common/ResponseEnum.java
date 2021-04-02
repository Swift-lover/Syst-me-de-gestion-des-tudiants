package com.antaadama.common;

public enum ResponseEnum {
    INNER_ERROR(2001, "INNER ERROR"),
    LOGIN_SUCCESS(20000, "LOGIN SUCCESS"),
    LOGIN_FAILED(3000, "Login failed，Passwore or username fail"),
    ACCOUNT_NOT_FOUND(2002, "ACCOUNT NOT FOUND"),
    ACCOUT_NOT_LOGIN(50008, "ACCOUT NOT LOGIN"),
    INVALID_PARAM(40001, "INVALID PARAM"),
    USERNAME_EXSIT(3002, "USERNAME EXSIT"),
    REGISTE_SUCCESS(20000, "REGISTE SUCCESS"),
    UPDATE_SUCCESS(20000, "UPDATE SUCCESS"),
    UPDATE_FAILED(20001, "UPDATE FAILED"),
    GET_SUCCESS(20000, "GET SUCCESS"),
    ADD_SUCCESS(20000, "ADD SUCCESS"),
    CLAZZ_EXSIT(3002, "CLAZZ EXSIT"),
    DELETE_SUCCESS(20000, "DELETE SUCCESS"),
    DELETE_FIALED(30000, "DELETE FIALED"),
    ;
    Integer code;
    String message;

    ResponseEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}