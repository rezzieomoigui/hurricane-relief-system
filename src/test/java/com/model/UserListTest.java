package com.model;

/*
 * Tests for the UserList class (Chance Price).
 * Used AI to assist with test implementation.
 * was worked out from the UserList code and requirement U01 (Creating account).
 *
 * +-------------------------------------------------+--------------------------------------------------------------------------+
 * | Test                                            | Reasoning                                                                |
 * +-------------------------------------------------+--------------------------------------------------------------------------+
 * | testGetUsersNeverNull                           | the list must never be null or every caller crashes (bug fixed in #213)  |
 * | testGetUsersCount                               | the list should hold exactly the 2 users added in setup                  |
 * | testGetUserByUsernameFirstInList                | lookup has to find the first user in the list                            |
 * | testGetUserByUsernameLastInList                 | lookup has to find the last user in the list                             |
 * | testGetUserByUsernameUnknownReturnsNull         | a username that isn't in the system returns null (second return path)    |
 * | testGetUserByUsernameNullReturnsNull            | a null username should not crash the lookup                              |
 * | testGetUserByUuidFound                          | users can also be looked up by their id                                  |
 * | testGetUserByUuidUnknownReturnsNull             | an id that isn't in the system returns null                              |
 * | testGetUserByUuidNullReturnsNull                | a null id should not crash the lookup                                    |
 * | testAddUserValidReturnsTrue                     | a new user with valid information should be accepted                     |
 * | testAddUserValidIncreasesCount                  | the new user actually has to be stored in the list                       |
 * | testAddUserStoredAsRegularUser                  | regular accounts should not get volunteer abilities                      |
 * | testAddUserDuplicateUsernameReturnsFalse        | each username has to be unique                                           |
 * | testAddUserDuplicateUsernameCountUnchanged      | a rejected duplicate must not be added to the list                       |
 * | testAddUserEmptyUsernameReturnsFalse            | U01.1: username is required                                              |
 * | testAddUserNullUsernameReturnsFalse             | U01.1: username is required                                              |
 * | testGetUserAfterNullUsernameAddedNoCrash        | a stored null username must not break later lookups                      |
 * | testAddUserEmptyFirstNameReturnsFalse           | U01.2: first name is required                                            |
 * | testAddUserEmptyLastNameReturnsFalse            | U01.3: last name is required                                             |
 * | testAddUserEmptyPasswordReturnsFalse            | U01.4: password is required                                              |
 * | testAddUserPasswordSevenCharsReturnsFalse       | U01.5: passwords need at least 8 characters (just under the boundary)    |
 * | testAddUserPasswordEightCharsReturnsTrue        | U01.5: 8 characters is the smallest allowed length (boundary)            |
 * | testAddUserPasswordFifteenCharsReturnsTrue      | U01.5: 15 characters is the largest allowed length (boundary)            |
 * | testAddUserPasswordSixteenCharsReturnsFalse     | U01.5: passwords can be at most 15 characters (just over the boundary)   |
 * | testAddUserPasswordNoNumberReturnsFalse         | U01.6: passwords need at least one number                                |
 * | testAddUserPasswordNoSpecialCharReturnsFalse    | U01.7: passwords need at least one special character                     |
 * | testAddUserPasswordNoUppercaseReturnsFalse      | U01.8: passwords need at least one uppercase letter                      |
 * | testAddUserPasswordNoLowercaseReturnsFalse      | U01.9: passwords need at least one lowercase letter                      |
 * | testAddUserEmptyZipCodeReturnsFalse             | U01.10: geographical location is required                                |
 * | testAddUserUnsupportedLanguageReturnsFalse      | U01.13: language must be English or Spanish                              |
 * | testAddUserSpanishLanguageReturnsTrue           | U01.13: Spanish is one of the two allowed languages                      |
 * | testAddVolunteerValidReturnsTrue                | a new volunteer with valid information should be accepted                |
 * | testAddVolunteerStoredAsVolunteer               | volunteers need volunteer abilities (accepting requests)                 |
 * | testAddVolunteerKeepsSkills                     | a volunteer's skill is used to match them to relief requests             |
 * | testAddVolunteerDuplicateOfUserReturnsFalse     | usernames have to be unique across users and volunteers                  |
 * | testAddVolunteerEmptyUsernameReturnsFalse       | U01.1 applies to volunteers too                                          |
 * | testAddVolunteerWeakPasswordReturnsFalse        | U01.5-U01.9 apply to volunteers too                                      |
 * | testAddVolunteerWithUserTypeUserStoresVolunteer | a volunteer saved with type USER is reloaded as a plain user and loses   |
 * |                                                 | their skills, so addVolunteer should always store type VOLUNTEER         |
 * +-------------------------------------------------+--------------------------------------------------------------------------+
 *
 */

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
    private static final String VALID_PASSWORD = "Relief#2026";

    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private User karley;

    /**
     * Every test starts with the same two people in memory:
     * a regular user (klong) and a CPR volunteer (bbowen).
     * Nothing is saved to the json files.
     */
    @BeforeEach
    public void setup() {
        users.clear();
        karley = new User("Karley", "Long", "klong", "Karley#2024", "klong@outlook.com", "07/23/1999",
                "437 Bull Street", "Columbia", "South Carolina", "29201", "English", false, UserType.USER);
        users.add(karley);
        users.add(new Volunteer("Bailey", "Bowen", "bbowen", "RelHe1p34!", "bbowen@gmail.com", "02/09/2003",
                "594 South St", "Columbia", "South Carolina", "29201", "English", false, UserType.VOLUNTEER,
                Skills.CPR, true, false, false));
    }

    @AfterEach
    public void tearDown() {
        users.clear();
    }

    /** Adds a regular user with valid data except for the fields passed in. */
    private boolean addUser(String username, String password, String firstName, String lastName,
            String zipCode, String language) {
        return userList.addUser(firstName, lastName, username, password, "user@gmail.com", "03/14/2001",
                "12 Pine Rd", "Columbia", "South Carolina", zipCode, language, false, UserType.USER);
    }

    /** Adds a regular user whose only possibly-invalid field is the username. */
    private boolean addUserWithUsername(String username) {
        return addUser(username, VALID_PASSWORD, "John", "Math", "29205", "English");
    }

    /** Adds a regular user whose only possibly-invalid field is the password. */
    private boolean addUserWithPassword(String password) {
        return addUser("jmath", password, "John", "Math", "29205", "English");
    }

    /** Adds a FLOOD_RESCUE volunteer with valid data except for the fields passed in. */
    private boolean addVolunteer(String username, String password, UserType userType) {
        return userList.addVolunteer("Tara", "Smith", username, password, "tsmith@gmail.com", "05/30/1995",
                "88 River Rd", "Columbia", "South Carolina", "29205", "English", false, userType,
                Skills.FLOOD_RESCUE, false, true, false);
    }

    // ---------- getUsers ----------

    @Test
    public void testGetUsersNeverNull() {
        assertNotNull(UserList.getInstance().getUsers());
    }

    @Test
    public void testGetUsersCount() {
        assertEquals(2, userList.getUsers().size());
    }

    // ---------- getUser(String username) ----------

    @Test
    public void testGetUserByUsernameFirstInList() {
        assertEquals("Karley", userList.getUser("klong").getFirstName());
    }

    @Test
    public void testGetUserByUsernameLastInList() {
        assertEquals("Bailey", userList.getUser("bbowen").getFirstName());
    }

    @Test
    public void testGetUserByUsernameUnknownReturnsNull() {
        assertNull(userList.getUser("nobody"));
    }

    @Test
    public void testGetUserByUsernameNullReturnsNull() {
        assertNull(userList.getUser((String) null));
    }

    // ---------- getUser(UUID uuid) ----------

    @Test
    public void testGetUserByUuidFound() {
        assertEquals("klong", userList.getUser(karley.getUuid()).getUsername());
    }

    @Test
    public void testGetUserByUuidUnknownReturnsNull() {
        assertNull(userList.getUser(UUID.randomUUID()));
    }

    @Test
    public void testGetUserByUuidNullReturnsNull() {
        assertNull(userList.getUser((UUID) null));
    }

    // ---------- addUser: valid and duplicate ----------

    @Test
    public void testAddUserValidReturnsTrue() {
        assertTrue(addUserWithUsername("jmath"));
    }

    @Test
    public void testAddUserValidIncreasesCount() {
        addUserWithUsername("jmath");
        assertEquals(3, users.size());
    }

    @Test
    public void testAddUserStoredAsRegularUser() {
        addUserWithUsername("jmath");
        assertFalse(userList.getUser("jmath") instanceof Volunteer);
    }

    @Test
    public void testAddUserDuplicateUsernameReturnsFalse() {
        assertFalse(addUserWithUsername("klong"));
    }

    @Test
    public void testAddUserDuplicateUsernameCountUnchanged() {
        addUserWithUsername("klong");
        assertEquals(2, users.size());
    }

    // ---------- addUser: required fields (U01.1 - U01.3, U01.10) ----------

    @Test
    public void testAddUserEmptyUsernameReturnsFalse() {
        assertFalse(addUserWithUsername(""));
    }

    @Test
    public void testAddUserNullUsernameReturnsFalse() {
        assertFalse(addUserWithUsername(null));
    }

    @Test
    public void testGetUserAfterNullUsernameAddedNoCrash() {
        addUserWithUsername(null);
        assertNull(userList.getUser("nobody"));
    }

    @Test
    public void testAddUserEmptyFirstNameReturnsFalse() {
        assertFalse(addUser("jmath", VALID_PASSWORD, "", "Math", "29205", "English"));
    }

    @Test
    public void testAddUserEmptyLastNameReturnsFalse() {
        assertFalse(addUser("jmath", VALID_PASSWORD, "John", "", "29205", "English"));
    }

    @Test
    public void testAddUserEmptyZipCodeReturnsFalse() {
        assertFalse(addUser("jmath", VALID_PASSWORD, "John", "Math", "", "English"));
    }

    // ---------- addUser: password rules (U01.4 - U01.9) ----------

    @Test
    public void testAddUserEmptyPasswordReturnsFalse() {
        assertFalse(addUserWithPassword(""));
    }

    @Test
    public void testAddUserPasswordSevenCharsReturnsFalse() {
        assertFalse(addUserWithPassword("Abc12#x"));
    }

    @Test
    public void testAddUserPasswordEightCharsReturnsTrue() {
        assertTrue(addUserWithPassword("Abc12#xy"));
    }

    @Test
    public void testAddUserPasswordFifteenCharsReturnsTrue() {
        assertTrue(addUserWithPassword("Abcdefgh12#xyzw"));
    }

    @Test
    public void testAddUserPasswordSixteenCharsReturnsFalse() {
        assertFalse(addUserWithPassword("Abcdefgh12#xyzwq"));
    }

    @Test
    public void testAddUserPasswordNoNumberReturnsFalse() {
        assertFalse(addUserWithPassword("Relief#Help"));
    }

    @Test
    public void testAddUserPasswordNoSpecialCharReturnsFalse() {
        assertFalse(addUserWithPassword("Relief2026"));
    }

    @Test
    public void testAddUserPasswordNoUppercaseReturnsFalse() {
        assertFalse(addUserWithPassword("relief#2026"));
    }

    @Test
    public void testAddUserPasswordNoLowercaseReturnsFalse() {
        assertFalse(addUserWithPassword("RELIEF#2026"));
    }

    // ---------- addUser: language (U01.13) ----------

    @Test
    public void testAddUserUnsupportedLanguageReturnsFalse() {
        assertFalse(addUser("jmath", VALID_PASSWORD, "John", "Math", "29205", "French"));
    }

    @Test
    public void testAddUserSpanishLanguageReturnsTrue() {
        assertTrue(addUser("jmath", VALID_PASSWORD, "John", "Math", "29205", "Spanish"));
    }

    // ---------- addVolunteer ----------

    @Test
    public void testAddVolunteerValidReturnsTrue() {
        assertTrue(addVolunteer("tsmith", VALID_PASSWORD, UserType.VOLUNTEER));
    }

    @Test
    public void testAddVolunteerStoredAsVolunteer() {
        addVolunteer("tsmith", VALID_PASSWORD, UserType.VOLUNTEER);
        assertTrue(userList.getUser("tsmith") instanceof Volunteer);
    }

    @Test
    public void testAddVolunteerKeepsSkills() {
        addVolunteer("tsmith", VALID_PASSWORD, UserType.VOLUNTEER);
        assertEquals(Skills.FLOOD_RESCUE, ((Volunteer) userList.getUser("tsmith")).getSkills());
    }

    @Test
    public void testAddVolunteerDuplicateOfUserReturnsFalse() {
        assertFalse(addVolunteer("klong", VALID_PASSWORD, UserType.VOLUNTEER));
    }

    @Test
    public void testAddVolunteerEmptyUsernameReturnsFalse() {
        assertFalse(addVolunteer("", VALID_PASSWORD, UserType.VOLUNTEER));
    }

    @Test
    public void testAddVolunteerWeakPasswordReturnsFalse() {
        assertFalse(addVolunteer("tsmith", "password", UserType.VOLUNTEER));
    }

    @Test
    public void testAddVolunteerWithUserTypeUserStoresVolunteer() {
        addVolunteer("tsmith", VALID_PASSWORD, UserType.USER);
        assertEquals(UserType.VOLUNTEER, userList.getUser("tsmith").getUserType());
    }
}