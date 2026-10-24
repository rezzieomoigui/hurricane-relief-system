package com.model;

/**
 * PartyMember Class creates party members of type person
 * Prevents all party members from having to be users of system
 * @author Fantastic Four
 */
public class PartyMember {
    //private UUID partyMemberID;
    //private String firstName;
    private String name;
    private String yearBorn;
    private boolean needsMedicalAssistance;
    private Being type;
    //private String email;

    /**
     * PartyMember constructor instantiates a new party member
     * @param personID party member ID
     * @param firstName party member first name
     * @param lastName party member last name
     * @param yearBorn year party member was born
     * @param needsMedicalAssistance does specific party member need medical assistance
     * @param type party member type (cat, dog, or person)
     * @param email party member email
     * @param personType person Type
     */
    public PartyMember(String name, String yearBorn, boolean needsMedicalAssistance, Being type) {
        //super(personID, firstName, lastName, email, personType);
        this.name = name;
        this.yearBorn = yearBorn;
        this.needsMedicalAssistance = needsMedicalAssistance;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    /**
     * getYearBorn gets the yer the party member was born
     * @return year party member was born
     */
    public String getYearBorn() {
        return yearBorn;
    }

    /**
     * getNeedsMedicalAssistance checks if the specific party member needs medical assitance
     * @return boolean based on whether or not the specific party member needs medical assistance
     */
    public boolean needsMedicalAssistance() {
        return needsMedicalAssistance;
    }

    /**
     * getType gets the type of being
     * @return party member type (cat, dog, or person)
     */
    public Being getType() {
        return type;
    }
    
}
