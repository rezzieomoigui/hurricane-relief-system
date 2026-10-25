package com.model;
import java.io.FileWriter;
import java.io.IOException;

import com.location.Address;
import com.location.MapTools;

public class ExportFile {
    public void write(ReliefRequest request, String startingAddress, String startingCity, String startingState, String startingZipCode, 
        Shelter shelter) throws IOException, InterruptedException {
        try (FileWriter writer = new FileWriter(".txt/tripInformation.txt")) {
            String victimAddress = request.getAddress();
            String victimCity = request.getCity();
            String victimState = request.getState();
            String victimZipCode = request.getZipCode();
            String endingAddress = shelter.getAddress();
            String endingCity = shelter.getCity();
            String endingState = shelter.getState();
            String endingZipCode = shelter.getZipCode();
            String str = "";
            String[] directionsToVictim = getDrivingDirections(startingAddress, startingCity, startingState, startingZipCode, victimAddress, victimCity, victimState, victimZipCode);
            String[] directionsToShelter = getDrivingDirections(victimAddress, victimCity, victimState, victimZipCode, endingAddress, endingCity, endingState, endingZipCode);
            String directionsToVictimStr = toString(directionsToVictim);
            String directionstoShelterStr = toString(directionsToShelter);
            str += "\nRELIEF REQUEST INFORMATION\n" + request.toString() + "\nDIRECTIONS TO VICTIM" + directionsToVictimStr + "\nDIRECTIONS TO SHELTER" + directionstoShelterStr + "\nSHELTER INFORMATION" + shelter.toString();
            writer.write(str);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private String toString(String[] directions) {
        String str = "\n\n";
        for (String direction : directions) {
            str += direction + "\n";
        }
        return str;

    }

    private String[] getDrivingDirections(String startingAddress, String startingCity, String startingState, String startingZip, 
        String endingAddress, String endingCity, String endingState, String endingZip) throws IOException, InterruptedException {
        MapTools mapTools = MapTools.getInstance();
        Address address1 = new Address(startingAddress, startingCity, startingState, startingZip);
        Address address2 = new Address(endingAddress, endingCity, endingState, endingZip);
        return mapTools.getDrivingDirections(address1, address2);
    }
}



