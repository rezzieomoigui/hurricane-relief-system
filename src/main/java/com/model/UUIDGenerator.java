package com.model;
import java.util.UUID;

/**
 * UUIDGenerator generates UUIDs for classes
 */
public class UUIDGenerator {
    /**
     * main method generates the UUIDs
     * @param args String array standard arguments
     */
    public static void main(String[] args) {
        UUID id = UUID.randomUUID();
        System.out.println(id);
    }
}


