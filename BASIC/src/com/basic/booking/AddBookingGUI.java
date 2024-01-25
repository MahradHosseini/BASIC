package com.basic.booking;

import com.basic.property.Property;
import com.basic.system.BASIC;
import com.basic.system.MainMenuGUI;
import com.basic.user.Customer;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The GUI to add a new Booking
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class AddBookingGUI extends JFrame {
    private JPanel addBookingGUIPanel;
    private JComboBox userComboBox;
    private JComboBox propertyComboBox;
    private JTextField startDateTextField;
    private JTextField endDateTextField;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton saveButton;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    ArrayList<Customer> customers;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public AddBookingGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        customers = new ArrayList<Customer>();
        setTitle("Add a Booking");
        setLocationRelativeTo(null);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(addBookingGUIPanel);
        setVisible(true);
        pack();

        for (User user : basic.users){
            if (user instanceof Customer){
                customers.add((Customer) user);
                String userIDName = user.getUserID() + ", " + user.getFirstName();
                userComboBox.addItem(userIDName);
            }
        }
        for (Property property : basic.properties){
            String propertyIDCity = property.getPropertyID() + ", " + property.getCity();
            propertyComboBox.addItem(propertyIDCity);
        }

        /**
         * Saves the Booking
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                int customerID;
                int propertyID;
                LocalDate startDate = null;
                LocalDate endDate = null;
                boolean isPaid = false;

                customerID = customers.get(userComboBox.getSelectedIndex()).getUserID();
                propertyID = basic.properties.get(propertyComboBox.getSelectedIndex()).getPropertyID();

                try {
                    startDate = LocalDate.parse(startDateTextField.getText(), formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(addBookingGUIPanel, "Incorrect Start Date!");
                    startDateTextField.setText("");
                    return;
                }

                try{
                    endDate = LocalDate.parse(endDateTextField.getText(), formatter);
                }catch (DateTimeParseException ex){
                    JOptionPane.showMessageDialog(addBookingGUIPanel, "Incorrect End Date!");
                    endDateTextField.setText("");
                    return;
                }

                if (yesRadioButton.isSelected()){
                    isPaid = true;
                }else{
                    isPaid = false;
                }
                 basic.addBooking(customerID, propertyID, startDate, endDate, isPaid);
                JOptionPane.showMessageDialog(addBookingGUIPanel, "New Booking Successfully Added!");
                setVisible(false);
                mainMenuGUI.setLocationRelativeTo(addBookingGUIPanel);
                mainMenuGUI.setVisible(true);
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
                mainMenuGUI.setLocationRelativeTo(addBookingGUIPanel);
            }
        });
    }
}
