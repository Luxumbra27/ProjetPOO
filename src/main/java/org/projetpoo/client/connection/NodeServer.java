package org.projetpoo.client.connection;

import org.projetpoo.client.gui.MainWindow;

import java.net.ServerSocket;
import java.net.Socket;


public class NodeServer implements Runnable{

    private int _port;
    private ServerSocket _serverSocket;
    private MainWindow _mainWindow;

    public NodeServer(int port, MainWindow mainWindow) throws Exception {
        this._port = port;
        this._serverSocket = new ServerSocket(port);
        this._mainWindow = mainWindow;
    }

    public void run(){

        System.out.println("[DBG] Client listening on port: " + _port);
        Socket sock;
        while (true) {

            try {
                sock = _serverSocket.accept();
            } catch (Exception e){
                e.printStackTrace();
                break;
            }
            ChatController chatController = new ChatController(sock, _mainWindow);

            Thread thread = new Thread(chatController);
            thread.start();
        }
    }
}