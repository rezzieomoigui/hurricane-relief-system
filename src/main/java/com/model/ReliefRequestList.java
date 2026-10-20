package com.model;
import java.util.ArrayList;
import java.util.UUID;

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

    /**
     * getInstance returns an instance of the relief request list
     * @return instance of relief request
     */
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
    public boolean addReliefRequest(User user, String address, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean isInjured, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        reliefRequests.add(new ReliefRequest(user.getUuid(), address, partyMembers, isIndoors, isMobile, isInjured, status, priority, estimatedArrivalMinutes, stayingHome));
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
     * prioritizeRequests adjusts order of requests based on priority level
     * @param reliefRequestList list of relief requests
     * @param isAdmin checks is user is an administrator
     */
    public void prioritizeRequests(ReliefRequestList reliefRequestList, boolean isAdmin) {
        // add implementation here
    }

    /**
     * save method saves the relief requests
     * @return boolean for if the relief requests were properly saved
     */
    public boolean save() {
        return DataWriter.saveReliefRequests();
    }
}