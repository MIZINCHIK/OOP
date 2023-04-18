package io.github.mizinchik;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static io.github.mizinchik.SnakeController.Direction.*;

public class SnakeController {
    @FXML
    private Canvas canvas;
    protected enum Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }
    private Direction currentDirection = RIGHT;

    protected Canvas getCanvas() {
        return canvas;
    }

    protected Direction getCurrentDirection() {
        return currentDirection;
    }

    @FXML
    protected void controlKeys(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            if (currentDirection != LEFT) {
                currentDirection = RIGHT;
            }
        } else if (code == KeyCode.LEFT || code == KeyCode.A) {
            if (currentDirection != RIGHT) {
                currentDirection = LEFT;
            }
        } else if (code == KeyCode.UP || code == KeyCode.W) {
            if (currentDirection != DOWN) {
                currentDirection = UP;
            }
        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
            if (currentDirection != UP) {
                currentDirection = DOWN;
            }
        }
    }
}