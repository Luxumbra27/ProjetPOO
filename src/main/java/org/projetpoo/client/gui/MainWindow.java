package org.projetpoo.client.gui;


import com.bulenkov.darcula.DarculaLaf;
import org.projetpoo.client.connection.ChatController;
import org.projetpoo.client.connection.CommunityController;
import org.projetpoo.client.connection.ManagementConnection;
import org.projetpoo.client.connection.NodeServer;
import org.projetpoo.client.users.LocalUserController;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class MainWindow extends JFrame {

    private static final int MANAGEMENT_PORT = 2000;
    private static final String MANAGEMENT_HOSTNAME = "localhost";
    public static LocalUserController localUser;
    public static ManagementConnection managementSystem;
    public static CommunityController communityController;
    public static ChatsPanel chatsPanel;
    public static HashMap<String, ChatController> activeChats = new HashMap<>();
    public static int NODE_LISTEN_PORT;

    public MainWindow(){

        try{
            //UIManager.setLookAndFeel(new MaterialLookAndFeel());
            UIManager.setLookAndFeel(new DarculaLaf());
        } catch (Exception e){
            e.printStackTrace();
        }

        initComponents();

        managementSystem = new ManagementConnection(MANAGEMENT_HOSTNAME, MANAGEMENT_PORT);

        localUser = new LocalUserController(managementSystem, this);

        setVisible(true);
        pack();
    }

    public static void main(String[] args) throws Exception {

        new MainWindow();

        NodeServer nodeServer = new NodeServer();

        Thread thread = new Thread(nodeServer);
        thread.start();

    }

    public void goToChat() {

        setTitle("Clavardage - " + localUser.userInformation.getUsername());
        setContentPane(new JPanel(new BorderLayout()));

        communityController = new CommunityController(managementSystem);

        chatsPanel = new ChatsPanel();

        getContentPane().add(communityController.communityWidget, BorderLayout.WEST);
        getContentPane().add(chatsPanel, BorderLayout.CENTER);

        pack();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Etienne de Prémare

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Enter your name:");
        setMinimumSize(new Dimension(340, 200));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Etienne de Prémare
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
