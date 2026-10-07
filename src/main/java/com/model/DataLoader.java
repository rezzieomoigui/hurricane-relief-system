package com.model;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants {
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }   
}
