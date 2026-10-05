package com.model;

public class ReliefRequestList {
    private ReliefRequestList reliefRequestList;
    private ArrayList<ReliefRequest> reliefRequests;

    private ReliefRequestList() {
        reliefRequests = new ArrayList<>();
    }

    public static ReliefRequestList getInstance() {
        if (reliefRequestList == null) {
            reliefRequestList = new ReliefRequestList();
        }
        return reliefRequestList;
    }

    public ReliefRequest getReliefRequestById(UUID id) {
        // add implementation here
        return null;
    }

    public ArrayList<ReliefRequest> getReliefRequests() {
        // add implementation here
        return null;
    }

    public boolean addReliefRequest(User requestWriter, String address) {
        // add implementation here
        return null;
    }

    public boolean removeReliefRequest(UUID id) {
        // add implementation here
        return null;
    }

    public void prioritizeRequests(ReliefRequestList reliefRequestList, boolean isAdmin) {
        // add implementation here
    }

    public boolean save() {
        // add implementation here
        return null;
    }
}