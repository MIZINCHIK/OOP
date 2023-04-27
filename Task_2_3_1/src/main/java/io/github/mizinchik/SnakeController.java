package io.github.mizinchik;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static io.github.mizinchik.SnakeController.Direction.*;

public class SnakeController extends Controller {
    public enum Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }
    private Direction currentDirection = RIGHT;

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    @FXML
    private void controlKeys(KeyEvent event) {
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