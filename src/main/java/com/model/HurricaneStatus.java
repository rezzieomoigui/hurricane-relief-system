package com.model;

/**
 * HurricaneStatus enum specific the status of the hurricane
 * @author Fantastic Four
 */
public enum HurricaneStatus {
    APPROACHING("APPROACHING"),
    ACTIVE("ACTIVE"),
    DISSIPATED("DISSIPATED"),
    LEAVING("LEAVING");


    public String str;
/**
 * HurricaneStatus enum specific the status of the hurricane
 * @param str string related to the enum value
 */
    private HurricaneStatus(String str) {
        this.str = str;
    }
}