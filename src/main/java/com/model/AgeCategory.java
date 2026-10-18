package com.model;

public enum AgeCategory {
    CHILD("Child"), ADULT("Adult"), ELDERLY("Elderly");

    public String str;  

    private AgeCategory(String str) {
        this.str = str;
    }


}
