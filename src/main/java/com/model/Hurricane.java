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
    private double windSpeed;
    private double movementSpeed;

    /**
     * Hurricane constructor creates a hurricane object with a known id (used when loading from JSON)
     * @param name hurricane name
     * @param id hurricane id
     * @param eyeOfStormZip zip code the eye of the storm is currently over
     * @param category hurricane category
     * @param status hurricane status
     * @param affectedZipCodes zip codes affected by the hurricane
     * @param windSpeed wind speed
     * @param movementSpeed movement speed
     */
    public Hurricane(String name, UUID id, String eyeOfStormZip, int category, HurricaneStatus status, ArrayList<String> affectedZipCodes, double windSpeed, double movementSpeed) {
        this.name = name;
        this.id = id;
        this.eyeOfStormZip = eyeOfStormZip;
        this.category = category;
        this.status = status;
        this.affectedZipCodes = (affectedZipCodes != null) ? new ArrayList<String>(affectedZipCodes) : new ArrayList<String>();
        this.windSpeed = windSpeed;
        this.movementSpeed = movementSpeed;
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
     * updateStatus updates the hurricane status
     * @param status new hurricane status
     */
    public void updateStatus(HurricaneStatus status) {
        this.status = status;
    }

    /**
     * affects checks if a zip code is in the area affected by the hurricane
     * @param zipCode zip code to check
     * @return true if the zip code is affected
     */
    public boolean affects(String zipCode) {
        return zipCode != null && affectedZipCodes.contains(zipCode);
    }

    /**
     * displayAffectedZipCodes displays the zip codes that are affected by the hurricane
     */
    public void displayAffectedZipCodes() {
        for (String zipCode : affectedZipCodes) {
            System.out.println(zipCode);
        }
    }

    /**
     * getHurricane gets the hurricane
     * @return Hurricane
     */
    public Hurricane getHurricane() {
        return this;
    }

    /**
     * getName gets the name of the hurricane
     * @return name of hurricane
     */
    public String getName() {
        return name;
    }

    /**
     * getID gets the id of the hurricane
     * @return UUID of hurricane
     */
    public UUID getId() {
        return id;
    }

    /**
     * getEyeOfStormZip gets the zip code of the eye of the storm
     * @return String representing the zip code of the eye of the storm
     */
    public String getEyeOfStormZip() {
        return eyeOfStormZip;
    }

    /**
     * getStatus gets the status of the hurricane
     * @return HurricaneStatus
     */
    public HurricaneStatus getStatus() {
        return status;
    }

    /**
     * getAffectedZipCodes gets the zip codes that the hurricane has affected
     * @return ArrayList of strings representing the zip codes that the hurricane has affected
     */
    public ArrayList<String> getAffectedZipCodes() {
        return affectedZipCodes;
    }

    /**
     * getWindSpeed gets the wind speed of the hurricane
     * @return double representing the hurricane's wind speed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * getMovementSpeed gets the speed represening how fast the hurricane is moving
     * @return double represening the speed at which the hurricane is moving
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * toString method prints the hurricane information
     * @return String representing organized hurricane infromation
     */
    @Override
    public String toString() {
        return "Hurricane " + name + " (Category " + category + ", " + status + ")";
    }
}
