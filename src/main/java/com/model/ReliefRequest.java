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
    private ArrayList<PartyMember> partyMembers;
    private boolean isIndoors;
    private boolean isMobile;
    private boolean needsMedicalAssistance;
    private ReliefRequestStatus status;
    private String priority;
    private int estimatedArrivalMinutes;
    private boolean stayingHome;

    /**
     * ReliefRequest constructor creates new relief request object
     * @param userUUID user id
     * @param uuid relief request id
     * @param address user address
     * @param partyMembers party members for relief request
     * @param isIndoors is user indoors
     * @param isMobile is user mobile
     * @param needsMedicalAssistance does user need medical assistance
     * @param status relief request status
     * @param priority priority of relief request
     * @param estimatedArrivalMinutes time before volunteer arrival
     * @param stayingHome is user staying home
     */
    public ReliefRequest(UUID userUUID, UUID uuid, String address, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        this.userUUID = userUUID;
        this.uuid = uuid;
        this.address = address;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.needsMedicalAssistance = needsMedicalAssistance;
        this.status = status;
        this.priority = String.valueOf(priority);
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }

    // size of party, youngest in party, oldest in party
    /**
     * ReliefRequest constructor creates new relief request and generates relief request uuid
     * @param userUUID user id
     * @param address user address
     * @param partyMembers party members for relief request
     * @param isIndoors is user indoors
     * @param isMobile is user mobile
     * @param needsMedicalAssistance does user need medical assitance
     * @param status relief request status
     * @param priority relief request priority
     * @param estimatedArrivalMinutes time before volunteer arrival
     * @param stayingHome is user staying home
     */
    public ReliefRequest(UUID userUUID, String address, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        this.userUUID = userUUID;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.needsMedicalAssistance = needsMedicalAssistance;
        this.status = status;
        this.priority = String.valueOf(priority);
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
    }
    
    /**
     * ReliefRequest constructor creates relief request
     * @param userUUID user id
     * @param address user address
     */
    public ReliefRequest(UUID userUUID, String address) {
        this.userUUID = userUUID;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.partyMembers = new ArrayList<PartyMember>();
        this.isIndoors = false;
        this.isMobile = false;
        this.needsMedicalAssistance = false;
        this.status = ReliefRequestStatus.SUBMITTED;
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
     * getUser looks up the user who wrote this relief request
     * @return the user that owns the request, or null if not found
     */
    public User getUser() {
        return UserList.getInstance().getUser(userUUID);
    }

    /**
     * getLocationZip gets the zip code of the user who wrote this request
     * @return zip code of the request writer, or null if the user can't be found
     */
    public String getLocationZip() {
        User user = getUser();
        return user == null ? null : user.getZipCode();
    }

    /**
     * setStatus updates the status of the relief request
     * @param status new relief request status
     */
    public void setStatus(ReliefRequestStatus status) {
        this.status = status;
    }

    /**
     * setPriority updates the priority of the relief request
     * @param priority new priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * addComments method allows the users to make comments
     */
    public void addComments() {
        // Implement logic to add comments
    }

    public UUID getUserUUID() {
        return this.userUUID;
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
     * needsMedicalAssistance returns if the user requires medical assitance or not
     * @return boolean based on if the user requires medical assistance
     */
    public boolean needsMedicalAssistance() {
        return needsMedicalAssistance;
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