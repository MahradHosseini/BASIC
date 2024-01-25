package com.basic.dataio;

import com.basic.property.FullProperty;
import com.basic.property.Property;
import com.basic.property.SharedProperty;
import com.basic.system.BASIC;
import com.basic.user.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataStorage {

    public static void writeData(BASIC basic) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicdb", "cng443user", "1234")) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            deleteUser(basic.deletedUserIDs, connection);
            deleteProperty(basic.deletedPropertyIDs, connection);

            // To refresh the IDs and free up the eliminated ones, new unique IDs are given to instances before writing to database
            int userIDCounter = 0;
            int propertyIDCounter = 0;
            for(User user : basic.users){
                user.setUserID(++userIDCounter);
            }
            for(Property property : basic.properties){
                property.setPropertyID(++propertyIDCounter);
            }
            writeUser(basic.users, connection);
            writeProperty(basic.properties, connection);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void readData(BASIC basic) {

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/basicdb", "cng443user", "1234")){
            Class.forName("com.mysql.cj.jdbc.Driver");
            Statement statement = connection.createStatement();
            basic.users = readUser(statement);
            basic.properties = readProperty(statement);
        }catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static void deleteUser(ArrayList<Integer> deletedUserIDs, Connection connection) throws Exception {
        String deleteSQL = "DELETE FROM user WHERE userID = ?";

        try(PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)){
            for(Integer userID : deletedUserIDs){
                deleteStatement.setInt(1, userID);
                deleteStatement.executeUpdate();
            }
        }
    }

    private static void deleteProperty(ArrayList<Integer> deletedPropertyIDs, Connection connection) throws Exception{
        String deleteSQL = "DELETE FROM property WHERE propertyID = ?";

        try(PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL)){
            for(Integer propertyID : deletedPropertyIDs){
                deleteStatement.setInt(1, propertyID);
                deleteStatement.executeUpdate();
            }
        }
    }

    private static void writeUser(ArrayList<User> users, Connection connection) throws Exception {
        String insertSQL = "INSERT INTO user " +
                "(userID," +
                "dateOfBirth," +
                "firstName," +
                "lastName," +
                "registrationDate," +
                "type," +
                "preferredPaymentMethod," +
                "taxNumber," +
                "goldLevel) " +

                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +

                "dateOfBirth = VALUES(dateOfBirth), " +
                "firstName = VALUES(firstName), " +
                "lastName = VALUES(lastName), " +
                "registrationDate = VALUES(registrationDate), " +
                "type = VALUES(type), " +
                "preferredPaymentMethod = VALUES(preferredPaymentMethod), " +
                "taxNumber = VALUES(taxNumber), " +
                "goldLevel = VALUES(goldLevel)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
            for (User user : users) {
                insertStatement.setInt(1, user.getUserID());
                insertStatement.setDate(2, Date.valueOf(user.getDateOfBirth()));
                insertStatement.setString(3, user.getFirstName());
                insertStatement.setString(4, user.getLastName());
                insertStatement.setDate(5, Date.valueOf(user.getRegistrationDate()));

                if (user instanceof Host host) {
                    insertStatement.setString(6, "h");
                    insertStatement.setString(7, null);
                    insertStatement.setDouble(8, host.getTaxNumber());
                    insertStatement.setNull(9, Types.INTEGER);
                } else {
                    if (user instanceof Gold gold) {
                        insertStatement.setString(6, "g");
                        insertStatement.setInt(9, gold.getGoldLevel());
                    } else {
                        insertStatement.setString(6, "s");
                        insertStatement.setNull(9, Types.INTEGER);
                    }
                    insertStatement.setString(7, ((Customer) user).getPreferredPaymentMethod());
                    insertStatement.setNull(8, Types.INTEGER);
                }
                insertStatement.executeUpdate();
            }
        }
    }

    private static void writeProperty(ArrayList<Property> properties, Connection connection) throws Exception {
        String insertSQL = "INSERT INTO property " +
                "(propertyID, " +
                "noBedRooms, " +
                "noRooms, " +
                "city, " +
                "pricePerDay, " +
                "type, " +
                "propertySize) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "noBedRooms = VALUES(noBedRooms), " +
                "noRooms = VALUES(noRooms), " +
                "city = VALUES(city), " +
                "pricePerDay = VALUES(pricePerDay), " +
                "type = VALUES(type), " +
                "propertySize = VALUES(propertySize)";

        try (PreparedStatement insertStatement = connection.prepareStatement(insertSQL)) {
            for (Property property : properties) {
                insertStatement.setInt(1, property.getPropertyID());
                insertStatement.setInt(2, property.getNoBedRooms());
                insertStatement.setInt(3, property.getNoRooms());
                insertStatement.setString(4, property.getCity());
                insertStatement.setDouble(5, property.getPricePerDay());
                if (property instanceof FullProperty fullProperty) {
                    insertStatement.setString(6, "f");
                    insertStatement.setDouble(7, fullProperty.getPropertySize());
                } else {
                    insertStatement.setString(6, "s");
                    insertStatement.setNull(7, Types.DOUBLE);
                }
                insertStatement.executeUpdate();
            }
        }
    }

    private static ArrayList<User> readUser(Statement statement) throws Exception{
        ArrayList<User> users = new ArrayList<>();
        ResultSet userResultSet = statement.executeQuery("select * from user");

        while (userResultSet.next()){
            int userID = userResultSet.getInt("userID");
            LocalDate dateOfBirth = userResultSet.getDate("dateOfBirth").toLocalDate();
            String firstName = userResultSet.getString("firstName");
            String lastName = userResultSet.getString("lastName");
            LocalDate registrationDate = userResultSet.getDate("registrationDate").toLocalDate();
            String type = userResultSet.getString("type");
            String preferredPaymentMethod = userResultSet.getString("preferredPaymentMethod");
            double taxNumber = userResultSet.getDouble("taxNumber");
            int goldLevel = userResultSet.getInt("goldLevel");

            if(type.equals("h")){
                users.add(new Host(userID, firstName, lastName, dateOfBirth, registrationDate, taxNumber));
            }else{
                if(type.equals("g")){
                    users.add(new Gold(userID, firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod, goldLevel));
                }else{
                    users.add(new Standard(userID, firstName, lastName, dateOfBirth, registrationDate, preferredPaymentMethod));
                }
            }
        }
        return users;
    }

    private static ArrayList<Property> readProperty(Statement statement) throws Exception{
        ArrayList<Property> properties = new ArrayList<>();
        ResultSet propertyResultSet = statement.executeQuery("select * from property");

        while (propertyResultSet.next()){
            int propertyID = propertyResultSet.getInt("propertyID");
            int noBedRooms = propertyResultSet.getInt("noBedRooms");
            int noRooms = propertyResultSet.getInt(("noRooms"));
            String city = propertyResultSet.getString("city");
            double pricePerDay = propertyResultSet.getDouble("pricePerDay");
            String type = propertyResultSet.getString("type");
            double propertySize = propertyResultSet.getDouble("propertySize");

            if(type.equals("f")){
                properties.add(new FullProperty(propertyID,noBedRooms, noRooms, city, pricePerDay, propertySize));
            }else{
                properties.add(new SharedProperty(propertyID, noBedRooms, noRooms, city, pricePerDay));
            }
        }
        return properties;
    }


}
