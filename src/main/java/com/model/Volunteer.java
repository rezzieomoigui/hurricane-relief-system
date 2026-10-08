package com.model;

public class Volunteer extends User {
    private Skills skills;
    private boolean firstResponder;
    private boolean canSwim;
    private boolean isShelterOwner;

    public Volunteer(UUID id, String firstName, String lastName, String username, String password, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin, Skills skills, boolean firstResponder, boolean canSwim, boolean isShelterOwner) {
        super(id, firstName, lastName, username, password, dateOfBirth, address, zipCode, language, isAdmin);
        this.skills = skills;
        this.firstResponder = firstResponder;
        this.canSwim = canSwim;
        this.isShelterOwner = isShelterOwner;
    }

    public Volunteer(User user, Skills skills, boolean firstResponder, boolean canSwim, boolean isShelterOwner) {
        super(user.getUuid(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getDateOfBirth(), user.getAddress(), user.getZipCode(), user.getLanguage(), user.isAdmin());
        this.skills = skills;
        this.firstResponder = firstResponder;
        this.canSwim = canSwim;
        this.isShelterOwner = isShelterOwner;
    }
    
    public void registerShelter(boolean isShelterOwner) {
        if (isShelterOwner) {
            System.out.println("Shelter registration successful.");
        } else {
            System.out.println("Shelter registration failed. You must be a shelter owner to register a shelter.");
        }
    }

    public void acceptRequest(ReliefRequest request) { 
        System.out.println("Request accepted: " + request.getRequestId());
    }

    public void denyRequest(ReliefRequest request) {
        System.out.println("Request denied: " + request);
    }

    public void markCompleteRequest(ReliefRequest request) { 
        System.out.println("Request marked as complete: " + request.getRequestId());
    }

    public String declineMessage(ReliefRequest request) { 
        System.out.println("Request declined: " + request.getRequestId());
        return null;
    }

    public void cancel(ReliefRequest request) { 
        System.out.println("Request canceled: " + request.getRequestId());
    }

    public void reopenRequest(ReliefRequest request, boolean isAdmin) { 
        System.out.println("Request reopened: " + request.getRequestId());
    }

    public Skills getSkills() {
        return skills;
    }

    public boolean isFirstResponder() {
        return firstResponder;
    }

    public boolean canSwim() {
        return canSwim;
    }

    public boolean isShelterOwner() {
        return isShelterOwner;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public void setFirstResponder(boolean firstResponder) {
        this.firstResponder = firstResponder;
    }

    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    public void setShelterOwner(boolean isShelterOwner) {
        this.isShelterOwner = isShelterOwner;
    }
}