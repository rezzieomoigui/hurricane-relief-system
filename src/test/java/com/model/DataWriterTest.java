package com.model;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {
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
    public void testWritingUsers() {
        users = DataLoader.getUsers();
		assertEquals(0, users.size());
    }

    @Test
    public void testWritingOneUser() {
        users.add(new User("Kate", "Blyth", "kblyth", "antlerPush12", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
		DataWriter.saveUsers();
		assertEquals("kblyth", DataLoader.getUsers().get(0).getUsername());
    }

    @Test
    public void testWritingFiveUsers() {
        users.add(new User("Kate", "Blyth", "kblyth", "antlerPush12", "kblyth@gmail.com", "11-09-2000", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("John", "Blyth", "jblyth", "88blindSnake12", "jblyth@gmail.com", "12-09-1998", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("Sam", "Blyth", "sblyth", "oldShoeBloom12", "sblyth@gmail.com", "08-09-1964", "42 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("Susan", "Johnson", "sjohnson", "windingRoad98", "sjohn@gmail.com", "02-12-2001", "45 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        users.add(new User("Marcilla", "Johnson", "mjohnson", "blueSky8", "mj@gmail.com", "02-28-2000", "45 Brook Dr", "Charleston", "South Carolina", "29218", "English", true, UserType.USER));
        DataWriter.saveUsers();
        assertEquals("mjohnson", DataLoader.getUsers().get(4).getUsername());
    }

    @Test
    public void testWritingEmptyUser() {
        users.add(new User("", "", "", "", "", "", "", "", "", "", "", true, UserType.USER));
		DataWriter.saveUsers();
		assertEquals(0, DataLoader.getUsers().size());
    }

    @Test
    public void testWritingNullUser() {
        users.add(new User(null, null, null, null, null, null, null, null, null, null, null, true, UserType.USER));
        DataWriter.saveUsers();
        assertEquals(0, DataLoader.getUsers().size());
    }

    
}
