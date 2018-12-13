package org.projetpoo.client.connection;

import org.projetpoo.client.gui.ChatWidget;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.gui.Widget;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Chat implements Runnable {

    private Socket _socket;
    private BufferedReader _in;
    private PrintWriter _out;
    private Widget _chatWidget;

    public Chat(Socket socket, MainWindow mainWindow) throws Exception{

        this._socket = socket;
        this._in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this._out = new PrintWriter(socket.getOutputStream(),true);
        this._chatWidget = new ChatWidget(Color.CYAN, this);

        mainWindow.add(_chatWidget);
    }

    public void run(){
        while (true){
            try {
                _chatWidget.println(_in.readLine());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
