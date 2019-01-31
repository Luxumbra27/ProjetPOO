package org.projetpoo.client.gui;

import org.projetpoo.client.connection.ChatController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatWidget extends Widget {

    private ChatController _chatController;
    private Font sentFont;
    private Font receivedFont;

    public ChatWidget(ChatController chatController) {

        super();

        this._chatController = chatController;

        initComponents();

        sentFont = new Font(getFont().getFontName(), Font.ITALIC, 14);
        receivedFont = new Font(getFont().getFontName(), Font.BOLD, 13);
    }

    public void printReceived(String str) {
        setFont(receivedFont);
        _discussion.append( str + "\n");
    }


    private void printSent(String str){
        setFont(sentFont);
        _discussion.append(str + "\n");
    }


    private void _sendButtonActionPerformed(ActionEvent e) {
        _chatController.sendMessage(_input.getText());
        printSent(" > " + _input.getText());
        _input.setText("");
    }

    private void _closeButtonActionPerformed(ActionEvent e) {
        _chatController.close();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Etienne de Prémare
        scrollPane1 = new JScrollPane();
        _discussion = new JTextArea();
        _input = new JTextArea();
        _sendButton = new JButton();
        _closeButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(200, 300));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0E-4};

        //======== scrollPane1 ========
        {

            //---- _discussion ----
            _discussion.setEditable(false);
            _discussion.setRows(14);
            scrollPane1.setViewportView(_discussion);
        }
        add(scrollPane1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- _input ----
        _input.setTabSize(4);
        _input.setRows(4);
        add(_input, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- _sendButton ----
        _sendButton.setText("Send");
        _sendButton.setMargin(new Insets(5, 5, 5, 5));
        _sendButton.addActionListener(e -> _sendButtonActionPerformed(e));
        add(_sendButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));

        //---- _closeButton ----
        _closeButton.setText("Close");
        _closeButton.addActionListener(e -> _closeButtonActionPerformed(e));
        add(_closeButton, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
            new Insets(0, 0, 0, 0), 0, 0));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Etienne de Prémare
    private JScrollPane scrollPane1;
    private JTextArea _discussion;
    private JTextArea _input;
    private JButton _sendButton;
    private JButton _closeButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
