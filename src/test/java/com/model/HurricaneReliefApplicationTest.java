package com.model;

/*
 * Tests for the HurricaneReliefApplication facade (Chance Price) - everything that works without the map API.
 * Most facade methods just hand the call to UserList, ShelterList or ReliefRequestList, and the detailed
 * error cases for those live in UserListTest, ShelterListTest and ReliefRequestListTest. This class tests
 * the logic the facade adds itself (login/logout, current user, acceptRequest, seeNearbyShelters) plus
 * one test per pass-through method to prove the facade is wired to the right list.
 * Used AI to assist with test implementation.
 * were worked out from the HurricaneReliefApplication code.
 *
 * Setup:  users klong (regular user, password Karley#2024, zip 29201) and bbowen (CPR volunteer, RelHe1p34!)
 *         shelter A = 1100 Gervais St 29201, OPEN, 4 of 10     shelter B = 9 Oak Ave 29205, OPEN, 0 of 5
 *         request R1 = submitted by klong at 437 Bull Street, needs CPR
 *
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | Test                                           | Reasoning                                                                 |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 * | testCreateAccountValidReturnsTrue              | a new user with valid information can sign up through the app             |
 * | testCreateAccountDuplicateReturnsFalse         | each username has to be unique                                            |
 * | testLoginValidReturnsTrue                      | the correct username and password log the user in                         |
 * | testLoginValidSetsCurrentUser                  | the app has to remember who is logged in                                  |
 * | testLoginWrongPasswordReturnsFalse             | a wrong password is rejected                                              |
 * | testLoginWrongPasswordNoCurrentUser            | a failed login must not log anyone in                                     |
 * | testLoginPasswordIsCaseSensitive               | passwords are case sensitive                                              |
 * | testLoginUnknownUserReturnsFalse               | a user that doesn't exist fails cleanly instead of crashing               |
 * | testLoginNullUsernameReturnsFalse              | a null username should not crash login                                    |
 * | testLogoutReturnsTrue                          | logout reports success                                                    |
 * | testLogoutClearsCurrentUser                    | after logging out nobody is logged in                                     |
 * | testLogoutWhenNotLoggedInReturnsTrue           | logging out twice should not break anything                               |
 * | testGetUserExisting                            | the app can look up a user by username                                    |
 * | testGetUsersCount                              | the app returns every user in the system                                  |
 * | testAddUserThroughAppIsStored                  | a user added through the app's addUser can then be looked up              |
 * | testCreateReliefRequestUsesUserZip             | a request made through the app is placed at the user's address            |
 * | testAddPartyMemberThroughApp                   | party members added through the app are stored on the request             |
 * | testAcceptRequestVolunteerMessage              | a logged in volunteer can accept a submitted request                      |
 * | testAcceptRequestSetsInProgress                | an accepted request moves to IN_PROGRESS                                  |
 * | testAcceptRequestRecordsVolunteer              | the request has to remember which volunteer accepted it                   |
 * | testAcceptRequestRegularUserRejected           | only volunteers can accept requests                                       |
 * | testAcceptRequestNotLoggedInRejected           | nobody can accept a request without logging in                            |
 * | testAcceptRequestUnknownIdRejected             | accepting a request that doesn't exist gives a not found message          |
 * | testAcceptRequestTwiceRejected                 | two volunteers cannot accept the same request                             |
 * | testMarkCompleteAdminSetsCompleted             | an admin can mark a request complete through the app                      |
 * | testMarkCompleteNonAdminRejected               | non-admins cannot mark a request complete (requirement A06)               |
 * | testGetReliefRequestFound                      | the app can find a request by address and submitter name                  |
 * | testGetShelterFound                            | the app can find a shelter by its address                                 |
 * | testGetSheltersByZip                           | the app can list the shelters in a zip code                               |
 * | testSeeNearbySheltersLoggedIn                  | nearby shelters are found using the logged in user's zip code             |
 * | testSeeNearbySheltersNotLoggedInReturnsEmpty   | with nobody logged in there is no location, so the list is empty          |
 * | testSetNumberOfOccupantsAdmin                  | an admin can update a shelter's occupant count through the app            |
 * | testAddShelterOccupantsThroughApp              | occupants added through the app are stored on the shelter                 |
 * | testAssignRequestNoShelterReturnsFalse         | a request cannot be assigned when no shelter has room (no map call)       |
 * | testClosestMatchingShelterNoMatchReturnsNull   | when no shelter has room there is no closest shelter (no map call)        |
 * +------------------------------------------------+---------------------------------------------------------------------------+
 *
 * Not tested: write() (creates a .txt file, excluded by the
 * assignment), and save() (writes the real json_files folder until DataWriter uses the JUnit test paths).
 * The map-dependent methods are tested in HurricaneReliefApplicationMapTest.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HurricaneReliefApplicationTest {
    private ArrayList<User> users = UserList.getInstance().getUsers();
    private ArrayList<Shelter> shelters = ShelterList.getInstance().getShelters();
    private ArrayList<ReliefRequest> requests = ReliefRequestList.getInstance().getReliefRequests();

    private HurricaneReliefApplication app;
    private Volunteer bailey;
    private Shelter shelterA;
    private ReliefRequest r1;

    /**
     * Every test starts logged out with the same users, shelters and request in memory.
     * Nothing is saved to the json files.
     */
    @BeforeEach
    public void setup() {
        users.clear();
        shelters.clear();
        requests.clear();

        User karley = new User("Karley", "Long", "klong", "Karley#2024", "klong@outlook.com", "07/23/1999",
                "437 Bull Street", "Columbia", "SC", "29201", "English", false, UserType.USER);
        bailey = new Volunteer("Bailey", "Bowen", "bbowen", "RelHe1p34!", "bbowen@gmail.com", "02/09/2003",
                "594 South St", "Columbia", "SC", "29201", "English", false, UserType.VOLUNTEER,
                Skills.CPR, true, false, false);
        users.add(karley);
        users.add(bailey);

        shelterA = new Shelter(ShelterType.SCHOOL, "1100 Gervais St", "Columbia", "SC", "29201",
                new ArrayList<ShelterResources>(Arrays.asList(ShelterResources.FOOD, ShelterResources.PET_FRIENDLY)),
                ShelterStatus.OPEN, "Brick school", 4, 10, true);
        shelters.add(shelterA);
        shelters.add(new Shelter(ShelterType.HOUSE, "9 Oak Ave", "Columbia", "SC", "29205",
                new ArrayList<ShelterResources>(Arrays.asList(ShelterResources.FOOD)),
                ShelterStatus.OPEN, "White house", 0, 5, false));

        app = new HurricaneReliefApplication();
        r1 = app.createReliefRequest(karley, true, false, false, false, false, Skills.CPR);
    }

    @AfterEach
    public void tearDown() {
        app.logout();
        users.clear();
        shelters.clear();
        requests.clear();
    }

    // ---------- createAccount ----------

    @Test
    public void testCreateAccountValidReturnsTrue() {
        assertTrue(app.createAccount("John", "Math", "jmath", "Relief#2026", "jmath@gmail.com", "03/14/2001",
                "12 Pine Rd", "Columbia", "SC", "29205", "English", false, UserType.USER));
    }

    @Test
    public void testCreateAccountDuplicateReturnsFalse() {
        assertFalse(app.createAccount("Kara", "Long", "klong", "Relief#2026", "kara@gmail.com", "03/14/2001",
                "12 Pine Rd", "Columbia", "SC", "29205", "English", false, UserType.USER));
    }

    // ---------- login / logout ----------

    @Test
    public void testLoginValidReturnsTrue() {
        assertTrue(app.login("klong", "Karley#2024"));
    }

    @Test
    public void testLoginValidSetsCurrentUser() {
        app.login("klong", "Karley#2024");
        assertEquals("klong", app.getCurrentUser().getUsername());
    }

    @Test
    public void testLoginWrongPasswordReturnsFalse() {
        assertFalse(app.login("klong", "Wrong#2024"));
    }

    @Test
    public void testLoginWrongPasswordNoCurrentUser() {
        app.login("klong", "Wrong#2024");
        assertNull(app.getCurrentUser());
    }

    @Test
    public void testLoginPasswordIsCaseSensitive() {
        assertFalse(app.login("klong", "KARLEY#2024"));
    }

    @Test
    public void testLoginUnknownUserReturnsFalse() {
        assertFalse(app.login("bross", "Karley#2024"));
    }

    @Test
    public void testLoginNullUsernameReturnsFalse() {
        assertFalse(app.login(null, "Karley#2024"));
    }

    @Test
    public void testLogoutReturnsTrue() {
        app.login("klong", "Karley#2024");
        assertTrue(app.logout());
    }

    @Test
    public void testLogoutClearsCurrentUser() {
        app.login("klong", "Karley#2024");
        app.logout();
        assertNull(app.getCurrentUser());
    }

    @Test
    public void testLogoutWhenNotLoggedInReturnsTrue() {
        assertTrue(app.logout());
    }

    // ---------- getUser ----------

    @Test
    public void testGetUserExisting() {
        assertEquals("Bowen", app.getUser("bbowen").getLastName());
    }

    @Test
    public void testGetUsersCount() {
        assertEquals(2, app.getUsers().size());
    }

    @Test
    public void testAddUserThroughAppIsStored() {
        app.addUser("John", "Math", "jmath", "Relief#2026", "jmath@gmail.com", "03/14/2001",
                "12 Pine Rd", "Columbia", "SC", "29205", "English", false, UserType.USER);
        assertEquals("Math", app.getUser("jmath").getLastName());
    }

    // ---------- relief requests ----------

    @Test
    public void testCreateReliefRequestUsesUserZip() {
        assertEquals("29201", r1.getZipCode());
    }

    @Test
    public void testAddPartyMemberThroughApp() {
        app.addPartyMember(r1, "Max Long", "2015", false, Being.PERSON);
        assertEquals(1, r1.getPartyMembers().size());
    }

    // ---------- acceptRequest ----------

    @Test
    public void testAcceptRequestVolunteerMessage() {
        app.login("bbowen", "RelHe1p34!");
        assertEquals("Relief request accepted.", app.acceptRequest(r1.getUuid()));
    }

    @Test
    public void testAcceptRequestSetsInProgress() {
        app.login("bbowen", "RelHe1p34!");
        app.acceptRequest(r1.getUuid());
        assertEquals(ReliefRequestStatus.IN_PROGRESS, r1.getStatus());
    }

    @Test
    public void testAcceptRequestRecordsVolunteer() {
        app.login("bbowen", "RelHe1p34!");
        app.acceptRequest(r1.getUuid());
        assertEquals(bailey.getUuid(), r1.getAssignedVolunteerID());
    }

    @Test
    public void testAcceptRequestRegularUserRejected() {
        app.login("klong", "Karley#2024");
        assertEquals("Only volunteers can accept relief requests.", app.acceptRequest(r1.getUuid()));
    }

    @Test
    public void testAcceptRequestNotLoggedInRejected() {
        assertEquals("Only volunteers can accept relief requests.", app.acceptRequest(r1.getUuid()));
    }

    @Test
    public void testAcceptRequestUnknownIdRejected() {
        app.login("bbowen", "RelHe1p34!");
        assertEquals("Relief request not found.", app.acceptRequest(UUID.randomUUID()));
    }

    @Test
    public void testAcceptRequestTwiceRejected() {
        app.login("bbowen", "RelHe1p34!");
        app.acceptRequest(r1.getUuid());
        assertEquals("This relief request has already been accepted.", app.acceptRequest(r1.getUuid()));
    }

    // ---------- markCompleteRequest ----------

    @Test
    public void testMarkCompleteAdminSetsCompleted() {
        app.markCompleteRequest(r1, true);
        assertEquals(ReliefRequestStatus.COMPLETED, r1.getStatus());
    }

    @Test
    public void testMarkCompleteNonAdminRejected() {
        assertEquals("Error: only admin can mark request as completed!", app.markCompleteRequest(r1, false));
    }

    // ---------- lookups ----------

    @Test
    public void testGetReliefRequestFound() {
        assertSame(r1, app.getReliefRequest("437 Bull Street", "Columbia", "SC", "29201", "Karley", "Long"));
    }

    @Test
    public void testGetShelterFound() {
        assertSame(shelterA, app.getShelter("1100 Gervais St", "Columbia", "SC", "29201"));
    }

    @Test
    public void testGetSheltersByZip() {
        assertEquals(1, app.getSheltersByZip("29201").size());
    }

    @Test
    public void testSeeNearbySheltersLoggedIn() {
        app.login("klong", "Karley#2024");
        assertTrue(app.seeNearbyShelters().contains(shelterA));
    }

    @Test
    public void testSeeNearbySheltersNotLoggedInReturnsEmpty() {
        assertTrue(app.seeNearbyShelters().isEmpty());
    }

    // ---------- shelters ----------

    @Test
    public void testSetNumberOfOccupantsAdmin() {
        app.setNumberOfOccupants(shelterA, 6, true);
        assertEquals(6, shelterA.getNumberOfOccupants());
    }

    @Test
    public void testAddShelterOccupantsThroughApp() {
        ArrayList<PartyMember> group = new ArrayList<PartyMember>();
        group.add(new PartyMember("Max Long", "2015", false, Being.PERSON));
        app.addShelterOccupants(shelterA, group);
        assertEquals(1, shelterA.getOccupants().size());
    }

    @Test
    public void testAssignRequestNoShelterReturnsFalse() throws Exception {
        assertFalse(app.assignReliefRequest(50, r1, bailey, true));
    }

    @Test
    public void testClosestMatchingShelterNoMatchReturnsNull() throws Exception {
        assertNull(app.closestMatchingShelter(50, r1));
    }
}