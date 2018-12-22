package org.projetpoo.server.communication;


import java.net.ServerSocket;

import org.projetpoo.server.data.DatabaseConnection;


public class TCPServer {

    private int _port;
    private ServerSocket _serverSocket;
    public DatabaseConnection connection;

    private static final int DEFAULT_LISTEN_PORT = 2000;

    public TCPServer(int port) throws Exception {
        _port = port;
        _serverSocket = new ServerSocket(port);
        connection = new DatabaseConnection();
    }

    public void listen() throws Exception {

        System.out.println("[DBG] Listening on port " + _port);
        while (true) {

            NodeHandler handler = new NodeHandler(_serverSocket.accept(), connection);

            System.out.println("[DBG] New client connected");

            Thread thread = new Thread(handler);
            thread.start();
        }
    }


    public static void main(String[] args) throws Exception{

        TCPServer serverInstance = new TCPServer(DEFAULT_LISTEN_PORT);
        try {
            serverInstance.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}