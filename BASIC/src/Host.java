import java.time.LocalDate;

/**
 * Represents the host of a property, with details such as host ID, first name, last name, and registration date.
 * Each host is uniquely identified by an automatically assigned host ID.
 *
 * @author Mahrad Hosseini
 * @version 1.0
 */
public class Host {
    private static int counter = 0;
    private int hostID;
    private String firstName;
    private String lastName;
    private LocalDate registrationDate;

    /**
     * Default constructor that initializes a host with a unique ID.
     */
    public Host(){
        this.hostID = ++counter;
    }

    /**
     * Constructor that initializes a host with first and last names.
     *
     * @param firstName The first name of the host
     * @param lastName The last name of the host
     */
    public Host(String firstName, String lastName){
        this.hostID = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Constructor that initializes a host with first and last names and a registration date.
     *
     * @param firstName The first name of the host
     * @param lastName The last name of the host
     * @param registrationDate The date when the host was registered
     */
    public Host(String firstName, String lastName, LocalDate registrationDate){
        this.hostID = ++counter;
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
    }

    /**
     * Gets the current count of hosts created.
     *
     * @return the current count of hosts
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Sets the host counter to a new value.
     *
     * @param counter The new value for the host counter
     */
    public static void setCounter(int counter) {
        Host.counter = counter;
    }

    /**
     * Gets the unique ID for this host.
     *
     * @return the unique host ID
     */
    public int getHostID() {
        return hostID;
    }

    /**
     * Sets the unique ID for this host.
     *
     * @param hostID The new ID for the host
     */
    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    /**
     * Gets the first name of the host.
     *
     * @return the first name of the host
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the host.
     *
     * @param firstName The new first name of the host
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the host.
     *
     * @return the last name of the host
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the host.
     *
     * @param lastName The new last name of the host
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the registration date of the host.
     *
     * @return the registration date of the host
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the registration date of the host.
     *
     * @param registrationDate The new registration date of the host
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
