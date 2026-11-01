package com.model;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestTesting {
    @Test
    public void testTesting() {
        assertTrue(true);
    }
}

// setup code, run before code
@BeforeClass
public void oneTimeSetup() {

}

@AfterClass
public void oneTImeTearDown() {

}

@BeforeEach
public void setup() {
    // runs before each test
    Users.getInstance().getUsers().clear();
}

@AfterEach
public void tearDown() {
    // runs after each test
}