package org.projetpoo.client.gui;


import org.projetpoo.client.connection.ManagementConnection;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame{

    public UserInfoWidget userInfoWidget;
    public JPanel contentPane;
    public ManagementConnection managementSystem;

    public MainWindow() throws Exception {

        setTitle("ProjetPOO Clavardage");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Color.BLUE);

        setContentPane(contentPane);
        setLayout(new GridLayout(3, 2));

        managementSystem = new ManagementConnection("localhost", 1234);

        userInfoWidget = new UserInfoWidget(getContentPane(), managementSystem);
        userInfoWidget.println("Hello !");



        setVisible(true);
    }

    public void addComponent(JComponent comp){

        getContentPane().add(comp);

    }

    public static void main (String[] args) throws Exception {

        MainWindow window = new MainWindow();


    }
}
