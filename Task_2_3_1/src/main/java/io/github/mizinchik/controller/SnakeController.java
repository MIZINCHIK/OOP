package io.github.mizinchik.controller;

import static io.github.mizinchik.utils.Direction.DOWN;
import static io.github.mizinchik.utils.Direction.LEFT;
import static io.github.mizinchik.utils.Direction.RIGHT;
import static io.github.mizinchik.utils.Direction.UP;
import static io.github.mizinchik.utils.JsonReader.readLevel;
import static io.github.mizinchik.view.SnakeView.drawImage;
import static io.github.mizinchik.view.SnakeView.drawPoint;
import static io.github.mizinchik.view.SnakeView.drawRoundPoint;
import static io.github.mizinchik.view.SnakeView.drawString;

import io.github.mizinchik.SnakeApplication;
import io.github.mizinchik.model.Snake;
import io.github.mizinchik.model.SnakeModel;
import io.github.mizinchik.utils.Direction;
import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Settings;
import io.github.mizinchik.view.ResizableCanvas;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/**
 * Controller for my snake game.
 *
 * @author MIZINCHIK
 */
public class SnakeController extends Controller {
    @FXML
    ResizableCanvas canvas;
    @FXML
    Pane pane;
    private static final Map<KeyCode, Direction> directions = new HashMap<>();
    private Direction currentDirection = RIGHT;

    private GraphicsContext graphicsContext;

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

    private static final String images = "io/github/mizinchik/img/";
    private static final Color oddColor = Color.web("A2D149");
    private static final Color evenColor = Color.web("AAD751");
    private static final Color wallColor = Color.web("#45a6fc");
    private static final Color gameOverColor = Color.PALEVIOLETRED;
    private static final Color gameWonColor = Color.HOTPINK;
    private static final Color scoreColor = Color.LIGHTGOLDENRODYELLOW;
    private static final Color goalColor = Color.SADDLEBROWN;
    private static final Color userHeadColor = Color.BLACK;
    private static final Color userBodyColor = Color.BLANCHEDALMOND;
    private static final Color botHeadColor = Color.CYAN;
    private static final Color botBodyColor = Color.FUCHSIA;
    private static final Color fullBodyColor = Color.DARKOLIVEGREEN;
    private static final Font font = Font.font("Comic Sans MS", 70);
    private static Image foodImage;
    private static final String gameOverText = "Game Over";
    private static final String gameWonText = "Chicken Dinner";
    private int speed;
    private int rows;
    private int columns;
    private SnakeModel game;
    private Timeline timeline;
    private Boolean onPause = false;

    /**
     * Returns current direction of the user snake.
     *
     * @return current direction of the user snake
     */
    public Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Reports the last key pressed by the user.
     *
     * @param event on the canvas
     */
    @FXML
    private void controlKeys(KeyEvent event) {
        currentDirection = directions.containsKey(event.getCode())
                ? directions.get(event.getCode()) : currentDirection;
        if (event.getCode() == KeyCode.P) {
            pauseGame();
        }
    }

    /**
     * Starts the actual game, creates the model for it and draws the window.
     *
     * @param root app parameter
     * @param stage app parameter
     * @param levelId level to start
     * @throws IOException if smth gone wrong with opening fxml or smth
     */
    public void takeControl(Parent root, Stage stage, int levelId) throws IOException {
        URL foodImageUrl = SnakeApplication.class.getResource("img/food.png");
        if (foodImageUrl == null) {
            throw new IOException("Couldn't load the food image");
        }
        foodImage = new Image(String.valueOf(foodImageUrl));
        canvas.widthProperty().bind(pane.widthProperty());
        canvas.heightProperty().bind(pane.heightProperty());
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
        Settings settings = buildFromFile(levelId);
        game = new SnakeModel(settings);
        speed = game.getTime();
        rows = game.getRows();
        columns = game.getColumns();
        run();
    }

    /**
     * Parses the settings from JSON into the corresponding object.
     *
     * @param levelId to extract from JSON
     * @return level config
     * @throws IOException if smth gone wrong
     */
    public Settings buildFromFile(int levelId) throws IOException {
        Settings settings;
        try {
            settings = readLevel(levelId);
        } catch (FileNotFoundException e) {
            settings = new Settings(15, 15, 1, 10, 135, new int[0][0], 1);
        }
        return settings;
    }

    /**
     * Runs the game (bonds the timeline with the game cycle).
     */
    public void run() {
        timeline = new Timeline(new KeyFrame(Duration.millis(speed), e -> cycle()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Draws the field and game over if the game is over or
     * makes a move in the model otherwise.
     */
    public void cycle() {
        prepareField(game.getFood(), game.getUserSnake(),
                game.getWalls(), game.getCompetitors(), rows, columns);
        if (game.isGameOver()) {
            gameOver();
        } else if (game.getUserSnake().getScore() >= game.getGoal()) {
            gameWon();
        } else {
            game.makeMove(getCurrentDirection());
        }
    }

    /**
     * Draws game over.
     */
    public void gameOver() {
        drawString(graphicsContext, gameOverText,
                canvas.getWidth() / 3.5, canvas.getHeight() / 2,
                gameOverColor, font);
    }

    /**
     * Draws game won.
     */
    public void gameWon() {
        drawString(graphicsContext, gameWonText,
                canvas.getWidth() / 3.5, canvas.getHeight() / 2,
                gameWonColor, font);
    }

    /**
     * Draws the game field.
     *
     * @param food point where it's located
     * @param userSnake player's snake
     * @param walls list of obstacle points
     * @param competitors list of AI snakes
     * @param rows on the field
     * @param columns on the field
     */
    public void prepareField(List<Point> food, Snake userSnake,
                             List<Point> walls, List<Snake> competitors,
                             int rows, int columns) {
        double squareWidth = getSquareWidth();
        double squareHeight = getSquareHeight();
        drawPlayground(squareWidth, squareHeight, rows, columns);
        for (Point meal : food) {
            drawFood(meal, squareWidth, squareHeight);
        }
        drawScore(userSnake.getScore());
        drawGoal(game.getGoal());
        drawWalls(walls, squareWidth, squareHeight);
        drawSnake(userSnake, squareWidth, squareHeight, true);
        competitors.forEach(snake -> drawSnake(snake, squareWidth, squareHeight, false));

    }

    /**
     * Draws the background.
     *
     * @param squareWidth of the square on the field
     * @param squareHeight of the square on the field
     * @param rows of the field
     * @param columns of the field
     */
    public void drawPlayground(double squareWidth, double squareHeight, int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                drawPoint(graphicsContext, column * squareWidth, row * squareHeight,
                        squareWidth, squareHeight, (row + column) % 2 == 0 ? evenColor : oddColor);
            }
        }
    }

    /**
     * Draws the food on the field.
     *
     * @param food point where it's located
     * @param squareWidth of the square on the field
     * @param squareHeight of the square on the field
     */
    public void drawFood(Point food, double squareWidth, double squareHeight) {
        drawImage(graphicsContext, food.getXcoord() * squareWidth, food.getYcoord() * squareHeight,
                squareWidth, squareHeight, foodImage);
    }

    /**
     * Draws the player's game score.
     *
     * @param score game score of the player
     */
    public void drawScore(int score) {
        drawString(graphicsContext, "Score: " + score, 10, 35, scoreColor, font);
    }

    public void drawGoal(int goal) {
        drawString(graphicsContext, "Goal: " + goal, 10, 75, goalColor, font);
    }

    /**
     * Draws the obstacles on the field.
     *
     * @param walls list of points of obstacles
     * @param squareWidth of the square on the field
     * @param squareHeight of the square on the field
     */
    public void drawWalls(List<Point> walls, double squareWidth, double squareHeight) {
        walls.forEach(wall -> drawRoundPoint(graphicsContext, wall.getXcoord() * squareWidth,
                wall.getYcoord() * squareHeight, squareWidth, squareHeight, wallColor));
    }

    /**
     * Draws the snake.
     *
     * @param snake to draw
     * @param squareWidth of the square on the field
     * @param squareHeight of the square on the field
     * @param user true if the snake is user's
     */
    public void drawSnake(Snake snake, double squareWidth, double squareHeight, boolean user) {
        drawRoundPoint(graphicsContext, snake.getX() * squareWidth, snake.getY() * squareHeight,
                squareWidth, squareHeight,
                user ? userHeadColor : botHeadColor);
        snake.getSnakeBody()
                .forEach(part -> drawRoundPoint(graphicsContext, part.getXcoord() * squareWidth,
                part.getYcoord() * squareHeight, squareWidth, squareHeight,
                part.full() ? fullBodyColor : user ? userBodyColor : botBodyColor));
    }

    /**
     * Returns width of a point on the field.
     *
     * @return width of a point on the field
     */
    public double getSquareWidth() {
        return canvas.getWidth() / columns;
    }

    /**
     * Returns height of a point on the field.
     *
     * @return height of a point on the field
     */
    public double getSquareHeight() {
        return canvas.getHeight() / rows;
    }

    /**
     * Stops and resumes the game cycle.
     */
    @FXML
    public void pauseGame() {
        if (!onPause) {
            timeline.stop();
        } else {
            timeline.play();
        }
        onPause = !onPause;
    }

    /**
     * Returns to the start menu.
     *
     * @throws IOException if smth gone wrong
     */
    @FXML
    public void stopGame() throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication
                .class.getResource("StartWindow.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.getIcons().add(new Image(images + "icon.png"));
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
    }
}