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

    //private static MapTools mapTools; 

    private ArrayList<ReliefRequest> reliefRequests;

    /**
     * ReliefRequestList constructor creates relief request list
     */
    private ReliefRequestList() {
        ArrayList<ReliefRequest> loaded = DataLoader.getReliefRequests();
        //reliefRequests = (loaded != null) ? loaded : new ArrayList<ReliefRequest>();
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
    public boolean addReliefRequest(User user, String address, String city, String state, String zipCode, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome, boolean hasPet, Skills skillsNeed) {
        reliefRequests.add(new ReliefRequest(user.getUuid(), address, city, state, zipCode, partyMembers, isIndoors, isMobile, needsMedicalAssistance, status, estimatedArrivalMinutes, stayingHome, hasPet, skillsNeed));
        return true;
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
     * Gives suggested request based on location
     * @param User the user who needs a suggested request
     */
    public ReliefRequest getSuggestedRequest(User user) 
            throws IOException, InterruptedException {
        String userType = user.getUserType().str;
        if (!user.isAdmin() && (!userType.equals("VOLUNTEER"))) 
            return null;
        String address = user.getAddress();
        String city = user.getCity();
        String state = user.getState();
        String zipCode = user.getZipCode();
        Address userAddress = new Address(address, city, state, zipCode);
        Address[] addresses = ReliefRequestAddresses();
        MapTools mapTools = MapTools.getInstance();
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

    private Address[] ReliefRequestAddresses() {
        reliefRequests = getReliefRequests();
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
        try {
            ReliefRequest r = reliefRequestList.getSuggestedRequest(user);
            System.out.println(r.getAddress());
        } catch (Exception e) {
            System.out.print("Error: "+e);
        }
    }
}