package org.projetpoo.client.gui;

import org.projetpoo.client.connection.ManagementConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoWidget extends Widget implements ActionListener {

    private ManagementConnection _managementSystem;
    private JButton _connectButton;
    private JTextArea _info;
    private JTextField _nicknameField;

    public UserInfoWidget(Container container, ManagementConnection managementSystem) {
        super(container);
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
                    System.out.println("[DBG] Logging in...");
                    _managementSystem.sendMessage("login");
                    _managementSystem.sendMessage(_nicknameField.getText());
                    println(_managementSystem.getReply());
                }
                println("You are connected !");
            } catch (Exception e){
                e.printStackTrace();
                println("Connection problem.");
            }
        }
    }
}
