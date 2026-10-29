package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DataLoaderTest {
    private Users users = Users.getInstance();
    ArrayList<User> userList = users.getUsers();

    @BeforeEach
    public void setup() {
        userList.clear();
        userList.add(new User("Bailey", "Bowen", "bbowen", "RelHe1p34", "bbowen@gmail.com", "02-09-2003", "594 South St", "New York", "New York", "29804", "English", false, UserType.VOLUNTEER));
    }
}
