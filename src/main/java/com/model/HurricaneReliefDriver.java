package com.model;
import java.util.ArrayList; 

public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;

 
    public HurricaneReliefDriver() {
        application = new HurricaneReliefApplication();
    }

 
    public void run() {
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
        ArrayList<PartyMember> collinFamily = new ArrayList<PartyMember>();
        ReliefRequest request = application.createReliefRequest(application.getCurrentUser(), true, false, true, true, true, Skills.TRANSPORTATION);
        application.addPartyMember(request, "Collin Carter", "1978", false, Being.PERSON);
        application.addPartyMember(request, "Sophie Carter", "2012", true, Being.PERSON);
        application.addPartyMember(request, "Connie Carter", "1979", false, Being.PERSON);
        application.addPartyMember(request, "Spot", "2023", false, Being.DOG);
        System.out.println(request.toString());
    }

   
    public void scenario2() {
        System.out.println();
        System.out.println("Scenario 2: Log in and log out");

        boolean loginResult = application.login("asmith", "12345");
        System.out.println("Logging in Amy Smith: "+loginResult);
        boolean logout = application.logout();
        System.out.println("Logging out: "+logout);
        System.out.println(application.getCurrentUser());

        boolean loginResult2 = application.login("probinson", "goodPassword5");
        System.out.println("Logging in Petra Robinson: "+loginResult2);
        boolean logout2 = application.logout();
        System.out.println("Logging out: "+logout2);
        System.out.println(application.getCurrentUser());
    }

    public void scenario3() {
        System.out.println();
        System.out.println("Scenario 3: Shelters");
    }



    public static void main(String[] args) {
        HurricaneReliefDriver driver = new HurricaneReliefDriver();
        driver.run();
    }
}