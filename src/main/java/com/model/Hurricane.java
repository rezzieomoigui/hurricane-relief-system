package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Hurricane class creates a hurricane
 * @author Fantastic Four
 */
public class Hurricane {
    /** format of the expected landfall text in the json file (the hour may be one or two digits) */
    public static final DateTimeFormatter LANDFALL_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");

    private String name;
    private UUID id;
    private String eyeOfStormZip;
    private int category;
    private HurricaneStatus status;
    private ArrayList<String> affectedZipCodes;
    private PreparationGuide preparationGuide;
    private double windSpeed;
    private double movementSpeed;
    private LocalDateTime expectedLandfall;
    private ArrayList<ReliefRequest> reliefRequests;
    private ArrayList<Shelter> shelters;

    /**
     * Hurricane constructor creates a hurricane object with a known id (used when loading from JSON)
     * @param name hurricane name
     * @param id hurricane id
     * @param eyeOfStormZip zip code the eye of the storm is currently over
     * @param category hurricane category
     * @param status hurricane status
     * @param affectedZipCodes zip codes affected by the hurricane
     * @param preparationGuide guide to help people prepare
     * @param windSpeed wind speed
     * @param movementSpeed movement speed
     * @param expectedLandfall expected date and time of landfall
     */
    public Hurricane(String name, UUID id, String eyeOfStormZip, int category, HurricaneStatus status, ArrayList<String> affectedZipCodes, PreparationGuide preparationGuide, double windSpeed, double movementSpeed, LocalDateTime expectedLandfall) {
        this.name = name;
        this.id = id;
        this.eyeOfStormZip = eyeOfStormZip;
        this.category = category;
        this.status = status;
        this.affectedZipCodes = (affectedZipCodes != null) ? new ArrayList<String>(affectedZipCodes) : new ArrayList<String>();
        this.preparationGuide = (preparationGuide != null) ? preparationGuide : new PreparationGuide();
        this.windSpeed = windSpeed;
        this.movementSpeed = movementSpeed;
        this.expectedLandfall = expectedLandfall;
        this.reliefRequests = new ArrayList<ReliefRequest>();
        this.shelters = new ArrayList<Shelter>();
    }

    /**
     * Hurricane constructor used by DataLoader: builds the preparation guide from its json object
     * and reads the landfall time from its text form (for example "2026-11-01 11:30:00")
     */
    public Hurricane(String name, UUID id, String eyeOfStormZip, int category, HurricaneStatus status, ArrayList<String> affectedZipCodes, JSONObject preparationGuide, double windSpeed, double movementSpeed, String expectedLandfall) {
        this(name, id, eyeOfStormZip, category, status, affectedZipCodes, guideFromJSON(preparationGuide), windSpeed, movementSpeed, LocalDateTime.parse(expectedLandfall, LANDFALL_FORMAT));
    }

    /**
     * guideFromJSON converts the json object into a preperation guide object
     * @param json json object that has preparation guide information
     * @return PreparationGuide object with preparation data from json object
     */
    private static PreparationGuide guideFromJSON(JSONObject json) {
        PreparationGuide guide = new PreparationGuide();
        if (json != null) {
            JSONArray tips = (JSONArray)json.get(DataConstants.PREPARATION_GUIDE_TIPS);
            if (tips != null) {
                for (Object tip : tips) {
                    guide.addTip(String.valueOf(tip));
                }
            }
            JSONArray numbers = (JSONArray)json.get(DataConstants.PREPARATION_GUIDE_PHONE_NUMBERS);
            if (numbers != null) {
                for (Object number : numbers) {
                    guide.addEmergencyPhoneNumber(String.valueOf(number));
                }
            }
        }
        return guide;
    }

    /**
     * Hurricane constructor creates a new hurricane and generates its id
     * @param name name of hurricane
     * @param eyeOfStormZip zip code representing location of eye of the hurricane
     * @param category category level of hurricane
     * @param status status of hurricane
     * @param affectedZipCodes zip codes affected by hurricane
     * @param preparationGuide tips for preparing for hurricane
     * @param windSpeed hurricane wind speed
     * @param movementSpeed hurricane movement speed
     * @param expectedLandfall amount of time until hurricane is expected to make landfall
     */
    public Hurricane(String name, String eyeOfStormZip, int category, HurricaneStatus status, ArrayList<String> affectedZipCodes, PreparationGuide preparationGuide, double windSpeed, double movementSpeed, LocalDateTime expectedLandfall) {
        this(name, UUID.randomUUID(), eyeOfStormZip, category, status, affectedZipCodes, preparationGuide, windSpeed, movementSpeed, expectedLandfall);
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
     * getRequestsByLocation gets the requests that were made from a specific zip code
     * @param zipCode zip code to look for
     * @return arrayList of requests made from that zip code
     */
    public ArrayList<ReliefRequest> getRequestsByLocation(String zipCode) {
        ArrayList<ReliefRequest> requestsByLocation = new ArrayList<>();
        for (ReliefRequest request : reliefRequests) {
            String requestZip = request.getLocationZip();
            if (requestZip != null && requestZip.equals(zipCode)) {
                requestsByLocation.add(request);
            }
        }
        return requestsByLocation;
    }

    /**
     * getRequestsByStatus gets requests based on their status
     * @param status relief request status to look for
     * @return arrayList of requests with that status
     */
    public ArrayList<ReliefRequest> getRequestsByStatus(ReliefRequestStatus status) {
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
     * isApproaching checks if the hurricane is approaching a zip code
     * @param zipCode zip code to check
     * @return true if the zip code is affected and the hurricane has not arrived yet
     */
    public boolean isApproaching(String zipCode) {
        return affects(zipCode) && status == HurricaneStatus.APPROACHING;
    }

    /**
     * isLeaving checks if the hurricane is leaving a zip code
     * @param zipCode zip code to check
     * @return true if the zip code is affected and the hurricane is moving away
     */
    public boolean isLeaving(String zipCode) {
        return affects(zipCode) && status == HurricaneStatus.LEAVING;
    }

    /**
     * displayAffectedZipCodes displays the zip codes that are affected by the hurricane
     */
    public void displayAffectedZipCodes() {
        for (String zipCode : affectedZipCodes) {
            System.out.println(zipCode);
        }
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

    public int getCategory() {
        return category;
    }

    public HurricaneStatus getStatus() {
        return status;
    }

    public ArrayList<String> getAffectedZipCodes() {
        return affectedZipCodes;
    }

    public PreparationGuide getPreparationGuide() {
        return preparationGuide;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

    public LocalDateTime getExpectedLandfall() {
        return expectedLandfall;
    }

    public ArrayList<ReliefRequest> getReliefRequests() {
        return reliefRequests;
    }

    public ArrayList<Shelter> getShelters() {
        return shelters;
    }

    @Override
    public String toString() {
        return "Hurricane " + name + " (Category " + category + ", " + status + ")";
    }
}
