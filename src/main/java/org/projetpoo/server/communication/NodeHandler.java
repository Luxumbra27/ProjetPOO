package org.projetpoo.server.communication;

import org.projetpoo.client.users.RemoteUser;
import org.projetpoo.server.data.DatabaseConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class NodeHandler extends Thread {

    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;
    private ObjectOutputStream _outObj;
    private DatabaseConnection _databaseConnection;

    private RemoteUser _remoteUser;

    NodeHandler(Socket socket, DatabaseConnection connection) throws Exception {

        _socket = socket;
        _out = new PrintWriter(_socket.getOutputStream(), true);
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _outObj = new ObjectOutputStream(_socket.getOutputStream());
        _databaseConnection = connection;

        _in.readLine(); // prevents initial bug

        // In case of a socket error, the user is disconnected
        this.setUncaughtExceptionHandler((th, ex) -> {
            _databaseConnection.setDisconnected(_remoteUser);
            System.out.println("[DBG] User " + _remoteUser.getUsername() + " disconnected.");
            ex.printStackTrace();
        });

    }


    private String getLastLine() {
        String input = null;
        try {
            input = _in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }


    private void sendConnectedUsersList() {
        try {
            _outObj.writeObject(_databaseConnection.getConnectedUsers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void userLogin(String nickname) {

        boolean isConnected = false;
        boolean New = false;
        try {
            isConnected = _databaseConnection.getUserStatusByNickName(nickname);
        } catch (Exception e) {
            //e.printStackTrace();
            New = true;
        }

        if (isConnected) {
            _out.println("userExists");
            return;
        }

        _out.println("getPort");

        int listen_port = 0;
        try {
            listen_port = Integer.parseInt(_in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        _remoteUser = new RemoteUser(nickname, _socket.getLocalAddress().getHostName(), listen_port);

        if (New) {
            _databaseConnection.addUser(_remoteUser);
        } else {
            _databaseConnection.setConnected(_remoteUser);
        }


        System.out.println("[DBG] User " + nickname + " connected.");
    }


    public void run() {

        System.out.println("[DBG]" + _socket.getRemoteSocketAddress());
        String recv;

        while (_socket.isConnected()) {

            recv = getLastLine();

            switch (recv) {
                case "getConnectedUsers":
                    System.out.println("[DBG] Get connected users command. ");
                    sendConnectedUsersList();
                    break;
                case "login":
                    userLogin(getLastLine());
                    break;
                default:
                    System.out.println("[DBG] Received:" + recv);
                    break;
            }
        }
    }
}
