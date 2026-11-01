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

    }

    @Test
    public void testWritingEmptyUser() {

    }

    @Test
    public void testWritingNullUser() {
        
    }

    
}
