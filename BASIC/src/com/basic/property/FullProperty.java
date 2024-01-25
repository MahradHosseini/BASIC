package com.basic.property;

import com.basic.user.Host;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;

/**
 * Represents a Full Property
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class FullProperty extends Property{
    /**
     * The size of the Property in square meter
     */
    private double propertySize;


    /**
     * Constructs the object with given values such as number of bedrooms, number of rooms
     * name of the city, price per day, host, and property size
     * @param noBedRooms Number of bedrooms
     * @param noRooms Number of rooms
     * @param city The name the city where it is located
     * @param pricePerDay Price per day
     * @param propertySize The size of the property
     */
    public FullProperty(int propertyID, int noBedRooms, int noRooms, String city, double pricePerDay, double propertySize){
        super(propertyID, noBedRooms, noRooms, city, pricePerDay);
        this.propertySize = propertySize;
    }

    /**
     * Constructs the object with given values such as number of bedrooms, number of rooms
     * name of the city, price per day, host, and property size
     * @param noBedRooms Number of bedrooms
     * @param noRooms Number of rooms
     * @param city The name the city where it is located
     * @param pricePerDay Price per day
     * @param host The Host object who owns the Property
     * @param propertySize The size of the property
     */
    public FullProperty(int noBedRooms, int noRooms, String city, double pricePerDay, Host host, double propertySize){
        super(noBedRooms, noRooms, city, pricePerDay, host);
        this.propertySize = propertySize;
    }

    /**
     * Constructs the object with given values such as number of bedrooms, number of rooms
     * name of the city, price per day, host, and property size
     * @param noBedRooms Number of bedrooms
     * @param noRooms Number of rooms
     * @param city The name the city where it is located
     * @param pricePerDay Price per day
     * @param host The Host object who owns the Property
     * @param propertySize The size of the property
     * @param inspection The inspection HashMap
     */
    public FullProperty(int noBedRooms, int noRooms, String city, double pricePerDay, Host host, double propertySize, HashMap<LocalDate, String> inspection){
        super(noBedRooms, noRooms, city, pricePerDay, host, inspection);
        this.propertySize = propertySize;
    }

    /**
     * Overrides the toString() method in its superclass to include other fields such as
     * Property type and size
     * @return The wholesome details of the property
     */
    public String toString(){
        return super.toString() +
                "Property Type: Full Property" + "\n" +
                "Property Size: " + propertySize;
    }

    /**
     * Gets the size of the property
     * @return The size of the property
     */
    public double getPropertySize() {
        return propertySize;
    }

    /**
     * Sets the size of the property
     * @param propertySize The size of the property
     */
    public void setPropertySize(double propertySize) {
        this.propertySize = propertySize;
    }

    /**
     * Overrides the calculatePricePerDay() method in PropertyPrice interface
     * To calculate the price of the full property based on its size
     * @return The modified price per day
     */
    @Override
    public double calculatePricePerDay() {
        if(propertySize < 200){
            return (getPricePerDay() * 1.01);
        } else if (propertySize > 300) {
            return (getPricePerDay() * 1.04);
        }else{
            return (getPricePerDay() * 1.03);
        }


    }

}
