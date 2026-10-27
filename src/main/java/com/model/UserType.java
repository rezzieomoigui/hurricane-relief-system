package com.model;

/**
 * UserType enum specifies the user type
 * @author Fantastic Four
 */
public enum UserType {
   VOLUNTEER("VOLUNTEER"),
   USER("USER");

    public String str;  

    /**
     * UserType constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private UserType(String str) {
        this.str = str;
    } 
}
