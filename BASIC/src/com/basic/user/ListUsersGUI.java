package com.basic.user;

import com.basic.system.BASIC;
import com.basic.system.MainMenuGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The GUI to show all the users
 * @author Mahrad Hosseini
 * @version 3.0
 */
public class ListUsersGUI extends JFrame{
    private JTextPane usersTextPane;
    private JPanel listUsersGUIPanel;
    private JButton cancelButton;
    private JScrollPane userScrollPane;
    private BASIC basic;
    private MainMenuGUI mainMenuGUI;

    /**
     * Default constructor
     * @param basic The current running app
     * @param mainMenuGUI The current Main Menu GUI
     */
    public ListUsersGUI(BASIC basic, MainMenuGUI mainMenuGUI){
        this.basic = basic;
        this.mainMenuGUI = mainMenuGUI;
        setTitle("Show All Users");
        setLocationRelativeTo(null);
        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(listUsersGUIPanel);
        setVisible(true);
        usersTextPane.setEditable(false);

        StringBuilder users = new StringBuilder();

        if (basic.users.isEmpty()) {
            usersTextPane.setText("No users found");
        }else{
            for (User user : basic.users) {
                users.append("\n").append(user.toString());
            }
            usersTextPane.setText(String.valueOf(users));
        }

        /**
         * Cancels the process and goes back to Main Menu GUI
         */
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainMenuGUI.setVisible(true);
                mainMenuGUI.setLocationRelativeTo(listUsersGUIPanel);
            }
        });
    }
}
