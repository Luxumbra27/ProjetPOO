package org.projetpoo.client.connection;

import org.projetpoo.client.gui.CommunityWidget;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.users.RemoteUser;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CommunityController {

    public CommunityWidget communityWidget;
    private ArrayList<RemoteUser> _connectedUsers;
    private ManagementConnection _managementConnection;

    public CommunityController(ManagementConnection managementConnection) {
        this._managementConnection = managementConnection;


        this.communityWidget = new CommunityWidget(this);

        updateConnectedList();
    }


    private void startChatWith(String nickName) {

        ChatController chat;
        try {
            chat = new ChatController(getUserByNickName(nickName));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Thread thread = new Thread(chat);
        thread.start();
    }


    public void switchChat(String userName) {
        ChatController chatController = MainWindow.activeChats.get(userName);
        if (chatController != null) {
            if (chatController.isOpen()) {
                ((CardLayout) MainWindow.chatsPanel.getLayout()).show(MainWindow.chatsPanel, userName);
            } else {
                if (askChatConfirmation(userName)) {
                    startChatWith(userName);
                }
            }
        } else {
            if (askChatConfirmation(userName)) {
                startChatWith(userName);
            }
        }

    }


    private boolean askChatConfirmation(String userName) {
        int response = JOptionPane.showConfirmDialog(null,
                "Do you want to start a chat with " + userName + " ?",
                "Sta0rt chat ?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.NO_OPTION) {
            return false;
        } else if (response == JOptionPane.YES_OPTION) {
            return true;
        } else if (response == JOptionPane.CLOSED_OPTION) {
            return false;
        }
        return false;
    }


    private RemoteUser getUserByNickName(String nickName) throws Exception {
        for (RemoteUser user : _connectedUsers) {
            if (user.getUsername().equals(nickName)) {
                return user;
            }
        }
        throw new Exception("[DBG] User cannot be found in local list.");
    }

    public void updateConnectedList() {

        System.out.println("[DBG] Updating displayed list: ");


        communityWidget.userListModel.clear();
        try {
            _connectedUsers = _managementConnection.getConnectedUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (RemoteUser user : _connectedUsers) {

            // We do not display the actual user
            if (!(user.getUsername().equals(MainWindow.localUser.userInformation.getUsername()))) {
                communityWidget.addUser(user.getUsername());
            }
        }
    }

}
