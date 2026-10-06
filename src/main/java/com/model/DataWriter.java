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
        shelterList.addShelter(ShelterType.HOUSE, "47 Green Forest Dr", "29203", shelterResources, ShelterStatus.CLOSED, "Brick house with flower boxes");
        saveShelters();
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
        ArrayList<String> shelterResources = new ArrayList<String>();
        for (int i = 0; i < shelter.getShelterResources().size(); ++i) {
            shelterResources.add(shelter.getShelterResources().get(i).str);
        }
        JSONObject shelterDetails = new JSONObject();
        shelterDetails.put(SHELTER_ADDRESS, shelter.getAddress());
        shelterDetails.put(SHELTER_ZIP_CODE, shelter.getZipCode());
        shelterDetails.put(SHELTER_SHELTER_TYPE, shelter.getShelterType().str);
        shelterDetails.put(SHELTER_SHELTER_STATUS, shelter.getShelterStatus().str);
        shelterDetails.put(SHELTER_SHELTER_RESOURCES, shelterResources);
        shelterDetails.put(SHELTER_VISUAL_DESCRIPTION, shelter.getVisualDescription());
        return shelterDetails;
    }
}
