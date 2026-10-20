package com.model;

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
    private HurricaneList hurricaneList;
    private User currentUser;

    /**
     * HurricaneReliefApplication constructor loads the lists of users, shelters, relief requests and hurricanes
     */
    public HurricaneReliefApplication() {
        userList = UserList.getInstance();
        shelterList = ShelterList.getInstance();
        reliefRequestList = ReliefRequestList.getInstance();
        hurricaneList = HurricaneList.getInstance();
    }

    // ------------------------------------------------------------------
    // Accounts
    // ------------------------------------------------------------------

  /**
   * creates account of individual new user
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
   */
    public void createAccount(String firstName, String lastName,
            String username, String password, String email,
            String dateOfBirth, String address, String zipCode,
            String language, boolean isAdmin) {

        userList.addUser(firstName, lastName, username, password,
                email, dateOfBirth, address, zipCode, language, isAdmin);
    }

    /**
     * Logs in and remembers the user if the credentials match.
     * @param username the user's username
     * @param password the user's password
     * @return the login result
     */
    public String login(String username, String password) {
        User user = userList.getUser(username);

        if (user == null) {
            return "User not found.";
        }

        if (!user.login(username, password)) {
            return "Incorrect password.";
        }

        currentUser = user;
        return "Successfully logged in!";
    }

   /**
    * Logs user out of account if a current user exists, saving the user and relief request data
    * @return string representing if the user was logged out properly
    */
    public String logout() {
        if (currentUser == null) {
            return "No user is currently logged in.";
        }

        String result = currentUser.logout(currentUser.getUsername());
        currentUser = null;
        save();

        return result;
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
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin) {
        return userList.addUser(firstName, lastName, username, password, email,
                dateOfBirth, address, zipCode, language, isAdmin);
    }

    // ------------------------------------------------------------------
    // Shelters
    // ------------------------------------------------------------------

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

    // ------------------------------------------------------------------
    // Relief requests
    // ------------------------------------------------------------------

    /**
     * submitReliefRequest creates a relief request for the logged in user
     * @param address address where help is needed
     * @return the new relief request, or null if nobody is logged in
     */
    public ReliefRequest submitReliefRequest(String address) {
        if (currentUser == null) {
            return null;
        }
        ReliefRequest request = new ReliefRequest(currentUser.getUuid(), address);
        reliefRequestList.addReliefRequest(request);
        hurricaneList.addReliefRequest(request);
        return request;
    }

    /**
     * getMyReliefRequests gets the relief requests the logged in user has made
     * @return ArrayList of the user's relief requests (empty if nobody is logged in)
     */
    public ArrayList<ReliefRequest> getMyReliefRequests() {
        ArrayList<ReliefRequest> mine = new ArrayList<ReliefRequest>();
        if (currentUser == null) {
            return mine;
        }
        for (ReliefRequest request : reliefRequestList.getReliefRequests()) {
            if (request.getUserUUID().equals(currentUser.getUuid())) {
                mine.add(request);
            }
        }
        return mine;
    }

    /**
     * cancelReliefRequest removes one of the logged in user's own relief requests (administrators can remove any)
     * @param id relief request id
     * @return true if the request was removed
     */
    public boolean cancelReliefRequest(UUID id) {
        ReliefRequest request = reliefRequestList.getReliefRequestById(id);
        if (currentUser == null || request == null) {
            return false;
        }
        if (!request.getUserUUID().equals(currentUser.getUuid()) && !currentUser.isAdmin()) {
            return false;
        }
        hurricaneList.removeReliefRequest(request);
        return reliefRequestList.removeReliefRequest(id);
    }

    // ------------------------------------------------------------------
    // Volunteer actions
    // ------------------------------------------------------------------

    /**
     * getAvailableRequests gets the relief requests that have not been accepted yet.
     * Only volunteers and administrators can see them.
     * @return ArrayList of submitted relief requests (empty if the user isn't a volunteer or admin)
     */
    public ArrayList<ReliefRequest> getAvailableRequests() {
        ArrayList<ReliefRequest> available = new ArrayList<ReliefRequest>();
        if (!canManageRequests()) {
            return available;
        }
        for (ReliefRequest request : reliefRequestList.getReliefRequests()) {
            if (request.getStatus() == ReliefRequestStatus.SUBMITTED) {
                available.add(request);
            }
        }
        return available;
    }

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

    // ------------------------------------------------------------------
    // Hurricanes
    // ------------------------------------------------------------------

    /**
     * getHurricanes gets every hurricane the application tracks
     * @return ArrayList of hurricanes
     */
    public ArrayList<Hurricane> getHurricanes() {
        return hurricaneList.getHurricanes();
    }

    /**
     * getHurricanesAffecting gets the hurricanes that affect a zip code
     * @param zipCode zip code to check
     * @return ArrayList of hurricanes affecting that zip code
     */
    public ArrayList<Hurricane> getHurricanesAffecting(String zipCode) {
        return hurricaneList.getHurricanesAffecting(zipCode);
    }

    /**
     * hurricaneIncoming tells the logged in user about hurricanes that are approaching their zip code
     * @return message describing approaching hurricanes
     */
    public String hurricaneIncoming() {
        if (currentUser == null) {
            return "Log in to see hurricane alerts for your area.";
        }
        String zip = currentUser.getZipCode();
        StringBuilder message = new StringBuilder();
        for (Hurricane hurricane : hurricaneList.getHurricanesAffecting(zip)) {
            if (hurricane.isApproaching(zip)) {
                message.append(describe(hurricane, "is approaching")).append("\n");
            }
        }
        return message.length() == 0 ? "No hurricanes are approaching your area." : message.toString().trim();
    }

    /**
     * hurricaneLeaving tells the logged in user about hurricanes that are leaving their zip code
     * @return message describing hurricanes that are moving away
     */
    public String hurricaneLeaving() {
        if (currentUser == null) {
            return "Log in to see hurricane alerts for your area.";
        }
        String zip = currentUser.getZipCode();
        StringBuilder message = new StringBuilder();
        for (Hurricane hurricane : hurricaneList.getHurricanesAffecting(zip)) {
            if (hurricane.isLeaving(zip)) {
                message.append(describe(hurricane, "is leaving")).append("\n");
            }
        }
        return message.length() == 0 ? "No hurricanes are leaving your area." : message.toString().trim();
    }

    /**
     * getHurricaneAlerts tells the logged in user about every hurricane affecting their zip code
     * @return ArrayList of alert messages (empty if none or nobody is logged in)
     */
    public ArrayList<String> getHurricaneAlerts() {
        ArrayList<String> alerts = new ArrayList<String>();
        if (currentUser == null) {
            return alerts;
        }
        String zip = currentUser.getZipCode();
        for (Hurricane hurricane : hurricaneList.getHurricanesAffecting(zip)) {
            String state;
            if (hurricane.getStatus() == HurricaneStatus.APPROACHING) {
                state = "is approaching";
            } else if (hurricane.getStatus() == HurricaneStatus.LEAVING) {
                state = "is leaving";
            } else {
                state = "is currently affecting";
            }
            alerts.add(describe(hurricane, state));
        }
        return alerts;
    }

    /**
     * getPreparationGuide gets the preparation guide of a hurricane
     * @param hurricaneName name of the hurricane
     * @return the hurricane's preparation guide, or null if there is no hurricane with that name
     */
    public PreparationGuide getPreparationGuide(String hurricaneName) {
        Hurricane hurricane = hurricaneList.getHurricane(hurricaneName);
        return hurricane == null ? null : hurricane.getPreparationGuide();
    }

    private String describe(Hurricane hurricane, String state) {
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
        saved = shelterList.save() && saved;
        saved = reliefRequestList.save() && saved;
        saved = hurricaneList.save() && saved;
        return saved;
    }
}
