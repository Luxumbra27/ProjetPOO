package org.projetpoo.client.gui;

import org.projetpoo.client.connection.Chat;
import org.projetpoo.client.connection.ManagementConnection;
import org.projetpoo.client.users.RemoteUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommunityWidget extends Widget  implements ActionListener {

    private ManagementConnection _managementSystem;
    private JButton _actButton;
    private JButton _startChatButton;
    private JPanel _listPanel;
    private JButton _prevButton;
    private JButton _nextButton;
    private int _selectedIndex = 0;
    private MainWindow _mainWindow;
    private ArrayList<UserLabel> _labelList;


    public CommunityWidget(MainWindow mainWindow, ManagementConnection managementSystem) {

        super(mainWindow.getContentPane());
        this._mainWindow = mainWindow;
        this._managementSystem = managementSystem;

        setLayout(new GridLayout(5, 1));

        _listPanel = new JPanel();
        add(_listPanel);

        _actButton = new JButton("Update list.");
        _actButton.addActionListener(this);
        add(_actButton);

        _prevButton = new JButton("Previous");
        _prevButton.addActionListener(this);
        add(_prevButton);

        _nextButton = new JButton("Next");
        _nextButton.addActionListener(this);
        add(_nextButton);

        _startChatButton = new JButton("Start chat.");
        _startChatButton.addActionListener(this);
        add(_startChatButton);

        _labelList = new ArrayList<>();

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
            startChatAction();
        }
        if (arg.getSource() == _nextButton){
            nextButtonAction();
        }
        if (arg.getSource() == _prevButton){
            prevButtonAction();
        }
    }


    private void nextButtonAction(){
        _labelList.get(_selectedIndex).setUnselected();
        if (_selectedIndex == (_labelList.size() - 1)){
            _selectedIndex = 0;
        } else {
            _selectedIndex++;
        }
        _labelList.get(_selectedIndex).setSelected();
    }

    private void prevButtonAction(){
        _labelList.get(_selectedIndex).setUnselected();
        if (_selectedIndex == 0){
            _selectedIndex = (_labelList.size() - 1);
        } else {
            _selectedIndex--;
        }
        _labelList.get(_selectedIndex).setSelected();
    }

    private void startChatAction(){

        Chat chat = new Chat(_mainWindow, _labelList.get(_selectedIndex).getAssociatedUser());

        Thread thread = new Thread(chat);
        thread.start();
    }


    private void updateConnectedList(){

        System.out.println("[DBG] Updating displayed list: ");
        _listPanel.removeAll();
        _labelList.clear();
        UserLabel label;
        for (RemoteUser user: _managementSystem.getConnectedList()){

            // We do not display the actual user
            if (!(user.getNickname().equals(UserInfoWidget.userName))){
                label = new UserLabel(user.getNickname(), user);

                _labelList.add(label);
                _listPanel.add(label);
            }

        }
        revalidate();
    }

}
