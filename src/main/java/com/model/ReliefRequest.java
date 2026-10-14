package com.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * ReliefRequest class creates relief requests
 * @author Fantastic Four
 */
public class ReliefRequest {
    private User user;
    private UUID uuid;
    private String address;
    private ArrayList<PartyMember> partyMembers;
    private boolean isIndoors;
    private boolean isMobile;
    private boolean isInjured;
    private ReliefRequestStatus status;
    private String priority;
    private int estimatedArrivalMinutes;
    private boolean stayingHome;

    public ReliefRequest(User user, UUID uuid, String address, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        this.user = user;
        this.uuid = uuid;
        this.address = address;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.isInjured = isInjured;
        this.status = status;
        this.priority = String.valueOf(priority);
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }

    // size of party, youngest in party, oldest in party
    public ReliefRequest(User user, String address, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        this.user = user;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.isInjured = isInjured;
        this.status = status;
        this.priority = String.valueOf(priority);
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }
    
    /**
     * ReliefRequest constructor creates relief request
     * @param address user address
     */
    public ReliefRequest(User user, String address) {
        this.user = user;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.partyMembers = new ArrayList<PartyMember>();
        this.isIndoors = false;
        this.isMobile = false;
        this.isInjured = false;
        this.status = ReliefRequestStatus.IN_PROGRESS;
        this.priority = "Not Assigned";
        this.estimatedArrivalMinutes = 0;
        this.stayingHome = false;
    }

    /**
     * calculatePriority calculates the priority of each request based on specific factors
     * @return priority value
     */
    public String calculatePriority() {
        // Implement logic to calculate priority based on specific factors
        return "Not Assigned";
    }

    /**
     * addPartyMember adds a user to the relief request
     * @param user account user
     * @return boolean based on if party member was correctly added
     */
    public boolean addPartyMember(PartyMember partyMember) {
        // Implement logic to add a party member
        return false;
    }

    /**
     * removePartyMember removes a user from the relief request
     * @param user account user
     * @return boolean based on if party member was correctly removed
     */
    public boolean removePartyMember(PartyMember partyMember) {
        // Implement logic to remove a party member
        return false;
    }

    /**
     * requestSupplies requests supplies in the relief request
     * @param user account user
     * @return requested supplies
     */
    public HashMap<String, Integer> requestSupplies(PartyMember partyMember) {
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

    public User getUser() {
        return this.user;
    }

    /**
     * getUUID returns an id
     * @return user uuid
     */
    public UUID getUuid() {
        return this.uuid;
    }

    public String getAddress() {
        return this.address;
    }

    public ArrayList<PartyMember> getPartyMembers() {
        return this.partyMembers;
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
    public String getPriority() {
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
        return uuid.toString();
    }
}