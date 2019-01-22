package org.projetpoo.client.gui;


import org.projetpoo.client.connection.CommunityController;
import org.projetpoo.client.connection.ManagementConnection;
import org.projetpoo.client.connection.NodeServer;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame{

    public UserInfoWidget userInfoWidget;
    public JPanel contentPane;
    public ManagementConnection managementSystem;
    public CommunityController communityController;

    public static final int MANAGEMENT_PORT = 2000;
    private static final String MANAGEMENT_HOSTNAME = "localhost";
    public static final int DISCOVERY_PORT = 2000;
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 800;
    public static int NODE_LISTEN_PORT = 1235;

    public MainWindow() throws Exception {

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        setTitle("POO TeamChat");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);

        setContentPane(contentPane);
        setLayout(new GridBagLayout());

        managementSystem = new ManagementConnection(MANAGEMENT_HOSTNAME, MANAGEMENT_PORT);

        userInfoWidget = new UserInfoWidget(managementSystem);
        getContentPane().add(userInfoWidget);
        userInfoWidget.println("Hello !");

        communityController = new CommunityController(this, managementSystem);


        setVisible(true);
    }

    public void addComponent(JComponent comp){

        getContentPane().add(comp);

    }

    public static void main (String[] args) throws Exception {

        NODE_LISTEN_PORT = Integer.parseInt(args[0]);

        MainWindow window = new MainWindow();

        NodeServer nodeServer = new NodeServer(NODE_LISTEN_PORT, window);

        Thread thread = new Thread(nodeServer);
        thread.start();

    }
}
