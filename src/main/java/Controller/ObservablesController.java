package Controller;

import Model.Model;
import View.GameFrame;

public class ObservablesController {
    private final Model model;
    private final GameFrame gameFrame;

    public ObservablesController(Model model, GameFrame gameFrame) {
        this.model = model;
        this.gameFrame = gameFrame;
    }


}
