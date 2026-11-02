package com.model;
/*
 * Used AI to assist with some test implementation.
 *
 * Test                                 | Reasoning
 * -------------------------------------|--------------------
 * testGetReliefRequestsByID()          | This test checks that the correct relieff request is found when looking for relief requests by user
 * testGetReliefRequestsByIDNotFound()  | This test checks that null is returned when no relief requests in relief request list match the uuid
 * testAddReliefRequest()               | This test checks that the relief request is properly added to relief request list and the size is increased to one
 * testAddTwoReliefRequests()           | This test checks that when multiple relief requests are added the size of the relief request list increases properly
 * testDuplicateReliefRequests()        | This test checks that duplicate relief requests can not be added to the relief request list to prevent unnecessary repetition
 * testAddNullReliefRequest()           | This test checks that null relief requests are not added into the relief request list
 * testAddEmptyReliefRequest()          | This test checks that empty relief requests are not added into the relief request list
 * testRemoveReliefRequest()            | This test checks that a relief request can be removed from the relief request list and the size is properly adjusted
 * testMarkCompleteRequestAdmin()       | This test checks that a relief request is marked completed by an administrator
 * testMarkCompleteRequestNotAdmin()    | This test checks that a relief request is not marked complete by someone who does not have administrator privlidges
 */
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReliefRequestTest {
    private ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
    private ArrayList<ReliefRequest> reliefRequests = reliefRequestList.getReliefRequests();

    @BeforeEach
    public void setup() {
        reliefRequestList.getReliefRequests().clear();
		DataWriter.saveReliefRequests();
    }

    @AfterEach
    public void tearDown() {
        reliefRequestList.getReliefRequests().clear();
		DataWriter.saveReliefRequests();
    }

    @Test
    public void testGetReliefRequestByID() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
		reliefRequests.add(request);
        assertEquals(request, reliefRequestList.getReliefRequestById(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1")));
    }

    @Test 
    public void testGetReliefRequestByIDNotFound() {
        ReliefRequest request = reliefRequestList.getReliefRequestById(UUID.fromString("616e1578-bf32-45b4-bb4b-9e64b96603ee"));
        assertEquals(null, request);
    }

    @Test
    public void testAddReliefRequest() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
		if (reliefRequestList.addReliefRequest(request)) {
            assertEquals(1, reliefRequests.size());
        }
    }

    @Test
    public void testAddTwoReliefRequests() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
        ReliefRequest request2 = new ReliefRequest(UUID.fromString("7e41fd9e-00d3-427f-9d32-502cd62b78d2"), UUID.fromString("592146b1-d945-4d79-8839-b8e44df6f548"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
		if (reliefRequestList.addReliefRequest(request)) {
            assertEquals(1, reliefRequests.size());
        }
        if (reliefRequestList.addReliefRequest(request2)) {
            assertEquals(2, reliefRequests.size());
        }
    }

    @Test
    public void testDuplicateReliefRequest() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
		reliefRequestList.addReliefRequest(request);
        assertEquals(true, reliefRequestList.addReliefRequest(request));
        assertEquals(false, reliefRequestList.addReliefRequest(request));
        assertEquals(1, reliefRequests.size());
    }

    @Test
    public void testAddNullReliefRequest() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember(null, null, false, null));
        ReliefRequest request = new ReliefRequest(null, null, null, null, null, null, partyMembers, true, true, false, null, 20, true, false, null);
        assertEquals(false, reliefRequestList.addReliefRequest(request));
        assertEquals(0, reliefRequests.size());
    }

    @Test
    public void testAddEmptyReliefRequest() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("", "", false, Being.CAT));
        ReliefRequest request = new ReliefRequest(null, null, "", "", "", "", partyMembers, true, true, false, null, 20, true, false, null);
        assertEquals(false, reliefRequestList.addReliefRequest(request));
        assertEquals(0, reliefRequests.size());
    }

    @Test
    public void testRemoveReliefRequest() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
        reliefRequests.add(request);
        if (reliefRequestList.removeReliefRequest(request)) {
            assertEquals(0, reliefRequests.size());
        }
    }

    @Test
    public void testRemoveFakeReliefRequest() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
        if (!reliefRequestList.removeReliefRequest(request)) {
            assertEquals(0, reliefRequests.size());
        }
    }

    @Test
    public void testMarkCompleteRequestAdmin() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
        reliefRequestList.markCompleteRequest(request, true);
        assertEquals(ReliefRequestStatus.COMPLETED, request.getStatus());
    }
    @Test
    public void testMarkCompleteRequestNotAdmin() {
        ArrayList<PartyMember> partyMembers = new ArrayList<>();
        partyMembers.add(new PartyMember("Brock", "1990", false, Being.PERSON));
        ReliefRequest request = new ReliefRequest(UUID.fromString("b692236d-d309-4329-a9fd-34a92d650ce1"), UUID.fromString("f5b6b164-12e5-4392-afb2-b31f682fd86f"), "573 Caravaan Road", "Greenville", "South Carolina", "78462", partyMembers, true, true, false, ReliefRequestStatus.SUBMITTED, 20, true, false, Skills.TRANSPORTATION);
        reliefRequestList.markCompleteRequest(request, false);
        assertEquals(ReliefRequestStatus.COMPLETED, request.getStatus());
    }
}
