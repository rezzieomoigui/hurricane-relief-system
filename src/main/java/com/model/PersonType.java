package com.model;

public enum PersonType {
   VOLUNTEER("Volunteer"), 
   USER("User"), 
   PARTY_MEMBER("Party_Member");

    public String str;  

    private PersonType(String str) {
        this.str = str;
    } 
}
