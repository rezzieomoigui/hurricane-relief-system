package com.model;
import java.io.IOException;

/**
 * HurricaneReliefDriver runs scenarios representing possibile situations a user could experience
 * @author Fantastic Four
 */
public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;

    /**
     * HurricaneReliefDriver sets application to the facade to be able to access facade methods
     */
    public HurricaneReliefDriver() {
        application = new HurricaneReliefApplication();
    }

    /**
     * run method runs the scenarios
     * @throws IOException
     * @throws InterruptedException
     */
    public void run() throws IOException, InterruptedException {
        scenario1();
        scenario2();
        scenario3();
        scenario4();
        scenario5();
        scenario6();
        //scenario5();
        //scenario6();
    }

    /**
     * scenario1 creates account for user and adds party members
     */
    public void scenario1() {
        System.out.println();
        System.out.println("Scenario 1");
        boolean accountCreated = application.createAccount("Collin", "Carter", "ccollin", "7snailBrook&", "ccollin@gmail.com", "04-09-1978", "1735 Vassar Dr", "Charleston", "South Carolina", "29407", "English", false, UserType.USER);
        System.out.println("Collin Carter's account created? "+accountCreated);
        boolean loggedIn = application.login("ccollin", "7snailBrook&");
        System.out.println("Collin Carter logged in? "+loggedIn);
        ReliefRequest request = application.createReliefRequest(application.getCurrentUser(), true, false, true, true, true, Skills.TRANSPORTATION);
        application.addPartyMember(request, "Collin Carter", "1978", false, Being.PERSON);
        application.addPartyMember(request, "Sophie Carter", "2012", true, Being.PERSON);
        application.addPartyMember(request, "Connie Carter", "1979", false, Being.PERSON);
        application.addPartyMember(request, "Buddy", "2023", false, Being.DOG);
        System.out.println(request.toString());
        boolean saved = application.save();
        System.out.println(saved);
        application.logout();
    }

    /**
     * scenario2 logs user in and sets volunteer
     * @throws IOException
     * @throws InterruptedException
     */
    public void scenario2() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Scenario 2");
        boolean loggedIn = application.login("rdelgado", "potted9Oak*");
        Volunteer rosa = (Volunteer) application.getCurrentUser();
        System.out.println("logged in? "+loggedIn);
        System.out.println(application.requestsToString(rosa));
    }

    /**
     * scenario3 finds shelter that matches relief request parameters and the closest one and then assigns the relief request
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public Shelter scenario3() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Scenario 3: Rosa Finds a Shelter and Accepts the Relief Request");
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        Volunteer rosa = (Volunteer) application.getCurrentUser();
        Shelter closestShelter = application.closestMatchingShelter(3, carterRequest);
        if (closestShelter == null) {
            System.out.println("No suitable shelter found.");
            return null;
        }
        System.out.println("Closest suitable shelter: ");
        System.out.println(closestShelter);
        boolean assigned = application.assignReliefRequest(3, carterRequest, rosa, application.getCurrentUser().isAdmin());
        System.out.println("Assignment successful? " + assigned);
        System.out.println("Updated relief request:");
        System.out.println(carterRequest);
        return closestShelter;
    }

    /**
     * scenario4 exports .txt file with shelter and relief request information
     * @throws IOException
     * @throws InterruptedException
     */
    public void scenario4() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Scenario 4: Exporting .txt file");
        Volunteer rosa = (Volunteer) application.getCurrentUser();
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        Shelter shelter = application.getShelter("100 Broad Street", "Charleston", "South Carolina", "29401");
        application.write(carterRequest, rosa.getAddress(), rosa.getCity(), rosa.getState(), rosa.getZipCode(), shelter);
    }

    /**
     * scenario5 shows that a volunteer can not mark a relief request as complete
     */
    public void scenario5() {
        System.out.println();
        System.out.println("Scenario 5: Rosa tries to mark request complete");
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        String message = application.markCompleteRequest(carterRequest, application.getCurrentUser().isAdmin());
        System.out.println(message);
        System.out.println(carterRequest);
    }

    /**
     * scenario6 checks the family into the shelter and adjusts occupancy
     * @throws IOException
     * @throws InterruptedException
     */
    public void scenario6() throws IOException, InterruptedException {
        System.out.println();
        application.logout();
        boolean loggedIn = application.login("felixh", "treeSpeaks@1");
        System.out.println("Scenario 6: Check the family in");
        System.out.println("loggedIn? "+loggedIn);
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        Shelter closestShelter = application.closestMatchingShelter(3, carterRequest);
        Shelter myShelter = application.getShelter(closestShelter.getAddress(), closestShelter.getCity(), closestShelter.getState(), closestShelter.getZipCode());
        System.out.println(myShelter.toString());
        // ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        application.addShelterOccupants(myShelter, carterRequest.getPartyMembers());
        myShelter.setNumberOfOccupants(myShelter.getNumberOfOccupants()+3, application.getCurrentUser().isAdmin());
        System.out.println(myShelter.toString());
        application.markCompleteRequest(carterRequest, application.getCurrentUser().isAdmin());
        System.out.println(carterRequest.toString());
    }

    /**
     * main method runs the driver class
     * @param args String array of standard arguments
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}