package com.basic.GUI;

import com.basic.property.Property;
import com.basic.system.BASIC;
import com.basic.GUI.MainMenuGUI;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI to show a property's details
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class GetPropertyDetailsGUI extends JFrame {
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;
    private JPanel getPropertyDetailsGUIPanel;
    private JComboBox propertyComboBox;
    private JTextPane propertyDetailTextPane;
    private JButton cancelButton;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public GetPropertyDetailsGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Show Property's Details");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(getPropertyDetailsGUIPanel);
        setVisible(true);
        propertyDetailTextPane.setEditable(false);

        for (Property property : basic.properties){
            String propertyIDCity = property.getPropertyID() + ", " + property.getCity();
            propertyComboBox.addItem(propertyIDCity);
        }

        /**
         * Refreshes the screen if a new property is selected
         */
        propertyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Property property = basic.properties.get(propertyComboBox.getSelectedIndex());
                propertyDetailTextPane.setText(property.toString());

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
                mainMenuGUI.setLocationRelativeTo(getPropertyDetailsGUIPanel);
            }
        });
    }
}
