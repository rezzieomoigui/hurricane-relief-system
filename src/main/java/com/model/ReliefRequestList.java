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
        if (loaded != null && !loaded.isEmpty()) {
            this.reliefRequests = loaded;
        }
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
        return this.reliefRequests;
    }

    /**
     * addReliefRequest adds relief requests to the arraylist
     * @param requestWriter user writing request
     * @param address user address
     * @return boolean of if request was added or not
     */
    public ReliefRequest addReliefRequest(User user, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
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
    
    public String toString() {
        String str = "";
        for (ReliefRequest reliefRequest : reliefRequests) {
            if (reliefRequest.getStatus() == ReliefRequestStatus.SUBMITTED)
                str += "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + reliefRequest.toString() + "\n";
        }
        return str;
    }

    /**
     * save method saves the relief requests
     * @return boolean for if the relief requests were properly saved
     */
    public boolean save() {
        return DataWriter.saveReliefRequests();
    }

    private ArrayList<ReliefRequest> matchingSkillReliefRequest(Skills skill) {
        ArrayList<ReliefRequest> matchingRequests = new ArrayList<ReliefRequest>();
        for( ReliefRequest reliefRequest : reliefRequests ) {
            if ( reliefRequest.getSkillsNeed() == skill )
                matchingRequests.add(reliefRequest);
        }
        return matchingRequests;
    }

    private Address[] ReliefRequestAddresses(ArrayList<ReliefRequest> reliefRequests) {
        Address[] addresses = new Address[reliefRequests.size()];
        for (int i = 0; i < reliefRequests.size(); ++i) {
            ReliefRequest reliefRequest = reliefRequests.get(i);
            addresses[i] = new Address(reliefRequest.getAddress(), reliefRequest.getCity(), reliefRequest.getState(), reliefRequest.getZipCode());
        }
        return addresses;
    }

    private ReliefRequest getReliefRequest(Address address) {
        for ( ReliefRequest reliefRequest : reliefRequests ) {
            if ( reliefRequest.getAddress().equals(address.getStreet()) && reliefRequest.getCity().equals(address.getCity()) && reliefRequest.getState().equals(address.getState()) && reliefRequest.getZipCode().equals(address.getZip())) 
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