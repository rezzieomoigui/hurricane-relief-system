package com.model;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataWriterTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users= userList.getUsers();

    @Before
    public void setup() {
        UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
    }

    @After
    public void tearDown() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
    }

    @Test
    public void testWritingUsers() {

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
