package com.model;
/*
 * Used AI to assist with test implementation.
 *
 * Test                                 | Reasoning
 * -------------------------------------|--------------------
 * testGetUsersSize()                   | This test checks that the user list is properly adjusting and reflecting the correct number of users that are added to the userList
 * testGetUsersSizeWrongZero()          | This test checks that the user list size is not equal to zero when it is supposed to have users listed in it
 * testGetUsersSizeZero()               | This test checks that the size of the user list is zero when the list is cleared and no users are listed
 * testGetUsersInvalidUUID()            | This test checks that the user uuid is correctly loaded and matches the expected value
 * testGetUsersInvalidUserType()        | This test checks that the user type is correctly loaded and set correctly
 * testGetUsersMissingFile()            | This test checks that the missing file returns null and prints a try-catch exception
 * testGetUsersMalformedJSON()          | This test checks that if the JSON file is incorrectly formatted and can't be read that it will return null and print a try-catch exception
 * testGetUsersMissingRequiredField()   | This test checks that if the JSON file is missing needed information and can't be read in that it will return null and print a try-catch exception
 * testGetUsersRegularUserInstane()     | This test checks that the user is set to a user if they aren't specified to be a volunteer so that the users don't end up with volunteer attributes
 * testGetUsersAdmin()                  | This test checks that the user is correctly showing administrator status so that they know if they can give the specific user administrator privileges
 * testGetUsersVolunteerInstance()      | This test checks that the volunteers are identified properly and not just considered users alone because they have additional attributes and responsibilities
 * testGetUsersNotNull()                | This test checks that the userList returns a list of users and not null even when the array list contains no users since it is not null, rather it is empty
 */
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    private UserList users = UserList.getInstance();
    ArrayList<User> userList = users.getUsers();

    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new User( "Bailey", "Bowen", "bbowen", "RelHe1p34", "bbowen@gmail.com", "02/09/2003", "594 South St", "New York", "New York", "29804", "English", true, UserType.VOLUNTEER));
        userList.add(new User("Karley", "Long", "klong", "grIe35!", "klong@outlook.com", "07/23/1999", "437 Bull Street", "Raleigh", "North Carolina", "49739", "English", false, UserType.USER));
        DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        users.getUsers().clear();
        DataWriter.saveUsers();
        assertEquals(0, userList.size());
    }

    @Test
    void testGetUsersSize() {
        userList = DataLoader.getUsers();
        assertEquals(2, userList.size());
    }

    @Test
    void testGetUsersSizeZero() {
        users.getUsers().clear();
        DataWriter.saveUsers();
        userList = DataLoader.getUsers();
        assertEquals(0, userList.size());
    }

    @Test 
    void testGetUsersNotNull() {
        userList = DataLoader.getUsers();
        assertNotNull(userList);
    }

    @Test 
    void testGetUsersVolunteerInstance() {
        userList = DataLoader.getUsers();
        assertTrue(userList.get(0) instanceof Volunteer);
    }

    @Test 
    void testGetUsersRegularUserInstance() {
        userList = DataLoader.getUsers();
        assertTrue(userList.get(0) instanceof User);
    }

    @Test 
    void testGetUsersAdminTrue() {
        userList = DataLoader.getUsers();
        assertTrue(userList.get(0).isAdmin());
    }

    @Test 
    void testGetUsersAdminFalse() {
        userList = DataLoader.getUsers();
        assertFalse(userList.get(1).isAdmin());
    }

    @Test 
    void testGetUsersInvalidUserType() {
        userList = DataLoader.getUsers();
        assertNull(userList);
    }

    @Test 
    void testGetUsersMissingFile() {
        userList = DataLoader.getUsers();
        assertNull(userList);
    }

    @Test 
    void testGetUsersMalformedJSON() {
        userList = DataLoader.getUsers();
        assertNull(userList);
    }
}
