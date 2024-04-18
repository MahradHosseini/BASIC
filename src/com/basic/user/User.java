package com.basic.user;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a user with personal details
 * Each user is uniquely identified by an automatically assigned user ID.
 *
 * @author Mahrad Hosseini
 * @version 2.0
 */
public abstract class User implements Serializable {
    /**
     * To generate unique user ID
     */
    private static int counter = 0;
    /**
     * The unique ID of the user
     */
    private int userID;
    /**
     * The First Name of the user
     */
    private String firstName;
    /**
     * The Last Name of the user
     */
    private String lastName;
    /**
     * Date of Birth of the user
     */
    private LocalDate dateOfBirth;
    /**
     * The date of registration of the user
     */
    private LocalDate registrationDate;


    /**
     * Initializes a user with first and last names, date of birth, and registration date.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param dateOfBirth The date of birth of the user
     * @param registrationDate the registration date of the user
     */
    public User(String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate){
        this.userID = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
    }

    /**
     * Initializes a user with first and last names, date of birth, and registration date.
     *
     * @param userID The ID of the user
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param dateOfBirth The date of birth of the user
     * @param registrationDate the registration date of the user
     */
    public User(int userID, String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        counter = userID;
    }

    public User() {

    }

    /**
     * Overrides the toString() method to include the detailed info of the user
     * @return The details of the user
     */
    public String toString(){
        return "\n" + "User ID: " + userID + "\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Date of Birth: " + dateOfBirth + "\n" +
                "Registration Date: " + registrationDate + "\n";
    }

    // Getters and Setters:

    /**
     * Gets the current count of users created.
     *
     * @return The current count of users
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Sets the user counter to a new value.
     *
     * @param counter The new value for the user counter
     */
    public static void setCounter(int counter) {
        User.counter = counter;
    }

    /**
     * Gets the unique ID of this user.
     *
     * @return The unique ID of the user
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the unique ID for this user.
     *
     * @param userID The new ID for the user
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The new first name for the user
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The new last name for the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the date of birth of the user.
     *
     * @return The date of birth of the user
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth for the user.
     *
     * @param dateOfBirth The new date of birth for the user
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the registration date
     * @return The registration date
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the registration date
     * @param registrationDate The registration date
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
