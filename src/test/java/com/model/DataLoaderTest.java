package com.model;
/*
 * Used AI to assist with test implementation.
 *
 * Test                                 | Reasoning
 * -------------------------------------|--------------------
 * testGetUsersSize()                   | This test checks that the user list is properly adjusting and reflecting the correct number of users that are added to the userList
 * testGetUsersSizeWrongZero()          | This test checks that the user list size is not equal to zero when it is supposed to have users listed in it
 * testGetUsersSizeZero()               | This test checks that the size of the user list is zero when the list is cleared and no users are listed
 * testGetUsersInvalidUUID()            | This test checks that the user uuid value matches the correct value
 * testGetUsersInvalidUserType()        | This test checks that the user type is set correctly
 * testGetUsersMissingFile()            |
 * testGetUsersMalformedJSON()          |
 * testGetUsersMissingRequiredField()   |
 * testGetUsersRegularUserInstane()     | This test checks that the user is set to a user if their they aren't specified to be a volunteer s that the users don't end up with volunteer attributes
 * testGetUsersAdmin()                  | 
 * testGetUsersVolunteerInstance()      | This test checks that the volunteers are identified properly and not just considered users alone because they have additional attributes and responsibilities
 * testGetUsersNotNull()                | This test checks that the userList returns a list of users and not null even when the array list contains no users since it is not null, rather it is empty
 */
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    private UserList users = UserList.getInstance();
    ArrayList<User> userList = users.getUsers();

    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new User("Bailey", "Bowen", "bbowen", "RelHe1p34", "bbowen@gmail.com", "02/09/2003", "594 South St", "New York", "New York", "29804", "English", true, UserType.VOLUNTEER));
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
        assertEquals(0, userList.size());
    }
    
    @Test
    void testGetUserFirstUserName() {
        userList = DataLoader.getUsers();
        assertEquals("bbowen", userList.get(0).getUsername());
    }
}
