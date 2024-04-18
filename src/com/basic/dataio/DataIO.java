package com.basic.dataio;

import com.basic.property.*;
import com.basic.user.*;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a class of automatic data storage and retrieval.
 * Generates five .dat output files:
 * Gold, Standard, Shared Property, Full Property, Host
 *
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class DataIO {

    static DataOutputStream outGold = null;
    static DataOutputStream outStandard = null;
    static DataOutputStream outHost = null;
    static DataOutputStream outFullProperty = null;
    static DataOutputStream outSharedProperty = null;
    static DataInputStream inGold = null;
    static DataInputStream inStandard = null;
    static DataInputStream inHost = null;
    static DataInputStream inFullProperty = null;
    static DataInputStream inSharedProperty = null;

    /**
     * Writes a list of users to three .dat files (Host, Standard, or Gold) according to their type
     * @param users A list of users
     */
    public static void writeUserList(List<User> users) {
        try {
            outGold = new DataOutputStream(new FileOutputStream("Gold.dat"));
            outStandard = new DataOutputStream(new FileOutputStream("Standard.dat"));
            outHost = new DataOutputStream(new FileOutputStream("Host.dat"));
        } catch (IOException e) {
            System.out.println("Error Opening User Output Streams: " + e.getMessage());
        }

        for (User user : users) {

            // Note that the User ID will not be stored. Instead, each User is automatically assigned a new ID when being read again.
            if (user instanceof Gold gold) {
                try {
                    writeUserCommonAttributes(user, outGold);
                    outGold.writeUTF(gold.getPreferredPaymentMethod());
                    outGold.writeInt(gold.getGoldLevel());
                } catch (IOException e) {
                    System.out.println("Error Writing Gold User" + e.getMessage());
                }
            } else if (user instanceof Standard standard) {
                try {
                    writeUserCommonAttributes(user, outStandard);
                    outStandard.writeUTF(standard.getPreferredPaymentMethod());
                } catch (IOException e) {
                    System.out.println("Error Writing Standard User: " + e.getMessage());
                }
            } else if (user instanceof Host host) {
                try {
                    writeUserCommonAttributes(user, outHost);
                    outHost.writeDouble(host.getTaxNumber());
                } catch (IOException e) {
                    System.out.println("Error Writing Host: " + e.getMessage());
                }
            }
        }

        try {
            if (outGold != null) {
                outGold.close();
            }
            if (outStandard != null) {
                outStandard.close();
            }
            if (outHost != null) {
                outHost.close();
            }
        } catch (IOException e) {
            System.out.println("Error Closing User Output Streams: " + e.getMessage());
        }
    }

    /**
     * Writes the common attributes of user which are same across Hosts, Standards, and Gold users
     * @param user      A single User type instance
     * @param dataOS    The active DataOutputStream
     * @throws IOException  If an exception happens passes it to upper method
     */
    public static void writeUserCommonAttributes(User user, DataOutputStream dataOS) throws IOException {
        dataOS.writeUTF(user.getDateOfBirth().toString());
        dataOS.writeUTF(user.getFirstName());
        dataOS.writeUTF(user.getLastName());
        dataOS.writeUTF(user.getRegistrationDate().toString());
    }

    /**
     * Writes a list of Properties to two .dat files (Full Property, or Shared Property) according to its type
     * @param properties A list of Properties
     */
    public static void writePropertyList(List<Property> properties) {
        try {
            outFullProperty = new DataOutputStream(new FileOutputStream("FullProperty.dat"));
            outSharedProperty = new DataOutputStream(new FileOutputStream("SharedProperty.dat"));
        } catch (IOException e) {
            System.out.println("Error Opening Property Output Streams: " + e.getMessage());
        }

        for (Property property : properties) {

            // Note that the Property ID and Host ID will not be stored.
            // Instead, each Property and Host are automatically assigned new IDs when being read again.
            if (property instanceof FullProperty fullProperty) {
                try {
                    writePropertyCommonAttributes(property, outFullProperty);
                    outFullProperty.writeDouble(fullProperty.getPropertySize());
                } catch (IOException e) {
                    System.out.println("Error Writing Full Property: " + e.getMessage());
                }

            } else if (property instanceof SharedProperty sharedProperty) {
                try {
                    writePropertyCommonAttributes(property, outSharedProperty);
                } catch (IOException e) {
                    System.out.println("Error Writing Shared Property: " + e.getMessage());
                }
            }
        }

        try {
            if (outFullProperty != null) {
                outFullProperty.close();
            }
            if (outSharedProperty != null) {
                outSharedProperty.close();
            }
        } catch (IOException e) {
            System.out.println("Error Closing Property Output Streams: " + e.getMessage());
        }
    }

    /**
     * Writes the common attributes of Properties which are the same across Full and Shared properties
     * @param property A single Property instance
     * @param dataOS The active DataOutputStream
     * @throws IOException If an exception occurs passes it to upper method
     */
    public static void writePropertyCommonAttributes(Property property, DataOutputStream dataOS) throws IOException {
        dataOS.writeInt(property.getNoBedRooms());
        dataOS.writeInt(property.getNoRooms());
        dataOS.writeUTF(property.getCity());
        dataOS.writeDouble(property.getPricePerDay());
        dataOS.writeInt(property.getInspection().size());

        // To write the Inspection HashMap
        for (Map.Entry<LocalDate, String> entry : property.getInspection().entrySet()) {
            dataOS.writeUTF(entry.getKey().toString());
            dataOS.writeUTF(entry.getValue());
        }

        // To write the Host
        dataOS.writeUTF(property.getHost().getDateOfBirth().toString());
        dataOS.writeUTF(property.getHost().getFirstName());
        dataOS.writeUTF(property.getHost().getLastName());
        dataOS.writeUTF(property.getHost().getRegistrationDate().toString());
        dataOS.writeDouble(property.getHost().getTaxNumber());
    }

    /**
     * Reads a list of users from three .dat files (Host, Standard, and Gold) and put them together in an ArrayList
     * @return An ArrayList of Users
     */
    public static ArrayList<User> readUserList() {
        ArrayList<User> users = new ArrayList<>();

        try {
            inGold = new DataInputStream(new FileInputStream("Gold.dat"));
            while (true) {
                try {
                    LocalDate dateOfBirth = LocalDate.parse(inGold.readUTF());
                    String firstName = inGold.readUTF();
                    String lastName = inGold.readUTF();
                    LocalDate registrationDate = LocalDate.parse(inGold.readUTF());
                    String preferredMethod = inGold.readUTF();
                    int goldLevel = inGold.readInt();

                    users.add(new Gold(firstName, lastName, dateOfBirth, registrationDate, preferredMethod, goldLevel));
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading Gold User: " + e.getMessage());
        }

        try {
            inStandard = new DataInputStream(new FileInputStream("Standard.dat"));
            while (true) {
                try {
                    LocalDate dateOfBirth = LocalDate.parse(inStandard.readUTF());
                    String firstName = inStandard.readUTF();
                    String lastName = inStandard.readUTF();
                    LocalDate registrationDate = LocalDate.parse(inStandard.readUTF());
                    String preferredMethod = inStandard.readUTF();

                    users.add(new Standard(firstName, lastName, dateOfBirth, registrationDate, preferredMethod));
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading Standard User: " + e.getMessage());
        }

        try {
            inHost = new DataInputStream(new FileInputStream("Host.dat"));
            while (true) {
                try {
                    LocalDate dateOfBirth = LocalDate.parse(inHost.readUTF());
                    String firstName = inHost.readUTF();
                    String lastName = inHost.readUTF();
                    LocalDate registrationDate = LocalDate.parse(inHost.readUTF());
                    double taxNumber = inHost.readDouble();

                    users.add(new Host(firstName, lastName, dateOfBirth, registrationDate, taxNumber));
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading Host: " + e.getMessage());
        }

        try {
            if (inGold != null) {
                inGold.close();
            }
            if (inStandard != null) {
                inStandard.close();
            }
            if (inHost != null) {
                inHost.close();
            }
        } catch (IOException e) {
            System.out.println("Error Closing User Input Streams: " + e.getMessage());
        }

        return users;
    }

    /**
     * Reads a list of properties from two .dat files (Full Property, and Shared Property) and puts them together in an ArrayList
     * @return An ArrayList of Properties
     */
    public static ArrayList<Property> readPropertyList(){
        ArrayList<Property> properties = new ArrayList<>();

        try {
            inFullProperty = new DataInputStream(new FileInputStream("FullProperty.dat"));
            while (true) {
                try {
                    int noBedRooms = inFullProperty.readInt();
                    int noRooms = inFullProperty.readInt();
                    String city = inFullProperty.readUTF();
                    double pricePerDay = inFullProperty.readDouble();

                    // To read the Inspection
                    int inspectionSize = inFullProperty.readInt();
                    HashMap<LocalDate, String> inspection = new HashMap<>();
                    for (int i = 0; i < inspectionSize; i++) {
                        LocalDate date = LocalDate.parse(inFullProperty.readUTF());
                        String comment = inFullProperty.readUTF();
                        inspection.put(date, comment);
                    }

                    // To read the Host
                    LocalDate dateOfBirth = LocalDate.parse(inFullProperty.readUTF());
                    String firstName = inFullProperty.readUTF();
                    String lastName = inFullProperty.readUTF();
                    LocalDate registrationDate = LocalDate.parse(inFullProperty.readUTF());
                    double taxNumber = inFullProperty.readDouble();
                    Host host = new Host(firstName, lastName, dateOfBirth, registrationDate, taxNumber);

                    double propertySize = inFullProperty.readDouble();

                    properties.add(new FullProperty(noBedRooms, noRooms, city, pricePerDay, host, propertySize, inspection));

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading Full Property: " + e.getMessage());
        }

        try {
            inSharedProperty = new DataInputStream(new FileInputStream("SharedProperty.dat"));
            while (true) {
                try {
                    int noBedRooms = inSharedProperty.readInt();
                    int noRooms = inSharedProperty.readInt();
                    String city = inSharedProperty.readUTF();
                    double pricePerDay = inSharedProperty.readDouble();

                    // To read the Inspection
                    int inspectionSize = inSharedProperty.readInt();
                    HashMap<LocalDate, String> inspection = new HashMap<>();
                    for (int i = 0; i < inspectionSize; i++) {
                        LocalDate date = LocalDate.parse(inSharedProperty.readUTF());
                        String comment = inSharedProperty.readUTF();
                        inspection.put(date, comment);
                    }

                    // To read the Host
                    LocalDate dateOfBirth = LocalDate.parse(inSharedProperty.readUTF());
                    String firstName = inSharedProperty.readUTF();
                    String lastName = inSharedProperty.readUTF();
                    LocalDate registrationDate = LocalDate.parse(inSharedProperty.readUTF());
                    double taxNumber = inSharedProperty.readDouble();
                    Host host = new Host(firstName, lastName, dateOfBirth, registrationDate, taxNumber);

                    properties.add(new SharedProperty(noBedRooms, noRooms, city, pricePerDay, host, inspection));

                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error Reading Shared Property: " + e.getMessage());
        }
        return properties;
    }
}
