package com.location;

/**
 * Represents a physical address with street, city, state, and zip code.
 * @author Fantastic Four
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;
/**
     * Constructs an Address object with the specified street, city, state, and zip code.
     *
     * @param street the street address
     * @param city   the city
     * @param state  the state
     * @param zip    the zip code
     */
    public Address(String street, String city, String state, String zip){
        this.street = street;
        this. city = city;
        this.state = state;
        this.zip = zip;
    }
/**
     * Returns the street address.
     *
     * @return the street address
     */
    public String getStreet() {
        return street;
    }
/**
     * Returns the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }
/**
     * Returns the state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }
/**
     * Returns the zip code.
     *
     * @return the zip code
     */
    public String getZip() {
        return zip;
    }
/**
     * Returns the coordinates of the address using the MapTools utility.
     *
     * @return the coordinates of the address, or null if an error occurs
     */
    public Coordinates getCoordinates() {
        try {
            return MapTools.getInstance().getCoordinates(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
/**
     * Returns a string representation of the address in the format "street, city, state zip".
     *
     * @return a string representation of the address
     */
    public String toString() {
        return String.format("%s, %s, %s %s", street, city, state, zip);
    }
}
