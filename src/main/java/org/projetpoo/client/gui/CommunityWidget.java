package org.projetpoo.client.gui;

import org.projetpoo.client.connection.ManagementConnection;
import org.projetpoo.client.users.RemoteUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommunityWidget extends Widget  implements ActionListener {

    private ManagementConnection _managementSystem;
    private JButton _actButton;
    private JButton _startChatButton;
    private JPanel _listPanel;

    public CommunityWidget(Container container, ManagementConnection managementSystem) {

        super(container);
        this._managementSystem = managementSystem;

        _listPanel = new JPanel();

        _actButton = new JButton("Update list.");
        _actButton.addActionListener(this);

        _startChatButton = new JButton("Start chat.");
        _startChatButton.addActionListener(this);


        add(_listPanel);
        add(_actButton);
        add(_startChatButton);

    }

    public void actionPerformed(ActionEvent arg){
        if (arg.getSource() == _actButton){
            try {
                _managementSystem.updateConnectedUsers();
            } catch (Exception e){
                e.printStackTrace();
            }
            updateConnectedList();
        }
        if (arg.getSource() == _startChatButton){
            System.out.println("Start chat button pressed.");
        }
    }

    public void updateConnectedList(){

        for (RemoteUser user: _managementSystem.getConnectedList()){

            _listPanel.add(new JLabel(user.getNickname()));

        }
    }

}
