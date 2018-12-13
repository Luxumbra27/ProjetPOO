package org.projetpoo.client.gui;

import org.projetpoo.client.connection.Chat;

import java.awt.*;

public class ChatWidget extends Widget{

    private Chat _chat;

    public ChatWidget(Color g, Chat chat) {

        super(g);
        this._chat = chat;

    }
}
