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
        UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
    }

    @Test
    public void testWritingUsers() {
        users = DataLoader.getUsers();
		assertEquals(0, users.size());
    }

    @Test
    public void testWritingOneUser() {

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
