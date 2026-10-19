package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Hurricane class creates a hurricane
 * @author Fantastic Four
 */
public class Hurricane {
    private String name;
    private UUID id;
    private String eyeOfStormZip;
    private int category;
    private HurricaneStatus status;
    private ArrayList<String> affectedZipCodes;
    private PreparationGuide preparationGuide;
    private double windSpeed;
    private double movementSpeed;
    private String expectedLandfall;

    /** 
     * Hurricane constructor creates a hurricane object
    */
    public Hurricane(String name, UUID id, String eyeOfStormZip, int category, HurricaneStatus status, ArrayList<String> affectedZipCodes, PreparationGuide preparationGuide, double windSpeed, double movementSpeed, String expectedLandfall) {
        this.name = name;
        this.id = id;
        this.eyeOfStormZip = eyeOfStormZip;
        this.category = category;
        this.status = status;
        this.affectedZipCodes = affectedZipCodes;
        this.preparationGuide = preparationGuide;
        this.windSpeed = windSpeed;
        this.movementSpeed = movementSpeed;
        this.expectedLandfall = expectedLandfall;
    }

    /**
     * addAffectedLocation adds locations affected by the storms
     * @param zipCode hurricane zip code
     * @return boolean for if the affected area was added
     */
    public boolean addAffectedLocation(String zipCode) { 
        if (!affectedZipCodes.contains(zipCode)) {
            affectedZipCodes.add(zipCode);
            return true;
        }
        return false;
    }

    /**
     * removeAffectedLocation removes locations that are no longer being affected by the storms
     * @param zipCode hurricane zip code
     * @return boolean for if the area was removed from affected areas
     */
    public boolean removeAffectedLocation(String zipCode) {
        return affectedZipCodes.remove(zipCode);
    }

    /**
<<<<<<< HEAD
     * addReliefRequest adds relief requests 
     * @param request relief request
     * @return boolean for if the relief request was added
     */
    public boolean addReliefRequest(ReliefRequest request) {
        for (ReliefRequest reliefReq : reliefRequests) {

        }
        if (!ReliefRequest.contains(request)) {
            ReliefRequest.add(request);
            return true;
        }
        return false;
    }

    /**
     * removeReliefRequest removes relief requests
     * @param request relief request
     * @return boolean for if the relief request was removed
     */
    public boolean removeReliefRequest(ReliefRequest request) {
        return reliefRequests.remove(request);
    }

    /**
=======
>>>>>>> branch-Abagail
     * getRequestsByLocation gets the requests based on their location
     * @return arrayList of requests based on their location
     */
    public ArrayList<ReliefRequest> getRequestsByLocation() {
        ArrayList<ReliefRequest> requestsByLocation = new ArrayList<>();
        for (ReliefRequest request : reliefRequests) {
            if (affectedZipCodes.contains(request.getLocationZip())) {
                requestsByLocation.add(request);
            }
        }
        return requestsByLocation;
    }

    /**
     * getRequestsByStatus gets requests based on their status
     * @return arrayList of requests based on their status
     */
    public ArrayList<ReliefRequest> getRequestsByStatus() {
        ArrayList<ReliefRequest> requestsByStatus = new ArrayList<>();
        for (ReliefRequest request : reliefRequests) {
            if (request.getStatus() == status) {
                requestsByStatus.add(request);
            }
        }
        return requestsByStatus;
    }


    /**
     * getAvailableShelters gets the available shelters
     * @return arrayList of available shelters
     */
    public ArrayList<Shelter> getAvailableShelters() {
        ArrayList<Shelter> availableShelters = new ArrayList<>();
        for (Shelter shelter : shelters) {
            if (shelter.isAvailable()) {
                availableShelters.add(shelter);
            }
        }
        return availableShelters;
    }

    /**
     * updateEyeLocation updates the location of the eye of the hurricane
     * @param zipCode hurricane zip code
     */
    public void updateEyeLocation(String zipCode) {
        this.eyeOfStormZip = zipCode;
    }

    /**
     * updateCategory updates the hurricane category
     * @param category hurricane category
     */
    public void updateCategory(int category) {
        this.category = category;
    }

    /**
     * isApproaching checks if the hurricane is approaching
     * @param zipCode hurricane zip code
     * @return boolean based on if the hurricane is appraoching or not
     */
    public boolean isApproaching(String zipCode) {
        return affectedZipCodes.contains(zipCode) && !zipCode.equals(eyeOfStormZip);
    }
    
    /**
     * isLeaving checks if the hurricane is leaving
     * @param zipCode hurricane zip code
     * @return boolean based on if the hurricane is leaving or not
     */
    public boolean isLeaving(String zipCode) {
        return !affectedZipCodes.contains(zipCode) && !zipCode.equals(eyeOfStormZip);
    }

    /**
     * displayAffectedZipCodes displays the zip codes that are affected by the hurricane
     */
    public void displayAffectedZipCodes() {
        for (String zipCode : affectedZipCodes) {
            System.out.println(zipCode);
        }
    }
}