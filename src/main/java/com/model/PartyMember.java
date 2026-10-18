package com.model;

public class PartyMember {
    private String name;
    private AgeCategory age;

    public PartyMember(String name, AgeCategory age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public AgeCategory getAge() {
        return this.age;
    }
    
}
