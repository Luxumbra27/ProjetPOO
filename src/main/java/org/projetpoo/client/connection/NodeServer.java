package org.projetpoo.client.connection;

import org.projetpoo.client.gui.MainWindow;

import java.net.ServerSocket;
import java.net.Socket;


public class NodeServer implements Runnable {

    private int _port;
    private ServerSocket _serverSocket;

    public NodeServer() throws Exception {
        this._serverSocket = new ServerSocket(0);
        this._port = _serverSocket.getLocalPort();
        MainWindow.NODE_LISTEN_PORT = _port;
    }

    public void run() {

        System.out.println("[DBG] Client listening on port: " + _port);
        Socket sock;
        while (true) {

            try {
                sock = _serverSocket.accept();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            MainWindow.communityController.updateConnectedList();

            ChatController chatController = new ChatController(sock);

            Thread thread = new Thread(chatController);
            thread.start();
        }
    }
}