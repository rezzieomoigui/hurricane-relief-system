package com.model;
import java.io.IOException;

public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;

 
    public HurricaneReliefDriver() {
        application = new HurricaneReliefApplication();
    }

 
    public void run() throws IOException, InterruptedException {
        scenario1();
        scenario2();
        scenario5();
        scenario6();
    }

    public void scenario1() {
        System.out.println();
        System.out.println("Scenario 1");
        boolean accountCreated = application.createAccount("Collin", "Carter", "ccollin", "7snailBrook&", "ccollin@gmail.com", "04-09-1978", "1735 Vassar Dr", "Charleston", "South Carolina", "29407", "English", false, UserType.USER);
        System.out.println("Collin Carter's account created? "+accountCreated);
        boolean loggedIn = application.login("ccollin", "7snailBrook&");
        System.out.println("Collin Carter logged in? "+loggedIn);
        ReliefRequest request = application.createReliefRequest(application.getCurrentUser(), true, false, true, true, true, Skills.TRANSPORTATION);
        //ReliefRequestList reliefRequestList = ReliefRequestList.getInstance();
        //ArrayList<ReliefRequest> rr = reliefRequestList.getReliefRequests();
        application.addPartyMember(request, "Collin Carter", "1978", false, Being.PERSON);
        application.addPartyMember(request, "Sophie Carter", "2012", true, Being.PERSON);
        application.addPartyMember(request, "Connie Carter", "1979", false, Being.PERSON);
        application.addPartyMember(request, "Buddy", "2023", false, Being.DOG);
        System.out.println(request.toString());
        boolean saved = application.save();
        System.out.println(saved);
        application.logout();
    }

   
    public void scenario2() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Scenario 2");
        boolean loggedIn = application.login("rdelgado", "potted9Oak*");
        Volunteer rosa = (Volunteer) application.getCurrentUser();
        System.out.println("logged in? "+loggedIn);
        System.out.println(application.requestsToString(rosa));
    }

    public void scenario3() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Scenario 3: Rosa Finds a Shelter and Accepts the Relief Request");
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        Volunteer rosa = (Volunteer) application.getCurrentUser();
        Shelter closestShelter = application.closestMatchingShelter(3, carterRequest);
        if (closestShelter == null) {
            System.out.println("No suitable shelter found.");
            return;
        }
        System.out.println("Closest suitable shelter: ");
        System.out.println(closestShelter);
        boolean assigned = application.assignReliefRequest(3, carterRequest, rosa, application.getCurrentUser().isAdmin());
        System.out.println("Assignment successful? " + assigned);
        System.out.println("Updated relief request:");
        System.out.println(carterRequest);
    }

    public void scenario5() {
        System.out.println();
        System.out.println("Scenario 5: Rosa tries to mark request complete");
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        String message = application.markCompleteRequest(carterRequest, application.getCurrentUser().isAdmin());
        System.out.println(message);
        System.out.println(carterRequest);
    }

    public void scenario6() {
        System.out.println();
        application.logout();
        boolean loggedIn = application.login("felixh", "treeSpeaks@1");
        System.out.println("Scenario 6: Check the family in");
        System.out.println("loggedIn? "+loggedIn);
        Shelter myShelter = application.getShelter("100 Broad Street", "Charleston", "South Carolina", "29401");
        System.out.println(myShelter.toString());
        ReliefRequest carterRequest = application.getReliefRequest("1735 Vassar Dr", "Charleston", "South Carolina", "29407", "Collin", "Carter");
        application.addShelterOccupants(myShelter, carterRequest.getPartyMembers());
        myShelter.setNumberOfOccupants(myShelter.getNumberOfOccupants()+3, application.getCurrentUser().isAdmin());
        System.out.println(myShelter.toString());
        application.markCompleteRequest(carterRequest, application.getCurrentUser().isAdmin());
        System.out.println(carterRequest.toString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}