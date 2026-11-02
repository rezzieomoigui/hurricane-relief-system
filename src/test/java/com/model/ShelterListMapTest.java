package com.model;

/*
 * Tests for the ShelterList methods that call the map API through MapTools (Chance Price).
 * Used AI to assist with test implementation.
 * was worked out from the ShelterList code
 * To keep the expected answers predictable, most tests leave exactly ONE shelter that matches,
 * so the result does not depend on real-world driving times. Only the "picks nearer" test
 * compares two shelters, and they are in different cities (Columbia vs. Charleston).
 *
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | Test                                           | Reasoning                                                                 |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | testClosestMatchingShelterSingleMatch          | when only one shelter matches, that shelter is the closest one            |
 * | testClosestMatchingShelterPicksNearer          | a Columbia shelter is closer to a Columbia request than a Charleston one, |
 * |                                                | even when the Charleston shelter comes first in the list                  |
 * | testAssignRequestAdminReturnsTrue              | an admin can assign a request when a shelter has room                     |
 * | testAssignRequestAdminAddsOccupants            | assigning 2 people to a shelter with 4 people should make it 6            |
 * | testAssignRequestSetsAssignedStatus            | a successfully assigned request moves to ASSIGNED                         |
 * | testAssignRequestRecordsVolunteer              | the request has to remember which volunteer is handling it                |
 * | testAssignRequestNonAdminAddsOccupants         | the method reports success for non-admins, so the shelter count must also |
 * |                                                | go up, otherwise the shelter overfills                                    |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShelterListMapTest {
    private ShelterList shelterList = ShelterList.getInstance();
    private ArrayList<Shelter> shelters = shelterList.getShelters();

    private Shelter columbiaShelter;
    private ReliefRequest request;
    private Volunteer volunteer;

    /**
     * Every test starts with one open Columbia shelter (4 of 10 occupants) and one
     * SUBMITTED request from downtown Columbia. Nothing is saved to the json files.
     */
    @BeforeEach
    public void setup() {
        String key = System.getenv("MAP_API_KEY");
        assumeTrue(key != null && !key.isBlank(), "MAP_API_KEY is not set, skipping map tests");

        shelters.clear();
        columbiaShelter = new Shelter(ShelterType.SCHOOL, "1100 Gervais St", "Columbia", "SC", "29201",
                new ArrayList<ShelterResources>(Arrays.asList(ShelterResources.FOOD)), ShelterStatus.OPEN,
                "Brick school", 4, 10, true);
        shelters.add(columbiaShelter);

        request = new ReliefRequest(UUID.randomUUID(), "1101 Lincoln St", "Columbia", "SC", "29201",
                true, true, false, ReliefRequestStatus.SUBMITTED, 0, false, false, Skills.NONE);

        volunteer = new Volunteer("Bailey", "Bowen", "bbowen", "RelHe1p34!", "bbowen@gmail.com", "02/09/2003",
                "594 South St", "Columbia", "SC", "29201", "English", false, UserType.VOLUNTEER,
                Skills.CPR, true, false, false);
    }

    @AfterEach
    public void tearDown() {
        shelters.clear();
    }

    // ---------- closestMatchingShelter ----------

    @Test
    public void testClosestMatchingShelterSingleMatch() throws Exception {
        assertSame(columbiaShelter, shelterList.closestMatchingShelter(2, request));
    }

    @Test
    public void testClosestMatchingShelterPicksNearer() throws Exception {
        shelters.add(0, new Shelter(ShelterType.COMMUNITY_CENTER, "40 Calhoun St", "Charleston", "SC", "29401",
                new ArrayList<ShelterResources>(Arrays.asList(ShelterResources.FOOD)), ShelterStatus.OPEN,
                "Church hall", 0, 50, true));
        assertSame(columbiaShelter, shelterList.closestMatchingShelter(2, request));
    }

    // ---------- assignReliefRequest (successful paths) ----------

    @Test
    public void testAssignRequestAdminReturnsTrue() throws Exception {
        assertTrue(shelterList.assignReliefRequest(2, request, volunteer, true));
    }

    @Test
    public void testAssignRequestAdminAddsOccupants() throws Exception {
        shelterList.assignReliefRequest(2, request, volunteer, true);
        assertEquals(6, columbiaShelter.getNumberOfOccupants());
    }

    @Test
    public void testAssignRequestSetsAssignedStatus() throws Exception {
        shelterList.assignReliefRequest(2, request, volunteer, true);
        assertEquals(ReliefRequestStatus.ASSIGNED, request.getStatus());
    }

    @Test
    public void testAssignRequestRecordsVolunteer() throws Exception {
        shelterList.assignReliefRequest(2, request, volunteer, true);
        assertEquals(volunteer.getUuid(), request.getAssignedVolunteerID());
    }

    @Test
    public void testAssignRequestNonAdminAddsOccupants() throws Exception {
        shelterList.assignReliefRequest(2, request, volunteer, false);
        assertEquals(6, columbiaShelter.getNumberOfOccupants());
    }
}