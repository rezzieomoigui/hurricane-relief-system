package com.model;

public class ReliefRequest {
    private UUID uuid;
    private ArrayList<Users> partyMembers;
    private boolean isIndoors;
    private boolean isMobile;
    private boolean isInjured;
    private ReliefRequestStatus status;
    private int priority;
    private int estimatedArrivalMinutes;
    private boolean stayingHome;

    public ReliefRequest(UUID uuid, ArrayList<Users> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        this.uuid = uuid;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.isInjured = isInjured;
        this.status = status;
        this.priority = priority;
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }

    public ReliefRequest(String address) {
        this.uuid = UUID.randomUUID();
        this.partyMembers = new ArrayList<>();
        this.isIndoors = false;
        this.isMobile = false;
        this.isInjured = false;
        this.status = ReliefRequestStatus.PENDING;
        this.priority = 0;
        this.estimatedArrivalMinutes = 0;
        this.stayingHome = false;
    }

    public int calculatePriority() {
        // Implement priority calculation logic here
        return null;
    }

    public boolean addPartyMember(User user) {
        // Implement logic to add a party member
        return null;
    }

    public boolean removePartyMember(User user) {
        // Implement logic to remove a party member
        return null;
    }

    public HashMap<Resource, Integer> requestSupplies(User user) {
        // Implement logic to request supplies
        return null;
    }

    public void notifyShelter(Shelter shelter) {
        // Implement logic to notify shelter
    }

    public void addComments() {
        // Implement logic to add comments
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<Users> getPartyMembers() {
        return partyMembers;
    }

    public boolean isIndoors() {
        return isIndoors;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public boolean isInjured() {
        return isInjured;
    }

    public ReliefRequestStatus getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public int getEstimatedArrivalMinutes() {
        return estimatedArrivalMinutes;
    }

    public boolean isStayingHome() {
        return stayingHome;
    }
}