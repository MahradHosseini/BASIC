package com.basic.property;

import com.basic.system.BASIC;
import com.basic.system.MainMenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The GUI to compare two properties price
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class ComparePropertiesGUI extends JFrame{
    private JPanel comparePropertiesGUIPanel;
    private JComboBox firstPropertyComboBox;
    private JComboBox secondPropertyComboBox;
    private JTextPane compareTextPane;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public ComparePropertiesGUI(BASIC basic, MainMenuGUI mainMenuGUI) {
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Compare Two Properties");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(comparePropertiesGUIPanel);
        setVisible(true);

        for (Property property : basic.properties) {
            String propertyIDCity = property.getPropertyID() + ", " + property.getCity();
            firstPropertyComboBox.addItem(propertyIDCity);
            secondPropertyComboBox.addItem(propertyIDCity);
        }

        /**
         * Refreshes the screen if a new property is chosen
         */
        firstPropertyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int comparisonResult = basic.properties.get(firstPropertyComboBox.getSelectedIndex()).compareTo(basic.properties.get(secondPropertyComboBox.getSelectedIndex()));

                if (comparisonResult < 0) {
                    compareTextPane.setText("Property ID " + basic.properties.get(firstPropertyComboBox.getSelectedIndex()).getPropertyID() + " is cheaper");
                } else if (comparisonResult > 0) {
                    compareTextPane.setText("Property ID " + basic.properties.get(secondPropertyComboBox.getSelectedIndex()).getPropertyID() + " is cheaper");
                } else {
                    compareTextPane.setText("They have the same price!");
                }
            }
        });
        /**
         * Refreshes the screen if a new property is chosen
         */
        secondPropertyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int comparisonResult = basic.properties.get(firstPropertyComboBox.getSelectedIndex()).compareTo(basic.properties.get(secondPropertyComboBox.getSelectedIndex()));

                if (comparisonResult < 0) {
                    compareTextPane.setText("Property ID " + basic.properties.get(firstPropertyComboBox.getSelectedIndex()).getPropertyID() + " is cheaper");
                } else if (comparisonResult > 0) {
                    compareTextPane.setText("Property ID " + basic.properties.get(secondPropertyComboBox.getSelectedIndex()).getPropertyID() + " is cheaper");
                } else {
                    compareTextPane.setText("They have the same price!");
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
                mainMenuGUI.setLocationRelativeTo(comparePropertiesGUIPanel);
            }
        });
    }
}
