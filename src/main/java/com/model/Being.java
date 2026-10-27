package com.model;

/**
 * Being enum specifies the type of being a user is
 * @author Fantastic Four
 */
public enum Being {
    CAT("CAT"), DOG("DOG"), PERSON("PERSON");

    public String str;  

    /**
    * Being constructor sets the string to the enum value
    * @param str string related to the enum value
    */
    private Being(String str) {
        this.str = str;
    }
}
