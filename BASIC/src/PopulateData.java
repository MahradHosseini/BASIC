import java.time.LocalDate;

/**
 * Represents a class of automatic data population.
 * Populates Users, Properties, Hosts, and Bookings.
 *
 * @author Mahrad Hosseini
 * @version 1.0
 */
public class PopulateData {
    /**
     * Populates users
     * @param system    The main program
     */
    public static void populateUsers (BASIC system){
        User user1 = new User("Mahrad", "Hosseini", LocalDate.of(2000, 2, 8));
        User user2 = new User("Homa", "Zabihi", LocalDate.of(2003, 7, 16));
        User user3 = new User("Mahila", "Hosseini", LocalDate.of(1997, 5, 25));

        system.users.add(user1);
        system.users.add(user2);
        system.users.add(user3);
    }

    /**
     * Populates Properties along with their Hosts
     * @param system    The main program
     */
    public static void populateProperties (BASIC system){
        Property property1 = new Property(1, 2, "New York", 100, new Host("Akbar", "Hosseini", LocalDate.of(2023, 5, 10)));
        Property property2 = new Property(2, 3, "Los Angeles", 150, new Host("Ela", "Soltan", LocalDate.of(2022, 6, 15)));
        Property property3 = new Property(3, 1, "Chicago", 80, new Host("Roya", "Zabihi", LocalDate.of(2021, 4, 20)));

        system.properties.add(property1);
        system.properties.add(property2);
        system.properties.add(property3);
    }

    /**
     * Populates Bookings
     * @param system    The main program
     */
    public static void populateBookings (BASIC system){
        Booking booking1 = new Booking(system.findPropertyByID(1), LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 10), true);
        Booking booking2 = new Booking(system.findPropertyByID(2), LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 10), true);
        Booking booking3 = new Booking(system.findPropertyByID(3), LocalDate.of(2023, 8, 1), LocalDate.of(2023, 8, 10), false);

        system.findUserByID(1).addBooking(booking1);
        system.findUserByID(2).addBooking(booking2);
        system.findUserByID(3).addBooking(booking3);
    }

    /**
     * Initializes the population process
     * @param system    The main program
     */
    public static void populate (BASIC system){
        populateUsers(system);
        populateProperties(system);
        populateBookings(system);
    }
}
