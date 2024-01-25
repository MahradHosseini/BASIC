package com.basic.user;

import java.time.LocalDate;

/**
 * Represents the host of a property
 *
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class Host extends User {
    /**
     * The host's tax number
     */
    private double taxNumber;


    /**
     * Constructor that initializes a host with first and last names, date of birth, registration date, and tax number
     *
     * @param firstName The first name of the host
     * @param lastName The last name of the host
     * @param dateOfBirth The date of birth of the host
     * @param registrationDate The date when the host was registered
     * @param taxNumber The host's tax number
     */
    public Host(String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, double taxNumber){
        super(firstName, lastName, dateOfBirth, registrationDate);
        this.taxNumber = taxNumber;
    }

    /**
     * Constructor that initializes a host with first and last names, date of birth, registration date, and tax number
     *
     * @param userID The ID of the user
     * @param firstName The first name of the host
     * @param lastName The last name of the host
     * @param dateOfBirth The date of birth of the host
     * @param registrationDate The date when the host was registered
     * @param taxNumber The host's tax number
     */
    public Host(int userID, String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, double taxNumber){
        super(userID, firstName, lastName, dateOfBirth, registrationDate);
        this.taxNumber = taxNumber;
    }

    public Host() {
        super();
    }

    /**
     * Overrides the toString() method to include other details of the user like user type and tax number
     * @return The wholesome detail of the Customer
     */
    public String toString(){
        return super.toString() +
                "User Type: Host" + "\n" +
                "Tax Number: " + String.format("%.0f", taxNumber);
    }

    /**
     * Gets host's tax number
     * @return Tax Number
     */
    public double getTaxNumber() {
        return taxNumber;
    }

    /**
     * Sets host's tax number
     * @param taxNumber tax number
     */
    public void setTaxNumber(double taxNumber) {
        this.taxNumber = taxNumber;
    }
}
