package com.model;

/**
 * ReliefRequestStatus enum specifies the status of the relief request
 * @author Fantastic Four
 */
public enum ReliefRequestStatus {
    SUBMITTED("Submitted"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    REVIEWED("Reviewed");

    public String str;
    /**
     * ReliefRequestStatus constructor sets the string to the enum value
     * @param str string related to the enum value
     */
    private ReliefRequestStatus(String str) {
        this.str = str;
    }
 }