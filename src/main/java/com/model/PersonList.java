package com.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * PersonList contains person methods
 * @author Fantastic Four
 */
public class PersonList {
    private static PersonList personList;
    private ArrayList<Person> people;

    /**
     * PersonList Constructor adds new person
     */
    private PersonList() {
        ArrayList<Person> loaded = DataLoader.getPerson();
        if (loaded != null && !loaded.isEmpty()) {
            this.people = loaded;
        }
    }

    /**
     * getInstance returns instance of the personList
     * @return instance of PersonList
     */
    public static PersonList getInstance() {
        if (personList == null) {
            personList = new PersonList();
        }
        return personList;
    }

    /**
     * getPerson method finds person in personList based on their email address
     * @param email person email
     * @return person relating to email address
     */
    public Person getPerson(String email) {
        for (Person person : people) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        return null;
    }

    /**
     * getPeople returns people
     * @return ArrayList of people
     */
    public ArrayList<Person> getPeople() {
        return this.people;
    }

    /**
     * addPerson method adds a person
     * @param personID person id
     * @param firstName person first name
     * @param lastName person last name
     * @param email person email
     * @return boolean true if person was added and didn't already exist
     */
    public boolean addPerson(UUID personID, String firstName, String lastName, String email, PersonType personType) {
        if (getPerson(email) != null) {
            return false;
        }
        people.add(new Person(personID, firstName, lastName, email, personType));
        return true;
    }
    /**
     * addUser method adds a user
     * @param firstName user first name
     * @param lastName user last name
     * @param username user username
     * @param password user password
     * @param email user email
     * @param dateOfBirth user birthday
     * @param address user address
     * @param zipCode user zip code
     * @param language user language
     * @param isAdmin checks if user is an administrator
     * @param personType person type
     * @return boolean true if user was added and didn't already exist
     */
    public boolean addUser(UUID id, String username, String firstName, String lastName, String password,
    String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin, PersonType personType) {
        if (getPerson(email) != null) {
            return false;
        }
        people.add(new User(id, username, firstName, lastName, password, email, 
                dateOfBirth, address, zipCode, language, isAdmin, personType));
        return true;
    }

    /**
     * addVolunteer method adds a volunteer
     * @param firstName volunteer first name
     * @param lastName volunteer last name
     * @param username volunteer username
     * @param password volunteer password
     * @param email volunteer email
     * @param dateOfBirth volunteer birthday
     * @param address volunteer address
     * @param zipCode volunteer zip code
     * @param language volunteer language
     * @param isAdmin checks if volunteer is an administrator
     * @param userType user type
     * @param skills volunteer skills
     * @param firstResponder checks if volunteer is a first responder
     * @param canSwim checks if volunteer can swim
     * @param isShelterOwner checks if volunteer is a shelter owner
     * @return boolean true if volunteer was added and didn't already exist
     */
    public boolean addVolunteer(UUID uuid, String firstName, String lastName, String username, String password, String email, String dateOfBirth, String address, String zipCode, String language, boolean isAdmin, PersonType personType, Skills skills, boolean firstResponder, boolean canSwim, boolean isShelterOwner) {
        if (getPerson(email) != null)
            return false;
        people.add(new Volunteer(uuid, firstName, lastName, username, password, email, dateOfBirth, address, zipCode, language, isAdmin, personType, skills, firstResponder, canSwim, isShelterOwner));
        return true;
    }

    public boolean addPartyMember(UUID partyMemberID, String firstName, String lastName, String yearBorn, boolean needsMedicalAssistance, Being type, String email, PersonType personType) {
        if (getPerson(email) != null)
            return false;
        people.add(new PartyMember(partyMemberID, firstName, lastName, yearBorn, needsMedicalAssistance, type, email, personType));
        return true;
    }

    /**
     * save method saves the userList
     * @return boolean true or false depending on if it saved properly
     */
    public boolean save() {
        return DataWriter.savePeople();
    }
}
