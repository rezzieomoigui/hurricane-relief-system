package com.model;
import java.util.UUID;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ReliefRequest class creates relief requests
 * @author Fantastic Four
 */
public class ReliefRequest {
    private UUID uuid;
    private ArrayList<UUID> partyMembers;
    private boolean isIndoors;
    private boolean isMobile;
    private boolean isInjured;
    private ReliefRequestStatus status;
    private int priority;
    private int estimatedArrivalMinutes;
    private boolean stayingHome;

    public ReliefRequest(UUID uuid, ArrayList<UUID> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
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

    public ReliefRequest(ArrayList<UUID> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        this.uuid = UUID.randomUUID();
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.isInjured = isInjured;
        this.status = status;
        this.priority = priority;
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }
    
    /**
     * ReliefRequest constructor creates relief request
     * @param address user address
     */
    public ReliefRequest(String address) {
        this.uuid = UUID.randomUUID();
        this.partyMembers = new ArrayList<UUID>();
        this.isIndoors = false;
        this.isMobile = false;
        this.isInjured = false;
        this.status = ReliefRequestStatus.IN_PROGRESS;
        this.priority = 0;
        this.estimatedArrivalMinutes = 0;
        this.stayingHome = false;
    }

    /**
     * calculatePriority calculates the priority of each request based on specific factors
     * @return priority value
     */
    public int calculatePriority() {
        // Implement priority calculation logic here
        return null;
    }

    /**
     * addPartyMember adds a user to the relief request
     * @param user account user
     * @return boolean based on if party member was correctly added
     */
    public boolean addPartyMember(User user) {
        // Implement logic to add a party member
        return null;
    }

    /**
     * removePartyMember removes a user from the relief request
     * @param user account user
     * @return boolean based on if party member was correctly removed
     */
    public boolean removePartyMember(User user) {
        // Implement logic to remove a party member
        return null;
    }

    /**
     * requestSupplies requests supplies in the relief request
     * @param user account user
     * @return requested supplies
     */
    public HashMap<Resources, Integer> requestSupplies(User user) {
        // Implement logic to request supplies
        return null;
    }

    /**
     * notifyShelter method lets the shelter know when a relief request has been made
     * @param shelter shelter
     */
    public void notifyShelter(Shelter shelter) {
        // Implement logic to notify shelter
    }

    /**
     * addComments method allows the users to make comments
     */
    public void addComments() {
        // Implement logic to add comments
    }

    /**
     * getUUID returns an id
     * @return user uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<UUID> getPartyMembers() {
        return partyMembers;
    }

    public boolean isIndoors() {
        return isIndoors;
    }

    /**
     * isMobile returns if the user is mobile
     * @return boolean based on if the user is mobile
     */
    public boolean isMobile() {
        return isMobile;
    }

    /**
     * isInjured returns if the user is injured or note
     * @return boolean based on if the user is injured or not
     */
    public boolean isInjured() {
        return isInjured;
    }

    /**
     * getStatus gets the relief request status
     * @return relief request status
     */
    public ReliefRequestStatus getStatus() {
        return status;
    }

    /**
     * getPriority gets the relief request priority
     * @return relief request priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * getEstimatedArrivalMinutes gets the estimated time of arrival for relief assistance
     * @return estimated time it will take for relief to arrive to the user
     */
    public int getEstimatedArrivalMinutes() {
        return estimatedArrivalMinutes;
    }

    /**
     * isStayingHome returns if the user is planning on staying home or not
     * @return boolean based on if the user is planning on staying home
     */
    public boolean isStayingHome() {
        return stayingHome;
    }

    /**
     * getRequestId gets the relief request id
     * @return relief request id
     */
    public String getRequestId() {
        return uuid;
    }
}