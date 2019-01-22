package org.projetpoo.client.gui;

import org.projetpoo.client.connection.ManagementConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoWidget extends Widget implements ActionListener {

    public static String userName;
    private ManagementConnection _managementSystem;
    private JButton _connectButton;
    private JTextArea _info;
    private JTextField _nicknameField;

    public UserInfoWidget(ManagementConnection managementSystem) {
        super();
        this._managementSystem = managementSystem;

        _nicknameField = new JTextField();

        _info = new JTextArea();
        _info.setEditable(false);

        _connectButton = new JButton("Connect.");
        _connectButton.addActionListener(this);

        setLayout(new BorderLayout());

        add(_nicknameField, BorderLayout.NORTH);
        add(_info, BorderLayout.CENTER);
        add(_connectButton, BorderLayout.SOUTH);

    }


    public void println(String str){
        _info.append(str + System.lineSeparator());
    }

    public void actionPerformed(ActionEvent arg){
        if (arg.getSource() == _connectButton){
            try {
                if (!_managementSystem.isConnected()){
                    System.out.println("[DBG] Attempting connection...");
                    _managementSystem.connect();
                }

                System.out.println("[DBG] Logging in...");
                _managementSystem.sendMessage("login");
                userName = _nicknameField.getText();
                _managementSystem.sendMessage(userName);

                switch (_managementSystem.getReply()) {

                    case "getPort":
                        _managementSystem.sendMessage(Integer.toString(MainWindow.NODE_LISTEN_PORT));
                        println("Successfully connected !");
                        break;
                    case "userExists":
                        println("Your login is already used !");
                        break;
                    default:
                        System.out.println("[DBG] Server response not expected.");
                        break;
                }

            } catch (Exception e){
                e.printStackTrace();
                println("Connection problem.");
            }
        }
    }
}
