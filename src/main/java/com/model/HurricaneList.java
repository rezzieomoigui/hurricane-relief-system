package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * HurricaneList class contains the hurricanes tracked by the application
 * @author Fantastic Four
 */
public class HurricaneList {
    private static HurricaneList hurricaneList;
    private ArrayList<Hurricane> hurricanes;

    /**
     * HurricaneList constructor loads the hurricanes and links the shelters and relief
     * requests that fall inside each hurricane's affected zip codes
     */
    private HurricaneList() {
        ArrayList<Hurricane> loaded = DataLoader.getHurricane();
        hurricanes = (loaded != null) ? loaded : new ArrayList<Hurricane>();
        for (Hurricane hurricane : hurricanes) {
            linkShelters(hurricane);
            linkReliefRequests(hurricane);
        }
    }

    /**
     * getInstance returns an instance of the hurricane list
     * @return instance of HurricaneList
     */
    public static HurricaneList getInstance() {
        if (hurricaneList == null) {
            hurricaneList = new HurricaneList();
        }
        return hurricaneList;
    }

    /**
     * getHurricanes returns all hurricanes
     * @return ArrayList of hurricanes
     */
    public ArrayList<Hurricane> getHurricanes() {
        return hurricanes;
    }

    /**
     * getHurricane finds a hurricane by id
     * @param id hurricane id
     * @return the hurricane with that id, or null
     */
    public Hurricane getHurricane(UUID id) {
        for (Hurricane hurricane : hurricanes) {
            if (hurricane.getId().equals(id)) {
                return hurricane;
            }
        }
        return null;
    }

    /**
     * getHurricane finds a hurricane by name
     * @param name hurricane name (case-insensitive)
     * @return the hurricane with that name, or null
     */
    public Hurricane getHurricane(String name) {
        for (Hurricane hurricane : hurricanes) {
            if (hurricane.getName().equalsIgnoreCase(name)) {
                return hurricane;
            }
        }
        return null;
    }

    /**
     * getHurricanesAffecting finds the hurricanes that affect a zip code (dissipated hurricanes are skipped)
     * @param zipCode zip code to check
     * @return ArrayList of hurricanes affecting the zip code
     */
    public ArrayList<Hurricane> getHurricanesAffecting(String zipCode) {
        ArrayList<Hurricane> affecting = new ArrayList<Hurricane>();
        for (Hurricane hurricane : hurricanes) {
            if (hurricane.getStatus() != HurricaneStatus.DISSIPATED && hurricane.affects(zipCode)) {
                affecting.add(hurricane);
            }
        }
        return affecting;
    }

    /**
     * addHurricane adds a hurricane to the list
     * @param hurricane hurricane to add
     * @return true if it was added (false if its id is already in the list)
     */
    public boolean addHurricane(Hurricane hurricane) {
        if (hurricane == null || getHurricane(hurricane.getId()) != null) {
            return false;
        }
        hurricanes.add(hurricane);
        linkShelters(hurricane);
        linkReliefRequests(hurricane);
        return true;
    }

    /**
     * removeHurricane removes a hurricane from the list
     * @param id hurricane id
     * @return true if it was removed
     */
    public boolean removeHurricane(UUID id) {
        Hurricane hurricane = getHurricane(id);
        return hurricane != null && hurricanes.remove(hurricane);
    }

    /**
     * addReliefRequest gives a new relief request to every hurricane that affects the request's zip code
     * @param request the new relief request
     */
    public void addReliefRequest(ReliefRequest request) {
        for (Hurricane hurricane : hurricanes) {
            if (hurricane.affects(request.getLocationZip())) {
                hurricane.addReliefRequest(request);
            }
        }
    }

    /**
     * removeReliefRequest takes a relief request out of every hurricane
     * @param request the relief request to remove
     */
    public void removeReliefRequest(ReliefRequest request) {
        for (Hurricane hurricane : hurricanes) {
            hurricane.removeReliefRequest(request);
        }
    }

    /**
     * save method saves the hurricanes
     * @return boolean for if the hurricanes were properly saved
     */
    public boolean save() {
        DataWriter.saveHurricane();   // DataWriter.saveHurricane() is still empty, so nothing is written yet
        return true;
    }

    private void linkShelters(Hurricane hurricane) {
        for (Shelter shelter : ShelterList.getInstance().getShelters()) {
            if (hurricane.affects(shelter.getZipCode())) {
                hurricane.addShelter(shelter);
            }
        }
    }

    private void linkReliefRequests(Hurricane hurricane) {
        for (ReliefRequest request : ReliefRequestList.getInstance().getReliefRequests()) {
            if (hurricane.affects(request.getLocationZip())) {
                hurricane.addReliefRequest(request);
            }
        }
    }
}
