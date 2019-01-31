package org.projetpoo.client.users;

import org.projetpoo.client.connection.ManagementConnection;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.gui.UserLoginPage;

public class LocalUserController {


    public UserInformation userInformation;
    private boolean is_connected = false;
    private ManagementConnection _managementConnection;
    private MainWindow _mainWindow;
    private UserLoginPage _userLoginPage;


    public LocalUserController(ManagementConnection managementConnection, MainWindow mainWindow) {
        this._mainWindow = mainWindow;
        this._managementConnection = managementConnection;

        this._userLoginPage = new UserLoginPage(this);
        _mainWindow.setContentPane(_userLoginPage);

        userInformation = new UserInformation("");
    }


    private void setConnected() {
        this.is_connected = true;
    }


    public void setDisconnected() {
        this.is_connected = false;
    }

    public boolean isConnected() {
        return is_connected;
    }


    public void login(String userName) {
        try {
            if (!_managementConnection.isConnected()) {
                System.out.println("[DBG] Attempting connection...");
                _managementConnection.connect();
            }

            System.out.println("[DBG] Logging in...");
            _managementConnection.sendMessage("login");
            _managementConnection.sendMessage(userName);

            switch (_managementConnection.getReply()) {

                case "getPort":
                    _managementConnection.sendMessage(Integer.toString(MainWindow.NODE_LISTEN_PORT));
                    _userLoginPage.println("Successfully connected !");
                    setConnected();
                    userInformation.setUsername(userName);
                    _mainWindow.goToChat();

                    break;
                case "userExists":
                    _userLoginPage.println("Your login is already used !");
                    break;
                default:
                    System.out.println("[DBG] Server response not expected.");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            _userLoginPage.println("Connection problem.");
        }
    }

}
