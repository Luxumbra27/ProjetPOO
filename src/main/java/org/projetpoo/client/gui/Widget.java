package org.projetpoo.client.gui;


import javax.swing.*;
import java.awt.*;


public class Widget extends JTextArea {


    public Widget(Color g) {
        setBackground(g);
        setEditable(false);

    }

    public void println(String str) {
        append(str + "\n");
    }
}
