package com.model;
/*
 * Used AI to assist with some test implementation.
 *
 * Test                                 | Reasoning
 * -------------------------------------|--------------------
 * testGetUsersSize()                   | This test checks that the user list is properly adjusting and reflecting the correct number of users that are added to the userList
 * testGetUsersSizeIncrease()           | This test checks that when a new user is added the size of the array increases correctly
 * testGetRemoveUser()                  | This test checks that when a user is removed, the size of the user list decreases properly 
 * testGetUsersSizeZero()               | This test checks that the size of the user list is zero when the list is cleared and no users are listed
 * testGetUsersNotNull()                | This test checks that the userList returns a list of users and not null even when the array list contains no users since it is not null, rather it is empty
 * testLoadingNullUser()                | This test checks that when a user is loaded in with all null properties they are not added to the user list
 * testLoadingEmptyUser()               | This test checks that when a user is loaded in with empty data they are not added to the user list
 * testLoadingEmptyUsersName()          | This test checks that when a user is loaded in with an empty first and last name they are not added to the user list
 * testGetUsersInvalidUserType()        | This test checks that the user type is correctly loaded and set correctly
 * testGetUsersMissingFile()            | This test checks that the missing file returns null and prints a try-catch exception
 * testGetUsersMalformedJSON()          | This test checks that if the JSON file is incorrectly formatted and can't be read that it will return null and print a try-catch exception
 * testGetUsersRegularUserInstance()    | This test checks that the user is set to a user if they aren't specified to be a volunteer so that the users don't end up with volunteer attributes
 * testGetUsersAdminTrue()              | This test checks that the user is correctly showing administrator status so that they know if they can give the specific user administrator privileges
 * testGetUsersAdminFalse()             | This test checks that the user isn't incorrectly showing administrator status when they shouldn't have that status so that they don't have access to administrator privileges they shouldn't have
 * testGetUsersVolunteerInstance()      | This test checks that the volunteers are identified properly and not just considered users alone because they have additional attributes and responsibilities
 * testLoadingFiveReleifRequests()      | This test checks that multiple relief requests can be loaded in from the data loader
 * testLoadingReliefRequestPartyMember()| This test checks that an individual party member is added in from the relief request
 * testLoadingEmptyReliefRequests()     | This test checks that an empty relief request is not being added into the data loader to prevent irrelevant relief requessts from piling up
 * testLoadingNullReliefRequest()       | This test checks that a null relief request is not being added into the data loader to prevent irrelevant relief requessts from piling up
 * testLoadingShelter()                 | This test checks that an individual shelter is properly added into data loader and the size is adjusted properly
 * testLoadingMultipleShelters()        | This test checks that multiple shelters can be added into the data loader and the size of shelter list is properly adjusted
 * testLoadingEmptyShelter()            | This test checks that an empty shelter is not being added into the data loader to prevent irrelevant relief requessts from piling up
 * testLoadingNullShelter()             | This test checks that a null shelter is not being added into the data loader to prevent irrelevant relief requessts from piling up
 * testLoadingNullShelterOccupants()    | This test checks that a relief requeset can still be loaded in properly without having any party members
 */
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    private UserList userList = UserList.getInstance();
    ArrayList<User> users = userList.getUsers();
    private ShelterList shelterList = ShelterList.getInstance();
    private ArrayList<Shelter> shelters = shelterList.getShelters();
    private ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
    ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();

    @BeforeEach
    public void setup() {
        userList.getUsers().clear();
        users.add(new User( "Bailey", "Bowen", "bbowen", "RelHe1p34", "bbowen@gmail.com", "02/09/2003", "594 South St", "New York", "New York", "29804", "English", true, UserType.USER));
        users.add(new User("Karley", "Long", "klong", "grIe35!", "klong@outlook.com", "07/23/1999", "437 Bull Street", "Raleigh", "North Carolina", "49739", "English", false, UserType.USER));
        DataWriter.saveUsers();
        shelterList.getShelters().clear();
        DataWriter.saveShelters();
        reliefRequestList.getReliefRequests().clear();
        DataWriter.saveReliefRequests();
    }

    @AfterEach
    public void tearDown() {
        userList.getUsers().clear();
        DataWriter.saveUsers();
        shelterList.getShelters().clear();
        DataWriter.saveShelters();
        reliefRequestList.getReliefRequests().clear();
        DataWriter.saveReliefRequests();
    }

    @Test
    void testGetUsersSize() {
        users = DataLoader.getUsers();
        assertEquals(2, users.size());
    }

    @Test
    void testGetUsersSizeIncrease() {
        users.add(new User( "Savannah", "Conner", "sconner", "FipH0;d94", "sconner@gmail.com", "08/10/2014", "8873 Garden Road", "Atlanta", "Georgia", "49739", "Spanish", false, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(3, users.size());
    }

    @Test 
    void testGetRemoveUser() {
        users.remove(1);
        DataWriter.saveUsers();
        assertEquals(1, users.size());
    }
    
    @Test
    void testGetUsersSizeZero() {
        userList.getUsers().clear();
        DataWriter.saveUsers();
        users = DataLoader.getUsers();
        assertEquals(0, users.size());
    }

    @Test 
    void testGetUsersNotNull() {
        users = DataLoader.getUsers();
        assertNotNull(userList);
    }

    @Test
    public void testLoadingEmptyUser() {
        users.add(new User("", "", "", "", "", "", "", "", "", "", "", true, UserType.USER));
		DataWriter.saveUsers();
		assertEquals(2, DataLoader.getUsers().size());
    }

    @Test
    public void testLoadingNullUser() {
        users.add(new User(null, null, null, null, null, null, null, null, null, null, null, true, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(2, DataLoader.getUsers().size());
    }

    @Test
    public void testLoadingEmptyUsersName() {
        users.add(new User( "", "", "sconner", "FipH0;d94", "sconner@gmail.com", "08/10/2014", "8873 Garden Road", "Atlanta", "Georgia", "49739", "Spanish", false, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(2, DataLoader.getUsers().size());
    }

    @Test 
    void testGetUsersVolunteerInstance() {
        users.add(new Volunteer( "Emmie", "Grand", "egrand", "listyFRI93", "egrand@gmail.com", "01/12/2018", "98 Crest Street", "Atlanta", "Georgia", "49739", "English", true, UserType.VOLUNTEER, Skills.WOUND_CARE, true, true, false));
        users = DataLoader.getUsers();
        assertTrue(users.get(0) instanceof Volunteer);
    }

    @Test 
    void testGetUsersRegularUserInstance() {
        users = DataLoader.getUsers();
        assertTrue(users.get(0) instanceof User);
    }

    @Test 
    void testGetUsersAdminTrue() {
        users = DataLoader.getUsers();
        assertTrue(users.get(0).isAdmin());
    }

    @Test 
    void testGetUsersAdminFalse() {
        users = DataLoader.getUsers();
        assertFalse(users.get(1).isAdmin());
    }

    @Test 
    void testGetUsersInvalidUserType() {
        users = DataLoader.getUsers();
        assertNull(userList);
    }

    @Test 
    void testGetUsersMissingFile() {
        users = DataLoader.getUsers();
        assertNull(userList);
    }

    @Test 
    void testGetUsersMalformedJSON() {
        users = DataLoader.getUsers();
        assertNull(userList);
    }

    // influenced by Abagail's code in data writer
    @Test 
    void testLoadingFiveReliefRequests() {
        ArrayList<PartyMember> partyMembers = new ArrayList<PartyMember>();
        partyMembers.add(new PartyMember("Bella", "2024", true, Being.DOG));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "102 Main St", "Charleston", "South Carolina", "29477", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 20, true, true, Skills.TRANSPORTATION));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "103 Main St", "Charleston", "South Carolina", "29218", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 15, true, true, Skills.CPR));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "104 Main St", "Charleston", "South Carolina", "29218", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 10, true, true, Skills.SEARCH_AND_RESCUE));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "105 Main St", "Charleston", "South Carolina", "29218", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 5, true, true, Skills.CPR));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "106 Main St", "Charleston", "South Carolina", "29218", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 12, true, true, Skills.CPR));
        DataWriter.saveReliefRequests();
        reliefRequests = DataLoader.getReliefRequests();
        assertEquals(5, reliefRequests.size());
    }

    @Test
    public void testLoadingReliefRequestPartyMemeber() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.IN_PROGRESS, 15, true, false, Skills.FLOOD_RESCUE));
        DataWriter.saveReliefRequests();
        assertEquals("Brock", reliefRequests.get(0).getPartyMembers().get(0).getName());
    }

    @Test
    public void testLoadingEmptyReliefRequests() {
        DataWriter.saveReliefRequests();
        reliefRequests = DataLoader.getReliefRequests();
        assertEquals(0, reliefRequests.size());
    }

    @Test 
    public void testLoadingNullReliefRequest() {
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), null, null, null, null, false, false, false, ReliefRequestStatus.SUBMITTED, 5, false, false, Skills.CPR));
        DataWriter.saveReliefRequests();
        assertEquals(0, DataLoader.getReliefRequests().size());
    }

    @Test 
    public void testLoadingShelter() {
        ArrayList<PartyMember> occupants = new ArrayList<PartyMember>();
        occupants.add(new PartyMember("Karmyn Gray", "2000", true, Being.PERSON));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "109 Flound Street", "Columbia", "South Carolina", "27645", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "Large red brick building", 9, 12, occupants, true));
        DataWriter.saveShelters();
        assertEquals(1, DataLoader.getShelters().size());
    }   
    
    @Test 
    public void testLoadingMultipleShelters() {
        ArrayList<PartyMember> occupants = new ArrayList<PartyMember>();
        occupants.add(new PartyMember("Karmyn Gray", "2000", true, Being.PERSON));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "210 Main Street", "Greenville", "South Carolina", "29601", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "Small blue building", 8, 10, occupants, true));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "315 Oak Street", "Charleston", "South Carolina", "29401", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "White building with a parking lot", 10, 15, occupants, true));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "420 Pine Avenue", "Columbia", "South Carolina", "29201", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "Two-story tan brick building", 12, 20, occupants, true));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "525 River Road", "Spartanburg", "South Carolina", "29302", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "Large gray building near the river", 7, 9, occupants, true));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "109 Flound Street", "Columbia", "South Carolina", "27645", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "Large red brick building", 9, 12, occupants, true));
        DataWriter.saveShelters();
        assertEquals(5, DataLoader.getShelters().size());
    }

    @Test
    public void testLoadingEmptyShelter() {
        DataWriter.saveShelters();
        shelters = DataLoader.getShelters();
        assertEquals(0, shelters.size());
    }

    @Test 
    public void testLoadingNullShelter() {
        ArrayList<PartyMember> occupants = new ArrayList<PartyMember>();
        occupants.add(new PartyMember(null, null, true, Being.PERSON));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, null, null, null, null, new ArrayList<ShelterResources>(), ShelterStatus.OPEN, null, 9, 12, occupants, true));
        DataWriter.saveShelters();
        assertEquals(0, DataLoader.getShelters().size());
    }

    @Test 
    public void testLoadingNullShelterOccupants() {
        ArrayList<PartyMember> occupants = new ArrayList<PartyMember>();
        occupants.add(new PartyMember(null, null, true, Being.PERSON));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "109 Flound Street", "Columbia", "South Carolina", "27645", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, "Large red brick building", 9, 12, occupants, true));
        DataWriter.saveShelters();
        assertEquals(1, DataLoader.getShelters().size());
    }
}
