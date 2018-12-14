package org.projetpoo.server.communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;

public class NodeHandler implements Runnable {

    private Socket _socket;
    private PrintWriter _out;
    private BufferedReader _in;

    public NodeHandler(Socket socket) throws Exception{
        _socket = socket;
        _out = new PrintWriter(_socket.getOutputStream(), true);
        _in = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
    }

    public void run(){
        try {
            while (true){
                System.out.println(_socket.getRemoteSocketAddress());
                System.out.println("Recv: " + _in.readLine());
                //_out.println("Hello from server.");
                TimeUnit.SECONDS.sleep(1);
                //_out.println("After message.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
