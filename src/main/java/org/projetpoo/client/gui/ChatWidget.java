package org.projetpoo.client.gui;

import org.projetpoo.client.connection.ChatController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWidget extends Widget implements ActionListener {

    private ChatController _chatController;
    /*private JTextArea _discussion;
    private JTextArea _input;
    private JButton _sendButton;*/

    public ChatWidget(ChatController chatController) {

        super();

        this._chatController = chatController;

        initComponents();


    }

    public void println(String str){
        _discussion.append(str + "\n");
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _sendButton){
            _chatController.sendMessage(_input.getText());
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Etienne de Prémare
        scrollPane1 = new JScrollPane();
        _discussion = new JTextArea();
        _input = new JTextArea();
        _sendButton = new JButton();
        button2 = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 1.0E-4};

        //======== scrollPane1 ========
        {

            //---- _discussion ----
            _discussion.setEditable(false);
            scrollPane1.setViewportView(_discussion);
        }
        add(scrollPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 6, 0), 0, 0));
        add(_input, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 6, 0), 0, 0));

        //---- _sendButton ----
        _sendButton.setText("Send");
        _sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChatWidget.this.actionPerformed(e);
            }
        });
        add(_sendButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 6, 0), 0, 0));

        //---- button2 ----
        button2.setText("Test");
        add(button2, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.BASELINE, GridBagConstraints.HORIZONTAL,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Etienne de Prémare
    private JScrollPane scrollPane1;
    private JTextArea _discussion;
    private JTextArea _input;
    private JButton _sendButton;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
