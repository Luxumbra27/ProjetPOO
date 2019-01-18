package org.projetpoo.client.connection;

import org.projetpoo.server.data.DatabaseConnection;
import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.users.RemoteUser;

import java.net.*;


public class NodeServer implements Runnable{

    private int _port;
    private DatagramSocket _serverSocket;
    private byte[] buf = new byte[256];
    private DatabaseConnection _databaseConnection;
    private MainWindow _mainWindow;

    public NodeServer(int port, MainWindow mainWindow) throws Exception {
        this._port = port;
        this._serverSocket = new DatagramSocket(MainWindow.DISCOVERY_PORT);
        _databaseConnection = new DatabaseConnection();
        _mainWindow = mainWindow;
    }

    public void run(){

        System.out.println("[DBG] Client listening on port: " + _port);
        while (true) {

            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                _serverSocket.receive(packet);
                handle(packet);
            } catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private void handle(DatagramPacket packet){

        String[] text = (new String(packet.getData(), 0, packet.getLength())).split("[-]");

        switch (text[0]){

            case "1": // User signals connection 1-USERNAME-PORT
                RemoteUser newUser = new RemoteUser(text[1], packet.getAddress().getHostName(), Integer.parseInt(text[2]));
                _databaseConnection.addUser(newUser);
                break;
            case "2": // User wants to start chat 2-USERNAME-PORT
                RemoteUser remUser = new RemoteUser(text[1], packet.getAddress().getHostName(), Integer.parseInt(text[2]));
                Chat chat = new Chat(_mainWindow, remUser);

                Thread thread = new Thread(chat);
                thread.start();


        }

    }
}