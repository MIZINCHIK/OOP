package io.github.mizinchik;

import io.github.mizinchik.utils.Direction;
import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Settings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.mizinchik.SnakeView.drawImage;
import static io.github.mizinchik.SnakeView.drawPoint;
import static io.github.mizinchik.SnakeView.drawString;
import static io.github.mizinchik.SnakeView.drawRoundPoint;
import static io.github.mizinchik.utils.Direction.DOWN;
import static io.github.mizinchik.utils.Direction.LEFT;
import static io.github.mizinchik.utils.Direction.RIGHT;
import static io.github.mizinchik.utils.Direction.UP;
import static io.github.mizinchik.utils.JsonReader.readLevel;

public class SnakeController extends Controller {
    @FXML
    ResizableCanvas canvas;
    @FXML
    Pane pane;
    private static final Map<KeyCode, Direction> directions = new HashMap<>();
    static {
        directions.put(KeyCode.RIGHT, RIGHT);
        directions.put(KeyCode.D, RIGHT);
        directions.put(KeyCode.LEFT, LEFT);
        directions.put(KeyCode.A, LEFT);
        directions.put(KeyCode.UP, UP);
        directions.put(KeyCode.W, UP);
        directions.put(KeyCode.DOWN, DOWN);
        directions.put(KeyCode.S, DOWN);
    }
    private Direction currentDirection = RIGHT;
    private GraphicsContext graphicsContext;
    public Direction getCurrentDirection() {
        return currentDirection;
    }
    private static final String images = "io/github/mizinchik/img/";
    private static final Color oddColor = Color.web("A2D149");
    private static final Color evenColor = Color.web("AAD751");
    private static final Color wallColor = Color.web("#45a6fc");
    private static final Color gameOverColor = Color.PALEVIOLETRED;
    private static final Color scoreColor = Color.LIGHTGOLDENRODYELLOW;
    private static final Color userHeadColor = Color.BLACK;
    private static final Color userBodyColor = Color.BLANCHEDALMOND;
    private static final Color botHeadColor = Color.CYAN;
    private static final Color botBodyColor = Color.FUCHSIA;
    private static final Color fullBodyColor = Color.DARKOLIVEGREEN;
    private static final Font font = Font.font("Comic Sans MS", 70);
    private static final Image foodImage = new Image(images + "food.png");
    private static final String gameOverText = "Game Over";
    private int speed;
    private int rows;
    private int columns;
    private SnakeModel game;


    @FXML
    private void controlKeys(KeyEvent event) {
        currentDirection = directions.get(event.getCode());
    }

    public void takeControl(Parent root, Stage stage, int levelId) {
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
        Settings settings = buildFromFile(levelId);
        game = new SnakeModel(this, settings);
        speed = game.getSpeed();
        rows = game.getRows();
        columns = game.getColumns();
        run();
    }

    public Settings buildFromFile(int levelId) {
        Settings settings;
        try {
            settings = readLevel(levelId);
        } catch (FileNotFoundException e) {
            settings = new Settings(15, 15, 1, 10, 135, new int[0][0]);
        }
        return settings;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed), e -> cycle()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void cycle() {
        if (game.isGameOver()) {
            gameOver();
        } else {
            prepareField(game.getFood(), game.getUserSnake(), game.getWalls(), game.getCompetitors(), rows, columns);
            game.makeMove(getCurrentDirection());
        }
    }

    public void gameOver() {
        drawString(graphicsContext, gameOverText,
                canvas.getWidth() / 3.5, canvas.getHeight() / 2,
                gameOverColor, font);
    }

    public void prepareField(Point food, Snake userSnake, List<Point> walls, List<Snake> competitors, int rows, int columns) {
        double squareWidth = getSquareWidth(columns);
        double squareHeight = getSquareHeight(rows);
        drawPlayground(squareWidth, squareHeight, rows, columns);
        drawFood(food, squareWidth, squareHeight);
        drawScore(userSnake.getScore());
        drawWalls(walls, squareWidth, squareHeight);
        drawSnake(userSnake, squareWidth, squareHeight, true);
        competitors.forEach(snake -> drawSnake(snake, squareWidth, squareHeight, false));

    }

    public void drawPlayground(double squareWidth, double squareHeight, int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                drawPoint(graphicsContext, column * squareWidth, row * squareHeight,
                        squareWidth, squareHeight, (row + column) % 2 == 0 ? evenColor : oddColor);
            }
        }
    }

    public void drawFood(Point food, double squareWidth, double squareHeight) {
        drawImage(graphicsContext, food.getX() * squareWidth, food.getY() * squareHeight,
                squareWidth, squareHeight, foodImage);
    }

    public void drawScore(int score) {
        drawString(graphicsContext, "Score: " + score, 10 , 35, scoreColor, font);
    }
    
    public void drawWalls(List<Point> walls, double squareWidth, double squareHeight) {
        walls.forEach(wall -> drawRoundPoint(graphicsContext, wall.getX() * squareWidth,
                wall.getY() * squareHeight, squareWidth, squareHeight, wallColor));
    }

    public void drawSnake(Snake snake, double squareWidth, double squareHeight, boolean user) {
        drawRoundPoint(graphicsContext, snake.getX() * squareWidth, snake.getY() * squareHeight,
                squareWidth, squareHeight, user ? userHeadColor : botHeadColor);
        snake.getSnakeBody().forEach(part -> drawRoundPoint(graphicsContext, part.getX() * squareWidth,
                part.getY() * squareHeight, squareWidth, squareHeight,
                part.full() ? fullBodyColor : user ? userBodyColor : botBodyColor));
    }

    public double getSquareWidth(int columns) {
        return canvas.getWidth() / columns;
    }

    public double getSquareHeight(int rows) {
        return canvas.getHeight() / rows;
    }

    public double getWidth() {
        return canvas.getWidth();
    }

    public double getHeight() {
        return canvas.getHeight();
    }
}