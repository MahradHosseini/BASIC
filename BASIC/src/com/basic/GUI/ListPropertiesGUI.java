package com.basic.GUI;

import com.basic.property.Property;
import com.basic.system.BASIC;
import com.basic.GUI.MainMenuGUI;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The GUI to show all the properties
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class ListPropertiesGUI extends JFrame{
    private JPanel listPropertiesGUIPanel;
    private JTextPane propertiesTextPane;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public ListPropertiesGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Show All Properties");
        setLocationRelativeTo(null);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(listPropertiesGUIPanel);
        setVisible(true);
        propertiesTextPane.setEditable(false);

        StringBuilder properties = new StringBuilder();

        if (basic.properties.isEmpty()) {
            propertiesTextPane.setText("No properties found");
        }else{
            for (Property property : basic.properties) {
                properties.append("\n").append(property.toString());
            }
            propertiesTextPane.setText(String.valueOf(properties));
        }

        /**
         * Cancels the process and goes back to Main Menu GUI
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainMenuGUI.setVisible(true);
                mainMenuGUI.setLocationRelativeTo(listPropertiesGUIPanel);
            }
        });
    }
}
