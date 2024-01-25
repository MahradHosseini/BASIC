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
     * The default constructor
     */
    public Host(){}

    /**
     * Constructs the object with a tax number
     * @param taxNumber The tax number of the Host
     */
    public Host(double taxNumber){
        this.taxNumber = taxNumber;
    }

    /**
     * Constructor that initializes a host with first and last names.
     *
     * @param firstName The first name of the host
     * @param lastName The last name of the host
     */
    public Host(String firstName, String lastName){
        super(firstName, lastName);
    }

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
