import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a user with personal details and a list of bookings.
 * Each user is uniquely identified by an automatically assigned user ID.
 *
 * @author Mahrad Hosseini
 * @version 1.0
 */
public class User {
    private static int counter = 0; // To generate unique user ID
    private int userID;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private List<Booking> bookings; // To represent list of bookings for the user

    /**
     * Default constructor which initializes a user with a unique ID and an empty list of bookings.
     */
    public User(){
        this.userID = ++counter;
        this.bookings = new ArrayList<>();
    }

    /**
     * Initializes a user with first and last names and an empty list of bookings.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     */
    public User(String firstName, String lastName){
        this.userID = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookings = new ArrayList<>();
    }

    /**
     * Initializes a user with first and last names, date of birth, and an empty list of bookings.
     *
     * @param firstName The first name of the user
     * @param lastName The last name of the user
     * @param dateOfBirth The date of birth of the user
     */
    public User(String firstName, String lastName, LocalDate dateOfBirth){
        this.userID = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bookings = new ArrayList<>();
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
     * Gets the list of bookings associated with the user.
     *
     * @return The list of the user's bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Sets the list of bookings for the user.
     *
     * @param bookings The new list of bookings for the user
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    /**
     * Add a booking to the user's list of bookings using data retrieved from PopulateData Class
     * @param booking   The booking to be added
     */
    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    /**
     * Adds a booking to the user's list of bookings.
     *
     * @param property The property for which the booking is made
     * @throws ParseException If the input dates are not in the correct format
     */
    public void addBooking(Property property) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Enter start date (dd/MM/yyyy): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(),formatter);

        System.out.print("Enter end date (dd/MM/yyyy): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine(),formatter);

        System.out.println("Is it paid? (Yes = 1, No = 0)");

        boolean isPaid = (scanner.nextInt() == 1);

        Booking booking = new Booking(property, startDate, endDate, isPaid);
        bookings.add(booking);

        System.out.println("Booking has successfully been added");
    }

}
