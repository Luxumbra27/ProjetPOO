package org.projetpoo.server.communication;

import org.projetpoo.client.users.RemoteUser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class NodeHandler implements Runnable {

    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;
    private ObjectOutputStream _outObj;

    public NodeHandler(Socket socket) throws Exception{

        _socket = socket;
        _out = new PrintWriter(_socket.getOutputStream(), true);
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _outObj = new ObjectOutputStream(_socket.getOutputStream());
        _in.readLine(); // prevents initial bug

    }


    private String getLastLine() {
        String input = null;
        try {
            input =_in.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return input;
    }


    private void sendConnectedUsersList(){

        RemoteUser testUser = new RemoteUser("debug2", "127.0.0.1", 1234);
        ArrayList<RemoteUser> testList = new ArrayList<>();
        testList.add(testUser);
        try {
            _outObj.writeObject(testList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void userLogin(String nickname){
        if (nickname.equals("fail")){
            _out.println("userExists");
        } else {
            _out.println("userLoggedIn");
            // TODO add/update user in database
        }
    }


    public void run(){

        System.out.println("[DBG]" + _socket.getRemoteSocketAddress());
        String recv;

        while(_socket.isConnected()){
            recv = getLastLine();
            switch (recv){
                case "getConnectedUsers":
                    System.out.println("[DBG] get connected users ");
                    sendConnectedUsersList();
                    break;
                case "login":
                    userLogin(getLastLine());
                    break;
                default:
                    System.out.println("[DBG] Received:" + recv);
                    break;
            }
        }

    }
}
