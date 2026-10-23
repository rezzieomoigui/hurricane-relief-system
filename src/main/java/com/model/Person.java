package com.model;

import java.util.UUID;

/**
 * Person class allows people to be created without having to be users or volunteers
 * Can add people as party members and store their information in the system
 * @author Fantastic Four
 */
public class Person {
    private UUID personID;
    private String firstName;
    private String lastName;
    private String email;
    private PersonType personType;

    /**
     * Person constructor initializes person object
     * @param personID person id
     * @param firstName person first name
     * @param lastName person last name
     * @param email person email
     * @param personType person type
     */
    public Person(UUID personID, String firstName, String lastName, String email, PersonType personType) {
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.personType = personType;
    }

    /**
     * getPersonID gets the person's id
     * @return UUID representing id of the person
     */
    public UUID getPersonID() {
        return personID;
    }

    /**
     * getFirstName gets the first name of the person
     * @return String representing first name of person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getLastName gets the last name of the person
     * @return String representing last name of person
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * getEmail gets the email of the person
     * @return String representing email of person
     */
    public String getEmail() {
        return email;
    }

    /**
     * getPersonType gets the person type
     * @return person type
     */
    public PersonType getPersonType() {
        return personType;
    }
}
