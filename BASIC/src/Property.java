import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents a property with details such as number of bedrooms, rooms, city, and price per day.
 * Each property is associated with a host.
 *
 * @author Mahrad Hosseini
 * @version 1.0
 */
public class Property {
    private static int counter = 0;
    private int propertyID;
    private int noBedRooms;
    private int noRooms;
    private String city;
    private float pricePerDay;
    private Host host;

    /**
     * Default constructor that initializes a property with a unique ID.
     */
    public Property(){
        this.propertyID = ++counter;
    }

    /**
     * Constructor that initializes a property with a specified number of bedrooms and rooms.
     *
     * @param noBedRooms the number of bedrooms
     * @param noRooms    the total number of rooms
     */
    public Property(int noBedRooms, int noRooms){
        this.propertyID = ++counter;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        addHost();

    }

    /**
     * Constructor that initializes a property with a specified price per day.
     *
     * @param pricePerDay the price per day for renting the property
     */
    public Property(float pricePerDay){
        this.propertyID = ++counter;
        this.pricePerDay = pricePerDay;
        addHost();
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
    public Property(int noBedRooms, int noRooms, String city, float pricePerDay){
        this.propertyID = ++counter;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.city = city;
        this.pricePerDay = pricePerDay;
        addHost();
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
    public Property(int noBedRooms, int noRooms, String city, float pricePerDay, Host host){
        this.propertyID = ++counter;
        this.noBedRooms = noBedRooms;
        this.noRooms = noRooms;
        this.city = city;
        this.pricePerDay = pricePerDay;
        this.host = host;
    }

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
    public float getPricePerDay() {
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
     * Adds a host to the property by collecting host details from the user input.
     */
    public void addHost(){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Enter Host Details.");

        System.out.println("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("Enter Registration Date (DD/MM/YYYY): ");

        LocalDate dateOfRegistration = LocalDate.parse(scanner.nextLine(),formatter);

        this.host = new Host(firstName, lastName, dateOfRegistration);

    }


}
