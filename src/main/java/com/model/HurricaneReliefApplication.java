package com.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * HurricaneReliefApplication is the facade for the system. The UI and driver talk to this class
 * instead of reaching into the user, shelter, relief request and hurricane lists directly.
 * @author Fantastic Four
 */
public class HurricaneReliefApplication {
    private UserList userList;
    private ShelterList shelterList;
    private ReliefRequestList reliefRequestList;
    private User currentUser;

    /**
     * HurricaneReliefApplication constructor loads the lists of users, shelters, relief requests and hurricanes
     */
    public HurricaneReliefApplication() {
        userList = UserList.getInstance();
        shelterList = ShelterList.getInstance();
        reliefRequestList = ReliefRequestList.getInstance();
    }


    /**
     * Logs in and remembers the user if the credentials match.
     * @param username the user's username
     * @param password the user's password
     * @return the login result
     */
     public boolean createAccount(String firstName, String lastName,
            String username, String password, String email,
            String dateOfBirth, String address, String city, String state, 
            String zipCode, String language, boolean isAdmin, UserType userType) {

        return userList.addUser(firstName, lastName, username, password,
                email, dateOfBirth, address, city, state, zipCode, language, isAdmin, userType);
    }

    public boolean login(String username, String password) {
        User user = userList.getUser(username);
        if (user == null) {
            return false;
        }
        if (user.login(username, password))
            currentUser = user;
        return user.login(username, password);
    }

    public boolean logout() {
        currentUser = null;
        return true;
    }

    public ReliefRequest createReliefRequest(User user, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
        return reliefRequestList.addReliefRequest(user, isIndoors, isMobile, needsMedicalAssistance, stayingHome, hasPet, skillsNeed);
    }

    public void addPartyMember(ReliefRequest request, String name, String yearBorn, boolean needsMedicalAssistance, Being type) {
        reliefRequestList.addPartyMember(request, name, yearBorn, needsMedicalAssistance, type);
    }

    /**
     * getCurrentUser gets the current user
     * @return current user, or null if nobody is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * getUser gets the user that matches the input username
     * @param username user username
     * @return a user with matching username as parameter
     */
    public User getUser(String username) {
        return userList.getUser(username);
    }

    /**
     * getUsers returns users
     * @return ArrayList of users
     */
    public ArrayList<User> getUsers() {
        return userList.getUsers();
    }

    /**
     * addUser method adds a user
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
     * @return boolean true if user was added and didn't already exist
     */
    public boolean addUser(String firstName, String lastName, String username, String password,
    String email, String dateOfBirth, String address, String city, String state, String zipCode, String language, boolean isAdmin, UserType userType) {
        return userList.addUser(firstName, lastName, username, password, email,
                dateOfBirth, address, city, state, zipCode, language, isAdmin, userType);
    }


    public ReliefRequest getSuggestedRequest(Volunteer volunteer) 
    throws IOException, InterruptedException {
        return reliefRequestList.getSuggestedRequest(volunteer);
    }

    /**
     * seeNearbyShelters finds the shelters in the logged in user's zip code
     * @return ArrayList of shelters in the user's zip code (empty if nobody is logged in)
     */
    public ArrayList<Shelter> seeNearbyShelters() {
        if (currentUser == null) {
            return new ArrayList<Shelter>();
        }
        return shelterList.getShelter(currentUser.getZipCode());
    }

    /**
     * getSheltersByZip finds the shelters in a zip code
     * @param zipCode zip code to search
     * @return ArrayList of shelters in that zip code
     */
    public ArrayList<Shelter> getSheltersByZip(String zipCode) {
        return shelterList.getShelter(zipCode);
    }


    public String requestsToString(Volunteer volunteer) throws IOException, InterruptedException {
        return "\nSUGGESTED REQUEST BY LOCATION AND SKILLS NEEDED: \n" + getSuggestedRequest(volunteer).toString() + "\n\nALL REQUESTS: \n" + reliefRequestList.toString();
    }


    // ------------------------------------------------------------------
    // Volunteer actions
    // ------------------------------------------------------------------


    /**
     * viewReliefRequest gets the details of a relief request.
     * Only volunteers and administrators can view any request; other users can view their own.
     * @param id relief request id
     * @return the relief request, or null if it doesn't exist or can't be viewed
     */
    public ReliefRequest viewReliefRequest(UUID id) {
        ReliefRequest request = reliefRequestList.getReliefRequestById(id);
        if (request == null || currentUser == null) {
            return null;
        }
        if (canManageRequests() || request.getUserUUID().equals(currentUser.getUuid())) {
            return request;
        }
        return null;
    }

    /**
     * acceptRequest lets the logged in volunteer accept a submitted relief request
     * @param id relief request id
     * @return message saying what happened
     */
    public String acceptRequest(UUID id) {
        if (!(currentUser instanceof Volunteer)) {
            return "Only volunteers can accept relief requests.";
        }
        ReliefRequest request = reliefRequestList.getReliefRequestById(id);
        if (request == null) {
            return "Relief request not found.";
        }
        if (request.getStatus() != ReliefRequestStatus.SUBMITTED) {
            return "This relief request has already been accepted.";
        }
        ((Volunteer)currentUser).acceptRequest(request);
        return "Relief request accepted.";
    }

    /**
     * markCompleteRequest lets the logged in volunteer mark an accepted relief request as complete
     * @param id relief request id
     * @return message saying what happened
     */
    public String markCompleteRequest(UUID id) {
        if (!(currentUser instanceof Volunteer)) {
            return "Only volunteers can complete relief requests.";
        }
        ReliefRequest request = reliefRequestList.getReliefRequestById(id);
        if (request == null) {
            return "Relief request not found.";
        }
        if (request.getStatus() != ReliefRequestStatus.IN_PROGRESS) {
            return "Only accepted relief requests can be marked complete.";
        }
        ((Volunteer)currentUser).markCompleteRequest(request);
        return "Relief request marked complete.";
    }

    private boolean canManageRequests() {
        return currentUser instanceof Volunteer || (currentUser != null && currentUser.isAdmin());
    }


    private String describeHurricane(Hurricane hurricane, String state) {
        return "Hurricane " + hurricane.getName() + " (Category " + hurricane.getCategory() + ") "
                + state + " your area. Expected landfall: "
                + hurricane.getExpectedLandfall().format(Hurricane.LANDFALL_FORMAT) + ".";
    }

    // ------------------------------------------------------------------
    // Saving
    // ------------------------------------------------------------------

    /**
     * save saves the users, shelters, relief requests and hurricanes
     * @return true if everything was saved
     */
    public boolean save() {
        boolean saved = userList.save();
        //saved = shelterList.save() && saved;
        saved = reliefRequestList.save() && saved;
        return saved;
    }
}
