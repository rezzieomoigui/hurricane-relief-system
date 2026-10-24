package com.model;

public enum UserType {
   VOLUNTEER("VOLUNTEER"),
   USER("USER");

    public String str;  

    private UserType(String str) {
        this.str = str;
    } 
}
