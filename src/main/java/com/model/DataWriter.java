package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.UUID;
import java.util.HashMap;

public class DataWriter extends DataConstants {
    public static void main(String[] args) {
        savePeople();
        saveShelters();
        saveReliefRequests();
    }

    public static boolean savePeople() {
        PersonList personList = PersonList.getInstance();
        ArrayList<Person> people = personList.getPeople();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < people.size(); i++) {
            jsonUsers.add(getPersonJSON(people.get(i)));
        }

        try(FileWriter file = new FileWriter(PERSON_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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

    public static void saveHurricane() {
        
    }


    public static JSONObject getShelterJSON(Shelter shelter) {
        ArrayList<String> shelterResources = convertShelterResources(shelter.getShelterResources());
        JSONObject shelterDetails = new JSONObject();
        shelterDetails.put(SHELTER_UUID, shelter.getId().toString());
        shelterDetails.put(SHELTER_ADDRESS, shelter.getAddress());
        shelterDetails.put(SHELTER_ZIP_CODE, shelter.getZipCode());
        shelterDetails.put(SHELTER_SHELTER_TYPE, shelter.getShelterType().str);
        shelterDetails.put(SHELTER_SHELTER_STATUS, shelter.getShelterStatus().str);
        shelterDetails.put(SHELTER_SHELTER_RESOURCES, shelterResources);
        shelterDetails.put(SHELTER_VISUAL_DESCRIPTION, shelter.getVisualDescription());
        return shelterDetails;
    }

    private static ArrayList<String> convertShelterResources(ArrayList<ShelterResources> shelterResources) {
        ArrayList<String> shelterResourcesStr = new ArrayList<String>();
        for (int i = 0; i < shelterResources.size(); ++i) {
            /**ShelterResources temp = shelterResources.get(i);
            String temp1 = temp.toString();
            String temp2 = temp.str;**/

            shelterResourcesStr.add(shelterResources.get(i).name());
        }
        return shelterResourcesStr;
    }

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
        userDetails.put(USER_LANGUAGE, user.getLanguage());
        userDetails.put(USER_IS_ADMIN, Boolean.toString(user.isAdmin()));
        userDetails.put(USER_TYPE, user.getUserType());
        if (user.getUserType().equals("Volunteer")) {
            Volunteer volunteer = (Volunteer) user;
            userDetails.put(VOLUNTEER_SKILLS, volunteer.getSkills().str);
            userDetails.put(VOLUNTEER_FIRST_RESPONDER, Boolean.toString(volunteer.isFirstResponder()));
            userDetails.put(VOLUNTEER_CAN_SWIM, Boolean.toString(volunteer.canSwim()));
            userDetails.put(VOLUNTEER_IS_SHELTER_OWNER, Boolean.toString(volunteer.isShelterOwner()));
        }
        return userDetails;
    }

    public static JSONObject getReliefRequestJSON(ReliefRequest request) {
        HashMap<String, String> partyMembers = convertPartyMembers(request.getPartyMembers());
        JSONObject requestDetails = new JSONObject();
        requestDetails.put(RELIEF_REQUEST_USER_UUID, request.getUuid().toString());
        requestDetails.put(RELIEF_REQUEST_UUID, request.getUuid().toString());
        requestDetails.put(RELIEF_REQUEST_ADDRESS, request.getAddress());
        requestDetails.put(RELIEF_REQUEST_CITY, request.getCity());
        requestDetails.put(RELIEF_REQUEST_STATE, request.getState());
        requestDetails.put(RELIEF_REQUEST_ZIP_CODE, request.getZipCode());
        requestDetails.put(RELIEF_REQUEST_PARTY_MEMBERS, partyMembers);
        requestDetails.put(RELIEF_REQUEST_IS_INDOORS, Boolean.toString(request.isIndoors()));
        requestDetails.put(RELIEF_REQUEST_IS_MOBILE, Boolean.toString(request.isMobile()));
        requestDetails.put(RELIEF_REQUEST_NEEDS_MEDICAL_ASSISTANCE, Boolean.toString(request.needsMedicalAssistance()));
        requestDetails.put(RELIEF_REQUEST_STATUS, request.getStatus().str);
        requestDetails.put(RELIEF_REQUEST_ESTIMATED_ARRIVAL_MINUTES, Integer.toString(request.getEstimatedArrivalMinutes()));
        requestDetails.put(RELIEF_REQUEST_STAYING_HOME, Boolean.toString(request.isStayingHome()));
        return requestDetails;
    }

    private static HashMap<String, String> convertPartyMembers(ArrayList<PartyMember> partyMembers) {
        HashMap<String, String> partyMembersMap = new HashMap();
        for (PartyMember partyMember : partyMembers) {
            partyMembersMap.put(partyMember.getName(), partyMember.getAge().str);
        }
        return partyMembersMap;
    }


}
