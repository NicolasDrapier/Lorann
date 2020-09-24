package View;

import Controller.Controller;
import Model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameFrame extends JFrame implements KeyListener, PropertyChangeListener {

    private final Controller controller;
    private final GamePanel gamePanel;

    public GameFrame(Model model, Controller controller) throws HeadlessException {
        this.controller = controller;
        this.gamePanel = new GamePanel(model);

        this.setTitle("Lorann Game");
        this.setSize(1140, 608);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(this.gamePanel);
        this.setResizable(true);
        this.addKeyListener(this);
        this.setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            controller.keyOrder(e.getKeyCode());
        } catch (Exception ignored) { }
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        controller.launches();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.gamePanel.repaint();
    }
}
