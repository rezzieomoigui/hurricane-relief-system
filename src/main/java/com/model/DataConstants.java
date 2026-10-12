package com.model;

/**
 * DataConstants are the data constants that are added in from the json files
 * @author Fantastic Four
 */
public abstract class DataConstants {
    protected static final String SHELTER_FILE_NAME = "json_files/shelter.json";
    protected static final String SHELTER_UUID = "uuid";
    protected static final String SHELTER_ADDRESS = "address";
    protected static final String SHELTER_ZIP_CODE = "zipCode";
    protected static final String SHELTER_SHELTER_TYPE = "shelterType";
    protected static final String SHELTER_SHELTER_STATUS = "shelterStatus";
    protected static final String SHELTER_SHELTER_RESOURCES = "shelterResources";
    protected static final String SHELTER_VISUAL_DESCRIPTION = "visualDescription";

    protected static final String USER_FILE_NAME = "json_files/users.json";
    protected static final String USER_UUID = "uuid";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_PASSWORD = "password";
    protected static final String USER_EMAIL = "email";
    protected static final String USER_DATE_OF_BIRTH = "dateOfBirth";
    protected static final String USER_ADDRESS = "address";
    protected static final String USER_ZIP_CODE = "zipCode";
    protected static final String USER_LANGUAGE = "language";
    protected static final String USER_IS_ADMIN = "isAdmin";

    protected static final String RELIEF_REQUEST_FILE_NAME = "json_files/reliefRequest.json";
    protected static final String RELIEF_REQUEST_UUID = "uuid";
    protected static final String RELIEF_REQUEST_PARTY_MEMBERS = "partyMembers";
    protected static final String RELIEF_REQUEST_IS_INDOORS = "isIndoors";
    protected static final String RELIEF_REQUEST_IS_MOBILE = "isMobile";
    protected static final String RELIEF_REQUEST_IS_INJURED = "isInjured";
    protected static final String RELIEF_REQUEST_STATUS = "status";
    protected static final String RELIEF_REQUEST_PRIORITY = "priority";
    protected static final String RELIEF_REQUEST_ESTIMATED_ARRIVAL_MINUTES = "estimatedArrivalMinutes";
    protected static final String RELIEF_REQUEST_STAYING_HOME = "stayingHome";

    protected static final String VOLUNTEER_FILE_NAME = "json_files/volunteer.json";
    protected static final String VOLUNTEER_SKILLS = "skills";
    protected static final String VOLUNTEER_FIRST_RESPONDER = "firstResponder";
    protected static final String VOLUNTEER_CAN_SWIM = "canSwim";
    protected static final String VOLUNTEER_IS_SHELTER_OWNER = "isShelterOwner";
}
