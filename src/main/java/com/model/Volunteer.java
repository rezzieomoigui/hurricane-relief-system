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
            // add implementation here
        }
    }

    public void acceptRequest(ReliefRequest request) {
        // add implementation here
    }

    public void denyRequest(ReliefRequest request) {
        System.out.println("Request denied: " + request.getRequestId());
    }

    public void markCompleteRequest(ReliefRequest request) {
        // add implementation here
    }

    public String declineMessage(ReliefRequest request) {
        // add implementation here
        return null;
    }

    public void cancel(ReliefRequest request) {
        // add implementation here
    }

    public void reopenRequest(ReliefRequest request, boolean isAdmin) {
        // add implementation here
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