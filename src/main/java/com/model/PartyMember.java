package com.model;

public class PartyMember {
    private String firstName;
    private String lastName;
    private boolean isChild;
    private boolean isElderly;

    public PartyMember(String firstName, String lastName, boolean isChild, boolean isElderly) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isChild = isChild;
        this.isElderly = isElderly;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public boolean isChild() {
        return this.isChild;
    }

    public boolean isElderly() {
        return this.isElderly;
    }
    
}
