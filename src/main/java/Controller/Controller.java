package Controller;

import Model.Direction;
import Model.Model;

import java.awt.event.KeyEvent;

import static Model.Direction.*;

public class Controller {
    private Model model;

    public Controller() {

    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void keyOrder(int keyCode) {

        if (keyCode != KeyEvent.VK_SPACE) {
            Direction direction;

            switch (keyCode) {
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> direction = RIGHT;
                case KeyEvent.VK_Q, KeyEvent.VK_LEFT -> direction = LEFT;
                case KeyEvent.VK_Z, KeyEvent.VK_UP -> direction = UP;
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> direction = DOWN;
                default -> throw new IllegalStateException("Unexpected value: " + keyCode);
            }
            model.movePlayer(direction);
        }
        else {
            model.launchSpell();
        }
    }

    public void launches() {
        model.launchSpell();
    }
}
