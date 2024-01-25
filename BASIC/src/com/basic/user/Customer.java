package com.basic.user;

import com.basic.booking.Booking;
import com.basic.property.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Customers who can later make a booking
 * @author Mahrad Hosseini
 * @version 2.0
 */
public abstract class Customer extends User {
    /**
     * The preferred payment method of the customer
     */
    private String preferredPaymentMethod;
    /**
     * The list of bookings made by the customer
     */
    private List<Booking> bookings;

    /**
     * The default constructor
     */
    public Customer() {
    }

    /**
     * Constructs the object with a preferred method of payment
     * @param preferredPaymentMethod
     */
    public Customer(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
        this.bookings = new ArrayList<>();
    }

    /**
     * Constructs the object with first name, last name, date of birth, registration date, and preferred payment method
     * @param firstName The first name of the customer
     * @param lastName The last name of the customer
     * @param dateOfBirth Date of birth of the customer
     * @param registrationDate Registration date of the customer
     * @param preferredPaymentMethod Customer's preferred method of payment
     */
    public Customer(String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, String preferredPaymentMethod) {
        super(firstName, lastName, dateOfBirth, registrationDate);
        this.preferredPaymentMethod = preferredPaymentMethod;
        this.bookings = new ArrayList<>();
    }

    /**
     * Overrides the toString() method to include other details of the user like preferred payment method
     * @return The wholesome detail of the Customer
     */
    public String toString() {
        return super.toString() +
                "Preferred Payment Method: " + preferredPaymentMethod;
    }

    /**
     * Gets the list of customer bookings
     * @return List of Customer's Bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Adds a booking to the user's list of bookings.
     *
     * @param property The property for which the booking is made
     */
    public void addBooking(Property property) {
        Scanner scanner = new Scanner(System.in);
        LocalDate startDate = null;
        LocalDate endDate = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (startDate == null) {
            System.out.print("Enter start date (dd/MM/yyyy): ");
            try {
                startDate = LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid Date Format ");
            }
        }

        while (endDate == null) {
            System.out.print("Enter end date (dd/MM/yyyy): ");
            try {
                endDate = LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid Date Format ");
            }
        }

        System.out.println("Is it paid? (Yes = 1, No = 0)");

        boolean isPaid = (scanner.nextInt() == 1);

        Booking booking = new Booking(property, startDate, endDate, isPaid);
        bookings.add(booking);

        System.out.println("Booking has successfully been added");
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public double discountPercentage() {
        if (this instanceof Gold) {
            if (((Gold) this).getGoldLevel() == 1) {
                return 0.99;
            } else if (((Gold) this).getGoldLevel() == 2) {
                return 0.98;
            } else if (((Gold) this).getGoldLevel() == 3) {
                return 0.97;
            }
        } else if (this instanceof Standard) {
            LocalDate registrationDate = getRegistrationDate();
            LocalDate currentDate = LocalDate.now();

            Period period = Period.between(registrationDate, currentDate);
            int yearsDiff = period.getYears();

            if (yearsDiff >= 10) {
                return 0.98;
            }
        }
        return 1;
    }

    /**
     * Gets the Customer's preferred method of payment
     * @return The Customer's preferred method of payment
     */
    public String getPreferredPaymentMethod() {
        return preferredPaymentMethod;
    }

    /**
     * Sets the Customer's preferred method of payment
     * @param preferredPaymentMethod the Customer's preferred method of payment
     */
    public void setPreferredPaymentMethod(String preferredPaymentMethod) {
        this.preferredPaymentMethod = preferredPaymentMethod;
    }

    /**
     * Sets the Customer's list of bookings
     * @param bookings The customer's list of bookings
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
