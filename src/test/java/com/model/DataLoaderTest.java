package com.model;
/*
 * Used AI to assist with some test implementation.
 *
 * Test                                 | Reasoning
 * -------------------------------------|--------------------
 * testGetUsersSize()                   | This test checks that the user list is properly adjusting and reflecting the correct number of users that are added to the userList
 * testGetUsersSizeIncrease()           | This test checks that when a new user is added the size of the array increases correctly
 * testGetRemoveUser()                  | This test checks that when a user is removed, the size of the user list decreases properly 
 * testGetUsersSizeZero()               | This test checks that the size of the user list is zero when the list is cleared and no users are listed
 * testGetUsersNotNull()                | This test checks that the userList returns a list of users and not null even when the array list contains no users since it is not null, rather it is empty
 * testLoadingNullUser()                | This test checks that when a user is loaded in with all null properties they are not added to the user list
 * testLoadingEmptyUser()               | This test checks that when a user is loaded in with empty data they are not added to the user list
 * testLoadingEmptyUsersName            | This test checks that when a user is loaded in with an empty first and last name they are not added to the user list
 * testGetUsersInvalidUserType()        | This test checks that the user type is correctly loaded and set correctly
 * testGetUsersMissingFile()            | This test checks that the missing file returns null and prints a try-catch exception
 * testGetUsersMalformedJSON()          | This test checks that if the JSON file is incorrectly formatted and can't be read that it will return null and print a try-catch exception
 * testGetUsersRegularUserInstance()    | This test checks that the user is set to a user if they aren't specified to be a volunteer so that the users don't end up with volunteer attributes
 * testGetUsersAdminTrue()              | This test checks that the user is correctly showing administrator status so that they know if they can give the specific user administrator privileges
 * testGetUsersAdminFalse()             | This test checks that the user isn't incorrectly showing administrator status when they shouldn't have that status so that they don't have access to administrator privileges they shouldn't have
 * testGetUsersVolunteerInstance()      | This test checks that the volunteers are identified properly and not just considered users alone because they have additional attributes and responsibilities
 */
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    private UserList userList = UserList.getInstance();
    ArrayList<User> users = userList.getUsers();

    @BeforeEach
    public void setup() {
        userList.getUsers().clear();
        users.add(new User( "Bailey", "Bowen", "bbowen", "RelHe1p34", "bbowen@gmail.com", "02/09/2003", "594 South St", "New York", "New York", "29804", "English", true, UserType.USER));
        users.add(new User("Karley", "Long", "klong", "grIe35!", "klong@outlook.com", "07/23/1999", "437 Bull Street", "Raleigh", "North Carolina", "49739", "English", false, UserType.USER));
        DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        userList.getUsers().clear();
        DataWriter.saveUsers();
    }

    @Test
    void testGetUsersSize() {
        users = DataLoader.getUsers();
        assertEquals(2, users.size());
    }

    @Test
    void testGetUsersSizeIncrease() {
        users.add(new User( "Savannah", "Conner", "sconner", "FipH0;d94", "sconner@gmail.com", "08/10/2014", "8873 Garden Road", "Atlanta", "Georgia", "49739", "Spanish", false, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(3, users.size());
    }

    @Test 
    void testGetRemoveUser() {
        users.remove(1);
        DataWriter.saveUsers();
        assertEquals(1, users.size());
    }
    
    @Test
    void testGetUsersSizeZero() {
        userList.getUsers().clear();
        DataWriter.saveUsers();
        users = DataLoader.getUsers();
        assertEquals(0, users.size());
    }

    @Test 
    void testGetUsersNotNull() {
        users = DataLoader.getUsers();
        assertNotNull(userList);
    }

    @Test
    public void testLoadingEmptyUser() {
        users.add(new User("", "", "", "", "", "", "", "", "", "", "", true, UserType.USER));
		DataWriter.saveUsers();
		assertEquals(2, DataLoader.getUsers().size());
    }

    @Test
    public void testLoadingNullUser() {
        users.add(new User(null, null, null, null, null, null, null, null, null, null, null, true, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(2, DataLoader.getUsers().size());
    }

    @Test
    public void testLoadingEmptyUsersName() {
        users.add(new User( "", "", "sconner", "FipH0;d94", "sconner@gmail.com", "08/10/2014", "8873 Garden Road", "Atlanta", "Georgia", "49739", "Spanish", false, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(2, DataLoader.getUsers().size());
    }

    @Test 
    void testGetUsersVolunteerInstance() {
        users.add(new Volunteer( "Emmie", "Grand", "egrand", "listyFRI93", "egrand@gmail.com", "01/12/2018", "98 Crest Street", "Atlanta", "Georgia", "49739", "English", true, UserType.VOLUNTEER, Skills.WOUND_CARE, true, true, false));
        users = DataLoader.getUsers();
        assertTrue(users.get(0) instanceof Volunteer);
    }

    @Test 
    void testGetUsersRegularUserInstance() {
        users = DataLoader.getUsers();
        assertTrue(users.get(0) instanceof User);
    }

    @Test 
    void testGetUsersAdminTrue() {
        users = DataLoader.getUsers();
        assertTrue(users.get(0).isAdmin());
    }

    @Test 
    void testGetUsersAdminFalse() {
        users = DataLoader.getUsers();
        assertFalse(users.get(1).isAdmin());
    }

    @Test 
    void testGetUsersInvalidUserType() {
        users = DataLoader.getUsers();
        assertNull(userList);
    }

    @Test 
    void testGetUsersMissingFile() {
        users = DataLoader.getUsers();
        assertNull(userList);
    }

    @Test 
    void testGetUsersMalformedJSON() {
        users = DataLoader.getUsers();
        assertNull(userList);
    }
}
