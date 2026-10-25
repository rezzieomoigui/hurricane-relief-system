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
    }
 
    /**public void scenario1() {
        System.out.println();
        System.out.println("Scenario 1: Create an account");

        application.createAccount(
            "Amy",
            "Smith",
            "asmith",
            "12345",
            "amy@example.com",
            "2000-05-15",
            "123 Example Street",
            "29201",
            "English",
            false,
            "User"
        );

        System.out.println("Account information submitted for Amy Smith.");
    }*/

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

   
    public void scenario2() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Scenario 2");
        boolean loggedIn = application.login("rdelgado", "potted9Oak*");
        Volunteer rosa = (Volunteer) application.getCurrentUser();
        System.out.println("logged in? "+loggedIn);
        System.out.println(application.requestsToString(rosa));
    }



    public static void main(String[] args) throws IOException, InterruptedException {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}