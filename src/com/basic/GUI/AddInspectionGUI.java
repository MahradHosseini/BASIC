package com.basic.GUI;

import com.basic.property.Property;
import com.basic.system.BASIC;
import com.basic.GUI.MainMenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
/**
 * The GUI to add an inspection to a property
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class AddInspectionGUI extends JFrame{
    private JPanel addInspectionGUIPanel;
    private JComboBox propertyComboBox;
    private JTextField inspectionTextField;
    private JButton saveButton;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public AddInspectionGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Add an Inspection");
        setLocationRelativeTo(null);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(addInspectionGUIPanel);
        setVisible(true);

        for (Property property : basic.properties){
            String propertyIDCity = property.getPropertyID() + ", " + property.getCity();
            propertyComboBox.addItem(propertyIDCity);
        }

        /**
         * Saves the inspection
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(basic.properties.get(propertyComboBox.getSelectedIndex()).getInspection().containsKey(LocalDate.now())){
                    JOptionPane.showMessageDialog(addInspectionGUIPanel, "An Inspection Exists For The Current Date!");
                }else{
                    basic.addInspectionToProperty(basic.properties.get(propertyComboBox.getSelectedIndex()).getPropertyID(), inspectionTextField.getText());
                    JOptionPane.showMessageDialog(addInspectionGUIPanel, "New Inspection Added Successfully!");
                    setVisible(false);
                    mainMenuGUI.setVisible(true);
                    mainMenuGUI.setLocationRelativeTo(addInspectionGUIPanel);
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
                mainMenuGUI.setLocationRelativeTo(addInspectionGUIPanel);
            }
        });
    }
}
