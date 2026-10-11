package com.model;

import java.util.UUID;

/**
 * Volunteer class creates volunteers that extend the user class
 * @author Fantastic Four
 */
public class Volunteer extends User {
    private Skills skills;
    private boolean firstResponder;
    private boolean canSwim;
    private boolean isShelterOwner;

    /**
     * Volunteer Constructor creates a volunteer
     * @param id user id
     * @param firstName user first name
     * @param lastName user last name
     * @param username user username
     * @param password user password
     * @param email user email
     * @param dateOfBirth user birthday
     * @param address user address
     * @param zipCode user zip code
     * @param language user language
     * @param isAdmin checks if user is an administrator
     * @param skills user skills
     * @param firstResponder checks if user is a first responder
     * @param canSwim checks if user can swim
     * @param isShelterOwner checks if user is a shelter owner
     */
    public Volunteer(UUID id, String firstName, String lastName, String username, String password, String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin, Skills skills, boolean firstResponder, boolean canSwim, boolean isShelterOwner) {
        super(id, firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin);
        this.skills = skills;
        this.firstResponder = firstResponder;
        this.canSwim = canSwim;
        this.isShelterOwner = isShelterOwner;
    }

    /**
     * Volunteer constructor creates a volunteer
     * @param user user object
     * @param skills user skills
     * @param firstResponder checks if user is a first responder
     * @param canSwim checks if user can swim
     * @param isShelterOwner checks if user is a shelter owner
     */
    public Volunteer(User user, Skills skills, boolean firstResponder, boolean canSwim, boolean isShelterOwner) {
        super(user.getUuid(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getDateOfBirth(), user.getAddress(), user.getZipCode(), user.getLanguage(), user.isAdmin());
        this.skills = skills;
        this.firstResponder = firstResponder;
        this.canSwim = canSwim;
        this.isShelterOwner = isShelterOwner;
    }
    
    /**
     * registerShelter registers the shelter
     * @param isShelterOwner checks if user is shelter owner
     */
    public void registerShelter(boolean isShelterOwner) {
        if (isShelterOwner) {
            System.out.println("Shelter registration successful.");
        } else {
            System.out.println("Shelter registration failed. You must be a shelter owner to register a shelter.");
        }
    }

    /**
     * acceptRequest method accepts the relief requests
     * @param request relief request
     */
    public void acceptRequest(ReliefRequest request) { 
        System.out.println("Request accepted: " + request.getRequestId());
    }

    /**
     * denyRequest method denies the relief requests
     * @param request relief request
     */
    public void denyRequest(ReliefRequest request) {
        System.out.println("Request denied: " + request);
    }

    /**
     * markCompleteRequest method marks the relief request as complete
     * @param request relief request
     */
    public void markCompleteRequest(ReliefRequest request) { 
        System.out.println("Request marked as complete: " + request.getRequestId());
    }

    /**
     * declineMessage method responds to user with declined relief request message
     * @param request relief request
     * @return string saying the relief request was declined
     */
    public String declineMessage(ReliefRequest request) { 
        System.out.println("Request declined: " + request.getRequestId());
        return null;
    }

    /**
     * cancel method cancels the relief request
     * @param request relief request
     */
    public void cancel(ReliefRequest request) { 
        System.out.println("Request canceled: " + request.getRequestId());
    }

    /**
     * reopenRequest opens the relief request again
     * @param request relief request
     * @param isAdmin checks if the user is an administrator
     */
    public void reopenRequest(ReliefRequest request, boolean isAdmin) { 
        System.out.println("Request reopened: " + request.getRequestId());
    }

    /**
     * getSkills returns the user skills
     * @return user skills
     */
    public Skills getSkills() {
        return skills;
    }

    /**
     * isFirstResponder returns if the user is a first responder
     * @return boolean depending on if the user is a first responer
     */
    public boolean isFirstResponder() {
        return firstResponder;
    }

    /**
     * canSwim returns if the user can swim
     * @return boolean depennding on if the user can swim
     */
    public boolean canSwim() {
        return canSwim;
    }

    /**
     * isShelterOwner returns if the user is a shelter owner
     * @return boolean depending on if the user is a shelter owner
     */
    public boolean isShelterOwner() {
        return isShelterOwner;
    }

    /**
     * setSkills sets the users skills
     * @param skills user skills
     */
    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    /**
     * setFirstResponder sets the user to be a first responder or not
     * @param firstResponder checks if the user is a first responder
     */
    public void setFirstResponder(boolean firstResponder) {
        this.firstResponder = firstResponder;
    }

    /**
     * setCanSwim sets the user to be able to swim or not
     * @param canSwim checks if the user is able to swim
     */
    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    /**
     * setShelterOwner sets the user to shelter owner or not
     * @param isShelterOwner checks if user is the shelter owner
     */
    public void setShelterOwner(boolean isShelterOwner) {
        this.isShelterOwner = isShelterOwner;
    }
}