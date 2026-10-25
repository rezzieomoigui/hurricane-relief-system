package com.model;

/**
 * ReliefRequestStatus enum specifies the status of the relief request
 * @author Fantastic Four
 */
public enum ReliefRequestStatus {
    SUBMITTED("SUBMITTED"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    ASSIGNED("ASSIGNED");

    public String str;
    /**
     * ReliefRequestStatus constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ReliefRequestStatus(String str) {
        this.str = str;
    }
 }