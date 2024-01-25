package com.basic.system;

import com.basic.dataio.DataIO;
import com.basic.exception.*;
import com.basic.booking.Booking;
import com.basic.user.*;
import com.basic.property.*;
import com.basic.populatedata.PopulateData;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the main application with user and property management capabilities.
 *
 * @author Mahrad Hosseini
 * @version 2.0
 */
public class BASIC {
    /**
     * To keep a list of Users
     */
    public ArrayList<User> users;
    /**
     * To keep a list of Properties
     */
    public ArrayList<Property> properties;
    public Scanner scanner;

    /**
     * Default constructor initializing empty lists for users and properties
     * and a new scanner object for input handling.
     */
    public BASIC() {
        this.users = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a BASIC object with a predefined list of properties.
     *
     * @param properties the initial list of properties.
     */
    public BASIC(ArrayList<Property> properties) {
        this.users = new ArrayList<>();
        this.properties = properties;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a BASIC object using an existing scanner.
     *
     * @param scanner the scanner object to be used for input handling.
     */
    public BASIC(Scanner scanner) {
        this.users = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.scanner = scanner;
    }

    // Validators

    /**
     * Validates the input number to make sure it is positive
     *
     * @param input The number input
     * @throws NegativeInputException If the number is not positive will be thrown
     */
    public static void validatePositiveInput(int input) throws NegativeInputException {
        if (input <= 0) {
            throw new NegativeInputException("Please Enter a Positive Number");
        }
    }

    /**
     * Validates the Gold Level input for the Gold Customers to be in range of 1-3
     *
     * @param goldLevel The Customer's Gold Level
     * @throws GoldLevelException If the value is not in range will be thrown
     */
    public static void validateGoldLevel(int goldLevel) throws GoldLevelException {
        if (goldLevel < 1 || goldLevel > 3) {
            throw new GoldLevelException("Please Enter a Valid Gold Level");
        }
    }

    // Helper Methods

    /**
     * Gets a user ID from the user input.
     *
     * @return The user ID as an integer.
     */
    public int getUserIdFromInput() {
        int userID = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the User ID:");
            try {
                userID = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a Valid Integer");
            }
            scanner.nextLine();
        }
        return userID;
    }

    /**
     * Gets a property ID from the user input.
     *
     * @return The property ID as an integer.
     */
    public int getPropertyIDFromInput() {
        int propertyID = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Enter the Property ID:");
            try {
                propertyID = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a Valid Integer");
            }
            scanner.nextLine();
        }
        return propertyID;
    }

    /**
     * Gets an Inspection text from user input
     *
     * @return Inspection text as a String
     */
    public String getInspectionTextFromInput() {
        System.out.print("Enter the Inspection Text: ");
        return scanner.nextLine();
    }

    /**
     * Finds a user by their ID.
     *
     * @param userID the ID of the user to find.
     * @return The found User object or null if not found.
     */
    public User findUserByID(int userID) {
        for (User u : users) {
            if (u.getUserID() == userID) {
                return u;
            }
        }
        return null;
    }

    /**
     * Finds a Customer by their ID.
     *
     * @param customerID the ID of the Customer to find.
     * @return The found Customer object or null if not found.
     */
    public Customer findCustomerByID(int customerID) {
        for (User user : users) {
            if (user instanceof Customer && user.getUserID() == customerID) {
                return (Customer) user;
            }
        }
        return null;
    }

    /**
     * Finds a Host by their ID.
     *
     * @param hostID the ID of the Host to find.
     * @return The found Host object or null if not found.
     */
    public Host findHostByID(int hostID) {
        for (User user : users) {
            if (user instanceof Host && user.getUserID() == hostID) {
                return (Host) user;
            }
        }
        return null;
    }

    /**
     * Finds a property by its ID.
     *
     * @param propertyID the ID of the property to find.
     * @return the found Property object or null if not found.
     */
    public Property findPropertyByID(int propertyID) {
        for (Property p : properties) {
            if (p.getPropertyID() == propertyID) {
                return p;
            }
        }
        return null;
    }


    public void addUser(String firstName, String lastName, LocalDate dateOfBirth, LocalDate registrationDate, String userType, String customerType, int goldLevel, double taxNumber, String preferredPaymentMethod) {
        if (userType.equals("Host")) {
            users.add(new Host(firstName, lastName, dateOfBirth, registrationDate, taxNumber));
        } else {
            if (customerType.equals("Standard")) {
                users.add(new Standard(firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod));
            } else {
                users.add(new Gold(firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod, goldLevel));
            }
        }
        DataIO.writeUserList(users);
    }

    /**
     * Adds a new user by prompting for user details.
     */
    public void addUser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateOfBirth = null;
        LocalDate dateOfRegistration = null;

        System.out.println("Enter the details of the new user:");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        while (dateOfBirth == null) {
            System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
            try {
                dateOfBirth = LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid Date Format ");
            }
        }

        while (dateOfRegistration == null) {
            System.out.print("Enter Registration Date (DD/MM/YYYY): ");
            try {
                dateOfRegistration = LocalDate.parse(scanner.nextLine(), formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Invalid Date Format ");
            }
        }

        System.out.print("Is it a Host or a Customer?: (1: Host, 2: Customer) ");
        int hostOrCustomer = scanner.nextInt();
        scanner.nextLine();

        boolean validHostOrCustomer = false;
        while (!validHostOrCustomer) {

            if (hostOrCustomer == 1) {
                System.out.print("Enter Tax Number: ");
                double taxNumber = scanner.nextDouble();

                Host host = new Host(firstName, lastName, dateOfBirth, dateOfRegistration, taxNumber);
                users.add(host);
                validHostOrCustomer = true;

            } else if (hostOrCustomer == 2) {
                System.out.print("Enter Preferred Payment Method: ");
                String preferredPaymentMethod = scanner.nextLine();

                boolean validGoldOrStandard = false;
                while (!validGoldOrStandard) {

                    System.out.print("Is it Gold or Standard?: (1: Gold, 2: Standard) ");
                    int goldOrStandard = scanner.nextInt();

                    if (goldOrStandard == 1) {
                        int goldLevel = 0;

                        boolean validGoldLevel = false;
                        while (!validGoldLevel) {
                            System.out.print("Enter Gold Level: (From 1 to 3) ");
                            goldLevel = scanner.nextInt();

                            try {
                                validateGoldLevel(goldLevel);
                                validGoldLevel = true;
                            } catch (GoldLevelException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        Gold gold = new Gold(firstName, lastName, dateOfBirth, dateOfRegistration, preferredPaymentMethod, goldLevel);
                        users.add(gold);
                        validGoldOrStandard = true;

                    } else if (goldOrStandard == 2) {
                        Standard standard = new Standard(firstName, lastName, dateOfBirth, dateOfRegistration, preferredPaymentMethod);
                        users.add(standard);
                        validGoldOrStandard = true;

                    } else {
                        System.out.println("Please Enter a Valid Option! ");
                    }
                }

                validHostOrCustomer = true;


            } else {
                System.out.println("Please Enter a Valid Option! ");
            }
        }
        DataIO.writeUserList(users);
        System.out.println("New user has been added");
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param userID the ID of the user to be deleted.
     */
    public void deleteUser(int userID) {

        User userToRemove = findUserByID(userID);

        if (userToRemove != null) {
            users.remove(userToRemove);
            DataIO.writeUserList(users);
            System.out.println("User with ID " + userID + " has been deleted");
        } else {
            System.out.println("User with ID " + userID + " does not exist");
        }

        DataIO.writeUserList(users);
    }

    /**
     * Displays details for a specific user by their ID.
     *
     * @param userID the ID of the user whose details are to be displayed.
     */
    public void getUserDetails(int userID) {

        User user = findUserByID(userID);

        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("User with ID " + userID + " does not exist");
        }
    }

    public void addProperty(int noBedrooms, int noRooms, String city, int pricePerDay, String propertyType, Host host, int propertySize) {
        if (propertyType.equals("SharedProperty")) {
            properties.add(new SharedProperty(noBedrooms, noRooms, city, pricePerDay, host));
        } else {
            properties.add(new FullProperty(noBedrooms, noRooms, city, pricePerDay, host, propertySize));
        }
        DataIO.writePropertyList(properties);
    }

    /**
     * Adds a new property by prompting for property details.
     */
    public void addProperty() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner scanner = new Scanner(System.in);

        Host host = new Host();
        int noBedRooms = -1;
        int noRooms = -1;
        float pricePerDay = -1;
        LocalDate dateOfBirth = null;
        LocalDate dateOfRegistration = null;

        System.out.println("Enter the details of the new property.");
        boolean validNoBedRooms = false;
        while (!validNoBedRooms) {
            System.out.print("Enter number of bedrooms: ");
            try {
                noBedRooms = scanner.nextInt();
                validatePositiveInput(noBedRooms);
                validNoBedRooms = true;
            } catch (NegativeInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a Valid Integer ");
                scanner.nextLine();
            }
        }

        boolean validNoRooms = false;
        while (!validNoRooms) {
            System.out.print("Enter number of rooms: ");
            try {
                noRooms = scanner.nextInt();
                validatePositiveInput(noRooms);
                validNoRooms = true;
            } catch (NegativeInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a Valid Integer ");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        boolean validPricePerDay = false;
        while (!validPricePerDay) {
            System.out.print("Enter price per day: ");
            try {
                pricePerDay = scanner.nextFloat();
                validatePositiveInput((int) pricePerDay);
                validPricePerDay = true;
            } catch (NegativeInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a Valid Float ");
                scanner.nextLine();
            }
        }

        boolean validHostID = false;
        while (!validHostID) {

            System.out.println("Enter the Host ID: (If new host enter 0) ");
            int hostID = scanner.nextInt();
            scanner.nextLine();

            if (hostID == 0) {
                System.out.println("Enter the details of the new host:");
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();

                while (dateOfBirth == null) {
                    System.out.print("Enter Date of Birth (DD/MM/YYYY): ");

                    try {
                        dateOfBirth = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid Date Format");
                    }
                }

                while (dateOfRegistration == null) {
                    System.out.print("Enter Registration Date (DD/MM/YYYY): ");
                    try {
                        dateOfRegistration = LocalDate.parse(scanner.nextLine(), formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid Date Format");
                    }
                }

                System.out.print("Enter Tax Number: ");
                double taxNumber = scanner.nextDouble();

                host = new Host(firstName, lastName, dateOfBirth, dateOfRegistration, taxNumber);
                users.add(host);
                DataIO.writeUserList(users);
                validHostID = true;

            } else {
                host = findHostByID(hostID);

                if (host != null) {
                    validHostID = true;

                } else {
                    System.out.println("The Host does not exist! ");
                }
            }
        }

        System.out.print("Is it a Full Property or Shared? (1: Full, 2: Shared) ");
        int fullOrShared = scanner.nextInt();

        boolean validFullOrShared = false;
        while (!validFullOrShared) {

            if (fullOrShared == 1) {
                double propertySize = -1;

                boolean validPropertySize = false;
                while (!validPropertySize) {
                    System.out.print("Enter Property Size in SquareMeter: ");
                    try {
                        propertySize = scanner.nextDouble();
                        validatePositiveInput((int) propertySize);
                        validPropertySize = true;
                    } catch (NegativeInputException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Please Enter a Valid Double ");
                        scanner.nextLine();
                    }
                }

                FullProperty fullProperty = new FullProperty(noBedRooms, noRooms, city, pricePerDay, host, propertySize);

                validFullOrShared = true;

                properties.add(fullProperty);

            } else if (fullOrShared == 2) {

                SharedProperty sharedProperty = new SharedProperty(noBedRooms, noRooms, city, pricePerDay, host);

                validFullOrShared = true;

                properties.add(sharedProperty);

            } else {
                System.out.println("Please Enter a valid option! ");
            }
        }
        DataIO.writePropertyList(properties);
        System.out.println("New Property has been added");
    }

    /**
     * Deletes a property with the specified ID.
     *
     * @param propertyID the ID of the property to be deleted.
     */

    public void deleteProperty(int propertyID) {

        Property propertyToRemove = findPropertyByID(propertyID);

        if (propertyToRemove != null) {
            properties.remove(propertyToRemove);
            DataIO.writePropertyList(properties);
            System.out.println("Property with ID " + propertyID + " has been deleted");
        } else {
            System.out.println("Property with ID " + propertyID + " does not exist");
        }
    }

    /**
     * Displays details for a specific property by its ID.
     *
     * @param propertyID the ID of the property whose details are to be displayed.
     */
    public void getPropertyDetails(int propertyID) {

        Property property = findPropertyByID(propertyID);

        if (property != null) {
            System.out.println(property);
        } else {
            System.out.println("Property with ID " + propertyID + " does not exist");
        }
    }

    public void addBooking(int customerID, int propertyID, LocalDate startDate, LocalDate endDate, boolean isPaid) {
        Customer customer = findCustomerByID(customerID);
        Property property = findPropertyByID(propertyID);
        customer.addBooking(property, startDate, endDate, isPaid);
    }

    /**
     * Adds a new booking for a user and property specified by their IDs.
     *
     * @param customerID the ID of the user making the booking.
     * @param propertyID the ID of the property to be booked.
     */
    public void addBooking(int customerID, int propertyID) {

        Customer customer = findCustomerByID(customerID);
        Property property = findPropertyByID(propertyID);

        if (customer == null) {
            System.out.println("Customer with ID " + customerID + " does not exist.");
            return;
        }

        if (property == null) {
            System.out.println("Property with ID " + propertyID + " does not exist.");
            return;
        }

        customer.addBooking(property);
    }

    /**
     * Displays all bookings for a specific user by their ID.
     *
     * @param customerID the ID of the user whose bookings are to be displayed.
     */
    public void getUserBooking(int customerID) {

        Customer customer = findCustomerByID(customerID);

        if (customer == null) {
            System.out.println("Customer with ID " + customerID + " does not exist");
            return;
        }

        if (customer.getBookings().isEmpty()) {
            System.out.println("No bookings found");
        } else {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Bookings for customer ID " + customerID + ":");

            for (Booking booking : customer.getBookings()) {
                String startDate = booking.getStartDate().format(dateFormat);
                String endDate = booking.getEndDate().format(dateFormat);
                System.out.println("Booking ID: " + booking.getBookingID() + " on Property ID: " + booking.getProperty().getPropertyID() + " from " + startDate + " to " + endDate);
            }
        }
    }

    /**
     * Calculates and displays the total cost for a specific booking by user and property IDs.
     *
     * @param customerID the ID of the user.
     * @param propertyID the ID of the property booked.
     */
    public void getBookingCost(int customerID, int propertyID) {
        Customer customer = findCustomerByID(customerID);

        if (customer == null) {
            System.out.println("Customer with ID " + customerID + " does not exist");
            return;
        }

        List<Booking> bookings = customer.getBookings();
        boolean bookingExists = false;

        for (Booking booking : bookings) {
            if (booking.getProperty().getPropertyID() == propertyID) {
                bookingExists = true;
                double cost = booking.totalCost() * getDiscountForUser(customerID);
                System.out.println("Booking ID: " + booking.getBookingID() + " for Property ID: " + propertyID + " costs: " + cost);
            }
        }

        if (!bookingExists) {
            System.out.println("No bookings found for Customer " + customerID + " on property " + propertyID);
        }
    }

    /**
     * Gets the Customer's discount value
     *
     * @param customerID The ID of Customer whose discount is to be fetched
     * @return The discount percentage of the Customer as a Double
     */
    public double getDiscountForUser(int customerID) {
        Customer customer = findCustomerByID(customerID);
        return customer.discountPercentage();
    }

    /**
     * Lists all registered users.
     */
    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * Lists all properties.
     */
    public void listProperties() {
        if (properties.isEmpty()) {
            System.out.println("No properties found.");
            return;
        }

        for (Property property : properties) {
            System.out.println(property);
        }
    }

    /**
     * Terminates the program.
     */
    public void exit() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    /**
     * Adds an Inspection to a Property
     *
     * @param propertyID     The ID of the Property that is getting an Inspection
     * @param inspectionText The Inspection text
     */
    public void addInspectionToProperty(int propertyID, String inspectionText) {
        Property property = findPropertyByID(propertyID);
        property.addInspection(inspectionText);
        DataIO.writePropertyList(properties);
    }

    /**
     * Compares two Properties in terms of price per day
     *
     * @param property1ID The ID of the first Property
     * @param property2ID The ID of the second Property
     */
    public void comparePropertyPricesPerDay(int property1ID, int property2ID) {
        Property property1 = findPropertyByID(property1ID);
        Property property2 = findPropertyByID(property2ID);

        int comparisonResult = property1.compareTo(property2);

        if (comparisonResult < 0) {
            System.out.println("Property ID " + property1ID + " is cheaper");
        } else if (comparisonResult > 0) {
            System.out.println("Property ID " + property2ID + " is cheaper");
        } else {
            System.out.println("They have the same price!");
        }
    }


    /**
     * This method displays the main menu of the console-based user interface.
     * It presents a list of options to the user and processes the selected
     * option. The menu will loop continuously until the user selects the option
     * to exit the application.
     */
    public void menu() throws ParseException {
        int choice = 0;
        boolean validChoice = false;

        do {
            System.out.println("\nPlease Enter Your Desired Option:");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Show User's Detail");
            System.out.println("4. Add Property");
            System.out.println("5. Delete Property");
            System.out.println("6. Show Property's Detail");
            System.out.println("7. Add Booking");
            System.out.println("8. Show User's Bookings");
            System.out.println("9. Calculate Booking's Cost");
            System.out.println("10. Show All Users");
            System.out.println("11. Show All Properties");
            System.out.println("12. Add Inspection to Property");
            System.out.println("13. Compare Two Properties");
            System.out.println("14. Exit");

            try {
                choice = scanner.nextInt();
                validChoice = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Please Enter a Valid Integer ");
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser(getUserIdFromInput());
                    break;
                case 3:
                    getUserDetails(getUserIdFromInput());
                    break;
                case 4:
                    addProperty();
                    break;
                case 5:
                    deleteProperty(getPropertyIDFromInput());
                    break;
                case 6:
                    getPropertyDetails(getPropertyIDFromInput());
                    break;
                case 7:
                    addBooking(getUserIdFromInput(), getPropertyIDFromInput());
                    break;
                case 8:
                    getUserBooking(getUserIdFromInput());
                    break;
                case 9:
                    getBookingCost(getUserIdFromInput(), getPropertyIDFromInput());
                    break;
                case 10:
                    listUsers();
                    break;
                case 11:
                    listProperties();
                    break;
                case 12:
                    addInspectionToProperty(getPropertyIDFromInput(), getInspectionTextFromInput());
                    break;
                case 13:
                    comparePropertyPricesPerDay(getPropertyIDFromInput(), getPropertyIDFromInput());
                    break;
                case 14:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 14);
    }

    /**
     * The main method serves as the entry point for the application.
     * It creates an instance of the BASIC class and calls the menu method
     * to start the program.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args) {
        BASIC basic = new BASIC();
        basic.users = DataIO.readUserList();
        basic.properties = DataIO.readPropertyList();
        MainMenuGUI mainMenuGUI = new MainMenuGUI(basic);

        //PopulateData.populate(basic);
        //basic.menu();


    }
}
