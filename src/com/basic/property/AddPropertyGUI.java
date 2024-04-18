package com.basic.property;

import com.basic.dataio.DataIO;
import com.basic.exception.NegativeInputException;
import com.basic.system.BASIC;
import com.basic.system.MainMenuGUI;
import com.basic.user.Host;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 * The GUI to add an inspection to a property
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class AddPropertyGUI extends JFrame {
    private JPanel addPropertyGUIPanel;
    private JTextField noBedroomsTextField;
    private JTextField noRoomsTextField;
    private JTextField cityTextField;
    private JTextField pricePerDayTextField;
    private JComboBox hostComboBox;
    private JRadioButton sharedPropertyRadioButton;
    private JRadioButton fullPropertyRadioButton;
    private JTextField propertySizeTextField;
    private JPanel newHostPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField dateOfBirthTextField;
    private JTextField registrationDateTextField;
    private JTextField taxNumberTextField;
    private JRadioButton newHostRadioButton;
    private JLabel propertySizeLabel;
    private JButton saveHostButton;
    private JButton saveButton;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;
    private ArrayList<Host> hosts;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public AddPropertyGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.mainMenuGUI = mainMenuGUI;
        this.basic = basic;
        hosts = new ArrayList<Host>();
        setTitle("Add New Property");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(addPropertyGUIPanel);
        setVisible(true);
        newHostPanel.setVisible(false);
        propertySizeLabel.setVisible(false);
        propertySizeTextField.setVisible(false);

        for(User user: basic.users){
            if(user instanceof Host){
                hosts.add((Host) user);
            }
        }
        printHostComboBox();
        pack();

        /**
         * Opens a panel to add a new host
         */
        newHostRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newHostRadioButton.isSelected()){
                    newHostPanel.setVisible(true);
                }else{
                    newHostPanel.setVisible(false);
                }
                pack();
            }
        });
        /**
         * Enables entering property size
         */
        fullPropertyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                propertySizeLabel.setVisible(true);
                propertySizeTextField.setVisible(true);
                pack();
            }
        });
        /**
         * Disables entering property size
         */
        sharedPropertyRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                propertySizeLabel.setVisible(false);
                propertySizeTextField.setVisible(false);
                pack();
            }
        });
        /**
         * Saves the host and adds it to the dropdown list
         */
        saveHostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOfBirth = null;
                LocalDate registrationDate = null;
                double taxNumber = -1;

                try {
                    dateOfBirth = LocalDate.parse(dateOfBirthTextField.getText(), formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(addPropertyGUIPanel, "Incorrect Birth Date!");
                    dateOfBirthTextField.setText("");
                    return;
                }

                try{
                    registrationDate = LocalDate.parse(registrationDateTextField.getText(), formatter);
                }catch (DateTimeParseException ex){
                    JOptionPane.showMessageDialog(addPropertyGUIPanel, "Incorrect Registration Date!");
                    registrationDateTextField.setText("");
                    return;
                }

                try{
                    taxNumber = Double.parseDouble(taxNumberTextField.getText());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(addPropertyGUIPanel, "Invalid Tax Number");
                    taxNumberTextField.setText("");
                    return;
                }

                Host host = new Host(firstName, lastName, dateOfBirth, registrationDate, taxNumber);
                basic.users.add(host);
                hosts.add(host);
                printHostComboBox();
                DataIO.writeUserList(basic.users);
                JOptionPane.showMessageDialog(addPropertyGUIPanel, "New Host Successfully Added To Dropdown!");
                newHostPanel.setVisible(false);
                newHostRadioButton.setSelected(false);
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
                mainMenuGUI.setLocationRelativeTo(addPropertyGUIPanel);
            }
        });
        /**
         * Saves the property
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int noBedrooms = -1;
                int noRooms = -1;
                int pricePerDay = -1;
                int propertySize = -1;
                String city = null;
                String propertyType = null;

                try{
                    noBedrooms = Integer.parseInt(noBedroomsTextField.getText());
                    BASIC.validatePositiveInput(noBedrooms);
                    noRooms = Integer.parseInt(noRoomsTextField.getText());
                    BASIC.validatePositiveInput(noRooms);
                    pricePerDay = Integer.parseInt(pricePerDayTextField.getText());
                    BASIC.validatePositiveInput(pricePerDay);
                }catch (NegativeInputException | NumberFormatException ex){
                    JOptionPane.showMessageDialog(addPropertyGUIPanel, ex.getMessage());
                    noBedroomsTextField.setText("");
                    noRoomsTextField.setText("");
                    pricePerDayTextField.setText("");
                    return;
                }

                city = cityTextField.getText();

                Host host = hosts.get(hostComboBox.getSelectedIndex());

                if (sharedPropertyRadioButton.isSelected()){
                    propertyType = "SharedProperty";
                    basic.addProperty(noBedrooms, noRooms, city, pricePerDay,propertyType, host, propertySize);
                }else{
                    propertyType = "FullProperty";

                    try{
                        propertySize = Integer.parseInt(propertySizeTextField.getText());
                        BASIC.validatePositiveInput(propertySize);
                    }catch(NegativeInputException | NumberFormatException ex){
                        JOptionPane.showMessageDialog(addPropertyGUIPanel, ex.getMessage());
                        propertySizeTextField.setText("");
                        return;
                    }
                    basic.addProperty(noBedrooms, noRooms, city, pricePerDay, propertyType, host, propertySize);
                }

                JOptionPane.showMessageDialog(addPropertyGUIPanel, "New Property Added Successfully!");
                setVisible(false);
                mainMenuGUI.setLocationRelativeTo(addPropertyGUIPanel);
                mainMenuGUI.setVisible(true);

            }
        });
    }

    /**
     * Prints the list of Hosts to the dropdown list
     */
    public void printHostComboBox(){
        String hostIDName;
        for(Host host : hosts){
            hostIDName = host.getUserID() + ", " + host.getFirstName();
            hostComboBox.addItem(hostIDName);
        }
    }
}
