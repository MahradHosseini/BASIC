package com.basic.GUI;

import com.basic.dataio.DataStorage;
import com.basic.system.BASIC;
import com.basic.GUI.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The Main GUI which includes main menu options
 */
public class MainMenuGUI extends JFrame {
    private JPanel mainMenuPanel;
    private JButton addBookingButton;
    private JButton getUserBookingButton;
    private JButton getBookingCostButton;
    private JButton addInspectionButton;
    private JButton comparePropertyButton;
    private JMenu propertyMenu;
    private JMenu userMenu;
    private JMenuBar userPropertyMenuBar;
    private JMenuItem addUserMenuItem;
    private JMenuItem addPropertyMenuItem;
    private JMenuItem deleteUserMenuItem;
    private JMenuItem getUserDetailMenuItem;
    private JMenuItem showAllUsersMenuItem;
    private JMenuItem deletePropertyMenuItem;
    private JMenuItem getPropertyDetailsMenuItem;
    private JMenuItem showAllPropertiesMenuItem;
    private BASIC basic;

    /**
     * Default constructor
     *
     * @param basic The current app running
     */
    public MainMenuGUI(BASIC basic) {
        this.basic = basic;
        setTitle("Main Menu");
        setLocationRelativeTo(null);
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainMenuPanel);
        setVisible(true);

        // User Menu
        userPropertyMenuBar.add(userMenu);
        userMenu.add(addUserMenuItem);
        userMenu.add(deleteUserMenuItem);
        userMenu.add(getUserDetailMenuItem);
        userMenu.add(showAllUsersMenuItem);

        // Property Menu
        userPropertyMenuBar.add(propertyMenu);
        propertyMenu.add(addPropertyMenuItem);
        propertyMenu.add(deletePropertyMenuItem);
        propertyMenu.add(getPropertyDetailsMenuItem);
        propertyMenu.add(showAllPropertiesMenuItem);

        pack();

        /**
         * To show Goodbye Message
         */
        /*this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(mainMenuPanel, "We Wish to See You Again!");
            }
        });*/

        /**
         * From User Menu dropdown -> to add a new user
         */
        addUserMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserGUI addUserGUI = new AddUserGUI(basic, MainMenuGUI.this);

                setVisible(false);
            }
        });
        /**
         * From User Menu dropdown -> To delete a user
         */
        deleteUserMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteUserGUI deleteUserGUI = new DeleteUserGUI(basic, MainMenuGUI.this);

                setVisible(false);
            }
        });
        /**
         * From User Menu dropdown -> to show a user's details
         */
        getUserDetailMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetUserDetailsGUI getUserDetailsGUI = new GetUserDetailsGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Property Menu dropdown -> to add a new property
         */
        addPropertyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPropertyGUI addPropertyGUI = new AddPropertyGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Property Menu dropdown -> to delete a property
         */
        deletePropertyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeletePropertyGUI deletePropertyGUI = new DeletePropertyGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Property Menu dropdown -> to show a property's details
         */
        getPropertyDetailsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetPropertyDetailsGUI getPropertyDetailsGUI = new GetPropertyDetailsGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Main Menu -> to add a new booking
         */
        addBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddBookingGUI addBookingGUI = new AddBookingGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Main Menu -> to get a user's booking/s
         */
        getUserBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetUserBookingGUI getUserBookingGUI = new GetUserBookingGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Main Menu -> to calculate a booking cost
         */
        getBookingCostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GetBookingCostGUI getBookingCostGUI = new GetBookingCostGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From User Menu drop down -> to list all the users
         */
        showAllUsersMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListUsersGUI listUsersGUI = new ListUsersGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Property Menu drop down -> to list all the properties
         */
        showAllPropertiesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListPropertiesGUI listPropertiesGUI = new ListPropertiesGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Main Menu -> to add an inspection to a property
         */
        addInspectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddInspectionGUI addInspectionGUI = new AddInspectionGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
        /**
         * From Main Menu -> To compare two properties costs
         */
        comparePropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComparePropertiesGUI comparePropertiesGUI = new ComparePropertiesGUI(basic, MainMenuGUI.this);
                setVisible(false);
            }
        });
    }


}
