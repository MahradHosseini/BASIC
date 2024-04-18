package com.basic.booking;

import com.basic.property.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a booking for a property with a start and end date, payment status, and associated property details.
 * Each booking is identified by a unique booking ID.
 *
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class Booking {
    /**
     * To keep track of ID sequence
     */
    private static int counter = 0;

    /**
     * The unique ID of each Booking
     */
    private int bookingID;

    /**
     * The Start Date of the booking
     */
    private LocalDate startDate;

    /**
     * The End Date of the booking
     */
    private LocalDate endDate;

    /**
     * To indicate payment status of the booking
     */
    private boolean isPaid;

    /**
     * To store the Property object to which this booking belongs
     */
    private Property property;

    /**
     * Default constructor that initializes a booking with a unique ID.
     */
    public Booking(){
        this.bookingID = ++counter;
    }

    /**
     * Constructor that initializes a booking with a specified property.
     *
     * @param property The property associated with the booking
     */
    public Booking(Property property){
        this.bookingID = ++counter;
        this.property = property;
    }

    /**
     * Constructor that initializes a booking with a property, start date, end date, and payment status.
     *
     * @param property The property associated with the booking
     * @param startDate The start date of the booking
     * @param endDate The end date of the booking
     * @param isPaid The payment status of the booking
     */
    public Booking(Property property, LocalDate startDate, LocalDate endDate, boolean isPaid){
        this.bookingID = ++counter;
        this.property = property;
        this.startDate = startDate;
        this.isPaid = isPaid;
        this.endDate = endDate;
    }

    // Getters and Setters

    /**
     * Gets the current booking count.
     *
     * @return the current count of bookings made
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Sets the booking counter to a new value.
     *
     * @param counter The new counter value
     */
    public static void setCounter(int counter) {
        Booking.counter = counter;
    }

    /**
     * Gets the unique ID for this booking.
     *
     * @return the booking ID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * Sets the unique ID for this booking.
     *
     * @param bookingID The new ID for the booking
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * Gets the start date of the booking.
     *
     * @return the start date of the booking
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the booking.
     *
     * @param startDate The new start date of the booking
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the end date of the booking.
     *
     * @return the end date of the booking
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the booking.
     *
     * @param endDate The new end date of the booking
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Checks if the booking has been paid for.
     *
     * @return the payment status of the booking
     */
    public boolean isPaid() {
        return isPaid;
    }

    /**
     * Sets the payment status of the booking.
     *
     * @param paid The new payment status
     */
    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Gets the property associated with this booking.
     *
     * @return the associated property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Sets the property associated with this booking.
     *
     * @param property The new property to associate with the booking
     */
    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * Calculates the total cost of the booking based on the number of days between start and end date and the price per day.
     *
     * @return the total cost of the booking
     */
    public double totalCost(){
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        return days * property.calculatePricePerDay();
    }

}
