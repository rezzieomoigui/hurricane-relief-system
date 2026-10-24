package com.model;

public enum UserType {
   VOLUNTEER("VOLUNTEER"), // Volunteers are also Users and People
   USER("USER"), // Users are also people
   PARTY_MEMBER("PARTY_MEMBER"); // PartyMembers are also People
// Persons are given the most specific userType possible
// (e.g., Volunteers have UserType VOLUNTEER, not PERSON or USER) 

    public String str;  

    private UserType(String str) {
        this.str = str;
    } 
}
