package io.github.mizinchik;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public abstract class Controller {
    @FXML
    Canvas canvas;

    @FXML
    public Canvas getCanvas() {
        return canvas;
    }

    @FXML
    private void stopGame() {

    }

    @FXML
    private void showHelp() {

    }

    @FXML
    private void pauseGame() {

    }
}
