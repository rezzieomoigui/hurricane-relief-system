package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Loads data from JSON files into the application.
 * @author FantasticFour
 */
public class DataLoader extends DataConstants {
     /**
      * main method created to test data loader
      */
    public static void main(String[] args) {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        ArrayList<Shelter> shelters = getShelters();
        for (Shelter shelter : shelters) {
            System.out.println(shelter.getAddress());
        }
        ArrayList<ReliefRequest> reliefRequests = getReliefRequests();
        for (ReliefRequest reliefRequest : reliefRequests) {
            System.out.println(reliefRequest.getStatus());
        }
    }
    
    /**
     * getUsers reads in data from the users json file and adds a user from the data that is read in
     * @return ArrayList of type user containing user data
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < peopleArray.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleArray.get(i);
                UUID id = UUID.fromString((String)personJSON.get(USER_UUID));
                String userName = (String)personJSON.get(USER_USERNAME);
                String firstName = (String)personJSON.get(USER_FIRST_NAME);
                String lastName = (String)personJSON.get(USER_LAST_NAME);
                String password = (String)personJSON.get(USER_PASSWORD);
                String zipCode = (String)personJSON.get(USER_ZIP_CODE);
                String email = (String)personJSON.get(USER_EMAIL);
                String dateOfBirth = (String)personJSON.get(USER_DATE_OF_BIRTH);
                String language = (String)personJSON.get(USER_LANGUAGE);
                String address = (String)personJSON.get(USER_ADDRESS);
                boolean isAdmin = (Boolean)personJSON.get(USER_IS_ADMIN);
                boolean firstResponder = (Boolean)personJSON.get(VOLUNTEER_FIRST_RESPONDER);
                boolean canSwim = (Boolean)personJSON.get(VOLUNTEER_CAN_SWIM);
                boolean isShelterOwner = (Boolean)personJSON.get(VOLUNTEER_IS_SHELTER_OWNER);
                Skills skills = Skills.valueOf(((String)personJSON.get(VOLUNTEER_SKILLS)).toUpperCase());
                String userType = (String)personJSON.get(USER_TYPE);
                if (userType.equals("VOLUNTEER")) {
                    users.add(new Volunteer(id, firstName, lastName, userName, password, email, dateOfBirth, address, zipCode, language, isAdmin, skills, firstResponder, canSwim, isShelterOwner));
                }
                users.add(new User(id, firstName, lastName, userName, password, email, dateOfBirth, address, zipCode, language, isAdmin));
            }

        return users;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getShelters reads in data from the shelter json file and adds a shelter from the data that is read in
     * @return ArrayList of type shelter containing shelter data
     */
    public static ArrayList<Shelter> getShelters() {
        ArrayList<Shelter> shelters = new ArrayList<Shelter>();
        try {
            FileReader reader = new FileReader(SHELTER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray shelterArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < shelterArray.size(); i++) {
                JSONObject shelterJSON = (JSONObject)shelterArray.get(i);
                UUID id = UUID.fromString((String)shelterJSON.get(SHELTER_UUID));
                String address = (String)shelterJSON.get(SHELTER_ADDRESS);
                String zipCode = (String)shelterJSON.get(SHELTER_ZIP_CODE);
                ShelterType shelterType = ShelterType.valueOf(((String)shelterJSON.get(SHELTER_SHELTER_TYPE)).toUpperCase());
                ShelterStatus shelterStatus = ShelterStatus.valueOf(((String)shelterJSON.get(SHELTER_SHELTER_STATUS)).toUpperCase());
                JSONArray shelterResources = (JSONArray)shelterJSON.get(SHELTER_SHELTER_RESOURCES);
                String visualDescription = (String)shelterJSON.get(SHELTER_VISUAL_DESCRIPTION);
                shelters.add(new Shelter(id, shelterType, address, zipCode, shelterResources, shelterStatus, visualDescription));
            }
        return shelters;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getReliefRequests reads in data from the reliefRequest json file and adds a relief request from the data that is read in
     * @return ArrayList of type reliefRequest containing reliefRequest data
     */
    public static ArrayList<ReliefRequest> getReliefRequests() {
        ArrayList<ReliefRequest> reliefRequests = new ArrayList<ReliefRequest>();
        try {
            FileReader reader = new FileReader(RELIEF_REQUEST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray reliefRequestArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < reliefRequestArray.size(); i++) {
                JSONObject reliefRequestJSON = (JSONObject)reliefRequestArray.get(i);
                UUID id = UUID.fromString((String)reliefRequestJSON.get(RELIEF_REQUEST_UUID));
                UUID userUUID = UUID.fromString((String)reliefRequestJSON.get(RELIEF_REQUEST_USER_UUID));
                String address = (String)reliefRequestJSON.get(RELIEF_REQUEST_ADDRESS);
                JSONArray partyMembers = (JSONArray)reliefRequestJSON.get(RELIEF_REQUEST_PARTY_MEMBERS);
                boolean isIndoors = (Boolean)reliefRequestJSON.get(RELIEF_REQUEST_IS_INDOORS);
                boolean isMobile = (Boolean)reliefRequestJSON.get(RELIEF_REQUEST_IS_MOBILE);
                boolean isInjured = (Boolean)reliefRequestJSON.get(RELIEF_REQUEST_IS_INJURED);
                ReliefRequestStatus status = ReliefRequestStatus.valueOf(((String)reliefRequestJSON.get(SHELTER_SHELTER_STATUS)).toUpperCase());
                int priority = (int)reliefRequestJSON.get(RELIEF_REQUEST_PRIORITY);
                int estimatedArrivalMinutes = (int)reliefRequestJSON.get(RELIEF_REQUEST_ESTIMATED_ARRIVAL_MINUTES);
                boolean stayingHome = (Boolean)reliefRequestJSON.get(RELIEF_REQUEST_STAYING_HOME);
                reliefRequests.add(new ReliefRequest(userUUID, id, address, partyMembers, isIndoors, isMobile, isInjured, status, priority, estimatedArrivalMinutes, stayingHome));
            }
        return reliefRequests;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * getVolunteer reads in data from the volunteer json file and adds a volunteer from the data that is read in
     * @return ArrayList of type volunteer containing volunteer data
     */
    /*
    public static ArrayList<Volunteer> getVolunteer() {
        ArrayList<Volunteer> volunteer = new ArrayList<Volunteer>();
        try {
            FileReader reader = new FileReader(VOLUNTEER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray reliefRequestArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < reliefRequestArray.size(); i++) {
                JSONObject volunteerJSON = (JSONObject)reliefRequestArray.get(i);
                UUID id = UUID.fromString((String)volunteerJSON.get(USER_UUID));
                String userName = (String)volunteerJSON.get(USER_USERNAME);
                String firstName = (String)volunteerJSON.get(USER_FIRST_NAME);
                String lastName = (String)volunteerJSON.get(USER_LAST_NAME);
                String password = (String)volunteerJSON.get(USER_PASSWORD);
                String zipCode = (String)volunteerJSON.get(USER_ZIP_CODE);
                String email = (String)volunteerJSON.get(USER_EMAIL);
                String dateOfBirth = (String)volunteerJSON.get(USER_DATE_OF_BIRTH);
                String language = (String)volunteerJSON.get(USER_LANGUAGE);
                String address = (String)volunteerJSON.get(USER_ADDRESS);
                Boolean isAdmin = (Boolean)volunteerJSON.get(USER_IS_ADMIN);
                boolean firstResponder = (Boolean)volunteerJSON.get(VOLUNTEER_FIRST_RESPONDER);
                boolean canSwim = (Boolean)volunteerJSON.get(VOLUNTEER_CAN_SWIM);
                boolean isShelterOwner = (Boolean)volunteerJSON.get(VOLUNTEER_IS_SHELTER_OWNER);
                Skills skills = Skills.valueOf(((String)volunteerJSON.get(VOLUNTEER_SKILLS)).toUpperCase());
                volunteer.add(new Volunteer(id, firstName, lastName, userName, password, email, dateOfBirth, address, zipCode, language, isAdmin, skills, firstResponder, canSwim, isShelterOwner));
            }
        return volunteer;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    */

    /**
     * getHurricane reads in data from the hurricane json file and adds a hurricane from the data that is read in
     * @return ArrayList of type hurricane containing hurricane data
     */
    public static ArrayList<Hurricane> getHurricane() {
        ArrayList<Hurricane> hurricane = new ArrayList<Hurricane>();
        try {
            FileReader reader = new FileReader(HURRICANE_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray hurricaneArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < hurricaneArray.size(); i++) {
                JSONObject hurricaneJSON = (JSONObject)hurricaneArray.get(i);
                UUID id = UUID.fromString((String)hurricaneJSON.get(HURRICANE_UUID));
                String name = (String)hurricaneJSON.get(HURRICANE_NAME);
                String eyeOfStormZip = (String)hurricaneJSON.get(HURRICANE_EYE_OF_STORM);
                int category = (int)hurricaneJSON.get(HURRICANE_CATEGORY);
                HurricaneStatus status = HurricaneStatus.valueOf(((String)hurricaneJSON.get(HURRICANE_STATUS)).toUpperCase());
                JSONArray affectedZipCodes = (JSONArray)hurricaneJSON.get(HURRICANE_AFFECTED_ZIP_CODES);
                JSONObject preparationGuide = (JSONObject)hurricaneJSON.get(HURRICANE_PREPARATION_GUIDE);
                double windSpeed = (double)hurricaneJSON.get(HURRICANE_WIND_SPEED);
                double movementSpeed = (double)hurricaneJSON.get(HURRICANE_MOVEMENT_SPEED);
                String expectedLandfall = (String)hurricaneJSON.get(HURRICANE_LOCAL_DATE_TIME);
                hurricane.add(new Hurricane(name, id, eyeOfStormZip, category, status, affectedZipCodes, preparationGuide, windSpeed, movementSpeed, expectedLandfall));
            }
        return hurricane;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
