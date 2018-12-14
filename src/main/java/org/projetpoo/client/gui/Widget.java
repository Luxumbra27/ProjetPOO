package org.projetpoo.client.gui;


import javax.swing.*;
import java.awt.*;


public class Widget extends JPanel {

    protected Container _container;

    public Widget(Container container) {
        super();
        this._container = container;
        _container.add(this);

    }

}
