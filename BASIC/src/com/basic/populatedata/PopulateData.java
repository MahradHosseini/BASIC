package com.basic.populatedata;

import com.basic.booking.Booking;
import com.basic.property.*;
import com.basic.user.*;
import com.basic.system.BASIC;

import java.time.LocalDate;

/**
 * Represents a class of automatic data population.
 * Populates Users, Properties, Hosts, and Bookings.
 *
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class PopulateData {
    /**
     * Populates Customer
     * @param system    The main program
     */
    public static void populateCustomers (BASIC system){
        Gold gold1 = new Gold("Mahrad", "Hosseini", LocalDate.of(2000, 2, 8), LocalDate.of(2022,5,15), "Card", 2);
        Gold gold2 = new Gold("Homa", "Zabihi", LocalDate.of(2002, 9, 16), LocalDate.of(2022,6,5), "Cash", 3);
        Gold gold3 = new Gold("Mahila", "Hosseini", LocalDate.of(1997, 12, 18), LocalDate.of(2020,5,17), "Installment", 1);

        system.users.add(gold1);
        system.users.add(gold2);
        system.users.add(gold3);

        Standard standard1 = new Standard("Ali", "Mokri", LocalDate.of(2005, 6, 20), LocalDate.of(2012,5,25), "Card");
        Standard standard2 = new Standard("Sima", "Safi", LocalDate.of(1995, 8, 24), LocalDate.of(2016,7,19), "Cash");

        system.users.add(standard1);
        system.users.add(standard2);

    }

    /**
     * Populates Properties along with their Hosts
     * @param system    The main program
     */
    public static void populatePropertiesAndHosts (BASIC system){

        Host host1 = new Host("Akbar", "Hosseini", LocalDate.of(1970, 5, 10), LocalDate.of(2018,3,26), 111111);
        Host host2 = new Host("Ela", "Soltan", LocalDate.of(1978, 8, 14), LocalDate.of(2022, 6, 15), 111112);
        Host host3 = new Host("Roya", "Zabihi", LocalDate.of(1980, 7, 20), LocalDate.of(2021, 4, 20), 111113);

        system.users.add(host1);
        system.users.add(host2);
        system.users.add(host3);


        FullProperty fullProperty1 = new FullProperty(2, 2, "Iskele", 100, host2, 150);
        FullProperty fullProperty2 = new FullProperty(3, 3, "Girne", 300, host1, 435);

        // Adding Inspections to Property 1
        fullProperty1.addHashMap(LocalDate.of(2023,9,10), "Very Well Managed");
        fullProperty1.addHashMap(LocalDate.of(2023,10,15), "Awesome View");

        system.properties.add(fullProperty1);
        system.properties.add(fullProperty2);

        SharedProperty sharedProperty1 = new SharedProperty(2, 2, "Magusa", 200, host3);
        system.properties.add(sharedProperty1);

    }

    /**
     * Populates Bookings
     * @param system    The main program
     */
    public static void populateBookings (BASIC system){
        Booking booking1 = new Booking(system.findPropertyByID(1), LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 8), true);
        Booking booking2 = new Booking(system.findPropertyByID(2), LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10), false);
        Booking booking3 = new Booking(system.findPropertyByID(3), LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 15), true);
        Booking booking4 = new Booking(system.findPropertyByID(1), LocalDate.of(2023, 4, 1), LocalDate.of(2023, 4, 24), false);
        Booking booking5 = new Booking(system.findPropertyByID(2), LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 17), true);


        system.findCustomerByID(1).addBooking(booking1);
        system.findCustomerByID(2).addBooking(booking2);
        system.findCustomerByID(3).addBooking(booking3);
        system.findCustomerByID(4).addBooking(booking4);
        system.findCustomerByID(5).addBooking(booking5);
    }

    /**
     * Initializes the population process
     * @param system    The main program
     */
    public static void populate (BASIC system){
        populateCustomers(system);
        populatePropertiesAndHosts(system);
        populateBookings(system);
    }
}
