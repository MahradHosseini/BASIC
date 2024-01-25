package com.basic.property;

import com.basic.user.Host;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Represents a property with details such as number of bedrooms, rooms, city, and price per day.
 * Each property is associated with a host and an Inspection (Not compulsory).
 *
 * @author Mahrad Hosseini
 * @version 2.0
 */
public abstract class Property implements PropertyPrice, Comparable<Property> {
    /**
     * To keep track of the ID sequence
     */
    private static int counter = 0;
    /**
     * The unique ID of each property
     */
    private int propertyID;
    /**
     * The number of bedrooms
     */
    private int noBedRooms;
    /**
     * The number of rooms
     */
    private int noRooms;
    /**
     * The name of the city where the Property is located
     */
    private String city;
    /**
     * The price of the Property for each day
     */
    private double pricePerDay;
    /**
     * The Inspection comment made on the Property
     * Each Property can have up to 1 Inspection per day
     * The key for the HashMap is the current date
     */
    private final HashMap<LocalDate, String> inspection;
    /**
     * The Host object who owns this Property
     */
    private Host host;

    /**
     * Default constructor that initializes a property with a unique ID and HashMap.
     */
    public Property() {
        this.propertyID = ++counter;
        this.inspection = new HashMap<>();
    }

    /**
     * Constructor that initializes a property with a specified number of bedrooms and rooms.
     *
     * @param noBedRooms the number of bedrooms
     * @param noRooms    the total number of rooms
     */
    public Property(int noBedRooms, int noRooms) {
        this.propertyID = ++counter;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.inspection = new HashMap<>();
    }

    /**
     * Constructor that initializes a property with the specified number of bedrooms, rooms,
     * city, and price per day.
     *
     * @param noBedRooms  the number of bedrooms
     * @param noRooms     the total number of rooms
     * @param city        the city where the property is located
     * @param pricePerDay the price per day for renting the property
     */
    public Property(int noBedRooms, int noRooms, String city, double pricePerDay) {
        this.propertyID = ++counter;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.city = city;
        this.pricePerDay = pricePerDay;
        this.inspection = new HashMap<>();
    }

    /**
     * Constructor that initializes a property with the specified number of bedrooms, rooms,
     * city, price per day, and host.
     *
     * @param noBedRooms  the number of bedrooms
     * @param noRooms     the total number of rooms
     * @param city        the city where the property is located
     * @param pricePerDay the price per day for renting the property
     * @param host        the host who owns the property
     */
    public Property(int noBedRooms, int noRooms, String city, double pricePerDay, Host host) {
        this.propertyID = ++counter;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.city = city;
        this.pricePerDay = pricePerDay;
        this.host = host;
        this.inspection = new HashMap<>();
    }

    /**
     * Adds a new row to the Inspection HashMap, used for Data Population purposes.
     * @param key The key of the HashMap as a LocalDate
     * @param text The Inspection Text
     */
    public void addHashMap(LocalDate key, String text) {
        this.inspection.put(key, text);
    }

    /**
     * Overrides the toString() method to print the details of the Property
     * @return Property details as a String
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n").append("Property ID: ").append(propertyID).append("\n")
                .append("Number of Bedrooms: ").append(noBedRooms).append("\n")
                .append("Number of Rooms: ").append(noRooms).append("\n")
                .append("City: ").append(city).append("\n")
                .append("Price Per Day: ").append(pricePerDay).append("$").append("\n")
                .append("Host: ").append(host.getFirstName()).append(" ").append(host.getLastName()).append("\n")
                .append("Host ID: ").append(host.getUserID()).append("\n")
                .append("Inspections: \n");

        if (inspection.isEmpty()) {
            result.append("No Inspections Have Been Added Yet\n");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

            for (Map.Entry<LocalDate, String> entry : inspection.entrySet()) {
                result.append(entry.getKey().format(formatter)).append(" - \"")
                        .append(entry.getValue()).append("\";\n");
            }
        }

        return result.toString();
    }


    /**
     * Adds an Inspection Text to the map using current date as the key
     * @param inspectionText The Inspection text that is to be added
     */
    public void addInspection(String inspectionText) {
        LocalDate currentDate = LocalDate.now();

        if (inspection.containsKey(currentDate)) {
            System.out.println("An Inspection Exists For The Current Date! ");
        } else {
            inspection.put(currentDate, inspectionText);
        }
    }

    // Getters and Setters

    /**
     * Gets the unique counter for properties.
     *
     * @return the current count of properties created
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Sets the property counter to a new value.
     *
     * @param counter the new counter value
     */
    public static void setCounter(int counter) {
        Property.counter = counter;
    }

    /**
     * Gets the unique ID for this property.
     *
     * @return the property ID
     */
    public int getPropertyID() {
        return propertyID;
    }

    /**
     * Sets the unique ID for this property.
     *
     * @param propertyID the new ID for the property
     */
    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    /**
     * Gets the number of bedrooms in this property.
     *
     * @return the number of bedrooms
     */
    public int getNoBedRooms() {
        return noBedRooms;
    }

    /**
     * Sets the number of bedrooms in this property.
     *
     * @param noBedRooms the new number of bedrooms
     */
    public void setNoBedRooms(int noBedRooms) {
        this.noBedRooms = noBedRooms;
    }

    /**
     * Gets the total number of rooms in this property.
     *
     * @return the total number of rooms
     */
    public int getNoRooms() {
        return noRooms;
    }

    /**
     * Sets the total number of rooms in this property.
     *
     * @param noRooms the new total number of rooms
     */
    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    /**
     * Gets the city where this property is located.
     *
     * @return the city of the property
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city where this property is located.
     *
     * @param city the new city of the property
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the price per day for renting this property.
     *
     * @return the price per day
     */
    public double getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Sets the price per day for renting this property.
     *
     * @param pricePerDay the new price per day
     */
    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Gets the host associated with this property.
     *
     * @return the host
     */
    public Host getHost() {
        return host;
    }

    /**
     * Sets the host associated with this property.
     *
     * @param host the new host
     */
    public void setHost(Host host) {
        this.host = host;
    }

    /**
     * Sets the price of the property per day
     * @param pricePerDay The price of the property per day
     */
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    /**
     * Gets the Inspection HashMap
     * @return The Inspection HashMap
     */
    public HashMap<LocalDate, String> getInspection() {
        return inspection;
    }

    /**
     * Compares two Properties based on their price per day
     * @param otherProperty the object to be compared.
     * @return The result of the comparison
     */
    public int compareTo(Property otherProperty) {
        return Double.compare(this.calculatePricePerDay(), otherProperty.calculatePricePerDay());
    }
}
