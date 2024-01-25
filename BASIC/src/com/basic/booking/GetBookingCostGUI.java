package com.basic.booking;

import com.basic.property.Property;
import com.basic.system.BASIC;
import com.basic.system.MainMenuGUI;
import com.basic.user.Customer;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The GUI to calculate a booking's cost
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class GetBookingCostGUI extends JFrame {
    private JPanel getBookingCostGUIPanel;
    private JComboBox userComboBox;
    private JComboBox propertyComboBox;
    private JTextPane bookingCostTextPane;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;
    private ArrayList<Customer> customers;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public GetBookingCostGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        customers = new ArrayList<Customer>();
        setTitle("Calculate Booking Cost");
        setLocationRelativeTo(null);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(getBookingCostGUIPanel);
        setVisible(true);

        for(User user : basic.users){
            if(user instanceof Customer){
                String userIDName = user.getUserID() + ", " + user.getFirstName();
                userComboBox.addItem(userIDName);
                customers.add((Customer) user);
            }
        }

        for(Property property : basic.properties){
            String propertyIDCity = property.getPropertyID() + ", " + property.getCity();
            propertyComboBox.addItem(propertyIDCity);
        }

        /**
         * Cancels the process and goes back to Main Menu GUI
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainMenuGUI.setVisible(true);
                mainMenuGUI.setLocationRelativeTo(getBookingCostGUIPanel);
            }
        });
        /**
         * Refreshes the screen if new user is selected
         */
        userComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Booking> bookings = customers.get(userComboBox.getSelectedIndex()).getBookings();
                if (bookings.isEmpty()){
                    bookingCostTextPane.setText("There no Booking available");
                }else{
                    for (Booking booking : bookings) {
                        if (booking.getProperty().getPropertyID() == basic.properties.get(propertyComboBox.getSelectedIndex()).getPropertyID()) {
                            double cost = booking.totalCost() * basic.getDiscountForUser(customers.get(userComboBox.getSelectedIndex()).getUserID());
                            bookingCostTextPane.setText("Booking ID: " + booking.getBookingID() + " for Property ID: " + booking.getProperty().getPropertyID() + " costs: " + cost + "\n");
                        }
                    }
                }
                bookingCostTextPane.setEditable(false);
            }
        });
        /**
         * Refreshes the screen if new property is selected
         */
        propertyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Booking> bookings = customers.get(userComboBox.getSelectedIndex()).getBookings();
                if (bookings.isEmpty()){
                    bookingCostTextPane.setText("There no Booking available");
                }else{
                    for (Booking booking : bookings) {
                        if (booking.getProperty().getPropertyID() == basic.properties.get(propertyComboBox.getSelectedIndex()).getPropertyID()) {
                            double cost = booking.totalCost() * basic.getDiscountForUser(customers.get(userComboBox.getSelectedIndex()).getUserID());
                            bookingCostTextPane.setText("Booking ID: " + booking.getBookingID() + " for Property ID: " + booking.getProperty().getPropertyID() + " costs: " + cost + "\n");
                        }
                    }
                }
                bookingCostTextPane.setEditable(false);
            }
        });

    }
}
