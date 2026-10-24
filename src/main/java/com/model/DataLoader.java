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
            System.out.println(user.getEmail());
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
            JSONArray userArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < userArray.size(); i++) {
                JSONObject userJSON = (JSONObject)userArray.get(i);
                UUID uuid = UUID.fromString((String)userJSON.get(USER_UUID));
                String firstName = (String)userJSON.get(USER_FIRST_NAME);
                String lastName = (String)userJSON.get(USER_LAST_NAME);
                String email = (String)userJSON.get(USER_EMAIL);
                UserType userType = UserType.valueOf(((String)userJSON.get(USER_USER_TYPE)));
                String userName = (String)userJSON.get(USER_USERNAME);
                String password = (String)userJSON.get(USER_PASSWORD);
                String zipCode = (String)userJSON.get(USER_ZIP_CODE);
                String dateOfBirth = (String)userJSON.get(USER_DATE_OF_BIRTH);
                String language = (String)userJSON.get(USER_LANGUAGE);
                String address = (String)userJSON.get(USER_ADDRESS);
                String city = (String)userJSON.get(USER_CITY);
                String state = (String)userJSON.get(USER_STATE);
                boolean isAdmin = Boolean.parseBoolean((String)userJSON.get(USER_IS_ADMIN));
                if (userType == UserType.VOLUNTEER) {
                    boolean firstResponder = Boolean.parseBoolean((String)userJSON.get(VOLUNTEER_FIRST_RESPONDER));
                    boolean canSwim = Boolean.parseBoolean((String)userJSON.get(VOLUNTEER_CAN_SWIM));
                    boolean isShelterOwner = Boolean.parseBoolean((String)userJSON.get(VOLUNTEER_IS_SHELTER_OWNER));
                    Skills skills = Skills.valueOf(((String)userJSON.get(VOLUNTEER_SKILLS)));
                    users.add(new Volunteer(uuid, firstName, lastName, userName, password, email, dateOfBirth, address, city, state, zipCode, language, isAdmin, userType, skills, firstResponder, canSwim, isShelterOwner));
                }
                else {
                    users.add(new User(uuid, firstName, lastName, userName, password, email, dateOfBirth, address, city, state, zipCode, language, isAdmin, userType));
                }
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
                ShelterType shelterType = ShelterType.valueOf(((String)shelterJSON.get(SHELTER_SHELTER_TYPE)));
                ShelterStatus shelterStatus = ShelterStatus.valueOf(((String)shelterJSON.get(SHELTER_SHELTER_STATUS)));
                JSONArray shelterResources = (JSONArray)shelterJSON.get(SHELTER_SHELTER_RESOURCES);
                ArrayList<ShelterResources> shelterResourcesArray = new ArrayList<ShelterResources>();
                for (int j = 0; j < shelterResources.size(); ++j) {
                    String shelterResourceStr = (String)shelterResources.get(j);
                    ShelterResources shelterResource = ShelterResources.valueOf(shelterResourceStr); // fix
                    shelterResourcesArray.add(shelterResource);
                } 
                String visualDescription = (String)shelterJSON.get(SHELTER_VISUAL_DESCRIPTION);
                shelters.add(new Shelter(id, shelterType, address, zipCode, shelterResourcesArray, shelterStatus, visualDescription));
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
                UUID uuid = UUID.fromString((String)reliefRequestJSON.get(RELIEF_REQUEST_UUID));
                UUID userUUID = UUID.fromString((String)reliefRequestJSON.get(RELIEF_REQUEST_USER_UUID));
                String address = (String)reliefRequestJSON.get(RELIEF_REQUEST_ADDRESS);
                String city = (String)reliefRequestJSON.get(RELIEF_REQUEST_CITY);
                String state = (String)reliefRequestJSON.get(RELIEF_REQUEST_STATE);
                String zipCode = (String)reliefRequestJSON.get(RELIEF_REQUEST_ZIP_CODE);
                JSONArray partyMembers = (JSONArray)reliefRequestJSON.get(RELIEF_REQUEST_PARTY_MEMBERS);
                ArrayList<PartyMember> partyMemberArr = convertPartyMembers(partyMembers);
                boolean isIndoors = Boolean.parseBoolean((String)reliefRequestJSON.get(RELIEF_REQUEST_IS_INDOORS));
                boolean isMobile = Boolean.parseBoolean((String)reliefRequestJSON.get(RELIEF_REQUEST_IS_MOBILE));
                boolean needsMedicalAssistance = Boolean.parseBoolean((String)reliefRequestJSON.get(RELIEF_REQUEST_NEEDS_MEDICAL_ASSISTANCE));
                ReliefRequestStatus status = ReliefRequestStatus.valueOf(((String)reliefRequestJSON.get(RELIEF_REQUEST_STATUS)).toUpperCase());
                int estimatedArrivalMinutes = Integer.parseInt((String)reliefRequestJSON.get(RELIEF_REQUEST_ESTIMATED_ARRIVAL_MINUTES));
                boolean stayingHome = Boolean.parseBoolean((String)reliefRequestJSON.get(RELIEF_REQUEST_STAYING_HOME));
                boolean hasPet = Boolean.parseBoolean((String)reliefRequestJSON.get(RELIEF_REQUEST_HAS_PET));
                Skills skillsNeed = Skills.valueOf((String)reliefRequestJSON.get(RELIEF_REQUEST_SKILLS_NEED));
                reliefRequests.add(new ReliefRequest(userUUID, uuid, address, city, state, zipCode, partyMemberArr, isIndoors, isMobile, needsMedicalAssistance, status, estimatedArrivalMinutes, stayingHome, hasPet, skillsNeed));
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

    private static ArrayList<PartyMember> convertPartyMembers(JSONArray partyMembers) {
        ArrayList<PartyMember> partyMemberArr = new ArrayList<>();
        for( int i = 0; i < partyMembers.size(); ++i ) {
            JSONObject partyMemberJSON = (JSONObject)partyMembers.get(i);
            String name = (String)(partyMemberJSON.get(PARTY_MEMBER_NAME));
            String yearBorn = (String)(partyMemberJSON.get(PARTY_MEMBER_YEAR_BORN));
            boolean needsMedicalAssistance = Boolean.parseBoolean((String)(partyMemberJSON.get(PARTY_MEMBER_NEEDS_MEDICAL_ASSISTANCE)));
            Being type = Being.valueOf((String)partyMemberJSON.get(PARTY_MEMBER_TYPE));
            partyMemberArr.add(new PartyMember(name, yearBorn, needsMedicalAssistance, type));
        }
        return partyMemberArr;
    }
}
