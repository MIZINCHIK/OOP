package io.github.mizinchik;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SnakeController {
    protected static final int RIGHT = 0;
    protected static final int LEFT = 1;
    protected static final int UP = 2;
    protected static final int DOWN = 3;
    private static int currentDirection;

    @FXML
    protected static Canvas canvas;

    protected static final int HEIGHT = (int) canvas.getHeight();
    protected static final int WIDTH = (int) canvas.getWidth();
    protected static final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    protected int getCurrentDirection() {
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