package com.model;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

public class DataLoaderTest {
    private UserList userList = UserList.getInstance();
    ArrayList<User> users = userList.getUsers();

    @BeforeEach
    public void setup() {
        //userList.clear();
        //userList.add(new User("Bailey", "Bowen", "bbowen", "RelHe1p34", "bbowen@gmail.com", "02-09-2003", "594 South St", "New York", "New York", "29804", "English", false, UserType.VOLUNTEER));
    }
}
