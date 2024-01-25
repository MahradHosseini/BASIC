package com.basic.property;

import com.basic.user.Host;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Represents the Shared Property
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class SharedProperty extends Property{


    /**
     * Constructs the object with number of bedrooms, number of rooms, city, and price per day
     * @param noBedRooms Number of bedrooms
     * @param noRooms Number of rooms
     * @param city The name of the city where it is located
     * @param pricePerDay The price per day
     */
    public  SharedProperty(int propertyID, int noBedRooms, int noRooms, String city, double pricePerDay){
        super(propertyID, noBedRooms, noRooms, city, pricePerDay);
    }

    /**
     * Constructs the object with number of bedrooms, number of rooms, city, price per day,
     * and host
     * @param noBedRooms Number of bedrooms
     * @param noRooms Number of rooms
     * @param city The name of the city where it is located
     * @param pricePerDay The price per day
     * @param host The Host object who owns this Property
     */
    public SharedProperty(int noBedRooms, int noRooms, String city, double pricePerDay, Host host){
        super(noBedRooms, noRooms, city, pricePerDay, host);
    }

    /**
     * Constructs the object with number of bedrooms, number of rooms, city, price per day,
     * and host
     * @param noBedRooms Number of bedrooms
     * @param noRooms Number of rooms
     * @param city The name of the city where it is located
     * @param pricePerDay The price per day
     * @param host The Host object who owns this Property
     * @param inspection The inspection HashMap
     */
    public SharedProperty(int noBedRooms, int noRooms, String city, double pricePerDay, Host host, HashMap<LocalDate, String> inspection){
        super(noBedRooms, noRooms, city, pricePerDay, host, inspection);
    }

    /**
     * Overrides the toString() method in its superclass to include other fields such as
     * Property type
     * @return The wholesome details of the property
     */
    public String toString(){
        return super.toString() +
                "Property Type: Shared Property" + "\n";
    }

    /**
     * Overrides the calculatePricePerDay() method in PropertyPrice interface
     * To calculate the price of the shared property based on the number of its bedrooms
     * @return The modified price per day
     */
    @Override
    public double calculatePricePerDay() {
        return (getPricePerDay()/getNoBedRooms());
    }


}
