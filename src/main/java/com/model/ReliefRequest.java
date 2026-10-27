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
    private boolean isMobile; // user is able to physically move
    private boolean needsMedicalAssistance;
    private ReliefRequestStatus status;
    private int estimatedArrivalMinutes;
    private boolean stayingHome;
    private boolean hasPet;
    private Skills skillsNeed;
    private UUID assignedVolunteerID;
    private UUID assignedShelterID;

    /**
     * ReliefRequest constructor creates an instance of a relief request object from DataLoader
     * @param userUUID user id
     * @param uuid relief request id
     * @param address user address
     * @param city city of user location
     * @param state state of user location
     * @param zipCode zip code of user location
     * @param partyMembers party members included in relief request
     * @param isIndoors checks if user is indoors
     * @param isMobile checks if user is mobile
     * @param needsMedicalAssistance checks if user needs medical assistance
     * @param status status of relief request
     * @param estimatedArrivalMinutes estimated time for volunteer to get to user
     * @param stayingHome checks if user is staying home
     * @param hasPet checks if user has a pet
     */
    public ReliefRequest(UUID userUUID, UUID uuid, String address, String city, String state, String zipCode, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, ReliefRequestStatus status, int estimatedArrivalMinutes, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
        this.userUUID = userUUID;
        this.uuid = uuid;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.partyMembers = partyMembers;
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.needsMedicalAssistance = needsMedicalAssistance;
        this.status = status;
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
        this.hasPet = hasPet;
        this.skillsNeed = skillsNeed;
        this.assignedVolunteerID = null;
        this.assignedShelterID = null;
    }

    /**
     * ReliefRequest constructor creates an instance of a relief request object and generates new uuid
     * @param userUUID user id
     * @param address user address
     * @param city city of user location
     * @param state state of user location
     * @param zipCode zip code of user location
     * @param partyMembers party members included in relief request
     * @param isIndoors checks if user is indoors
     * @param isMobile checks if user is mobile
     * @param needsMedicalAssistance checks if user needs medical assistance
     * @param status status of relief request
     * @param estimatedArrivalMinutes estimated time for volunteer to get to user
     * @param stayingHome checks if user is staying home
     * @param hasPet checks if user has a pet
     */
    public ReliefRequest(UUID userUUID, String address, String city, String state, String zipCode, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, ReliefRequestStatus status, int estimatedArrivalMinutes, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
        this.userUUID = userUUID;
        this.uuid = UUID.randomUUID();
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.partyMembers = new ArrayList<PartyMember>();
        this.isIndoors = isIndoors;
        this.isMobile = isMobile;
        this.needsMedicalAssistance = needsMedicalAssistance;
        this.status = status;
        this.estimatedArrivalMinutes = estimatedArrivalMinutes;
        this.stayingHome = stayingHome;
        this.hasPet = hasPet;
        this.skillsNeed = skillsNeed;
        this.assignedVolunteerID = null;
        this.assignedShelterID = null;
    }
    
    /**
     * This creates a general relief request for when users have no time enter detailed information
     * @param userUUID user id
     * @param address user address
     * @param city city of user location
     * @param state state of user location
     * @param zipCode zip code of user location
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
        this.needsMedicalAssistance = false;
        this.status = ReliefRequestStatus.SUBMITTED;
        this.estimatedArrivalMinutes = 0;
        this.stayingHome = false;
        this.hasPet = false;
        this.skillsNeed = Skills.NONE;
        this.assignedVolunteerID = null;
        this.assignedShelterID = null;
    }

    /**
     * getUser looks up the user who wrote this rselief request
     * @return the user that owns the request, or null if not found
     */
    public User getUser() {
        UserList userList = UserList.getInstance();
        return userList.getUser(this.userUUID);
    }

    /**
     * getZipCode gets the zip code of the user who wrote this request
     * @return zip code of user location
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
     * setPartyMembers sets the party members to the party members parameter
     * @param partyMembers party members to add
     */
    public void setPartyMembers(ArrayList<PartyMember> partyMembers) {
        this.partyMembers = partyMembers;
    }

    /**
     * getUserUUID gets the user's uuid
     * @return UUID of user
     */
    public UUID getUserUuid() {
        return this.userUUID;
    }

    /**
     * getUUID returns an id
     * @return user uuid
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * getAddress gets the user address
     * @return user address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * getCity gets the user city
     * @return city of user location
     */
    public String getCity() {
        return this.city;
    }

    /**
     * getState gets the user state
     * @return state of user location
     */
    public String getState() {
        return this.state;
    }

    /**
     * getPartyMembers gets the party members of the relief request
     * @return arrayList of other members of the party
     */
    public ArrayList<PartyMember> getPartyMembers() {
        return this.partyMembers;
    }

    /**
     * getIsIndoors gets if the user is indoors
     * @return boolean depending on if the user is indoors or not
     */
    public boolean getIsIndoors() {
        return isIndoors;
    }

    /**
     * isMobile returns if the user is mobile
     * @return boolean based on if the user is mobile
     */
    public boolean getIsMobile() {
        return isMobile;
    }

    /**
     * needsMedicalAssistance returns if the user requires medical assitance or not
     * @return boolean based on if the user requires medical assistance
     */
    public boolean getNeedsMedicalAssistance() {
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
    public boolean getIsStayingHome() {
        return stayingHome;
    }

    /**
     * getHasPet gets if the user has a pet
     * @return boolean based on if the user has a pet or not
     */
    public boolean getHasPet() {
        return hasPet;
    }

    /**
     * getSkillsNeed gets the needed skill
     * @return Skill needed by requester
     */
    public Skills getSkillsNeed() {
        return this.skillsNeed;
    }

    /**
     * getRequestId gets the relief request id
     * @return relief request id
     */
    public String getRequestId() {
        return uuid.toString();
    }

    /**
     * getAssignedVolunteerID gets the id of the assigned volunteer
     * @return id of assigned volunteer
     */
    public UUID getAssignedVolunteerID() {
        return assignedVolunteerID;
    }

    /**
     * setAssignedVolunteer sets the assigned volunteer to specific relief request
     * @param volunteer volunteer assigned to relief request
     * @return boolean representing if volunteer was properly assigned to the specific relief request
     */
    public boolean setAssignedVolunteer(Volunteer volunteer) {
        if (volunteer == null) {
            return false;
        }
        this.assignedVolunteerID = volunteer.getUuid();
        return true;
    }

    /**
     * toString method prints the relief request in a readable manner
     * @return String of organized relief request information 
     */
    @Override
    public String toString() {
        String partyMemberStr = partyMembersToString();
        return "Address: "+this.address+", "+this.city+", "+ this.state+", "+this.zipCode + "\n" +
        "Status: "+this.getStatus().str+"\nIndoors? "+this.isIndoors + "\n" + "Able to move from location? " + this.isMobile + "\n" +
        "Need medical assistance? " + this.needsMedicalAssistance + "\n" + "Staying home? " + this.stayingHome + "\n" + "Have a pet? " + this.hasPet + partyMemberStr;
    }

    /**
     * partyMembersToString method prints the relief request party members
     * @return String of organized party members
     */
    private String partyMembersToString() {
        String str = "\nPARTY MEMBERS\n";
        int age;
        for ( PartyMember partyMember : this.partyMembers ) {
            age = 2026 - Integer.parseInt(partyMember.getYearBorn());
            str += "----------------------------------------------" + "\nname: "+partyMember.getName()+"\nage: "+age+"\nneeds medical assistance? "+partyMember.needsMedicalAssistance()+"\nI am a: "+partyMember.getType().str+"\n";
        }
        return str;
    }
}
