package org.projetpoo.client.gui;

import org.projetpoo.client.users.RemoteUser;

import javax.swing.*;
import java.awt.*;

public class UserLabel extends JLabel {

    private RemoteUser _associatedUser;
    private static final Font _selFont = new Font("sans-serif", Font.BOLD, 14);
    private static final Font _unselFont = new Font("sans-serif", Font.PLAIN, 14);


    public UserLabel(String text, RemoteUser remoteUser) {
        super(text);
        setFont(_unselFont);
        this._associatedUser = remoteUser;
    }


    public void setSelected(){
        setFont(_selFont);
        revalidate();
    }


    public void setUnselected(){
        setFont(_unselFont);
        revalidate();
    }


    public RemoteUser getAssociatedUser(){
        return _associatedUser;
    }
}
