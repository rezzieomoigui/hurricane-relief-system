package com.model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.location.Address;
import com.location.MapTools;


/**
 * ReliefRequestList class contains a list of the relief requests
 * @author Fantastic Four
 */
public class ReliefRequestList {
    private static ReliefRequestList reliefRequestList;

    private ArrayList<ReliefRequest> reliefRequests;

    /**
     * ReliefRequestList constructor creates relief request list
     */
    private ReliefRequestList() {
        ArrayList<ReliefRequest> loaded = DataLoader.getReliefRequests();
        reliefRequests = (loaded != null) ? loaded : new ArrayList<ReliefRequest>();
    }

    public static ReliefRequestList getInstance() {
        if (reliefRequestList == null) {
            reliefRequestList = new ReliefRequestList();
        }
        return reliefRequestList;
    }

    /**
     * getReliefRequestById gets the relief request that matches the prodived id
     * @param id relief request uuid
     * @return relief request associated with specific uuid
     */
    public ReliefRequest getReliefRequestById(UUID id) {
        for (ReliefRequest request : reliefRequests) {
            if (request.getUuid().equals(id)) {
                return request;
            }
        }
        return null;
    }

    /**
     * getReliefRequests method gets the relief requests 
     * @return arrayList of relief requests
     */
    public ArrayList<ReliefRequest> getReliefRequests() {
        return DataLoader.getReliefRequests();
    }

    /**
     * addReliefRequest adds relief requests to the arraylist
     * @param requestWriter user writing request
     * @param address user address
     * @return boolean of if request was added or not
     */
    public ReliefRequest addReliefRequest(User user, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
        ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
        ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();
        ReliefRequest request = new ReliefRequest(user.getUuid(), user.getAddress(), user.getCity(), user.getState(), user.getZipCode(), isIndoors, isMobile, needsMedicalAssistance, ReliefRequestStatus.SUBMITTED, 0, stayingHome, hasPet, skillsNeed);
        reliefRequests.add(request);
        return request;
    }

    /**
     * addReliefRequest adds an already-built relief request to the list
     * @param request the relief request to add
     * @return boolean true if the request was added (false if it was already in the list)
     */
    public boolean addReliefRequest(ReliefRequest request) {
        ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
        ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();
        if (request == null || getReliefRequestById(request.getUuid()) != null) {
            return false;
        }
        reliefRequests.add(request);
        return true;
    }

    /**
     * removeReliefRequest removes requests from the arrayList
     * @param id user uuid
     * @return boolean if request was properly removed
     */
    public boolean removeReliefRequest(UUID id) {
        ReliefRequest request = getReliefRequestById(id);
        if (request == null) {
            return false;
        }
        return reliefRequests.remove(request);
    }

    /**
     * addPartyMember adds a user to the relief request
     * @param user account user
     * @return boolean based on if party member was correctly added
     */
    public void addPartyMember(ReliefRequest request, String name, String yearBorn, boolean needsMedicalAssistance, Being type) {
        ArrayList<PartyMember> partyMembers = request.getPartyMembers();
        partyMembers.add(new PartyMember(name, yearBorn, needsMedicalAssistance, type));
        request.setPartyMembers(partyMembers);
    }

    /**
     * Gives suggested request based on location
     * @param User the user who needs a suggested request
     */
    public ReliefRequest getSuggestedRequest(Volunteer volunteer) // only Volunteers need a suggested request
            throws IOException, InterruptedException {
        String address = volunteer.getAddress();
        String city = volunteer.getCity();
        String state = volunteer.getState();
        String zipCode = volunteer.getZipCode();
        Address userAddress = new Address(address, city, state, zipCode);
        // first select requests with needed Skills that match the Volunteer's 
        ArrayList<ReliefRequest> matchingRequests = matchingSkillReliefRequest(volunteer.getSkills());
        if (matchingRequests.isEmpty())
            matchingRequests = reliefRequests;
        Address[] addresses = ReliefRequestAddresses(matchingRequests);
        MapTools mapTools = MapTools.getInstance();
        // then select the closest address out of the matching requests
        Address closestAddress = mapTools.findClosestAddress(userAddress, addresses);
        ReliefRequest matchingRequest = getReliefRequest(closestAddress);
        return matchingRequest;
    }
    

    /**
     * save method saves the relief requests
     * @return boolean for if the relief requests were properly saved
     */
    public boolean save() {
        return DataWriter.saveReliefRequests();
    }

    /**
     * markCompleteRequest marks a relief request as completed
     * @param request relief request
     * @param isAdmin checks if the user is an administrator
     * @return boolean based on if the request was marked as completed
     */
    public String markCompleteRequest(ReliefRequest request, boolean isAdmin) {
        if (!isAdmin) {
            return "Error: only admin can mark request as completed!";
        }
        request.setStatus(ReliefRequestStatus.COMPLETED);
        return "Request marked complete";
    }

    /**
     * matchingSkillReliefRequest adds relief requests with that skill to an array list
     * @param skill user skill
     * @return arrayList of relief requests containing given skill
     */
    private ArrayList<ReliefRequest> matchingSkillReliefRequest(Skills skill) {
        ArrayList<ReliefRequest> matchingRequests = new ArrayList<ReliefRequest>();
        ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
        ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();
        for( ReliefRequest reliefRequest : reliefRequests ) {
            if ( reliefRequest.getSkillsNeed() == skill )
                matchingRequests.add(reliefRequest);
        }
        return matchingRequests;
    }

    /**
     * ReliefRequestAddresses gets the addresses of the relief requests
     * @param reliefRequests relief requests
     * @return array of addresses of the relief requests
     */
    private Address[] ReliefRequestAddresses(ArrayList<ReliefRequest> reliefRequests) {
        Address[] addresses = new Address[reliefRequests.size()];
        for (int i = 0; i < reliefRequests.size(); ++i) {
            ReliefRequest reliefRequest = reliefRequests.get(i);
            addresses[i] = new Address(reliefRequest.getAddress(), reliefRequest.getCity(), reliefRequest.getState(), reliefRequest.getZipCode());
        }
        return addresses;
    }

    /**
     * getReliefRequest gets the relief request based on address
     * @param address user address
     * @return relief request with provided address
     */
    private ReliefRequest getReliefRequest(Address address) {
        ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
        ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();
        for ( ReliefRequest reliefRequest : reliefRequests ) {
            if (reliefRequest.getAddress().equals(address.getStreet()) && reliefRequest.getCity().equals(address.getCity()) && reliefRequest.getState().equals(address.getState()) && reliefRequest.getZipCode().equals(address.getZip())) 
                return reliefRequest;
        }
        return null;
    }

    /**
     * getReliefRequest gets the relief request 
     * @param address user address
     * @param city user city
     * @param state user state
     * @param zipCode user zip code
     * @param submitterFirstName first name of person submitting request
     * @param submitterLastName last name of person submitting request
     * @return relief request matching given parameters
     */
    public ReliefRequest getReliefRequest(String address, String city, String state, String zipCode, String submitterFirstName, String submitterLastName) {
        for ( ReliefRequest reliefRequest : reliefRequests ) {
            if ( reliefRequest.getAddress().equals(address) && 
            reliefRequest.getCity().equals(city) && reliefRequest.getState().equals(state) && 
            reliefRequest.getZipCode().equals(zipCode) && reliefRequest.getUser().getFirstName().equals(submitterFirstName) && reliefRequest.getUser().getLastName().equals(submitterLastName)) 
                return reliefRequest;
        }
        return null;
    }

    public static void main(String[] args) {
        ReliefRequestList reliefRequestList = new ReliefRequestList();
        UserList userList = UserList.getInstance();
        User user = userList.getUser("jgrey");
        Volunteer volunteer = (Volunteer) user;
        try {
            ReliefRequest r = reliefRequestList.getSuggestedRequest(volunteer);
            System.out.println(r.getAddress());
        } catch (Exception e) {
            System.out.println(e);
            System.out.print(e.getStackTrace()[0].getLineNumber());
        }
    }
}