package org.projetpoo.client.connection;

import org.projetpoo.client.gui.MainWindow;
import org.projetpoo.client.gui.UserInfoWidget;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class Discovery {

    public Discovery() {

    }

    private List<InetAddress> getBroadcastAddress(){
        List<InetAddress> broadcastList = new ArrayList<>();
        try{
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                networkInterface.getInterfaceAddresses().stream()
                        .map(InterfaceAddress::getBroadcast)
                        .filter(Objects::nonNull)
                        .forEach(broadcastList::add);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return broadcastList;
    }

    private void broadcast(String broadcastMessage, InetAddress address) throws Exception {
        DatagramSocket _datagramSocket = new DatagramSocket();
        _datagramSocket.setBroadcast(true);

        byte[] buffer = broadcastMessage.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, MainWindow.DISCOVERY_PORT);
        _datagramSocket.send(packet);
        _datagramSocket.close();
    }



    public void signalConnection(){
        for (InetAddress address : getBroadcastAddress()){
            try{
                broadcast(UserInfoWidget.userName, address);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
