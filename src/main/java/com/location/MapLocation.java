package com.location;

/*
 * Represents one address after it has been converted
 * into latitude and longitude coordinates.
 */
public class MapLocation {
/**
     * The physical address of the location.
     */
    private final Address address;
    private final Coordinates coordinates;
/**
     * Constructs a MapLocation object with the specified address and coordinates.
     *
     * @param address     the physical address of the location
     * @param coordinates the geographical coordinates of the location
     */
    public MapLocation(Address address, Coordinates coordinates) {
        this.address = address;
        this.coordinates = coordinates;
    }
/**
     * Returns the physical address of the location.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }
/**
     * Returns the geographical coordinates of the location.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }
/**
     * Returns a string representation of the MapLocation, which is the string representation of the address.
     *
     * @return a string representation of the MapLocation
     */
    @Override
    public String toString() {
        return address.toString();
    }
}