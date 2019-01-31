package org.projetpoo;

import org.projetpoo.server.communication.TCPServer;

public class TCPServerTest extends TCPServer implements Runnable {

    public TCPServerTest(int port) throws Exception {
        super(port);
    }

    public void run(){
        try {
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
