package org.projetpoo.client.gui;

import org.projetpoo.client.users.LocalUserController;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UserLoginPage extends JPanel {

    private LocalUserController _localUser;



    public UserLoginPage(LocalUserController userController) {
        super();
        this._localUser = userController;

        initComponents();
    }

    public void println(String str) {
        loginInfo.append(str + System.lineSeparator());
    }

    private void loginButtonActionPerformed(ActionEvent e) {
        _localUser.login(userNameInput.getText());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Etienne de Prémare
        loginInfo = new JTextArea();
        userNameInput = new JTextField();
        loginButton = new JButton();

        //======== this ========

        // JFormDesigner evaluation mark
        setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                        java.awt.Color.red), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //---- loginInfo ----
        loginInfo.setEditable(false);
        loginInfo.setRequestFocusEnabled(false);
        add(loginInfo);

        //---- userNameInput ----
        userNameInput.setHorizontalAlignment(SwingConstants.CENTER);
        add(userNameInput);

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));
        add(loginButton);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Etienne de Prémare
    private JTextArea loginInfo;
    private JTextField userNameInput;
    private JButton loginButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
