import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the main application with user and property management capabilities.
 *
 * @author Mahrad Hosseini
 * @version 1.0
 */
public class BASIC {
    public ArrayList<User> users;
    public ArrayList<Property> properties;
    public Scanner scanner;

    /**
     * Default constructor initializing empty lists for users and properties
     * and a new scanner object for input handling.
     */
    public BASIC(){
        this.users = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a BASIC object with a predefined list of properties.
     *
     * @param properties the initial list of properties.
     */
    public BASIC(ArrayList<Property> properties){
        this.users = new ArrayList<>();
        this.properties = properties;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Constructs a BASIC object using an existing scanner.
     *
     * @param scanner the scanner object to be used for input handling.
     */
    public BASIC (Scanner scanner){
        this.users = new ArrayList<>();
        this.properties = new ArrayList<>();
        this.scanner = scanner;
    }

    // Helper Methods

    /**
     * Gets a user ID from the user input.
     *
     * @return the user ID as an integer.
     */
    public int getUserIdFromInput(){
        int userID;
        System.out.println("Enter the User ID:");
        userID = scanner.nextInt();
        scanner.nextLine();
        return userID;
    }

    /**
     * Gets a property ID from the user input.
     *
     * @return the property ID as an integer.
     */
    public int getPropertyIDFromInput(){
        int propertyID;
        System.out.println("Enter the Property ID:");
        propertyID = scanner.nextInt();
        scanner.nextLine();
        return propertyID;
    }

    /**
     * Finds a user by their ID.
     *
     * @param userID the ID of the user to find.
     * @return the found User object or null if not found.
     */
    public User findUserByID(int userID){
        for(User u : users){
            if(u.getUserID() == userID){
                return u;
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
    public Property findPropertyByID(int propertyID){
        for(Property p : properties){
            if(p.getPropertyID() == propertyID){
                return p;
            }
        }

        return null;
    }

    /**
     * Adds a new user by prompting for user details.
     */
    public void addUser(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter the details of the new user:");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Date of Birth: ");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine(),formatter);

        User user = new User(firstName,lastName,dateOfBirth);
        users.add(user);
        System.out.println("New user has been added");
    }

    /**
     * Deletes a user with the specified ID.
     *
     * @param userID the ID of the user to be deleted.
     */
    public void deleteUser(int userID){

        User userToRemove = findUserByID(userID);

        if (userToRemove != null){
            users.remove(userToRemove);
            System.out.println("User with ID" + userID + "has been deleted");
        }
        else{
            System.out.println("User with ID" + userID + "does not exist");
        }
    }

    /**
     * Displays details for a specific user by their ID.
     *
     * @param userID the ID of the user whose details are to be displayed.
     */
    public void getUserDetails(int userID){

        User user = findUserByID(userID);

        if (user != null){
            System.out.println("User Details:");
            System.out.println("ID: " + user.getUserID());
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Date of Birth: " + user.getDateOfBirth());
        }
        else{
            System.out.println("User with ID " + userID + "does not exist");
        }
    }

    /**
     * Adds a new property by prompting for property details.
     */
    public void addProperty(){
        System.out.println("Enter the details of the new property.");

        System.out.print("Enter number of bedrooms: ");
        int noBedRooms = scanner.nextInt();

        System.out.print("Enter number of rooms: ");
        int noRooms = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter city: ");
        String city = scanner.nextLine();

        System.out.print("Enter price per day: ");
        float pricePerDay = scanner.nextFloat();

        Property property = new Property(noBedRooms, noRooms, city, pricePerDay);
        properties.add(property);

        System.out.println("New Property has been added");
    }

    /**
     * Deletes a property with the specified ID.
     *
     * @param propertyID the ID of the property to be deleted.
     */

    public void deleteProperty(int propertyID){

        Property propertyToRemove = findPropertyByID(propertyID);

        if (propertyToRemove != null){
            properties.remove(propertyToRemove);
            System.out.println("Property with ID " + propertyID + "has been deleted");
        }
        else{
            System.out.println("Property with ID " + propertyID + "does not exist");
        }
    }

    /**
     * Displays details for a specific property by its ID.
     *
     * @param propertyID the ID of the property whose details are to be displayed.
     */
    public void getPropertyDetails(int propertyID){

        Property property = findPropertyByID(propertyID);

        if (property != null){
            System.out.println("Property ID: " + property.getPropertyID());
            System.out.println("Number of Bedrooms: " + property.getNoBedRooms());
            System.out.println("Number of Rooms: " + property.getNoRooms());
            System.out.println("City: " + property.getCity());
            System.out.println("Price Per Day: " + property.getPricePerDay());
        }
        else{
            System.out.println("Property with ID " + propertyID + "does not exist");
        }
    }

    /**
     * Adds a new booking for a user and property specified by their IDs.
     *
     * @param userID the ID of the user making the booking.
     * @param propertyID the ID of the property to be booked.
     * @throws ParseException if the date parsing fails.
     */
    public void addBooking(int userID, int propertyID) throws ParseException {

        User user = findUserByID(userID);
        Property property = findPropertyByID(propertyID);

        if (user == null) {
            System.out.println("User with ID " + userID + " does not exist.");
            return;
        }

        if (property == null) {
            System.out.println("Property with ID " + propertyID + " does not exist.");
            return;
        }

        user.addBooking(property);
    }

    /**
     * Displays all bookings for a specific user by their ID.
     *
     * @param userID the ID of the user whose bookings are to be displayed.
     */
    public void getUserBooking(int userID){

        User user = findUserByID(userID);

        if (user == null){
            System.out.println("User with ID " + userID + "does not exist");
            return;
        }

        if (user.getBookings().isEmpty()){
            System.out.println("No bookings found");
        }
        else{
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            System.out.println("Bookings for user ID " + userID + ":");

            for (Booking booking : user.getBookings()){
                String startDate = booking.getStartDate().format(dateFormat);
                String endDate = booking.getEndDate().format(dateFormat);
                System.out.println("Booking ID " + booking.getBookingID() + "from " + startDate + " to " + endDate);
            }
        }
    }

    /**
     * Calculates and displays the total cost for a specific booking by user and property IDs.
     *
     * @param userID the ID of the user.
     * @param propertyID the ID of the property booked.
     */
    public void getBookingCost(int userID, int propertyID){
        User user = findUserByID(userID);

        if (user == null){
            System.out.println("User with ID " + userID + "does not exist");
            return;
        }

        List<Booking> bookings = user.getBookings();
        boolean bookingExists = false;

        for (Booking booking : bookings){
            if (booking.getProperty().getPropertyID() == propertyID) {
                bookingExists = true;
                float cost = booking.totalCost();
                System.out.println("Booking ID " + booking.getBookingID() + " for property ID " + propertyID + " costs: " + cost);
            }
        }

        if (!bookingExists){
            System.out.println("No bookings found for user ID " + userID + " on property ID " + propertyID);
        }
    }

    /**
     * Lists all registered users.
     */
    public void listUsers(){
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (User user : users){
            int userID = user.getUserID();
            String dob = user.getDateOfBirth().format(formatter);
            String firstName = user.getFirstName();
            String lastName = user.getLastName();

            System.out.println("User ID: " + userID + ", Date of Birth: " + dob + ", First Name: " + firstName + ", Last Name: " + lastName);
        }
    }

    /**
     * Lists all properties.
     */
    public void listProperties(){
        if (properties.isEmpty()) {
            System.out.println("No properties found.");
            return;
        }

        for (Property property : properties){
            int propertyID = property.getPropertyID();
            int numBedRooms = property.getNoBedRooms();
            int numRooms = property.getNoRooms();
            String city = property.getCity();
            float pricePerDay = property.getPricePerDay();

            System.out.println("Property ID: " + propertyID + ", Bedrooms: " + numBedRooms + ", Rooms: " + numRooms + ", City: " + city + ", Price per day: $" + pricePerDay);
        }
    }

    /**
     * Terminates the program.
     */
    public void exit(){
        System.out.println("Exiting the program...");
        System.exit(0);
    }


    /**
     * This method displays the main menu of the console-based user interface.
     * It presents a list of options to the user and processes the selected
     * option. The menu will loop continuously until the user selects the option
     * to exit the application.
     */
    public void menu() throws ParseException {
        int choice;
        do{
            System.out.println("Please Enter Your Desired Option:");
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
            System.out.println("12. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
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
                    addBooking(getUserIdFromInput(),getPropertyIDFromInput());
                    break;
                case 8:
                    getUserBooking(getUserIdFromInput());
                    break;
                case 9:
                    getBookingCost(getUserIdFromInput(),getPropertyIDFromInput());
                    break;
                case 10:
                    listUsers();
                    break;
                case 11:
                    listProperties();
                    break;
                case 12:
                    exit();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 12);
    }

    /**
     * The main method serves as the entry point for the application.
     * It creates an instance of the BASIC class and calls the menu method
     * to start the program.
     *
     * @param args Not used in this application.
     */
    public static void main(String[] args) throws ParseException {
        BASIC basic = new BASIC();
        PopulateData.populate(basic);
        basic.menu();
    }
}
