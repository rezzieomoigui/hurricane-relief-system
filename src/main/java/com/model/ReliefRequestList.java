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
    public boolean addReliefRequest(User user, String address, String city, String state, String zipCode, ArrayList<PartyMember> partyMembers, boolean isIndoors, boolean isMobile, boolean needsMedicalAssistance, ReliefRequestStatus status, int priority, int estimatedArrivalMinutes, boolean stayingHome) {
        reliefRequests.add(new ReliefRequest(user.getUuid(), address, city, state, zipCode, partyMembers, isIndoors, isMobile, needsMedicalAssistance, status, priority, estimatedArrivalMinutes, stayingHome));
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
    /**public ArrayList<ReliefRequest> getOrderedRequests(User user) 
            throws IOException, InterruptedException {
        String address = user.getAddress();
        String city = user.getCity();
        String state = user.getState();
        String zipCode = user.getZipCode();
        String userType = user.getUsername();
        // get user's skills. how to manage this since admin can also access this method?
        if (!userType.equals("Admin") && (!userType.equals("Volunteer"))) 
            return null;
        Address userAddress = new Address(address, city, state, zipCode);
        HashMap<ReliefRequest, Integer> requestPriorityPoints = new HashMap<ReliefRequest, Integer>();
        for (int i = 0; i < this.reliefRequests.size(); ++i) {
            int priorityPoints = 0;
            ReliefRequest request = reliefRequests.get(i);
            Address requestAddress = new Address(request.getAddress(), request.getCity(), request.getState(), request.getZipCode());
            MapTools mapTools = MapTools.getInstance();
            Double drivingTime = mapTools.getDrivingTime(userAddress, requestAddress);
            if (drivingTime <= 900) // 15 minutes
                priorityPoints += 10;
            if (user.hasAttribute(Skills))
            
        }*/
       // Address requestAddress = new Address(String this)
        // create an Address object from relief request and entered address String
        // list in order


        // alternative logic: create copy of reliefRequests
        // find closest shelter
        // if this shelter matches with skills, 
        // put this shelter at top of list 
        // otherwise, remove the shelter from the arraylist copy and
        // try the next closest shelter
        // list length gets to 0? just return the closest shelter
        // may need one version for isAdmin, another for isVolunteer
        
  //  }

    /**
     * save method saves the relief requests
     * @return boolean for if the relief requests were properly saved
     */
    public boolean save() {
        return DataWriter.saveReliefRequests();
    }
}