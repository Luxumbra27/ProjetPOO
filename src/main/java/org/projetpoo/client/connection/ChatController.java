package org.projetpoo.client.connection;

import org.projetpoo.client.gui.ChatWidget;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.users.RemoteUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ChatController implements Runnable {

    private ChatWidget chatWidget;
    private Socket _socket;
    private BufferedReader _in;
    private PrintWriter _out;
    private RemoteUser _remoteUser;
    private boolean _open = false;

    ChatController(RemoteUser remoteUser) {
        // On chat init send
        this._remoteUser = remoteUser;
        this.chatWidget = new ChatWidget(this);
        MainWindow.chatsPanel.add(chatWidget);
        MainWindow.activeChats.put(_remoteUser.getUsername(), this);

        try {
            _socket = new Socket(_remoteUser.getHostname(), _remoteUser.getPort());
            this._out = new PrintWriter(_socket.getOutputStream(), true);
            this._in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _out.println(MainWindow.localUser.userInformation.getUsername());
            _open = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainWindow.communityController.communityWidget.nextChat();

    }

    ChatController(Socket socket) {
        // On chat init reception

        this._socket = socket;
        this.chatWidget = new ChatWidget(this);

        try {

            this._out = new PrintWriter(_socket.getOutputStream(), true);
            this._in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _remoteUser = new RemoteUser(_in.readLine(), _socket.getLocalAddress().getHostName(), _socket.getPort());
            _open = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        MainWindow.chatsPanel.add(chatWidget);
        MainWindow.activeChats.put(_remoteUser.getUsername(), this);
        MainWindow.communityController.communityWidget.nextChat();
    }


    public void sendMessage(String str) {
        _out.println(str);
    }

    public void close() {
        if (_open) {
            _open = false;
            try {
                _in.close();
                _out.close();
                _socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    boolean isOpen() {
        return _open;
    }


    public void run() {

        while (_open) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                String recv = _in.readLine();
                if (recv != null){
                    chatWidget.printReceived(_remoteUser.getUsername() + " : " + recv);
                } else {
                    chatWidget.printReceived("Chat ended.");
                    close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
