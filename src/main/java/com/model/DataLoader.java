package com.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    public static void main(String[] args) {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            System.out.println(user.getUsername());
        }
        ArrayList<Shelter> shelters = getShelters();
        for (Shelter shelter : shelters) {
            System.out.println(shelter.getAddress());
        }
    }
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        /*try {
            FileReader reader = new FileReader(USER_FILE_NAME);
            JSONParser parser = new JSONParser();
            JSONArray peopleArray = (JSONArray)new JSONParser().parse(reader);
            for (int i = 0; i < peopleArray.size(); i++) {
                JSONObject personJSON = (JSONObject)peopleArray.get(i);
                UUID id = UUID.fromString((String)personJSON.get(USER_ID));
                String userName = (String)personJSON.get(USER_NAME);
                String firstName = (String)personJSON.get(USER_FIRST_NAME);
                String lastName = (String)personJSON.get(USER_LAST_NAME);
                users.add(new User(id, userName, firstName, lastName));
            }

        return users;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        */
       users.add(new User(UUID.randomUUID(), "cBrown", "Claire", "Brown", "Cr@b156"));
       users.add(new User(UUID.randomUUID(), "gCarlton", "Grace", "Carlton", "Gc@rl0ts!:)"));
       return users;
    }
    public static ArrayList<Shelter> getShelters() {
        ArrayList<Shelter> shelters = new ArrayList<Shelter>();
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.SCHOOL, "374 Lincoln St", "02111", ShelterResources.WATER, ShelterStatus.OPEN, "Large brick building, neon sign on front"));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.HOUSE, "124 Main St", "52471", ShelterResources.FOOD, ShelterStatus.NEAR_CAPACITY, "Two story blue house with a red door"));
        return shelters;
    }
}
