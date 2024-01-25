package com.basic.user;

import com.basic.system.BASIC;
import com.basic.system.MainMenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The GUI to show a user's details
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class GetUserDetailsGUI extends JFrame{
    private JComboBox userComboBox;
    private JPanel getUserDetailsGUIPanel;
    private JTextPane userDetailTextPane;
    private JButton cancelButton;
    private JFormattedTextField userDetailsFormattedTextField;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public GetUserDetailsGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Show User's Details");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(getUserDetailsGUIPanel);
        setVisible(true);
        userDetailTextPane.setEditable(false);

        for (User user : basic.users){
            String userIDName = user.getUserID() + ", " + user.getFirstName();
            userComboBox.addItem(userIDName);
        }

        /**
         * Refreshes the screen when a user is selected
         */
        userComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = basic.users.get(userComboBox.getSelectedIndex());
                userDetailTextPane.setText(user.toString());

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
                mainMenuGUI.setLocationRelativeTo(getUserDetailsGUIPanel);
            }
        });
    }
}
