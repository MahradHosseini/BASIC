package com.basic.GUI;

import com.basic.system.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The GUI to add a new user
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class AddUserGUI extends JFrame {
    private JPanel addUserGUIPanel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;
    private JTextField registrationDateField;
    private JRadioButton hostRadioButton;
    private JRadioButton customerRadioButton;
    private JRadioButton standardRadioButton;
    private JRadioButton goldRadioButton;
    private JPanel userTypePanel;
    private JButton cancelButton;
    private JButton saveButton;
    private JComboBox goldLevelComboBox;
    private JTextField taxNumberTextField;
    private JTextField preferredPaymentMethodTextField;
    private JLabel preferredPaymentMethodLabel;
    private JLabel taxNumberLabel;
    private MainMenuGUI mainMenuGUI;
    private BASIC basic;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public AddUserGUI(BASIC basic, MainMenuGUI mainMenuGUI) {
        this.mainMenuGUI = mainMenuGUI;
        this.basic = basic;
        setTitle("Add New User");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(addUserGUIPanel);
        setVisible(true);
        goldLevelComboBox.setVisible(false);
        pack();

        /**
         * Disables tax number field and enables customer type, and preferred payment method field
         */
        customerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taxNumberTextField.setEnabled(false);
                taxNumberLabel.setEnabled(false);
                standardRadioButton.setEnabled(true);
                goldRadioButton.setEnabled(true);
                preferredPaymentMethodLabel.setEnabled(true);
                preferredPaymentMethodTextField.setEnabled(true);
            }
        });

        /**
         * Disables customer related fields and enables tax number field
         */
        hostRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goldLevelComboBox.setVisible(false);
                standardRadioButton.setEnabled(false);
                goldRadioButton.setEnabled(false);
                taxNumberTextField.setEnabled(true);
                taxNumberLabel.setEnabled(true);
                preferredPaymentMethodLabel.setEnabled(false);
                preferredPaymentMethodTextField.setEnabled(false);
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
                mainMenuGUI.setLocationRelativeTo(addUserGUIPanel);
            }
        });
        /**
         * Saves the user and goes back to Main Menu
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dateOfBirth = null;
                LocalDate registrationDate = null;
                String userType = null;
                String customerType = null;
                String preferredPaymentMethod = null;
                int goldLevel = -1;
                double taxNumber = -1;

                try {
                    dateOfBirth = LocalDate.parse(dateOfBirthField.getText(), formatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(addUserGUIPanel, "Incorrect Birth Date!");
                    dateOfBirthField.setText("");
                    return;
                }

                try{
                    registrationDate = LocalDate.parse(registrationDateField.getText(), formatter);
                }catch (DateTimeParseException ex){
                    JOptionPane.showMessageDialog(addUserGUIPanel, "Incorrect Registration Date!");
                    registrationDateField.setText("");
                    return;
                }

                if (hostRadioButton.isSelected()){
                    userType = "Host";
                    try{
                        taxNumber = Double.parseDouble(taxNumberTextField.getText());
                    }catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(addUserGUIPanel, "Invalid Tax Number");
                        taxNumberTextField.setText("");
                        return;
                    }
                }else{
                    preferredPaymentMethod = preferredPaymentMethodTextField.getText();
                    userType = "Customer";
                    if(standardRadioButton.isSelected()){
                        customerType = "Standard";
                    }else{
                        customerType = "Gold";
                        goldLevel = goldLevelComboBox.getSelectedIndex() + 1;
                    }
                }

                basic.addUser(firstName, lastName, dateOfBirth, registrationDate, userType, customerType, goldLevel, taxNumber, preferredPaymentMethod);
                JOptionPane.showMessageDialog(addUserGUIPanel, "New User Added Successfully!");
                setVisible(false);
                mainMenuGUI.setLocationRelativeTo(addUserGUIPanel);
                mainMenuGUI.setVisible(true);
            }
        });
        /**
         * Enables gold level selection and prints the available levels
         */
        goldRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goldLevelComboBox.setVisible(true);
                goldLevelComboBox.setToolTipText("Select Gold Level");
                goldLevelComboBox.addItem("1");
                goldLevelComboBox.addItem("2");
                goldLevelComboBox.addItem("3");
                pack();
            }
        });
        /**
         * Disables gold level selection
         */
        standardRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goldLevelComboBox.setVisible(false);
            }
        });
    }
}
