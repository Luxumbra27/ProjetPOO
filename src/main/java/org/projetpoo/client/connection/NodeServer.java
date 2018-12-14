package org.projetpoo.client.connection;

import org.projetpoo.client.gui.MainWindow;

import java.net.ServerSocket;
import java.net.Socket;


public class NodeServer {

    private int _port;
    private ServerSocket _serverSocket;
    private MainWindow _mainWindow;

    public NodeServer(int port, MainWindow mainWindow) throws Exception {
        this._port = port;
        this._serverSocket = new ServerSocket(port);
        this._mainWindow = mainWindow;
    }

    public void listen() throws Exception {

        System.out.println("Client listening on port " + _port);
        while (true) {

            Socket sock = _serverSocket.accept();
            Chat chat = new Chat(sock, _mainWindow, _mainWindow.managementSystem.getRemoteUserBySocket(sock));

            Thread thread = new Thread(chat);
            thread.start();
        }
    }
}