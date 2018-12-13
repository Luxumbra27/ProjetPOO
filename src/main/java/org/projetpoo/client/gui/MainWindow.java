package org.projetpoo.client.gui;


import org.projetpoo.client.connection.RemoteConnection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class MainWindow extends JFrame{

    public Widget chatWidget;
    private JPanel contentPane;

    public MainWindow() throws Exception {

        setTitle("ProjetPOO Clavardage");
        setSize(400, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBackground(Color.ORANGE);

        setContentPane(contentPane);

        chatWidget = new Widget(Color.BLUE);


        setVisible(true);
    }

    public static void main (String[] args) throws Exception {

        MainWindow window = new MainWindow();


        RemoteConnection test = new RemoteConnection();


        TimeUnit.SECONDS.sleep(8);
        test.printReply();
        test.close();

    }
}
