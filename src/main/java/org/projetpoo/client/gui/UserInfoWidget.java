package org.projetpoo.client.gui;

import org.projetpoo.client.connection.RemoteConnection;

import java.awt.*;
import java.net.Inet4Address;

public class UserInfoWidget extends Widget {

    public RemoteConnection _serverConnection;

    public UserInfoWidget(Color g, RemoteConnection remote) {
        super(g);
        _serverConnection = remote;

    }

    public void connectButton() throws Exception{
        _serverConnection.connect();
    }
}
