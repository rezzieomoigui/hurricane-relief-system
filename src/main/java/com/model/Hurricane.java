package com.model;

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
    private LocalDateTime expectedLandfall;

    public Hurricane(String name, UUID id, String eyeOfStormZip, int category, HurricaneStatus status, ArrayList<String> affectedZipCodes, PreparationGuide preparationGuide, double windSpeed, double movementSpeed, LocalDateTime expectedLandfall) {
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

    public boolean addAffectedLocation(String zipCode) {
        // add implementation here
        return null;
    }

    public boolean removeAffectedLocation(String zipCode) {
        // add implementation here
        return null;
    }

    public boolean addReliefRequest(ReliefRequest request) {
        // add implementation here
        return null;
    }

    public boolean removeReliefRequest(ReliefRequest request) {
        // add implementation here
        return null;
    }

    public ArrayList<ReliefRequest> getRequestsByLocation() {
        // add implementation here
        return null;
    }

    public ArrayList<ReliefRequest> getRequestsByStatus() {
        // add implementation here
        return null;
    }

    public boolean addShelter(Shelter shelter) {
        // add implementation here
        return null;
    }

    public ArrayList<Shelter> getAvailableShelters() {
        // add implementation here
        return null;
    }

    public void updateEyeLocation(String zipCode) {
        // add implementation here
    }

    public void updateCategory(int category) {
        // add implementation here
    }

    public boolean isApproaching(String zipCode) {
        // add implementation here
        return null;
    }
    
    public boolean isLeaving(String zipCode) {
        // add implementation here
        return null;
    }

    public void displayAffectedZipCodes() {
        // add implementation here
    }
}