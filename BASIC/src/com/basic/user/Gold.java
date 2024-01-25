package com.basic.user;

import java.time.LocalDate;

/**
 * Represents the Gold Customer
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class Gold extends Customer{
    /**
     * The Level of the Gold Customer
     */
    private int goldLevel;

    /**
     * Constructs the object with the level
     * @param goldLevel The level of the Gold Customer
     */
    public Gold(int goldLevel){
        this.goldLevel = goldLevel;
    }

    /**
     * Constructs the object with the preferred method of payment
     * @param preferredPaymentMethod the preferred method of payment
     */
    public Gold(String preferredPaymentMethod) {
        super(preferredPaymentMethod);
    }

    /**
     * Constructs the object with first name, last name, date of birth, registration date, preferred payment method, and Gold level
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param dateOfBirth Date of birth of the customer
     * @param registrationDate Registration date of the customer
     * @param preferredPaymentMethod Customer's preferred method of payment
     * @param goldLevel The Customers gold level
     */
    public Gold(String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, String preferredPaymentMethod, int goldLevel){
        super(firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod);
        this.goldLevel = goldLevel;
    }
    /**
     * Overrides the toString() method to include other details of the user like User type and gold level
     * @return The wholesome detail of the Gold Customer
     */
    public String toString(){
        return super.toString() + "\n" +
                "User Type: Gold" + "\n" +
                "Gold Level: " + goldLevel;
    }

    /**
     * Gets gold level
     * @return Gold level
     */
    public int getGoldLevel() {
        return goldLevel;
    }

    /**
     * Sets the gold level
     * @param goldLevel The gold level
     */
    public void setGoldLevel(int goldLevel) {
        this.goldLevel = goldLevel;
    }
}
