package org.projetpoo.client.gui;

import org.projetpoo.client.connection.CommunityController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CommunityWidget extends Widget {

    private CommunityController _communityController;
    public DefaultListModel<String> userListModel;


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
        _communityController.updateConnectedList();
    }

    private void nextChatButtonActionPerformed(ActionEvent e) {
        nextChat();
    }

    private void userListValueChanged(ListSelectionEvent e) {
        _communityController.switchChat(userListModel.get(e.getFirstIndex()));

    }

    public void nextChat(){
        ((CardLayout) MainWindow.chatsPanel.getLayout()).next(MainWindow.chatsPanel);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Etienne de Prémare
        scrollPane = new JScrollPane();
        userList = new JList<>(userListModel);
        updateListButton = new JButton();
        nextChatButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(80, 200));
        setPreferredSize(new Dimension(140, 300));

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

        setLayout(new GridLayout(3, 1, 20, 20));

        //======== scrollPane ========
        {

            //---- userList ----
            userList.setFont(userList.getFont().deriveFont(userList.getFont().getSize() + 2f));
            userList.setVisibleRowCount(12);
            userList.addListSelectionListener(e -> userListValueChanged(e));
            scrollPane.setViewportView(userList);
        }
        add(scrollPane);

        //---- updateListButton ----
        updateListButton.setText("Update List");
        updateListButton.addActionListener(e -> updateListButtonActionPerformed(e));
        add(updateListButton);

        //---- nextChatButton ----
        nextChatButton.setText("Next Chat");
        nextChatButton.addActionListener(e -> nextChatButtonActionPerformed(e));
        add(nextChatButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Etienne de Prémare
    private JScrollPane scrollPane;
    private JList<String> userList;
    private JButton updateListButton;
    private JButton nextChatButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
