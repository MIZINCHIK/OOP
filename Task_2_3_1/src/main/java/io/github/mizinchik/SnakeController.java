package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.List;

import static io.github.mizinchik.SnakeController.Direction.*;
import static io.github.mizinchik.SnakeView.*;

public class SnakeController extends Controller {
    public enum Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }
    private Direction currentDirection = RIGHT;
    private GraphicsContext graphicsContext;
    private Canvas canvas;

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

    public void takeControl(Parent root, Stage stage, Canvas canvas) {
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
        SnakeModel game = new SnakeModel(15, 15, this);
        game.run();
    }

    public void gameOver() {
        drawGameOver(graphicsContext, (int) canvas.getWidth(), (int) canvas.getHeight());
    }

    public void prepareField(Point food, int rows, Point head, List<Point> body, int score) {
        int size = getSquareSize(rows);
        drawPlayground(graphicsContext, 3, 10, 10);
        drawFood(food, graphicsContext, size);
        drawSnakeHead(graphicsContext, head, size);
        drawSnakeBody(graphicsContext, body, size);
        drawScore(graphicsContext, score);
    }

    public int getSquareSize(int rows) {
        return (int) (canvas.getWidth() / rows);
    }

    public double getWidth() {
        return canvas.getWidth();
    }

    public double getHeight() {
        return canvas.getHeight();
    }
}