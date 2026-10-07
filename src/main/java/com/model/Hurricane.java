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
        if (!affectedZipCodes.contains(zipCode)) {
            affectedZipCodes.add(zipCode);
            return true;
        }
        return false;
    }

    public boolean removeAffectedLocation(String zipCode) {
        return affectedZipCodes.remove(zipCode);
    }

    public boolean addReliefRequest(ReliefRequest request) {
        if (!reliefRequests.contains(request)) {
            reliefRequests.add(request);
            return true;
        }
        return false;
    }

    public boolean removeReliefRequest(ReliefRequest request) {
        return reliefRequests.remove(request);
    }

    public ArrayList<ReliefRequest> getRequestsByLocation() {
        ArrayList<ReliefRequest> requestsByLocation = new ArrayList<>();
        for (ReliefRequest request : reliefRequests) {
            if (affectedZipCodes.contains(request.getLocationZip())) {
                requestsByLocation.add(request);
            }
        }
        return requestsByLocation;
    }

    public ArrayList<ReliefRequest> getRequestsByStatus() {
        ArrayList<ReliefRequest> requestsByStatus = new ArrayList<>();
        for (ReliefRequest request : reliefRequests) {
            if (request.getStatus() == status) {
                requestsByStatus.add(request);
            }
        }
        return requestsByStatus;
    }

    public boolean addShelter(Shelter shelter) {
        if (!shelters.contains(shelter)) {
            shelters.add(shelter);
            return true;
        }
        return false;
    }

    public ArrayList<Shelter> getAvailableShelters() {
        ArrayList<Shelter> availableShelters = new ArrayList<>();
        for (Shelter shelter : shelters) {
            if (shelter.isAvailable()) {
                availableShelters.add(shelter);
            }
        }
        return availableShelters;
    }

    public void updateEyeLocation(String zipCode) {
        this.eyeOfStormZip = zipCode;
    }

    public void updateCategory(int category) {
        this.category = category;
    }

    public boolean isApproaching(String zipCode) {
        return affectedZipCodes.contains(zipCode) && !zipCode.equals(eyeOfStormZip);
    }
    
    public boolean isLeaving(String zipCode) {
        return !affectedZipCodes.contains(zipCode) && !zipCode.equals(eyeOfStormZip);
    }

    public void displayAffectedZipCodes() {
        for (String zipCode : affectedZipCodes) {
            System.out.println(zipCode);
        }
    }
}