package org.projetpoo.client.connection;

import org.projetpoo.client.gui.ChatWidget;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.users.RemoteUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Chat implements Runnable {

    private Socket _socket;
    private BufferedReader _in;
    private PrintWriter _out;
    private ChatWidget _chatWidget;
    private RemoteUser _remoteUser;

    public Chat(MainWindow mainWindow, RemoteUser remoteUser) throws Exception{

        this._remoteUser = remoteUser;
        this._chatWidget = new ChatWidget(mainWindow.getContentPane(), this);
        initiateChat();

        mainWindow.addComponent(_chatWidget);
    }

    public Chat(Socket socket, MainWindow mainWindow, RemoteUser remoteUser) throws Exception{

        this._socket = socket;
        this._remoteUser = remoteUser;
        this._chatWidget = new ChatWidget(mainWindow.getContentPane(), this);

        mainWindow.addComponent(_chatWidget);
    }

    private void initiateChat() throws Exception{
        _socket = new Socket(_remoteUser.getIPAddress(), _remoteUser.getPort());
        this._out = new PrintWriter(_socket.getOutputStream(),true);
        this._in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
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
