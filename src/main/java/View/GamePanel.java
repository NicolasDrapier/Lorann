package View;

import Model.GameObject;
import Model.Model;
import Model.Position;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final Model model;

    public GamePanel(Model model) {
        this.model = model;
        this.setLayout(new FlowLayout());
    }

    public void paintComponent(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (GameObject o : model.getGameObjects()) {
            Position position = o.getPosition();
            graphics.drawImage(
                    o.getImageIcon().getImage(),
                    position.getX(),
                    position.getY(),
                    48,
                    48,
                    null);
        }
    }

    public Model getModel() {
        return model;
    }
}
