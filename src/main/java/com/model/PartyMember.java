package com.model;

/**
 * PartyMember Class creates party members of type person
 * Prevents all party members from having to be users of system
 * @author Fantastic Four
 */
public class PartyMember {
    private String name;
    private String yearBorn;
    private boolean needsMedicalAssistance;
    private Being type;

    /**
     * PartyMember constructor instantiates a new party member
     * @param name party member name
     * @param yearBorn year party member was born
     * @param needsMedicalAssistance does specific party member need medical assistance
     * @param type party member type (cat, dog, or person)
     */
    public PartyMember(String name, String yearBorn, boolean needsMedicalAssistance, Being type) {
        this.name = name;
        this.yearBorn = yearBorn;
        this.needsMedicalAssistance = needsMedicalAssistance;
        this.type = type;
    }

    /**
     * getName gets the name of the party member
     * @return party member name
     */
    public String getName() {
        return this.name;
    }

    /**
     * getYearBorn gets the yer the party member was born
     * @return year party member was born
     */
    public String getYearBorn() {
        return this.yearBorn;
    }

    /**
     * getNeedsMedicalAssistance checks if the specific party member needs medical assitance
     * @return boolean based on whether or not the specific party member needs medical assistance
     */
    public boolean getNeedsMedicalAssistance() {
        return this.needsMedicalAssistance;
    }

    /**
     * getType gets the type of being
     * @return party member type (cat, dog, or person)
     */
    public Being getType() {
        return this.type;
    }
    
}
