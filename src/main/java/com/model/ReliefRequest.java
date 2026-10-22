package com.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * ReliefRequest class creates relief requests
 * @author Fantastic Four
 */
public class ReliefRequest {
    private UUID userUUID;
    private UUID uuid;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private ArrayList<PartyMember> partyMembers;
    private boolean isIndoors;
    private boolean isMobile;
    private boolean isInjured;
    private ReliefRequestStatus status;
    private int estimatedArrivalMinutes;
    private boolean stayingHome;

    public ReliefRequest(UUID userUUID, UUID uuid, String address, String city, String state, String zipCode, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int estimatedArrivalMinutes, boolean stayingHome) {
        this.userUUID = userUUID;
        this.uuid = uuid;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.isInjured = isInjured;
        this.status = status;
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }

    // size of party, youngest in party, oldest in party
    public ReliefRequest(UUID userUUID, String address, String city, String state, String zipCode, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int estimatedArrivalMinutes, boolean stayingHome) {
        this.userUUID = userUUID;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.isInjured = isInjured;
        this.status = status;
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }
    
    /**
     * This creates a general relief request for when users have no time enter detailed information
     * @param address user address
     */
    public ReliefRequest(UUID userUUID, String address, String city, String state, String zipCode) {
        this.userUUID = userUUID;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.partyMembers = new ArrayList<PartyMember>();
        this.isIndoors = false;
        this.isMobile = false;
        this.isInjured = false;
        this.status = ReliefRequestStatus.SUBMITTED;
        this.estimatedArrivalMinutes = 0;
        this.stayingHome = false;
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
     * getUser looks up the user who wrote this relief request
     * @return the user that owns the request, or null if not found
     */
    public User getUser() {
        return UserList.getInstance().getUser(userUUID);
    }

    /**
     * getZipCode gets the zip code of the user who wrote this request
     * 
     */
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * setStatus updates the status of the relief request
     * @param status new relief request status
     */
    public void setStatus(ReliefRequestStatus status) {
        this.status = status;
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
        return this.uuid;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
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