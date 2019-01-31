package org.projetpoo.server.communication;


import org.projetpoo.server.data.DatabaseConnection;

import java.net.ServerSocket;


public class TCPServer {

    private static final int DEFAULT_LISTEN_PORT = 2000;
    public DatabaseConnection connection;
    private int _port;
    private ServerSocket _serverSocket;

    public TCPServer(int port) throws Exception {
        _port = port;
        _serverSocket = new ServerSocket(port);
        connection = new DatabaseConnection();
        connection.disconnectAll();
    }

    public static void main(String[] args) throws Exception {

        TCPServer serverInstance = new TCPServer(DEFAULT_LISTEN_PORT);
        try {
            serverInstance.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listen() throws Exception {

        System.out.println("[DBG] Listening on port " + _port);
        while (_serverSocket.isBound()) {

            NodeHandler handler = new NodeHandler(_serverSocket.accept(), connection);

            System.out.println("[DBG] New client connected");

            handler.start();
        }
    }
}