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
        /*ArrayList<Shelter> shelters = getShelters();
        for (Shelter shelter : shelters) {
            System.out.println(shelter.getAddress());
        }*/
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
                //UUID id = UUID.fromString((String)shelterJSON.get());
                String address = (String)shelterJSON.get(SHELTER_ADDRESS);
                String zipCode = (String)shelterJSON.get(SHELTER_ZIP_CODE);
                ShelterType shelterType = ShelterType.valueOf((String)shelterJSON.get(SHELTER_SHELTER_TYPE));
                ShelterStatus shelterStatus = ShelterStatus.valueOf((String)shelterJSON.get(SHELTER_SHELTER_STATUS));
                ShelterResources shelterResources = ShelterResources.valueOf((String)shelterJSON.get(SHELTER_SHELTER_RESOURCES));
                String visualDescription = (String)shelterJSON.get(SHELTER_VISUAL_DESCRIPTION);
                shelters.add(new Shelter(id, shelterType, address, zipCode, shelterResources, shelterStatus, visualDescription));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

        /* 
       users.add(new User(UUID.randomUUID(), "cBrown", "Claire", "Brown", "Cr@b156"));
       users.add(new User(UUID.randomUUID(), "gCarlton", "Grace", "Carlton", "Gc@rl0ts!:)"));
       return users;
    }
    public static ArrayList<Shelter> getShelters() {
        ArrayList<Shelter> shelters = new ArrayList<Shelter>();
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.SCHOOL, "374 Lincoln St", "02111", ShelterResources.WATER, ShelterStatus.OPEN, "Large brick building, neon sign on front"));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.HOUSE, "124 Main St", "52471", ShelterResources.FOOD, ShelterStatus.NEAR_CAPACITY, "Two story blue house with a red door"));
        return shelters;
        */
}
