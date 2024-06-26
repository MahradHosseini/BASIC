package com.basic.user;

import java.time.LocalDate;
import java.time.Period;

/**
 * Represents the Standard Customer
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class Standard extends Customer{
    /**
     * The default constructor
     */
    public Standard(){}

    /**
     * Constructs the object with preferred method of payment
     * @param preferredPaymentMethod Preferred method of payment
     */
    public Standard(String preferredPaymentMethod){
        super(preferredPaymentMethod);
    }

    /**
     * Constructs the object with first name, last name, date of birth, registration date, and preferred payment method
     * @param userID The ID of the user
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param dateOfBirth Date of birth of the customer
     * @param registrationDate Registration date of the customer
     * @param preferredPaymentMethod Customer's preferred method of payment
     */
    public Standard(int userID, String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, String preferredPaymentMethod){
        super(userID, firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod);
    }

    /**
     * Constructs the object with first name, last name, date of birth, registration date, and preferred payment method
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param dateOfBirth Date of birth of the customer
     * @param registrationDate Registration date of the customer
     * @param preferredPaymentMethod Customer's preferred method of payment
     */
    public Standard(String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, String preferredPaymentMethod){
        super(firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod);
    }

    /**
     * Overrides the toString() method to include other details of the user like User type
     * @return The wholesome detail of the Gold Customer
     */
    public String toString(){
        return super.toString() + "\n" +
                "User Type: Standard";
    }

    public double discountPercentage(){
        LocalDate registrationDate = getRegistrationDate();
        LocalDate currentDate = LocalDate.now();

        Period period = Period.between(registrationDate, currentDate);
        int yearsDiff = period.getYears();

        if (yearsDiff >= 10) {
            return 0.98;
        }else{
            return 1;
        }
    }

}
