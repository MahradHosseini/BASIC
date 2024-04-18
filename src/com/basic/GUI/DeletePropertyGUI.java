package com.basic.GUI;

import com.basic.dataio.DataIO;
import com.basic.property.Property;
import com.basic.system.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The GUI to delete a property
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class DeletePropertyGUI extends JFrame{
    private JPanel deletePropertyGUIPanel;
    private JComboBox propertyComboBox;
    private JButton deleteButton;
    private JButton cancelButton;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public DeletePropertyGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Delete a Property");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(deletePropertyGUIPanel);
        setVisible(true);
        printComboBox();

        /**
         * Deletes the property and saves the changes
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = propertyComboBox.getSelectedItem().toString();
                basic.deletedPropertyIDs.add(basic.properties.get(propertyComboBox.getSelectedIndex()).getPropertyID());
                basic.properties.remove(propertyComboBox.getSelectedIndex());
                JOptionPane.showMessageDialog(deletePropertyGUIPanel, "Property with ID and City: (" + str + ") is deleted");
                propertyComboBox.removeAllItems();
                //DataIO.writePropertyList(basic.properties);
                printComboBox();
                revalidate();

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
                mainMenuGUI.setLocationRelativeTo(deletePropertyGUIPanel);
            }
        });
    }

    private void printComboBox(){
        for (Property property : basic.properties){
            String propertyIDCity = property.getPropertyID() + ", " + property.getCity();
            propertyComboBox.addItem(propertyIDCity);
        }
    }
}
