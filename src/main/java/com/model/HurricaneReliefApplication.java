package com.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.location.Address;
import com.location.MapTools;

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

    /**
     * login method logs in a user
     * @param username user username
     * @param password user password
     * @return boolean representing if the user was able to log in
     */
    public boolean login(String username, String password) {
        User user = userList.getUser(username);
        if (user == null) {
            return false;
        }
        if (user.login(username, password))
            currentUser = user;
        return user.login(username, password);
    }

    /**
     * logout logs a user out
     * @return boolean represenging if the user was properly logged out
     */
    public boolean logout() {
        currentUser = null;
        return true;
    }

    /**
     * createReliefRequest creates a relief requests with the following parameteres
     * @param user user
     * @param isIndoors checks if user is indoors
     * @param isMobile checks if user is mobile
     * @param needsMedicalAssistance checks if user needs medical assistance
     * @param stayingHome checks if user is is staying home
     * @param hasPet checks if user has a pet
     * @param skillsNeed skills user requires
     * @return relief request created
     */
    public ReliefRequest createReliefRequest(User user, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
        return reliefRequestList.addReliefRequest(user, isIndoors, isMobile, needsMedicalAssistance, stayingHome, hasPet, skillsNeed);
    }

    /**
     * addPartyMember adds a party member to the relief request
     * @param request relief request
     * @param name party member name
     * @param yearBorn year party member was born
     * @param needsMedicalAssistance checks if party member needs medical assistance
     * @param type type of being
     */
    public void addPartyMember(ReliefRequest request, String name, String yearBorn, boolean needsMedicalAssistance, Being type) {
        reliefRequestList.addPartyMember(request, name, yearBorn, needsMedicalAssistance, type);
    }

    /**
     * addShelterOccupants adds shelter occupants to the shelter
     * @param shelter shelter occupants are at
     * @param newOccupants new occupants being added
     */
    public void addShelterOccupants(Shelter shelter, ArrayList<PartyMember> newOccupants) {
        shelterList.addOccupants(shelter, newOccupants);
    }

    /**
     * closestMatchingShelter finds the shelter closest to the relief request address
     * @param numPeople number of people in request
     * @param reliefRequest relief request
     * @return Shelter that is closest to relief request address
     * @throws IOException
     * @throws InterruptedException
     */
    public Shelter closestMatchingShelter(int numPeople, ReliefRequest reliefRequest) throws IOException, InterruptedException {
        return ShelterList.getInstance().closestMatchingShelter(numPeople, reliefRequest);
    }

    /**
     * assignReliefRequest assigns a relief request to a specific volunteer
     * @param numPeople number of people in relief request
     * @param reliefRequest relief request
     * @param volunteer volunteer to assign to request
     * @param isAdmin checks if they are admin
     * @return boolean representing if the relief request was assigned properly
     * @throws InterruptedException
     */
    public boolean assignReliefRequest(int numPeople, ReliefRequest reliefRequest, Volunteer volunteer, boolean isAdmin) throws InterruptedException {
        // System.out.println("Made it to facade");
        return ShelterList.getInstance().assignReliefRequest(numPeople, reliefRequest, volunteer, isAdmin);
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

    /**
     * getSuggestedRequest gets the suggested request
     * @param volunteer volunteer for relief request
     * @return suggested relief request
     * @throws IOException
     * @throws InterruptedException
     */
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

    /**
     * requestsToString prints relief requests
     * @param volunteer volunteer for relief request
     * @return String representing relief requests
     * @throws IOException
     * @throws InterruptedException
     */
    public String requestsToString(Volunteer volunteer) throws IOException, InterruptedException {
        return "\nSUGGESTED REQUEST BY LOCATION AND SKILLS NEEDED: \n" + getSuggestedRequest(volunteer).toString() + "\n\nALL REQUESTS: \n" + reliefRequestList.toString();
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
     * markCompleteRequest lets the logged in admin mark an accepted relief request as complete
     * @param id relief request id
     * @param isAdmin 
     * @return Whether marking as complete was successful
     */
    public String markCompleteRequest(ReliefRequest request, boolean isAdmin) {
        return reliefRequestList.markCompleteRequest(request, isAdmin);
    }

    /**
     * getReliefRequest gets the relief request with the following parameters
     * @param address relief request address
     * @param city relief request city
     * @param state relief request state
     * @param zipCode relief request zip code
     * @param submitterFirstName first name of person who submitted request
     * @param submitterLastName last name of person who submitted request
     * @return relief request matching parameters
     */
    public ReliefRequest getReliefRequest(String address, String city, String state, String zipCode, String submitterFirstName, String submitterLastName) {
        return reliefRequestList.getReliefRequest(address, city, state, zipCode, submitterFirstName, submitterLastName);
    }

    /**
     * getShelter gets the shelter with the following parameters
     * @param address shelter address
     * @param city shelter city
     * @param state shelter state
     * @param zipCode shelter zipCode
     * @return Shelter mathching parameters
     */
    public Shelter getShelter(String address, String city, String state, String zipCode) {
        return shelterList.getShelter(address, city, state, zipCode);
    }

    /**
     * setNumberOfOccupants sets the number of occupants
     * @param shelter shelter
     * @param numberOfOccupants number of occupants at shelter
     * @param isAdmin checks if they are an administrator 
     */
    public void setNumberOfOccupants(Shelter shelter, int numberOfOccupants, boolean isAdmin) {
        shelter.setNumberOfOccupants(numberOfOccupants, isAdmin);
    }

    /**
     * write method writes a file of important information for user
     * @param request relief request
     * @param victimAddress victim address
     * @param victimCity victim city
     * @param victimState victim state
     * @param victimZipCode victim zip code
     * @param shelter shelter
     * @throws IOException
     * @throws InterruptedException
     */
    public void write(ReliefRequest request, String victimAddress, String victimCity, String victimState, String victimZipCode, Shelter shelter) throws IOException, InterruptedException {
        ExportFile export = new ExportFile();
        export.write(request, victimAddress, victimCity, victimState, victimZipCode, shelter);
    }

    /**
     * save saves the users, shelters, relief requests and hurricanes
     * @return true if everything was saved
     */
    public boolean save() {
        boolean saved = userList.save();
        saved = shelterList.save() && saved;
        saved = reliefRequestList.save() && saved;
        return saved;
    }
}
