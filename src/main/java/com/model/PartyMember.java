package com.model;

public class PartyMember {
    private String name;
    private boolean isChild;
    private boolean isElderly;

    public PartyMember(String name, boolean isChild, boolean isElderly) {
        this.name = name;
        this.isChild = isChild;
        this.isElderly = isElderly;
    }

    public String getName() {
        return this.name;
    }

    public boolean isChild() {
        return this.isChild;
    }

    public boolean isElderly() {
        return this.isElderly;
    }
    
}
