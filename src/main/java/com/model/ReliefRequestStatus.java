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

    private ReliefRequestStatus(String str) {
        this.str = str;
    }
 }