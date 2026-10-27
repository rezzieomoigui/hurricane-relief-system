package com.model;

import java.io.FileWriter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * DataWriter class extends data constants and writes in data to the json files
 * @author Fantastic Four
 */
public class DataWriter extends DataConstants {

    /**
     * saveUsers saves the users by writing them into the user JSON file
     * @return boolean representing if the users were properly saved to the JSON file
     */
    public static boolean saveUsers() {
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < users.size(); i++) {
            jsonUsers.add(getUserJSON(users.get(i)));
        }

        try(FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * saveShelters saves the shelters by writing them into the shelter JSON file
     * @return boolean representing if the shelters were properly saved to the JSON file
     */
    public static boolean saveShelters() {
        ShelterList shelterList = ShelterList.getInstance();
        ArrayList<Shelter> shelters = shelterList.getShelters();
        JSONArray jsonShelters = new JSONArray();

        for(int i = 0; i < shelters.size(); i++) {
            jsonShelters.add(getShelterJSON(shelters.get(i)));
        }

        try(FileWriter file = new FileWriter(SHELTER_FILE_NAME)) {
            file.write(jsonShelters.toJSONString());
            file.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * saveReliefRequests saves the relief requests by writing them into the relief request JSON file
     * @return boolean representing if the relief requests were properly saved to the JSON file
     */
    public static boolean saveReliefRequests() {
        ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
        ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();
        JSONArray jsonRequests = new JSONArray();
        for (int i = 0; i < reliefRequests.size(); ++i ) {
            jsonRequests.add(getReliefRequestJSON(reliefRequests.get(i)));
        }
        try(FileWriter file = new FileWriter(RELIEF_REQUEST_FILE_NAME)) {
            file.write(jsonRequests.toJSONString());
            file.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * getShelterJSON gets the JSON object for an individual shelter
     * @param shelter shelter being found in JSON
     * @return JSONObject for individual shelter
     */
    public static JSONObject getShelterJSON(Shelter shelter) {
        JSONObject shelterDetails = new JSONObject();
        ArrayList<String> shelterResources = convertShelterResources(shelter.getShelterResources());
        JSONArray occupantArr = new JSONArray();
        ArrayList<PartyMember> partyMembers = shelter.getOccupants();
        for (int i = 0; i < partyMembers.size(); ++i) {
            occupantArr.add(getPartyMemberJSON(partyMembers.get(i)));
        }
        shelterDetails.put(SHELTER_UUID, shelter.getId().toString());
        shelterDetails.put(SHELTER_ADDRESS, shelter.getAddress());
        shelterDetails.put(SHELTER_CITY, shelter.getCity());
        shelterDetails.put(SHELTER_STATE, shelter.getState());
        shelterDetails.put(SHELTER_ZIP_CODE, shelter.getZipCode());
        shelterDetails.put(SHELTER_SHELTER_TYPE, shelter.getShelterType().str);
        shelterDetails.put(SHELTER_SHELTER_STATUS, shelter.getShelterStatus().str);
        shelterDetails.put(SHELTER_SHELTER_RESOURCES, shelterResources);
        shelterDetails.put(SHELTER_VISUAL_DESCRIPTION, shelter.getVisualDescription());
        shelterDetails.put(SHELTER_NUMBER_OF_OCCUPANTS, Integer.toString(shelter.getNumberOfOccupants()));
        shelterDetails.put(SHELTER_MAX_NUMBER_OF_OCCUPANTS, Integer.toString(shelter.getMaxNumberOfOccupants()));
        shelterDetails.put(SHELTER_MEDICAL_STAFF_AVAILABLE, Boolean.toString(shelter.getMedicalStaffAvailable()));
        shelterDetails.put(SHELTER_OCCUPANTS, occupantArr);
        return shelterDetails;
    }

    /**
     * convertShelterResources converts the shelter resources from an array list of type shelter resources to type String
     * @param shelterResources array list of shelter resources of type shelter resources
     * @return array list of string representing shelter resources
     */
    private static ArrayList<String> convertShelterResources(ArrayList<ShelterResources> shelterResources) {
        ArrayList<String> shelterResourcesStr = new ArrayList<String>();
        for (int i = 0; i < shelterResources.size(); ++i) {
            shelterResourcesStr.add(shelterResources.get(i).name());
        }
        return shelterResourcesStr;
    }

    /**
     * getUserJSON gets the JSON object for an individual user
     * @param user user being found in JSON
     * @return JSONObject for individual user
     */
    public static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();
        userDetails.put(USER_UUID, user.getUuid().toString());
        userDetails.put(USER_FIRST_NAME, user.getFirstName());
        userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_USERNAME, user.getUsername());
        userDetails.put(USER_PASSWORD, user.getPassword());
        userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_DATE_OF_BIRTH, user.getDateOfBirth());
        userDetails.put(USER_ADDRESS, user.getAddress());
        userDetails.put(USER_ZIP_CODE, user.getZipCode());
        userDetails.put(USER_CITY, user.getCity());
        userDetails.put(USER_STATE, user.getState());
        userDetails.put(USER_LANGUAGE, user.getLanguage());
        userDetails.put(USER_IS_ADMIN, Boolean.toString(user.isAdmin()));
        userDetails.put(USER_USER_TYPE, user.getUserType().str);
        if (user.getUserType().str.equals("VOLUNTEER")) {
            Volunteer volunteer = (Volunteer) user;
            userDetails.put(VOLUNTEER_SKILLS, volunteer.getSkills().str);
            userDetails.put(VOLUNTEER_FIRST_RESPONDER, Boolean.toString(volunteer.isFirstResponder()));
            userDetails.put(VOLUNTEER_CAN_SWIM, Boolean.toString(volunteer.canSwim()));
            userDetails.put(VOLUNTEER_IS_SHELTER_OWNER, Boolean.toString(volunteer.isShelterOwner()));
        }
        return userDetails;
    }

    /**
     * getReliefRequestJSON gets the JSON object for an individual relief request
     * @param request relief request being found in JSON
     * @return JSONObject for individual relief request
     */
    public static JSONObject getReliefRequestJSON(ReliefRequest request) {
        JSONObject requestDetails = new JSONObject();
        JSONArray partyMemberArr = new JSONArray();
        ArrayList<PartyMember> partyMembers = request.getPartyMembers();
        for (int i = 0; i < partyMembers.size(); ++i) {
            partyMemberArr.add(getPartyMemberJSON(partyMembers.get(i)));
        }
        requestDetails.put(RELIEF_REQUEST_USER_UUID, request.getUserUuid().toString());
        requestDetails.put(RELIEF_REQUEST_UUID, request.getUuid().toString());
        requestDetails.put(RELIEF_REQUEST_ADDRESS, request.getAddress());
        requestDetails.put(RELIEF_REQUEST_CITY, request.getCity());
        requestDetails.put(RELIEF_REQUEST_STATE, request.getState());
        requestDetails.put(RELIEF_REQUEST_ZIP_CODE, request.getZipCode());
        requestDetails.put(RELIEF_REQUEST_PARTY_MEMBERS, partyMemberArr);
        requestDetails.put(RELIEF_REQUEST_IS_INDOORS, Boolean.toString(request.getIsIndoors()));
        requestDetails.put(RELIEF_REQUEST_IS_MOBILE, Boolean.toString(request.getIsMobile()));
        requestDetails.put(RELIEF_REQUEST_NEEDS_MEDICAL_ASSISTANCE, Boolean.toString(request.getNeedsMedicalAssistance()));
        requestDetails.put(RELIEF_REQUEST_STATUS, request.getStatus().str);
        requestDetails.put(RELIEF_REQUEST_ESTIMATED_ARRIVAL_MINUTES, Integer.toString(request.getEstimatedArrivalMinutes()));
        requestDetails.put(RELIEF_REQUEST_STAYING_HOME, Boolean.toString(request.getIsStayingHome()));
        requestDetails.put(RELIEF_REQUEST_HAS_PET, Boolean.toString(request.getHasPet()));
        requestDetails.put(RELIEF_REQUEST_SKILLS_NEED, request.getSkillsNeed().str);
        return requestDetails;
    }

    /**
     * getPartyMemberJSON gets the JSON object for an individual party member
     * @param partyMember party member being found in JSON
     * @return JSONObject for individual party member
     */
    private static JSONObject getPartyMemberJSON(PartyMember partyMember) {
        JSONObject partyMemberDetails = new JSONObject();
        partyMemberDetails.put(PARTY_MEMBER_NAME, partyMember.getName());
        partyMemberDetails.put(PARTY_MEMBER_YEAR_BORN, partyMember.getYearBorn());
        partyMemberDetails.put(PARTY_MEMBER_NEEDS_MEDICAL_ASSISTANCE, Boolean.toString(partyMember.needsMedicalAssistance()));
        partyMemberDetails.put(PARTY_MEMBER_TYPE, partyMember.getType().str);
        return partyMemberDetails;
    }

}
