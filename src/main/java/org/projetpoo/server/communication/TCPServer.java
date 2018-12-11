package org.projetpoo.server.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;


public class TCPServer {

    private int _port;
    private ServerSocket _serverSocket;

    TCPServer(int port) throws Exception {
        _port = port;
        _serverSocket = new ServerSocket(port);
    }

    public void listen() throws Exception {

        System.out.println("Listening on port %d" + _port);
        while (true) {
            Socket socket = _serverSocket.accept();

            System.out.println("New client connected");
        }
    }


    public static void main(String[] args) throws Exception{

        TCPServer serverInstance = new TCPServer(1234);
        try {
            serverInstance.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}