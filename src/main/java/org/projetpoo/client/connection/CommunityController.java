package org.projetpoo.client.connection;

import org.projetpoo.client.gui.CommunityWidget;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.gui.UserInfoWidget;
import org.projetpoo.client.users.RemoteUser;

import java.util.ArrayList;

public class CommunityController {

    private MainWindow _mainWindow;
    private ArrayList<RemoteUser> _connectedUsers;
    private ManagementConnection _managementConnection;
    private CommunityWidget _communityWidget;

    public CommunityController(MainWindow mainWindow, ManagementConnection managementConnection) {
        this._mainWindow = mainWindow;
        this._managementConnection = managementConnection;


        this._communityWidget = new CommunityWidget(this);
        _mainWindow.getContentPane().add(_communityWidget);

    }


    public void startChatWith(String nickName){

        ChatController chat;
        try {
            chat = new ChatController(_mainWindow, getUserByNickName(nickName));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        Thread thread = new Thread(chat);
        thread.start();
    }


    private RemoteUser getUserByNickName(String nickName) throws Exception{
        for (RemoteUser user: _connectedUsers){
            if (user.getNickname().equals(nickName)){
                return user;
            }
        }
        throw new Exception("[DBG] User cannot be found in local list.");
    }

    public void updateConnectedList(){

        System.out.println("[DBG] Updating displayed list: ");

        try {
            _connectedUsers = _managementConnection.getConnectedUsers();
        } catch (Exception e){
            e.printStackTrace();
        }

        for (RemoteUser user: _connectedUsers){

            // We do not display the actual user
            if (!(user.getNickname().equals(UserInfoWidget.userName))){
                _communityWidget.addUser(user.getNickname());
            }
        }
    }

}
