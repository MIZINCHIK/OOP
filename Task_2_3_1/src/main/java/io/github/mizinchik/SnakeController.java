package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.List;

import static io.github.mizinchik.SnakeController.Direction.*;
import static io.github.mizinchik.SnakeView.*;

public class SnakeController extends Controller {
    @FXML
    ResizableCanvas canvas;
    @FXML
    Pane pane;
    public enum Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP
    }
    private Direction currentDirection = RIGHT;
    private GraphicsContext graphicsContext;

    public Direction getCurrentDirection() {
        return currentDirection;
    }
    private int rows;
    private int columns;


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

    private void buildFromFile(int levelId) {

    }

    public void takeControl(Parent root, Stage stage, int levelId) {
        int speed = buildFromFile();
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
        SnakeModel game = new SnakeModel(rows, columns, this, speed);
        game.run();
    }

    public void gameOver() {
        drawGameOver(graphicsContext, (int) canvas.getWidth(), (int) canvas.getHeight());
    }

    public void prepareField(Point food, Point head, List<Point> body, int score) {
        double squareWidth = getSquareWidth();
        double squareHeight = getSquareHeight();
        drawPlayground(graphicsContext, squareWidth, squareHeight, rows, columns);
        drawFood(graphicsContext, food, squareWidth, squareHeight);
        drawSnakeHead(graphicsContext, head, squareWidth, squareHeight);
        drawSnakeBody(graphicsContext, body, squareWidth, squareHeight);
        drawScore(graphicsContext, score);
    }

    public double getSquareWidth() {
        return canvas.getWidth() / columns;
    }

    public double getSquareHeight() {
        return canvas.getHeight() / rows;
    }

    public double getWidth() {
        return canvas.getWidth();
    }

    public double getHeight() {
        return canvas.getHeight();
    }
}