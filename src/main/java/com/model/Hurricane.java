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

    public Hurricane getHurricane() {
        return this;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public String getEyeOfStormZip() {
        return eyeOfStormZip;
    }

    public HurricaneStatus getStatus() {
        return status;
    }

    public ArrayList<String> getAffectedZipCodes() {
        return affectedZipCodes;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public String toString() {
        return "Hurricane " + name + " (Category " + category + ", " + status + ")";
    }
}
