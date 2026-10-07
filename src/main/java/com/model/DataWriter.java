package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {
    public static void main(String[] args) {
        ShelterList shelterList = ShelterList.getInstance();
        ArrayList<ShelterResources> shelterResources = new ArrayList<ShelterResources>();
        shelterResources.add(ShelterResources.WATER);
        shelterResources.add(ShelterResources.BLANKET);
        shelterList.addShelter(ShelterType.BUSINESS, "47 Blue Ocean Dr", "29223", shelterResources, ShelterStatus.OPEN, "Pink two-story building");
        saveShelters();
        UserList userList = UserList.getInstance();
        userList.addUser("Marie", "Collins", "mcollins", "securePassword5", "mcollins@gmail.com", "07-08-1982", "42 Bluff River Rd", "29210", "English", false);
        saveUsers();
    }
    
    public static void saveUsers() {
        UserList userList = UserList.getInstance();
        ArrayList<User> users = userList.getUsers();
        JSONArray jsonUsers = new JSONArray();

        for (int i = 0; i < users.size(); i++) {
            jsonUsers.add(getUserJSON(users.get(i)));
        }

        try(FileWriter file = new FileWriter(USER_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveShelters() {
        ShelterList shelterList = ShelterList.getInstance();
        ArrayList<Shelter> shelters = shelterList.getShelters();
        JSONArray jsonShelters = new JSONArray();

        for(int i = 0; i < shelters.size(); i++) {
            jsonShelters.add(getShelterJSON(shelters.get(i)));
        }

        try(FileWriter file = new FileWriter(SHELTER_FILE_NAME)) {
            file.write(jsonShelters.toJSONString());
            file.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JSONObject getShelterJSON(Shelter shelter) {
        ArrayList<String> shelterResources = convertShelterResources(shelter.getShelterResources());
        JSONObject shelterDetails = new JSONObject();
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
            shelterResourcesStr.add(shelterResources.get(i).str);
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
        userDetails.put(USER_IS_ADMIN, user.isAdmin());
        return userDetails;
    }
}
