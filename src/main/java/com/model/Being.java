package com.model;

public enum Being {
    CAT("CAT"), DOG("DOG"), PERSON("PERSON");

    public String str;  

    private Being(String str) {
        this.str = str;
    }
}
