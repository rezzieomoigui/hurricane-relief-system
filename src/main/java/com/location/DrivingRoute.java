package com.location;
/**
 * Represents a driving route with a starting location, ending location, and a list of route points.
 * @author Fantastic Four
 */
import java.util.ArrayList;
/**
 * Represents a driving route with a starting location, ending location, and a list of route points.
 * @author Fantastic Four
 */
public class DrivingRoute {
/**
     * The starting location of the driving route.
     */
    private final MapLocation startingLocation;
    /**
     * The ending location of the driving route.
     */
    private final MapLocation endingLocation;
    /**
     * The list of coordinates representing the route points.
     */
    private final ArrayList<Coordinates> routePoints;
/**
     * Constructs a DrivingRoute object with the specified starting location, ending location, and route points.
     *
     * @param startingLocation the starting location of the route
     * @param endingLocation   the ending location of the route
     * @param routePoints      the list of coordinates representing the route points
     */
    public DrivingRoute(
            MapLocation startingLocation,
            MapLocation endingLocation,
            ArrayList<Coordinates> routePoints
    ) {
        this.startingLocation = startingLocation;
        this.endingLocation = endingLocation;
        this.routePoints = routePoints;
    }
/**
     * Returns the starting location of the driving route.
     *
     * @return the starting location
     */
    public MapLocation getStartingLocation() {
        return startingLocation;
    }
/**
     * Returns the ending location of the driving route.
     *
     * @return the ending location
     */
    public MapLocation getEndingLocation() {
        return endingLocation;
    }
/**
     * Returns the list of coordinates representing the route points.
     *
     * @return the list of route points
     */
    public ArrayList<Coordinates> getRoutePoints() {
        return routePoints;
    }
}