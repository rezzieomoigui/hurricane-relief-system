package com.model;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();

    @BeforeEach
    public void setup() {
        userList.getUsers().clear();
		DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        userList.getUsers().clear();
        DataWriter.saveUsers();
    }

    @Test
    public void testMinimumLengthOfPassword() {
        users.add(new User("Kate", "Blyth", "kblyth", "22", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testMaximumLengthOfPassword() {
        users.add(new User("Kate", "Blyth", "kblyth", "!Seeeeeeabrook81", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testUpperCaseInPassword() {
        users.add(new User("Kate", "Blyth", "kblyth", "!seabrook81", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testLowerCaseInPassword() {
        users.add(new User("Kate", "Blyth", "kblyth", "!SEABROOK81", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testNumberInPassword() {
        users.add(new User("Kate", "Blyth", "kblyth", "!Seabrook", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testSpecialCharacterInPassword() {
        users.add(new User("Kate", "Blyth", "kblyth", "Seabrook81", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    @Test 
    public void testPasswordProtection() {
        users.add(new User("Kate", "Blyth", "kblyth", "!Seabrook81", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        User user = users.get(0);
        String password = user.getPassword();
        assertEquals(null, password);
    }
}
