package com.model;

import java.util.Scanner;

public class HurricaneReliefDriver {
    private HurricaneReliefApplication application;
    public HurricaneReliefDriver() {
         application = new HurricaneReliefApplication();
    }

public void run() {
     scenario1(); 
     scenario2();
}
  public void scenario1() {
      System.out.println("Create an account");
      
      application.createAccount( "Catherine", "Philip", "cathp123", "2468", "catherine.phil@gmail.com", "1990-05-15", "123 Main St, CityVille MD", "12345", "English", false);
      System.out.println("Account created");
  }


  public void scenario2() {
      System.out.println("Login");
		if (!application.login("cathp123", "2468")) {
			System.out.println("Sorry we couldn't login.");
			return;
        }
        System.out.println("Welcome Back Catherine!");
  }

























}