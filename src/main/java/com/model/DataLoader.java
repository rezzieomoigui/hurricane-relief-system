package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Loads data from JSON files into the application.
 */
public class DataLoader extends DataConstants {
     
    public static void main(String[] args) {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        UUID id = UUID.randomUUID();
        System.out.println(id);
        ArrayList<Shelter> shelters = getShelters();
        for (Shelter shelter : shelters) {
            System.out.println(shelter.getAddress());
        }
    }
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
                Boolean isAdmin = (Boolean)personJSON.get(USER_IS_ADMIN);
                users.add(new User(id, userName, firstName, lastName, password));
            }

        return users;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
                ShelterType shelterType = ShelterType.valueOf((String)shelterJSON.get(SHELTER_SHELTER_TYPE));
                ShelterStatus shelterStatus = ShelterStatus.valueOf((String)shelterJSON.get(SHELTER_SHELTER_STATUS));
                ShelterResources shelterResources = ShelterResources.valueOf((String)shelterJSON.get(SHELTER_SHELTER_RESOURCES));
                String visualDescription = (String)shelterJSON.get(SHELTER_VISUAL_DESCRIPTION);
                shelters.add(new Shelter(id, shelterType, address, zipCode, shelterResources, shelterStatus, visualDescription));
            }
        return shelters;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<ReliefRequest> getReliefRequests() {
        ArrayList<ReliefRequest> reliefRequests = new ArrayList<ReliefRequest>();
        try {
            FileReader reader = new FileReader(RELIEF_REQUEST_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray reliefRequestArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < reliefRequestArray.size(); i++) {
                JSONObject reliefRequestJSON = (JSONObject)reliefRequestArray.get(i);
                UUID id = UUID.fromString((String)reliefRequestJSON.get(RELIEF_REQUEST_UUID));
                String description = (String)reliefRequestJSON.get(RELIEF_REQUEST_DESCRIPTION);
                String zipCode = (String)reliefRequestJSON.get(RELIEF_REQUEST_ZIP_CODE);
                ReliefType reliefType = ReliefType.valueOf((String)reliefRequestJSON.get(RELIEF_REQUEST_RELIEF_TYPE));
                ReliefStatus reliefStatus = ReliefStatus.valueOf((String)reliefRequestJSON.get(RELIEF_REQUEST_RELIEF_STATUS));
                reliefRequests.add(new ReliefRequest(id, description, zipCode, reliefType, reliefStatus));
            }
        return reliefRequests;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
