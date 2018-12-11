package org.projetpoo.server.communication;


import java.net.ServerSocket;
import org.projetpoo.server.communication.NodeHandler;


public class TCPServer {

    private int _port;
    private ServerSocket _serverSocket;

    public TCPServer(int port) throws Exception {
        _port = port;
        _serverSocket = new ServerSocket(port);
    }

    public void listen() throws Exception {

        System.out.println("Listening on port " + _port);
        while (true) {

            System.out.println("New client connected");

            NodeHandler handler = new NodeHandler(_serverSocket.accept());
            Thread thread = new Thread(handler);
            thread.start();
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