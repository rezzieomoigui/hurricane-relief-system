package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.io.IOException;

import com.location.Address;
import com.location.MapTools;

/**
 * ShelterList method contains the shelter methods
 * @author Fantastic Four
 */
public class ShelterList {
    private static ShelterList shelterList;
    private ArrayList<Shelter> shelters;
    
    /**
     * ShelterList constructor sets the shelters into a shelterList arrayList
     */
    private ShelterList() {
        ArrayList<Shelter> loaded = DataLoader.getShelters();
        this.shelters = (loaded != null) ? loaded : new ArrayList<Shelter>();
    }

    /**
     * getInstance method returns an instance of shelter list
     * @return instance of ShelterList
     */
    public static ShelterList getInstance() {
        if (shelterList == null) {
            shelterList = new ShelterList();
        }
        return shelterList;
    }

    /**
     * getShelters returns the shelters
     * @return ArrayList of shelters
     */
    public ArrayList<Shelter> getShelters() {
        return this.shelters;
        // this needs to iterate through the shelters
    }

    /**
     * getShelter returns the shelter of a specific id
     * @param uuid user id
     * @return shelter of specific uuid
     */
    public Shelter getShelter(UUID uuid) {
        for (Shelter shelter : this.shelters) {
            if (shelter.getId().equals(uuid)) {
                return shelter;
            }
        }
        return null;
    }

    public Shelter getShelter(String address, String city, String state, String zipCode) {
        for (Shelter shelter : this.shelters) {
            if (shelter.getAddress().equals(address) && shelter.getCity().equals(city) &&
                shelter.getState().equals(state) && shelter.getZipCode().equals(zipCode)) {
                return shelter;
            }
        }
        return null;
    }

    /**
     * geShelters returns arraylist of shelters with a specific zip code
     * @param zipCode shelter zip code
     * @return array list of shelters with specific zip code
     */
    public ArrayList<Shelter> getShelter(String zipCode) {
        ArrayList<Shelter> matches = new ArrayList<Shelter>();
        for (Shelter shelter : this.shelters) {
            if (shelter.getZipCode() != null && shelter.getZipCode().equals(zipCode)) {
                matches.add(shelter);
            }
        }
        return matches;
    }

    /**
     * spaceLeft method checks if there is enough space for a given number of people at a shelter
     * @param shelter shelter being looked at
     * @param num number of people needing space at shelter
     * @return boolean based on if there is space at the given shelter
     */
    public boolean spaceLeft(Shelter shelter, int num) {
        if (shelter.getMaxNumberOfOccupants() - shelter.getNumberOfOccupants() >= num && num > 0) {
            return true;
        }
        return false;
    }

    /**
     * meetsPetRequirement checks if the shelter is pet friendly if the user has pets
     * @param shelter shelter being looked at
     * @param reliefRequest relief request showing if user has pets
     * @return boolean depending on if the shelter is pet friendly and they have pets or not
     */
    public boolean meetsPetRequirement(Shelter shelter, ReliefRequest reliefRequest) {
        if (!reliefRequest.getHasPet()) {
            return true;
        }
        return shelter.getShelterResources().contains(ShelterResources.PET_FRIENDLY);
    }

    /**
     * meetsMedicalRequirement checks if the shelter has medical staff available and if that is needed by the user
     * @param shelter shelter being looked at
     * @param reliefRequest relief request showing if the user needs medical assistance
     * @return boolean depending on if the shelter has medical staff and if they are needed by the user or not
     */
    public boolean meetsMedicalRequirement(Shelter shelter, ReliefRequest reliefRequest) {
        if (!reliefRequest.getNeedsMedicalAssistance()) {
            return true;
        }
        return shelter.getMedicalStaffAvailable();
    }

    /**
     * matchingShelters checks requirements and then adds shelters that fit those requirements to an arrayList
     * @param numPeople number of people needing space at a shelter
     * @param reliefRequest relief request
     * @return arrayList of shelters that fit the requirements
     */
    public ArrayList<Shelter> matchingShelters(int numPeople, ReliefRequest reliefRequest) {
        ArrayList<Shelter> matchingShelters = new ArrayList<>();
        // System.out.println("Shelters Loaded: " + shelters.size());
        for (Shelter shelter : shelters) {
            if (shelter.isAvailable() && spaceLeft(shelter, numPeople) && meetsMedicalRequirement(shelter, reliefRequest) && meetsPetRequirement(shelter, reliefRequest)) {
                matchingShelters.add(shelter);
            }
        }
        return matchingShelters;
    }

    public Shelter closestMatchingShelter(int numPeople, ReliefRequest reliefRequest) throws IOException, InterruptedException {
        ArrayList<Shelter> matchingShelters = matchingShelters(numPeople, reliefRequest);
        // System.out.println("Matching Shelter Found: " + matchingShelters.size());
        if (matchingShelters.isEmpty()) {
            return null;
        }
        Address[] addresses = ShelterAddresses(matchingShelters);
        Address closestAddress = MapTools.getInstance().findClosestAddress(new Address(reliefRequest.getAddress(), reliefRequest.getCity(), reliefRequest.getState(), reliefRequest.getZipCode()), addresses);
        for (int i = 0; i < addresses.length; ++i) {
            if (addresses[i] == closestAddress) {
                return matchingShelters.get(i);
            }
        }
        return null;
    }

    /**
     * ShelterAddresses gets the addresses of the shelters
     * @param shelters arrayList of shelters
     * @return array of shelter addresses
     */
    private Address[] ShelterAddresses(ArrayList<Shelter> shelters) {
        Address[] addresses = new Address[shelters.size()];
        for (int i = 0; i < shelters.size(); ++i) {
            Shelter shelter = shelters.get(i);
            addresses[i] = new Address(shelter.getAddress(), shelter.getCity(), shelter.getState(), shelter.getZipCode());
        }
        return addresses;
    }

    public boolean assignReliefRequest(int numPeople, ReliefRequest reliefRequest, Volunteer volunteer, boolean isAdmin) throws InterruptedException {
        System.out.println("Made it to assignment");
        Shelter shelter = null;
        try {
            shelter = closestMatchingShelter(numPeople, reliefRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Shelter found? " + (shelter != null));
        if (shelter == null) {
            return false;
        }
        int newOccupacy = shelter.getNumberOfOccupants() + numPeople;
        shelter.setNumberOfOccupants(newOccupacy, isAdmin);
        reliefRequest.setAssignedVolunteer(volunteer);
        reliefRequest.setStatus(ReliefRequestStatus.ASSIGNED);
        return true;
    }

    /**
     * addShelter method adds a shelter
     * @param shelterType type of shelter
     * @param address address of shelter
     * @param city the shelter's city
     * @param state the state the shelter is in
     * @param zipCode zip code of shelter
     * @param shelterResources shelter resources
     * @param occupants All occupants of shelter
     * @param numberOfOccupants Total number of occupants
     * @param maxNumberOfOccupants Max occupants allowed
     * @param shelterStatus shelter status
     * @param visualDescription shelter visual description
     * @return boolean true if shelter was added correctly
     */
    public Shelter addShelter(ShelterType shelterType, String address, String city, String state, String zipCode, ArrayList<ShelterResources> shelterResources, 
        ShelterStatus shelterStatus, String visualDescription, int numberOfOccupants, int maxNumberOfOccupants, boolean medicalStaffAvailable) {
        Shelter shelter = new Shelter(shelterType, address, city, state, zipCode, shelterResources, shelterStatus, visualDescription, numberOfOccupants, maxNumberOfOccupants, medicalStaffAvailable);
        shelters.add(shelter);
        return shelter;
    }

    /**
     * addPartyMember adds a user to the relief request
     * @param user account user
     * @return boolean based on if party member was correctly added
     */
    public void addOccupants(Shelter shelter, ArrayList<PartyMember> newOccupants) {
        ArrayList<PartyMember> occupants = shelter.getOccupants();
        for (PartyMember occupant : newOccupants) {
            occupants.add(new PartyMember(occupant.getName(), occupant.getYearBorn(), occupant.needsMedicalAssistance(), occupant.getType()));
        }
        shelter.setOccupants(occupants);
    }

    /**
     * save method saves shelters
     * @return boolean depending on if it saved properly or not
     */
    public boolean save() {
        return DataWriter.saveShelters();
    }


}
