package org.projetpoo.client.gui;

import org.projetpoo.client.connection.CommunityController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommunityWidget extends Widget {

    private CommunityController _communityController;
    private DefaultListModel<String> userListModel;


    public CommunityWidget(CommunityController communityController) {

        super();
        this._communityController = communityController;
        userListModel = new DefaultListModel<>();

        initComponents();
    }

    public void addUser(String userName) {
        userListModel.addElement(userName);
    }


    private void updateListButtonActionPerformed(ActionEvent e) {
        userListModel.clear();
        _communityController.updateConnectedList();
    }

    private void startChatButtonActionPerformed(ActionEvent e) {
        _communityController.startChatWith(userList.getSelectedValue());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Etienne de Prémare
        scrollPane1 = new JScrollPane();
        userList = new JList<>(userListModel);
        updateListButton = new JButton();
        startChatButton = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridLayout(3, 1));

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(userList);
        }
        add(scrollPane1);

        //---- updateListButton ----
        updateListButton.setText("Update List");
        updateListButton.addActionListener(this::updateListButtonActionPerformed);
        add(updateListButton);

        //---- startChatButton ----
        startChatButton.setText("Start Chat");
        startChatButton.addActionListener(this::startChatButtonActionPerformed);
        add(startChatButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Etienne de Prémare
    private JScrollPane scrollPane1;
    private JList<String> userList;
    private JButton updateListButton;
    private JButton startChatButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
