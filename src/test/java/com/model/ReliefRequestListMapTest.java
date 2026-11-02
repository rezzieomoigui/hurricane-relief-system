package com.model;

/*
 * Tests for ReliefRequestList.getSuggestedRequest, which calls the map API through MapTools (Chance Price).
 * Used AI to assist with test implementation.
 * was worked out from the ReliefRequestList code.
 *
 * To keep the expected answers predictable, each test leaves exactly one request that should be
 * suggested, so the result does not depend on real-world driving times.
 *
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | Test                                           | Reasoning                                                                 |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | testSuggestedRequestMatchesVolunteerSkill      | a CPR volunteer should be sent to the request that needs CPR              |
 * | testSuggestedRequestNoSkillMatchFallsBack      | when no request needs the volunteer's skill, any request is suggested     |
 * | testSuggestedRequestEmptyListReturnsNull       | with no requests at all there is nothing to suggest                       |
 * | testSuggestedRequestSkipsInProgressRequest     | a request someone is already working on should not be suggested again     |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 */

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReliefRequestListMapTest {
    private ReliefRequestList requestList = ReliefRequestList.getInstance();
    private ArrayList<ReliefRequest> requests = requestList.getReliefRequests();

    private ReliefRequest cprRequest;
    private ReliefRequest floodRequestInProgress;

    @BeforeEach
    public void setup() {
        String key = System.getenv("MAP_API_KEY");
        assumeTrue(key != null && !key.isBlank(), "MAP_API_KEY is not set, skipping map tests");

        requests.clear();
        cprRequest = new ReliefRequest(java.util.UUID.randomUUID(), "1101 Lincoln St", "Columbia", "SC", "29201",
                true, false, false, ReliefRequestStatus.SUBMITTED, 0, false, false, Skills.CPR);
        floodRequestInProgress = new ReliefRequest(java.util.UUID.randomUUID(), "1100 Gervais St", "Columbia", "SC",
                "29201", false, false, false, ReliefRequestStatus.IN_PROGRESS, 0, false, false, Skills.FLOOD_RESCUE);
    }

    @AfterEach
    public void tearDown() {
        requests.clear();
    }

    private Volunteer volunteerWithSkill(Skills skill) {
        return new Volunteer("Bailey", "Bowen", "bbowen", "RelHe1p34!", "bbowen@gmail.com", "02/09/2003",
                "1523 Greene St", "Columbia", "SC", "29201", "English", false, UserType.VOLUNTEER,
                skill, true, false, false);
    }

    @Test
    public void testSuggestedRequestMatchesVolunteerSkill() throws Exception {
        requests.add(floodRequestInProgress);
        requests.add(cprRequest);
        assertSame(cprRequest, requestList.getSuggestedRequest(volunteerWithSkill(Skills.CPR)));
    }

    @Test
    public void testSuggestedRequestNoSkillMatchFallsBack() throws Exception {
        requests.add(cprRequest);
        assertSame(cprRequest, requestList.getSuggestedRequest(volunteerWithSkill(Skills.TRANSPORTATION)));
    }

    @Test
    public void testSuggestedRequestEmptyListReturnsNull() throws Exception {
        assertNull(requestList.getSuggestedRequest(volunteerWithSkill(Skills.CPR)));
    }

    @Test
    public void testSuggestedRequestSkipsInProgressRequest() throws Exception {
        requests.add(cprRequest);
        requests.add(floodRequestInProgress);
        assertNotSame(floodRequestInProgress, requestList.getSuggestedRequest(volunteerWithSkill(Skills.FLOOD_RESCUE)));
    }
}