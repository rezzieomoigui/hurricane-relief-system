package com.location;
/**
 * Represents geographical coordinates with latitude and longitude.
 * @author Fantastic Four
 */
public class Coordinates {
    private double latitude;
    private double longitude;
/**
     * Constructs a Coordinates object with the specified latitude and longitude.
     *
     * @param latitude  the latitude
     * @param longitude the longitude
     */
    public Coordinates(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }
/**
     * Returns the latitude of the coordinates.
     *
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

/**
     * Returns the longitude of the coordinates.
     *
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }
/**
     * Returns a string representation of the coordinates in the format "latitude, longitude".
     *
     * @return a string representation of the coordinates
     */
    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}
