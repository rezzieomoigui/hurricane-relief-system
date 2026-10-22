package com.model;

public enum Being {
    CAT("Cat"), DOG("Dog"), PERSON("Person");

    public String str;  

    private Being(String str) {
        this.str = str;
    }
}
