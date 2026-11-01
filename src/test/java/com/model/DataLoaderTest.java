package com.model;
/*
 * Used AI to assist with test implementation.
 *
 * Test                      | Reasoning
 * --------------------------|--------------------
 * testGetUsersSize()        | This test checks that the user list is properly adjusting and reflecting the correct number of users that are added to the userList
 * testGetUsersSizeWrong()   | 
 * testGetUsersSizeZero()    | This test checks that the size of the user list is zero when the list is cleared and no users are listed
 * testGetUserFirstUserName()|
 * testGetUsersNotNull()     |
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

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
        assertEquals("bbowen", userList.get(0).getUserName());
    }
}
