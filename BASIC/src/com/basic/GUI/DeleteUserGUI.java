package com.basic.GUI;

import com.basic.dataio.DataIO;
import com.basic.system.BASIC;
import com.basic.GUI.MainMenuGUI;
import com.basic.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The GUI to delete a user
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class DeleteUserGUI extends JFrame {
    BASIC basic;
    MainMenuGUI mainMenuGUI;
    private JPanel deleteUserGUIPanel;
    private JButton deleteButton;
    private JButton cancelButton;
    private JComboBox userComboBox;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public DeleteUserGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Delete a User");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(deleteUserGUIPanel);
        setVisible(true);
        printComboBox();

        /**
         * Deletes the user and saves the changes
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = userComboBox.getSelectedItem().toString();
                basic.deletedUserIDs.add(basic.users.get(userComboBox.getSelectedIndex()).getUserID());
                basic.users.remove(userComboBox.getSelectedIndex());
                JOptionPane.showMessageDialog(deleteUserGUIPanel, "User ID and Name: (" + str + ") is deleted");
                userComboBox.removeAllItems();
                //DataIO.writeUserList(basic.users);
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
                mainMenuGUI.setLocationRelativeTo(deleteUserGUIPanel);
            }
        });
    }

    /**
     * Prints the list of users to the dropdown list
     */
    private void printComboBox(){
        for (User user : basic.users){
            String userIDName = user.getUserID() + ", " + user.getFirstName();
            userComboBox.addItem(userIDName);
        }
    }
}
