package org.projetpoo.client.connection;

import org.projetpoo.client.gui.ChatWidget;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.gui.UserInfoWidget;
import org.projetpoo.client.users.RemoteUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class ChatController implements Runnable {

    private Socket _socket;
    private BufferedReader _in;
    private PrintWriter _out;
    private ChatWidget _chatWidget;
    private RemoteUser _remoteUser;

    public ChatController(MainWindow mainWindow, RemoteUser remoteUser){
        // On chat init send

        this._remoteUser = remoteUser;
        this._chatWidget = new ChatWidget(this);
        mainWindow.getContentPane().add(_chatWidget);
        try {
            _socket = new Socket(_remoteUser.getHostname(), _remoteUser.getPort());
            this._out = new PrintWriter(_socket.getOutputStream(),true);
            this._in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _out.println(UserInfoWidget.userName);
        } catch (Exception e){
            e.printStackTrace();
        }

        mainWindow.addComponent(_chatWidget);
        mainWindow.revalidate();
    }

    public ChatController(Socket socket, MainWindow mainWindow){
        // On chat init reception

        this._socket = socket;
        this._chatWidget = new ChatWidget(this);
        mainWindow.getContentPane().add(_chatWidget);

        try {

            this._out = new PrintWriter(_socket.getOutputStream(),true);
            this._in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _remoteUser = new RemoteUser(_in.readLine(), _socket.getLocalAddress().getHostName(), _socket.getPort());

        } catch (Exception e){
            e.printStackTrace();
        }

        mainWindow.addComponent(_chatWidget);
        mainWindow.revalidate();

    }


    public void sendMessage(String str){
        _out.println(str);
    }

    public void run(){


        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                _chatWidget.println(_in.readLine());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
