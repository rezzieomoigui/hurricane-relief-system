package com.model;

/*
 * Tests for the ReliefRequestList class (Chance Price) - everything that works without the map API.
 * getSuggestedRequest (which calls the map API) is tested in ReliefRequestListMapTest.
 * Used AI to assist with test implementation.
 * were worked out from the ReliefRequestList code.
 *
 * Setup:  users klong (Karley Long) and jdoe (Jane Doe)
 *         R1 = klong, 437 Bull Street, Columbia SC 29201, needs CPR,           SUBMITTED
 *         R2 = jdoe,  12 Pine Rd,      Columbia SC 29205, needs FLOOD_RESCUE,  IN_PROGRESS
 *
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | Test                                           | Reasoning                                                                 |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | testGetReliefRequestsNeverNull                 | the request list must never be null or every caller crashes (fix #213)    |
 * | testGetReliefRequestsCount                     | the list should hold exactly the 2 requests added in setup                |
 * | testGetByIdFound                               | a request can be looked up by its id                                      |
 * | testGetByIdUnknownReturnsNull                  | an id that isn't in the system returns null (second return path)          |
 * | testGetByIdNullReturnsNull                     | a null id should not crash the lookup                                     |
 * | testGetByAddressAndNameFound                   | a request is found when the address and the submitter's name all match    |
 * | testGetByAddressWrongFirstNameReturnsNull      | the submitter's name has to match, not just the address                   |
 * | testGetByAddressWrongStreetReturnsNull         | the address has to match, not just the submitter's name                   |
 * | testGetByAddressWrongCityReturnsNull           | the city has to match (each part of the && check is tested failing)       |
 * | testGetByAddressWrongStateReturnsNull          | the state has to match                                                    |
 * | testGetByAddressWrongZipReturnsNull            | the zip code has to match                                                 |
 * | testGetByAddressWrongLastNameReturnsNull       | the submitter's last name has to match, not just the first name           |
 * | testGetByAddressSkipsRequestWithDeletedUser    | one request whose user no longer exists must not crash every search       |
 * | testAddRequestForUserUsesUserZip               | a new request is placed at the submitting user's address                  |
 * | testAddRequestForUserStatusSubmitted           | every new request starts out SUBMITTED                                    |
 * | testAddRequestForUserKeepsSkill                | the skill needed is used to match the request to a volunteer              |
 * | testAddRequestForUserIncreasesCount            | the new request actually has to be stored in the list                     |
 * | testAddRequestForNullUserReturnsNull           | a request with no user should be rejected instead of crashing             |
 * | testAddRequestObjectValidReturnsTrue           | a new request object is accepted                                          |
 * | testAddRequestObjectIncreasesCount             | the new request object actually has to be stored in the list              |
 * | testAddRequestObjectNullReturnsFalse           | a null request is rejected                                                |
 * | testAddRequestObjectDuplicateReturnsFalse      | the same request (same id) cannot be added twice                          |
 * | testRemoveRequestExistingReturnsTrue           | a request in the list can be removed                                      |
 * | testRemoveRequestDecreasesCount                | the removed request is actually gone from the list                        |
 * | testRemoveRequestNotInListReturnsFalse         | a request that was never added cannot be removed                          |
 * | testRemoveRequestNullReturnsFalse              | removing null is rejected                                                 |
 * | testAddPartyMemberOne                          | adding one party member gives the request one member                      |
 * | testAddPartyMemberTwo                          | a request can hold several party members                                  |
 * | testAddPartyMemberStoresType                   | pets are stored as party members with their type                          |
 * | testAddPartyMemberNullRequestNoCrash           | a null request should be ignored instead of crashing                      |
 * | testMarkCompleteAdminMessage                   | an admin gets the success message                                         |
 * | testMarkCompleteAdminSetsCompleted             | an admin marking a request complete changes its status to COMPLETED       |
 * | testMarkCompleteNonAdminMessage                | only admins can mark requests complete (requirement A06)                  |
 * | testMarkCompleteNonAdminStatusUnchanged        | a rejected attempt must leave the request SUBMITTED                       |
 * | testMarkCompleteNullRequestNoCrash             | a null request should be rejected instead of crashing                     |
 * | testToStringIncludesSubmittedRequest           | open (SUBMITTED) requests are listed for volunteers                       |
 * | testToStringExcludesInProgressRequest          | requests someone is already working on are not listed                     |
 * | testToStringEmptyListIsEmpty                   | with no requests the loop runs zero times and nothing is listed           |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 *
 * Not tested yet: save(). It writes the real json_files folder until DataWriter uses the
 * JUnit test paths, so running it now would overwrite the team's real data.
 */

import java.util.ArrayList;
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

public class ReliefRequestListTest {
    private ReliefRequestList requestList = ReliefRequestList.getInstance();
    private ArrayList<ReliefRequest> requests = requestList.getReliefRequests();
    private ArrayList<User> users = UserList.getInstance().getUsers();

    private User karley;
    private User jane;
    private ReliefRequest r1;
    private ReliefRequest r2;

    /**
     * Every test starts with the same two users and two relief requests in memory.
     * Nothing is saved to the json files.
     */
    @BeforeEach
    public void setup() {
        users.clear();
        requests.clear();
        karley = new User("Karley", "Long", "klong", "Karley#2024", "klong@outlook.com", "07/23/1999",
                "437 Bull Street", "Columbia", "SC", "29201", "English", false, UserType.USER);
        jane = new User("Jane", "Doe", "jdoe", "JaneDoe#99", "jdoe@gmail.com", "01/15/1988",
                "12 Pine Rd", "Columbia", "SC", "29205", "English", false, UserType.USER);
        users.add(karley);
        users.add(jane);

        r1 = new ReliefRequest(karley.getUuid(), "437 Bull Street", "Columbia", "SC", "29201",
                true, false, false, ReliefRequestStatus.SUBMITTED, 0, false, false, Skills.CPR);
        r2 = new ReliefRequest(jane.getUuid(), "12 Pine Rd", "Columbia", "SC", "29205",
                false, false, false, ReliefRequestStatus.IN_PROGRESS, 0, false, false, Skills.FLOOD_RESCUE);
        requests.add(r1);
        requests.add(r2);
    }

    @AfterEach
    public void tearDown() {
        requests.clear();
        users.clear();
    }

    /** A request that has never been added to the list. */
    private ReliefRequest newRequest() {
        return new ReliefRequest(karley.getUuid(), "1 Lake Dr", "Columbia", "SC", "29201");
    }

    // ---------- getReliefRequests ----------

    @Test
    public void testGetReliefRequestsNeverNull() {
        assertNotNull(ReliefRequestList.getInstance().getReliefRequests());
    }

    @Test
    public void testGetReliefRequestsCount() {
        assertEquals(2, requestList.getReliefRequests().size());
    }

    // ---------- getReliefRequestById ----------

    @Test
    public void testGetByIdFound() {
        assertSame(r2, requestList.getReliefRequestById(r2.getUuid()));
    }

    @Test
    public void testGetByIdUnknownReturnsNull() {
        assertNull(requestList.getReliefRequestById(UUID.randomUUID()));
    }

    @Test
    public void testGetByIdNullReturnsNull() {
        assertNull(requestList.getReliefRequestById(null));
    }

    // ---------- getReliefRequest(address, city, state, zip, first name, last name) ----------

    @Test
    public void testGetByAddressAndNameFound() {
        assertSame(r1, requestList.getReliefRequest("437 Bull Street", "Columbia", "SC", "29201", "Karley", "Long"));
    }

    @Test
    public void testGetByAddressWrongFirstNameReturnsNull() {
        assertNull(requestList.getReliefRequest("437 Bull Street", "Columbia", "SC", "29201", "Kara", "Long"));
    }

    @Test
    public void testGetByAddressWrongStreetReturnsNull() {
        assertNull(requestList.getReliefRequest("438 Bull Street", "Columbia", "SC", "29201", "Karley", "Long"));
    }

    @Test
    public void testGetByAddressWrongCityReturnsNull() {
        assertNull(requestList.getReliefRequest("437 Bull Street", "Irmo", "SC", "29201", "Karley", "Long"));
    }

    @Test
    public void testGetByAddressWrongStateReturnsNull() {
        assertNull(requestList.getReliefRequest("437 Bull Street", "Columbia", "NC", "29201", "Karley", "Long"));
    }

    @Test
    public void testGetByAddressWrongZipReturnsNull() {
        assertNull(requestList.getReliefRequest("437 Bull Street", "Columbia", "SC", "29202", "Karley", "Long"));
    }

    @Test
    public void testGetByAddressWrongLastNameReturnsNull() {
        assertNull(requestList.getReliefRequest("437 Bull Street", "Columbia", "SC", "29201", "Karley", "Short"));
    }

    @Test
    public void testGetByAddressSkipsRequestWithDeletedUser() {
        requests.add(0, new ReliefRequest(UUID.randomUUID(), "437 Bull Street", "Columbia", "SC", "29201"));
        assertSame(r1, requestList.getReliefRequest("437 Bull Street", "Columbia", "SC", "29201", "Karley", "Long"));
    }

    // ---------- addReliefRequest(User, ...) ----------

    @Test
    public void testAddRequestForUserUsesUserZip() {
        ReliefRequest added = requestList.addReliefRequest(jane, true, true, false, false, true, Skills.WOUND_CARE);
        assertEquals("29205", added.getZipCode());
    }

    @Test
    public void testAddRequestForUserStatusSubmitted() {
        ReliefRequest added = requestList.addReliefRequest(jane, true, true, false, false, true, Skills.WOUND_CARE);
        assertEquals(ReliefRequestStatus.SUBMITTED, added.getStatus());
    }

    @Test
    public void testAddRequestForUserKeepsSkill() {
        ReliefRequest added = requestList.addReliefRequest(jane, true, true, false, false, true, Skills.WOUND_CARE);
        assertEquals(Skills.WOUND_CARE, added.getSkillsNeed());
    }

    @Test
    public void testAddRequestForUserIncreasesCount() {
        requestList.addReliefRequest(jane, true, true, false, false, true, Skills.WOUND_CARE);
        assertEquals(3, requests.size());
    }

    @Test
    public void testAddRequestForNullUserReturnsNull() {
        assertNull(requestList.addReliefRequest(null, true, true, false, false, true, Skills.WOUND_CARE));
    }

    // ---------- addReliefRequest(ReliefRequest) ----------

    @Test
    public void testAddRequestObjectValidReturnsTrue() {
        assertTrue(requestList.addReliefRequest(newRequest()));
    }

    @Test
    public void testAddRequestObjectIncreasesCount() {
        requestList.addReliefRequest(newRequest());
        assertEquals(3, requests.size());
    }

    @Test
    public void testAddRequestObjectNullReturnsFalse() {
        assertFalse(requestList.addReliefRequest((ReliefRequest) null));
    }

    @Test
    public void testAddRequestObjectDuplicateReturnsFalse() {
        assertFalse(requestList.addReliefRequest(r1));
    }

    // ---------- removeReliefRequest ----------

    @Test
    public void testRemoveRequestExistingReturnsTrue() {
        assertTrue(requestList.removeReliefRequest(r1));
    }

    @Test
    public void testRemoveRequestDecreasesCount() {
        requestList.removeReliefRequest(r1);
        assertEquals(1, requests.size());
    }

    @Test
    public void testRemoveRequestNotInListReturnsFalse() {
        assertFalse(requestList.removeReliefRequest(newRequest()));
    }

    @Test
    public void testRemoveRequestNullReturnsFalse() {
        assertFalse(requestList.removeReliefRequest(null));
    }

    // ---------- addPartyMember ----------

    @Test
    public void testAddPartyMemberOne() {
        requestList.addPartyMember(r1, "Max Long", "2015", false, Being.PERSON);
        assertEquals(1, r1.getPartyMembers().size());
    }

    @Test
    public void testAddPartyMemberTwo() {
        requestList.addPartyMember(r1, "Max Long", "2015", false, Being.PERSON);
        requestList.addPartyMember(r1, "Whiskers", "2020", false, Being.CAT);
        assertEquals(2, r1.getPartyMembers().size());
    }

    @Test
    public void testAddPartyMemberStoresType() {
        requestList.addPartyMember(r1, "Whiskers", "2020", false, Being.CAT);
        assertEquals(Being.CAT, r1.getPartyMembers().get(0).getType());
    }

    @Test
    public void testAddPartyMemberNullRequestNoCrash() {
        assertDoesNotThrow(() -> requestList.addPartyMember(null, "Max Long", "2015", false, Being.PERSON));
    }

    // ---------- markCompleteRequest ----------

    @Test
    public void testMarkCompleteAdminMessage() {
        assertEquals("Request marked complete", requestList.markCompleteRequest(r1, true));
    }

    @Test
    public void testMarkCompleteAdminSetsCompleted() {
        requestList.markCompleteRequest(r1, true);
        assertEquals(ReliefRequestStatus.COMPLETED, r1.getStatus());
    }

    @Test
    public void testMarkCompleteNonAdminMessage() {
        assertEquals("Error: only admin can mark request as completed!", requestList.markCompleteRequest(r1, false));
    }

    @Test
    public void testMarkCompleteNonAdminStatusUnchanged() {
        requestList.markCompleteRequest(r1, false);
        assertEquals(ReliefRequestStatus.SUBMITTED, r1.getStatus());
    }

    @Test
    public void testMarkCompleteNullRequestNoCrash() {
        assertDoesNotThrow(() -> requestList.markCompleteRequest(null, true));
    }

    // ---------- toString ----------

    @Test
    public void testToStringIncludesSubmittedRequest() {
        assertTrue(requestList.toString().contains("437 Bull Street"));
    }

    @Test
    public void testToStringExcludesInProgressRequest() {
        assertFalse(requestList.toString().contains("12 Pine Rd"));
    }

    @Test
    public void testToStringEmptyListIsEmpty() {
        requests.clear();
        assertEquals("", requestList.toString());
    }
}