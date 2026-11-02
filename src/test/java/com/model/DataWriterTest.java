package com.model;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private ShelterList shelterList = ShelterList.getInstance();
    private ArrayList<Shelter> shelters = shelterList.getShelters();
    private ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
    ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();

    @BeforeEach
    public void setup() {
        userList.getUsers().clear();
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
    public void testWritingUsers() {
        users = DataLoader.getUsers();
		assertEquals(0, users.size());
    }

    @Test
    public void testWritingOneUser() {
        users.add(new User("Kate", "Blyth", "kblyth", "antlerPush12", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
		assertEquals("kblyth", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    public void testWritingFiveUsers() {
        users.add(new User("Kate", "Blyth", "kblyth", "antlerPush12", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("John", "Blyth", "jblyth", "88blindSnake12", "jblyth@gmail.com", "12-09-1998", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("Sam", "Blyth", "sblyth", "oldShoeBloom12", "sblyth@gmail.com", "08-09-1964", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("Susan", "Johnson", "sjohnson", "windingRoad98", "sjohn@gmail.com", "02-12-2001", "45 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("Marcilla", "Johnson", "mjohnson", "blueSky8", "mj@gmail.com", "02-28-2000", "45 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        DataWriter.saveUsers();
        assertEquals("mjohnson", DataLoader.getUsers().get(4).getUsername());
    }

    @Test
    public void testWritingEmptyUser() {
        users.add(new User("", "", "", "", "", "", "", "", "", "", "", true, UserType.USER));
		DataWriter.saveUsers();
		assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testWritingNullUser() {
        users.add(new User(null, null, null, null, null, null, null, null, null, null, null, true, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testWritingUserWithUUID() {
        UUID uuid = UUID.randomUUID();
        users.add(new User(uuid, "Kate", "Blyth", "kblyth", "antlerPush12", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        DataWriter.saveUsers();
		assertEquals("kblyth", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    public void testWritingFiveUsersWithUUID() {
        users.add(new User(UUID.randomUUID(), "Kate", "Blyth", "kblyth", "antlerPush12", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User(UUID.randomUUID(), "John", "Blyth", "jblyth", "88blindSnake12", "jblyth@gmail.com", "12-09-1998", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User(UUID.randomUUID(), "Sam", "Blyth", "sblyth", "oldShoeBloom12", "sblyth@gmail.com", "08-09-1964", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User(UUID.randomUUID(), "Susan", "Johnson", "sjohnson", "windingRoad98", "sjohn@gmail.com", "02-12-2001", "45 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User(UUID.randomUUID(), "Marcilla", "Johnson", "mjohnson", "blueSky8", "mj@gmail.com", "02-28-2000", "45 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        DataWriter.saveUsers();
		assertEquals("mjohnson", DataLoader.getUsers().get(4).getUsername());
    }

    @Test
    public void testWritingShelters() {
        shelters = DataLoader.getShelters();
		assertEquals(0, shelters.size());
    }

    @Test
    public void testWritingOneShelter() {
        ArrayList<ShelterResources> shelterResources = new ArrayList<ShelterResources>();
        shelterResources.add(ShelterResources.FOOD);
        shelterResources.add(ShelterResources.WATER);
        shelters.add(new Shelter(ShelterType.BUSINESS, "1000 Decker Blvd", "Charleston", "South Carolina", "29121",
                   shelterResources, ShelterStatus.OPEN, "Large brick building", 16,
                   18, true));
		DataWriter.saveShelters();
		assertEquals("1000 Decker Blvd", DataLoader.getShelters().get(0).getAddress());
    }

    @Test
    public void testWritingFiveShelters() {
        ArrayList<ShelterResources> shelterResources = new ArrayList<ShelterResources>();
        shelterResources.add(ShelterResources.FOOD);
        shelterResources.add(ShelterResources.WATER);
        shelters.add(new Shelter(ShelterType.BUSINESS, "1000 Decker Blvd", "Charleston", "South Carolina", "29121",
                   shelterResources, ShelterStatus.OPEN, "Large brick building", 16,
                   18, true));
		shelters.add(new Shelter(ShelterType.COMMUNITY_CENTER, "100 Main St", "Charleston", 
                    "South Carolina", "29212", shelterResources, ShelterStatus.CLOSED, 
                    "Concrete building", 20, 40, true));
        shelters.add(new Shelter(ShelterType.HOUSE, "20 Main St", "Charleston", 
                    "South Carolina", "29212", shelterResources, ShelterStatus.OPEN, 
                    "White building with blue shutters", 5, 10, false));
        shelters.add(new Shelter(ShelterType.HOUSE, "100 Vassar St", "Charleston", 
                    "South Carolina", "29212", shelterResources, ShelterStatus.OPEN, 
                    "Brick house", 8, 12, true));
        shelters.add(new Shelter(ShelterType.SCHOOL, "10 Water St", "Charleston", 
                    "South Carolina", "29212", shelterResources, ShelterStatus.CLOSED, 
                    "Wooden schoolhouse", 10, 20, false));
        DataWriter.saveShelters();
		assertEquals("10 Water St", DataLoader.getShelters().get(4).getAddress());
    }

    @Test
    public void testWritingEmptyShelter() {
        shelters.add(new Shelter(ShelterType.HOUSE, "", "", 
                    "", "", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, 
                    "", 0, 1, false));
		DataWriter.saveShelters();
		assertEquals(0, DataLoader.getShelters().size());
    }

    @Test
    public void testWritingNullShelter() {
        shelters.add(new Shelter(ShelterType.HOUSE, null, null, 
                    null, null, new ArrayList<ShelterResources>(), ShelterStatus.OPEN, 
                    null, 0, 1, false));
		DataWriter.saveShelters();
		assertEquals(0, DataLoader.getShelters().size());
    }

    @Test
    public void testWritingShelterWithUUID() {
        ArrayList<PartyMember> occupants = new ArrayList<PartyMember>();
        occupants.add(new PartyMember("Mary Brown", "2001", false, Being.PERSON));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.HOUSE, "17 Brook Dr", "Charleston", "South Carolina", 
                    "29208", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, 
                    "Brown building", 10, 12, occupants, true));
		DataWriter.saveShelters();
		assertEquals("17 Brook Dr", DataLoader.getShelters().get(0).getAddress());
    }

    @Test
    public void testWritingFourSheltersWithUUID() {
        ArrayList<PartyMember> occupants = new ArrayList<PartyMember>();
        occupants.add(new PartyMember("Mary Brown", "2001", false, Being.PERSON));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.HOUSE, "17 Brook Dr", "Charleston", "South Carolina", 
                    "29208", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, 
                    "Brown building", 10, 12, occupants, true));
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "31 Brook Dr", "Charleston", "South Carolina", 
                    "29208", new ArrayList<ShelterResources>(), ShelterStatus.CLOSED, 
                    "Red building", 11, 15, occupants, true));
		shelters.add(new Shelter(UUID.randomUUID(), ShelterType.COMMUNITY_CENTER, "00 Main St", "Charleston", "South Carolina", 
                    "29208", new ArrayList<ShelterResources>(), ShelterStatus.OPEN, 
                    "Green building", 10, 12, occupants, true));
                    DataWriter.saveShelters();
        shelters.add(new Shelter(UUID.randomUUID(), ShelterType.BUSINESS, "72 Vassar St", "Charleston", "South Carolina", 
                    "29208", new ArrayList<ShelterResources>(), ShelterStatus.CLOSED, 
                    "Brown building", 11, 12, occupants, true));
        DataWriter.saveShelters();
		assertEquals("72 Vassar St", DataLoader.getShelters().get(3).getAddress());
    }

    @Test
    public void testMaximumShelterCapacity() {
        ArrayList<ShelterResources> shelterResources = new ArrayList<ShelterResources>();
        shelterResources.add(ShelterResources.FOOD);
        shelterResources.add(ShelterResources.WATER);
        shelters.add(new Shelter(ShelterType.BUSINESS, "1000 Decker Blvd", "Charleston", "South Carolina", "29121",
                   shelterResources, ShelterStatus.OPEN, "Large brick building", 20,
                   10, true));
		DataWriter.saveShelters();
        assertEquals(0, DataLoader.getShelters().size());
    }

    @Test
    public void testWritingReliefRequests() {
        reliefRequests = DataLoader.getReliefRequests();
		assertEquals(0, shelters.size());
    }

    @Test
    public void testWritingOneReliefRequest() {
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "52 Water St", "Charleston", "South Carolina", "29218", false, false, true, ReliefRequestStatus.SUBMITTED, 20, true, true, Skills.TRANSPORTATION));
        DataWriter.saveReliefRequests();
        assertEquals("52 Water St", DataLoader.getReliefRequests().get(0).getAddress());
    }

    @Test
    public void testWritingFiveReliefRequests() {
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "52 Water St", "Charleston", "South Carolina", "29218", 
        false, false, true, ReliefRequestStatus.SUBMITTED, 20, true, true, Skills.TRANSPORTATION));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "57 Water St", "Charleston", "South Carolina", "29218", 
        false, false, true, ReliefRequestStatus.SUBMITTED, 15, true, true, Skills.CPR));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "20 Main St", "Charleston", "South Carolina", "29218", 
        false, false, true, ReliefRequestStatus.SUBMITTED, 10, true, true, Skills.SEARCH_AND_RESCUE));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "17 Johns Island St", "Charleston", "South Carolina", "29218", 
        false, false, true, ReliefRequestStatus.SUBMITTED, 5, true, true, Skills.CPR));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "32 Blue Swan Rd", "Charleston", "South Carolina", "29218", 
        false, false, true, ReliefRequestStatus.SUBMITTED, 12, true, true, Skills.CPR));
        DataWriter.saveReliefRequests();
        assertEquals("32 Blue Swan Rd", DataLoader.getReliefRequests().get(4).getAddress());
    }

    @Test 
    public void testWritingEmptyReliefRequest() {
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "", "", "", "",
            false, false, false, ReliefRequestStatus.SUBMITTED, 5, false, false, Skills.CPR));
        DataWriter.saveReliefRequests();
        assertEquals(0, DataLoader.getReliefRequests().size());
    }

    @Test 
    public void testWritingNullReliefRequest() {
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), null, null, null, null,
            false, false, false, ReliefRequestStatus.SUBMITTED, 5, false, false, Skills.CPR));
        DataWriter.saveReliefRequests();
        assertEquals(0, DataLoader.getReliefRequests().size());
    }


    @Test 
    public void testEmergencyReliefRequestConstructor() {
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), "101 Main St", "Charleston", "South Carolina", "29477"));
        DataWriter.saveReliefRequests();
        assertEquals("101 Main St", DataLoader.getReliefRequests().get(0).getAddress());
    }

    @Test
    public void testWritingOneReliefRequestWithUUID() {
        ArrayList<PartyMember> partyMembers = new ArrayList<PartyMember>();
        partyMembers.add(new PartyMember("Mr. Whiskers", "2020", true, Being.CAT));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "52 Water St", "Charleston", "South Carolina", "29218", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 20, true, true, Skills.TRANSPORTATION));
        DataWriter.saveReliefRequests();
        assertEquals("52 Water St", DataLoader.getReliefRequests().get(0).getAddress());
    }

    @Test
    public void testWritingFiveReliefRequestsWithUUID() {
        ArrayList<PartyMember> partyMembers = new ArrayList<PartyMember>();
        partyMembers.add(new PartyMember("Mr. Whiskers", "2020", true, Being.CAT));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "52 Water St", "Charleston", "South Carolina", "29218", partyMembers, false, false, true, ReliefRequestStatus.SUBMITTED, 20, true, true, Skills.TRANSPORTATION));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "57 Water St", "Charleston", "South Carolina", "29218", partyMembers,
        false, false, true, ReliefRequestStatus.SUBMITTED, 15, true, true, Skills.CPR));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "20 Main St", "Charleston", "South Carolina", "29218", partyMembers,
        false, false, true, ReliefRequestStatus.SUBMITTED, 10, true, true, Skills.SEARCH_AND_RESCUE));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "17 Johns Island St", "Charleston", "South Carolina", "29218", partyMembers,
        false, false, true, ReliefRequestStatus.SUBMITTED, 5, true, true, Skills.CPR));
        reliefRequests.add(new ReliefRequest(UUID.randomUUID(), UUID.randomUUID(), "32 Blue Swan Rd", "Charleston", "South Carolina", "29218", partyMembers,
        false, false, true, ReliefRequestStatus.SUBMITTED, 12, true, true, Skills.CPR));
        DataWriter.saveReliefRequests();
        DataWriter.saveReliefRequests();
        assertEquals("52 Water St", DataLoader.getReliefRequests().get(0).getAddress());
    }





    
}
