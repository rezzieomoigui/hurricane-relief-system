package com.model;

/*
 * Tests for the ShelterList class (Chance Price) - everything that works without the map API.
 * The map-dependent paths (closest shelter / successful assignment) are in ShelterListMapTest.
 * Used AI to assist with test implementation.
 * were worked out from the ShelterList code.
 *
 * Setup shelters:  A = 1100 Gervais St 29201, FOOD + PET_FRIENDLY, OPEN,   4 of 10, medical staff
 *                  B = 9 Oak Ave 29205,       FOOD,               OPEN,   0 of 5,  no medical staff
 *                  C = 3 Elm St 29201,        PET_FRIENDLY,       CLOSED, 0 of 20, medical staff
 *
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | Test                                           | Reasoning                                                                 |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | testGetSheltersNeverNull                       | the shelter list must never be null or every caller crashes               |
 * | testGetSheltersCount                           | the list should hold exactly the 3 shelters added in setup                |
 * | testGetShelterByIdFound                        | shelters can be looked up by their id                                     |
 * | testGetShelterByIdUnknownReturnsNull           | an id that isn't in the system returns null (second return path)          |
 * | testGetShelterByIdNullReturnsNull              | a null id should not crash the lookup                                     |
 * | testGetShelterByAddressFound                   | a shelter is found when street, city, state and zip all match             |
 * | testGetShelterByAddressWrongCityReturnsNull    | every part of the address has to match, not just the street               |
 * | testGetShelterByAddressWrongZipReturnsNull     | every part of the address has to match, not just the street               |
 * | testGetShelterByAddressWrongStateReturnsNull   | every part of the address has to match, not just the street               |
 * | testGetShelterByAddressWrongStreetReturnsNull  | the street has to match, not just the city, state and zip                 |
 * | testGetShelterByAddressSkipsNullAddress        | one shelter with a missing address must not crash every address search   |
 * | testGetSheltersByZipMultipleMatches            | a zip with two shelters returns both (loop matches many times)            |
 * | testGetSheltersByZipOneMatch                   | a zip with one shelter returns exactly that one                           |
 * | testGetSheltersByZipNoMatchReturnsEmpty        | a zip with no shelters returns an empty list, not null                    |
 * | testGetSheltersByZipNullReturnsEmpty           | a null zip should not crash the search                                    |
 * | testSpaceLeftExactlyFills                      | boundary: shelter A has exactly 6 open spots (10 - 4)                     |
 * | testSpaceLeftOneOverCapacity                   | just past the boundary: 7 people do not fit in 6 spots                    |
 * | testSpaceLeftOnePerson                         | normal case: one person fits                                              |
 * | testSpaceLeftZeroPeople                        | placing nobody is not a valid request                                     |
 * | testSpaceLeftNegativePeople                    | a negative group size is invalid input                                    |
 * | testSpaceLeftFullShelter                       | a shelter at maximum capacity has no room for anyone                      |
 * | testMeetsPetRequirementNoPet                   | a request without a pet can go to any shelter                             |
 * | testMeetsPetRequirementPetFriendlyShelter      | a request with a pet can go to a pet friendly shelter                     |
 * | testMeetsPetRequirementNotPetFriendly          | a request with a pet cannot go to a shelter that isn't pet friendly       |
 * | testMeetsMedicalRequirementNotNeeded           | a request without medical needs can go to any shelter                     |
 * | testMeetsMedicalRequirementStaffAvailable      | a request with medical needs can go to a shelter with medical staff       |
 * | testMeetsMedicalRequirementNoStaff             | a request with medical needs cannot go to a shelter without medical staff |
 * | testMatchingSheltersPlainRequestCount          | both open shelters with room match a request with no special needs        |
 * | testMatchingSheltersExcludesClosed             | closed shelters must never be matched                                     |
 * | testMatchingSheltersPetOnlyPetFriendly         | the pet filter leaves only shelter A                                      |
 * | testMatchingSheltersMedicalOnlyStaffed         | the medical filter leaves only shelter A                                  |
 * | testMatchingSheltersPetAndMedical              | both filters at once still leave shelter A                                |
 * | testMatchingSheltersSixPeopleOnlyLargeShelter  | shelter B only holds 5, so a group of 6 only matches A                    |
 * | testMatchingSheltersTooManyPeopleReturnsEmpty  | no shelter has room for 50 people                                         |
 * | testMatchingSheltersZeroPeopleReturnsEmpty     | a group of zero people is not a valid request                             |
 * | testMatchingSheltersNoSheltersReturnsEmpty     | with no shelters loaded the loop runs zero times and returns empty        |
 * | testClosestMatchingShelterNoMatchReturnsNull   | when nothing matches there is no closest shelter (no map call is made)    |
 * | testAssignRequestNoMatchReturnsFalse           | a request cannot be assigned when no shelter has room                     |
 * | testAssignRequestNoMatchStatusUnchanged        | a failed assignment must leave the request SUBMITTED                      |
 * | testAssignRequestNoMatchNoVolunteerRecorded    | a failed assignment must not record a volunteer                           |
 * | testAddShelterReturnsNewShelter                | addShelter returns the shelter it created with the given address          |
 * | testAddShelterIncreasesCount                   | the new shelter actually has to be stored in the list                     |
 * | testAddOccupantsTwoMembers                     | adding two party members gives the shelter two occupants                  |
 * | testAddOccupantsKeepsName                      | the occupant stored is the person who was added                           |
 * | testAddOccupantsEmptyList                      | adding an empty group leaves the shelter with no occupants                |
 * | testAddOccupantsNullListNoCrash                | a null group should be ignored instead of crashing                        |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 *
 * Not tested yet: save(). It writes the real json_files folder until DataWriter uses the
 * JUnit test paths, so running it now would overwrite the team's real data.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShelterListTest {
    private ShelterList shelterList = ShelterList.getInstance();
    private ArrayList<Shelter> shelters = shelterList.getShelters();

    private Shelter shelterA;
    private Shelter shelterB;
    private Shelter shelterC;

    private ReliefRequest plainRequest;
    private ReliefRequest petRequest;
    private ReliefRequest medicalRequest;
    private ReliefRequest petAndMedicalRequest;

    private Volunteer volunteer;

    /**
     * Every test starts with the same three shelters and four kinds of relief request in memory.
     * Nothing is saved to the json files.
     */
    @BeforeEach
    public void setup() {
        shelters.clear();
        shelterA = new Shelter(ShelterType.SCHOOL, "1100 Gervais St", "Columbia", "SC", "29201",
                resources(ShelterResources.FOOD, ShelterResources.PET_FRIENDLY), ShelterStatus.OPEN,
                "Brick school", 4, 10, true);
        shelterB = new Shelter(ShelterType.HOUSE, "9 Oak Ave", "Columbia", "SC", "29205",
                resources(ShelterResources.FOOD), ShelterStatus.OPEN,
                "White house", 0, 5, false);
        shelterC = new Shelter(ShelterType.COMMUNITY_CENTER, "3 Elm St", "Columbia", "SC", "29201",
                resources(ShelterResources.PET_FRIENDLY), ShelterStatus.CLOSED,
                "Gym", 0, 20, true);
        shelters.add(shelterA);
        shelters.add(shelterB);
        shelters.add(shelterC);

        plainRequest = request(false, false);
        petRequest = request(true, false);
        medicalRequest = request(false, true);
        petAndMedicalRequest = request(true, true);

        volunteer = new Volunteer("Bailey", "Bowen", "bbowen", "RelHe1p34!", "bbowen@gmail.com", "02/09/2003",
                "594 South St", "Columbia", "SC", "29201", "English", false, UserType.VOLUNTEER,
                Skills.CPR, true, false, false);
    }

    @AfterEach
    public void tearDown() {
        shelters.clear();
    }

    private ArrayList<ShelterResources> resources(ShelterResources... list) {
        return new ArrayList<ShelterResources>(Arrays.asList(list));
    }

    /** A SUBMITTED request from 437 Bull Street, Columbia, with or without a pet / medical needs. */
    private ReliefRequest request(boolean hasPet, boolean needsMedicalAssistance) {
        return new ReliefRequest(UUID.randomUUID(), "437 Bull Street", "Columbia", "SC", "29201",
                true, true, needsMedicalAssistance, ReliefRequestStatus.SUBMITTED, 0, false, hasPet, Skills.NONE);
    }

    private Shelter fullShelter() {
        return new Shelter(ShelterType.HOUSE, "5 Full Rd", "Columbia", "SC", "29201",
                resources(ShelterResources.FOOD), ShelterStatus.OPEN, "Packed house", 10, 10, false);
    }

    // ---------- getShelters ----------

    @Test
    public void testGetSheltersNeverNull() {
        assertNotNull(ShelterList.getInstance().getShelters());
    }

    @Test
    public void testGetSheltersCount() {
        assertEquals(3, shelterList.getShelters().size());
    }

    // ---------- getShelter(UUID) ----------

    @Test
    public void testGetShelterByIdFound() {
        assertSame(shelterB, shelterList.getShelter(shelterB.getId()));
    }

    @Test
    public void testGetShelterByIdUnknownReturnsNull() {
        assertNull(shelterList.getShelter(UUID.randomUUID()));
    }

    @Test
    public void testGetShelterByIdNullReturnsNull() {
        assertNull(shelterList.getShelter((UUID) null));
    }

    // ---------- getShelter(address, city, state, zip) ----------

    @Test
    public void testGetShelterByAddressFound() {
        assertSame(shelterA, shelterList.getShelter("1100 Gervais St", "Columbia", "SC", "29201"));
    }

    @Test
    public void testGetShelterByAddressWrongCityReturnsNull() {
        assertNull(shelterList.getShelter("1100 Gervais St", "Irmo", "SC", "29201"));
    }

    @Test
    public void testGetShelterByAddressWrongZipReturnsNull() {
        assertNull(shelterList.getShelter("1100 Gervais St", "Columbia", "SC", "29202"));
    }

    @Test
    public void testGetShelterByAddressWrongStateReturnsNull() {
        assertNull(shelterList.getShelter("1100 Gervais St", "Columbia", "NC", "29201"));
    }

    @Test
    public void testGetShelterByAddressWrongStreetReturnsNull() {
        assertNull(shelterList.getShelter("1200 Gervais St", "Columbia", "SC", "29201"));
    }

    @Test
    public void testGetShelterByAddressSkipsNullAddress() {
        shelters.add(0, new Shelter(ShelterType.HOUSE, null, "Columbia", "SC", "29201",
                resources(ShelterResources.FOOD), ShelterStatus.OPEN, "Unknown", 0, 5, false));
        assertSame(shelterB, shelterList.getShelter("9 Oak Ave", "Columbia", "SC", "29205"));
    }

    // ---------- getShelter(zipCode) ----------

    @Test
    public void testGetSheltersByZipMultipleMatches() {
        assertEquals(2, shelterList.getShelter("29201").size());
    }

    @Test
    public void testGetSheltersByZipOneMatch() {
        assertSame(shelterB, shelterList.getShelter("29205").get(0));
    }

    @Test
    public void testGetSheltersByZipNoMatchReturnsEmpty() {
        assertTrue(shelterList.getShelter("99999").isEmpty());
    }

    @Test
    public void testGetSheltersByZipNullReturnsEmpty() {
        assertTrue(shelterList.getShelter((String) null).isEmpty());
    }

    // ---------- spaceLeft ----------

    @Test
    public void testSpaceLeftExactlyFills() {
        assertTrue(shelterList.spaceLeft(shelterA, 6));
    }

    @Test
    public void testSpaceLeftOneOverCapacity() {
        assertFalse(shelterList.spaceLeft(shelterA, 7));
    }

    @Test
    public void testSpaceLeftOnePerson() {
        assertTrue(shelterList.spaceLeft(shelterA, 1));
    }

    @Test
    public void testSpaceLeftZeroPeople() {
        assertFalse(shelterList.spaceLeft(shelterA, 0));
    }

    @Test
    public void testSpaceLeftNegativePeople() {
        assertFalse(shelterList.spaceLeft(shelterA, -1));
    }

    @Test
    public void testSpaceLeftFullShelter() {
        assertFalse(shelterList.spaceLeft(fullShelter(), 1));
    }

    // ---------- meetsPetRequirement ----------

    @Test
    public void testMeetsPetRequirementNoPet() {
        assertTrue(shelterList.meetsPetRequirement(shelterB, plainRequest));
    }

    @Test
    public void testMeetsPetRequirementPetFriendlyShelter() {
        assertTrue(shelterList.meetsPetRequirement(shelterA, petRequest));
    }

    @Test
    public void testMeetsPetRequirementNotPetFriendly() {
        assertFalse(shelterList.meetsPetRequirement(shelterB, petRequest));
    }

    // ---------- meetsMedicalRequirement ----------

    @Test
    public void testMeetsMedicalRequirementNotNeeded() {
        assertTrue(shelterList.meetsMedicalRequirement(shelterB, plainRequest));
    }

    @Test
    public void testMeetsMedicalRequirementStaffAvailable() {
        assertTrue(shelterList.meetsMedicalRequirement(shelterA, medicalRequest));
    }

    @Test
    public void testMeetsMedicalRequirementNoStaff() {
        assertFalse(shelterList.meetsMedicalRequirement(shelterB, medicalRequest));
    }

    // ---------- matchingShelters ----------

    @Test
    public void testMatchingSheltersPlainRequestCount() {
        assertEquals(2, shelterList.matchingShelters(1, plainRequest).size());
    }

    @Test
    public void testMatchingSheltersExcludesClosed() {
        assertFalse(shelterList.matchingShelters(1, plainRequest).contains(shelterC));
    }

    @Test
    public void testMatchingSheltersPetOnlyPetFriendly() {
        assertEquals(Arrays.asList(shelterA), shelterList.matchingShelters(1, petRequest));
    }

    @Test
    public void testMatchingSheltersMedicalOnlyStaffed() {
        assertEquals(Arrays.asList(shelterA), shelterList.matchingShelters(1, medicalRequest));
    }

    @Test
    public void testMatchingSheltersPetAndMedical() {
        assertEquals(Arrays.asList(shelterA), shelterList.matchingShelters(1, petAndMedicalRequest));
    }

    @Test
    public void testMatchingSheltersSixPeopleOnlyLargeShelter() {
        assertEquals(Arrays.asList(shelterA), shelterList.matchingShelters(6, plainRequest));
    }

    @Test
    public void testMatchingSheltersTooManyPeopleReturnsEmpty() {
        assertTrue(shelterList.matchingShelters(50, plainRequest).isEmpty());
    }

    @Test
    public void testMatchingSheltersZeroPeopleReturnsEmpty() {
        assertTrue(shelterList.matchingShelters(0, plainRequest).isEmpty());
    }

    @Test
    public void testMatchingSheltersNoSheltersReturnsEmpty() {
        shelters.clear();
        assertTrue(shelterList.matchingShelters(1, plainRequest).isEmpty());
    }

    // ---------- closestMatchingShelter / assignReliefRequest (paths with no map call) ----------

    @Test
    public void testClosestMatchingShelterNoMatchReturnsNull() throws Exception {
        assertNull(shelterList.closestMatchingShelter(50, plainRequest));
    }

    @Test
    public void testAssignRequestNoMatchReturnsFalse() throws Exception {
        assertFalse(shelterList.assignReliefRequest(50, plainRequest, volunteer, true));
    }

    @Test
    public void testAssignRequestNoMatchStatusUnchanged() throws Exception {
        shelterList.assignReliefRequest(50, plainRequest, volunteer, true);
        assertEquals(ReliefRequestStatus.SUBMITTED, plainRequest.getStatus());
    }

    @Test
    public void testAssignRequestNoMatchNoVolunteerRecorded() throws Exception {
        shelterList.assignReliefRequest(50, plainRequest, volunteer, true);
        assertNull(plainRequest.getAssignedVolunteerID());
    }

    // ---------- addShelter ----------

    @Test
    public void testAddShelterReturnsNewShelter() {
        Shelter added = shelterList.addShelter(ShelterType.BUSINESS, "7 Ash Ct", "Columbia", "SC", "29210",
                resources(ShelterResources.WATER), ShelterStatus.OPEN, "Warehouse", 0, 30, false);
        assertEquals("7 Ash Ct", added.getAddress());
    }

    @Test
    public void testAddShelterIncreasesCount() {
        shelterList.addShelter(ShelterType.BUSINESS, "7 Ash Ct", "Columbia", "SC", "29210",
                resources(ShelterResources.WATER), ShelterStatus.OPEN, "Warehouse", 0, 30, false);
        assertEquals(4, shelters.size());
    }

    // ---------- addOccupants ----------

    @Test
    public void testAddOccupantsTwoMembers() {
        ArrayList<PartyMember> group = new ArrayList<PartyMember>();
        group.add(new PartyMember("Max Long", "2015", false, Being.PERSON));
        group.add(new PartyMember("Whiskers", "2020", false, Being.CAT));
        shelterList.addOccupants(shelterA, group);
        assertEquals(2, shelterA.getOccupants().size());
    }

    @Test
    public void testAddOccupantsKeepsName() {
        ArrayList<PartyMember> group = new ArrayList<PartyMember>();
        group.add(new PartyMember("Max Long", "2015", false, Being.PERSON));
        shelterList.addOccupants(shelterA, group);
        assertEquals("Max Long", shelterA.getOccupants().get(0).getName());
    }

    @Test
    public void testAddOccupantsEmptyList() {
        shelterList.addOccupants(shelterA, new ArrayList<PartyMember>());
        assertEquals(0, shelterA.getOccupants().size());
    }

    @Test
    public void testAddOccupantsNullListNoCrash() {
        assertDoesNotThrow(() -> shelterList.addOccupants(shelterA, null));
    }
}