package com.model;

/*
 * Tests for the HurricaneReliefApplication methods that call the map API (Chance Price).
 * Used AI to assist with test implementation.
 * was worked out from the HurricaneReliefApplication code.
 *
 * Setup: one open shelter (1100 Gervais St, 4 of 10), one CPR volunteer, one submitted CPR request,
 * so there is exactly one right answer and it does not depend on real-world driving times.
 *
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | Test                                           | Reasoning                                                                 |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | testClosestMatchingShelterThroughApp           | the app finds the only shelter that has room for the request              |
 * | testAssignRequestThroughAppReturnsTrue         | an admin can assign a request to a shelter through the app                |
 * | testGetSuggestedRequestThroughApp              | a CPR volunteer is suggested the request that needs CPR                   |
 * | testRequestsToStringShowsSuggestion            | the volunteer's request screen includes the suggested request section     |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 */

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HurricaneReliefApplicationMapTest {
    private ArrayList<User> users = UserList.getInstance().getUsers();
    private ArrayList<Shelter> shelters = ShelterList.getInstance().getShelters();
    private ArrayList<ReliefRequest> requests = ReliefRequestList.getInstance().getReliefRequests();

    private HurricaneReliefApplication app;
    private Volunteer bailey;
    private Shelter shelter;
    private ReliefRequest request;

    @BeforeEach
    public void setup() {
        String key = System.getenv("MAP_API_KEY");
        assumeTrue(key != null && !key.isBlank(), "MAP_API_KEY is not set, skipping map tests");

        users.clear();
        shelters.clear();
        requests.clear();

        User karley = new User("Karley", "Long", "klong", "Karley#2024", "klong@outlook.com", "07/23/1999",
                "1101 Lincoln St", "Columbia", "SC", "29201", "English", false, UserType.USER);
        bailey = new Volunteer("Bailey", "Bowen", "bbowen", "RelHe1p34!", "bbowen@gmail.com", "02/09/2003",
                "1523 Greene St", "Columbia", "SC", "29201", "English", false, UserType.VOLUNTEER,
                Skills.CPR, true, false, false);
        users.add(karley);
        users.add(bailey);

        shelter = new Shelter(ShelterType.SCHOOL, "1100 Gervais St", "Columbia", "SC", "29201",
                new ArrayList<ShelterResources>(Arrays.asList(ShelterResources.FOOD)),
                ShelterStatus.OPEN, "Brick school", 4, 10, true);
        shelters.add(shelter);

        app = new HurricaneReliefApplication();
        request = app.createReliefRequest(karley, true, false, false, false, false, Skills.CPR);
    }

    @AfterEach
    public void tearDown() {
        users.clear();
        shelters.clear();
        requests.clear();
    }

    @Test
    public void testClosestMatchingShelterThroughApp() throws Exception {
        assertSame(shelter, app.closestMatchingShelter(2, request));
    }

    @Test
    public void testAssignRequestThroughAppReturnsTrue() throws Exception {
        assertTrue(app.assignReliefRequest(2, request, bailey, true));
    }

    @Test
    public void testGetSuggestedRequestThroughApp() throws Exception {
        assertSame(request, app.getSuggestedRequest(bailey));
    }

    @Test
    public void testRequestsToStringShowsSuggestion() throws Exception {
        assertTrue(app.requestsToString(bailey).contains("SUGGESTED REQUEST"));
    }
}