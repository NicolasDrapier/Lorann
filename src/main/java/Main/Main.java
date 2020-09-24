package Main;

import Controller.Controller;
import Model.Model;
import Model.Player;
import View.GameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        /*
                O---------------------O
                |                     |
                |                     |
                |                     |
                |             P       |
                |                     G
                |                     |
                O---------------------O
         */
        model.buildMap("23$O%21-%O%|%X%20 %2|%20 %X%2|%21 %2|%13 %P%7 %2|%21 %G%|%21 %|%O%21-%O");
        Player player = model.getPlayer();

        Controller controller = new Controller();
        controller.setModel(model);

        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame(model, controller);
            player.addPropertyChangeListener(gameFrame);
        });


    }
}
