package org.projetpoo.client.gui;

import org.projetpoo.client.connection.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWidget extends Widget implements ActionListener {

    private Chat _chat;
    private JTextArea _discussion;
    private JTextArea _input;
    private JButton _sendButton;

    public ChatWidget(Container container, Chat chat) {

        super(container);
        this._chat = chat;

        this.setLayout(new BorderLayout());

        _discussion = new JTextArea();
        _discussion.setEditable(false);

        _input = new JTextArea();
        _input.setEditable(true);

        _sendButton = new JButton("Send.");
        _sendButton.addActionListener(this);


        this.add(_discussion, BorderLayout.NORTH);
        this.add(_input, BorderLayout.NORTH);
        this.add(_sendButton, BorderLayout.NORTH);

    }

    public void println(String str){
        _discussion.append(str + "\n");
    }

    public void actionPerformed(ActionEvent arg){
        if (arg.getSource() == _sendButton){
            _chat.sendMessage(_input.getText());
        }
    }

}
