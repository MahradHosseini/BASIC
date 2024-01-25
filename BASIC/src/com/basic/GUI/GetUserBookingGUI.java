package com.basic.GUI;

import com.basic.booking.Booking;
import com.basic.system.BASIC;
import com.basic.GUI.MainMenuGUI;
import com.basic.user.Customer;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * The GUI to show a user's booking/s
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class GetUserBookingGUI extends JFrame{
    private JPanel getUserBookingGUIPanel;
    private JComboBox userComboBox;
    private JTextPane bookingDetailTextPane;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;
    private ArrayList<Customer> customers;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public GetUserBookingGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        customers = new ArrayList<Customer>();
        setTitle("Show User's Booking");
        setLocationRelativeTo(null);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(getUserBookingGUIPanel);
        setVisible(true);


        for (User user : basic.users){
            if (user instanceof Customer){
                customers.add((Customer) user);
                String userIDName = user.getUserID() + ", " + user.getFirstName();
                userComboBox.addItem(userIDName);
            }
        }

        /**
         * Show's the user's bookings when a user is selected from the dropdown
         */
        userComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                for (Booking booking : customers.get(userComboBox.getSelectedIndex()).getBookings()){
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String startDate = booking.getStartDate().format(dateFormat);
                    String endDate = booking.getEndDate().format(dateFormat);
                    bookingDetailTextPane.setText("Booking ID: " + booking.getBookingID() + " on Property ID: " + booking.getProperty().getPropertyID() + " from " + startDate + " to " + endDate);
                }
            }
        });
        /**
         * Cancels the process and goes back to Main Menu GUI
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainMenuGUI.setVisible(true);
                mainMenuGUI.setLocationRelativeTo(getUserBookingGUIPanel);
            }
        });
    }
}

