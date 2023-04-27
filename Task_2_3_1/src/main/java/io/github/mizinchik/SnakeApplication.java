package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Snake;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SnakeApplication extends Application {
    private static final String images = "io/github/mizinchik/img/";
    private static SnakeController controller;
    private static GraphicsContext graphicsContext;
    private static final int ROWS = 10;
    private static final int COLUMNS = ROWS;
    private static final String oddColor = "A2D149";
    private static final String evenColor = "AAD751";
    private static final String font = "Comic Sans MS";
    private Snake userSnake;
    private final Image foodImage = new Image(images + "food.png");
    private final Point food = new Point(0, 0);
    private boolean gameOver;
    private int score = 0;
    private Canvas canvas;


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("StartWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run()));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
    }

//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("SnakeView.fxml"));
//        Parent root = fxmlLoader.load();
//        controller = fxmlLoader.getController();
//        this.canvas = controller.getCanvas();
//        graphicsContext = canvas.getGraphicsContext2D();
//        Scene scene = new Scene(root);
//        stage.setTitle("Don't Tread on Me");
//        stage.setScene(scene);
//        stage.show();
//        userSnake = new Snake(5, ROWS / 2, 3);
//        generateFood();
//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run()));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();
//    }

    private void generateFood() {
        start:
        while (true) {
            food.setX((int) (Math.random() * ROWS));
            food.setY((int) (Math.random() * COLUMNS));

            for (Point snake : userSnake.getSnakeBody()) {
                if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
                    continue start;
                }
            }
            break;
        }
    }

    private void run() {
        if (gameOver) {
            graphicsContext.setFill(Color.RED);
            graphicsContext.setFont(Font.font(font, 70));
            graphicsContext.fillText("Game Over", canvas.getWidth() / 3.5,
                    canvas.getHeight() / 2);
            return;
        }
        drawBackground();
        drawFood();
        userSnake.draw(graphicsContext, (int) (canvas.getWidth() / ROWS));
        drawScore();
        userSnake.move();
        switch (controller.getCurrentDirection()) {
            case RIGHT -> userSnake.moveRight();
            case LEFT -> userSnake.moveLeft();
            case UP -> userSnake.moveUp();
            case DOWN -> userSnake.moveDown();
        }

        gameOver();
        eatFood();
    }

    public void gameOver() {
        int squareSize = (int) (canvas.getWidth() / ROWS);
        if (userSnake.getX() < 0 || userSnake.getY() < 0 || userSnake.getX() * squareSize >= canvas.getWidth()
                || userSnake.getY() * squareSize >= canvas.getHeight()) {
            gameOver = true;
        }

        if (userSnake.collideItself()) {
            gameOver = true;
        }
    }

    private void eatFood() {
        if (userSnake.getX() == food.getX() && userSnake.getY() == food.getY()) {
            userSnake.enlarge();
            generateFood();
            score += 5;
        }
    }

    private void drawScore() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font(font, 35));
        graphicsContext.fillText("Score: " + score, 10, 35);
    }

    private void drawBackground() {
        int squareSize = (int) (canvas.getWidth() / ROWS);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.web(evenColor));
                } else {
                    graphicsContext.setFill(Color.web(oddColor));
                }
                graphicsContext.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }
    }

    private void drawFood() {
        int squareSize = (int) (canvas.getWidth() / ROWS);
        graphicsContext.drawImage(foodImage, food.getX() * squareSize,
                food.getY() * squareSize, squareSize, squareSize);
    }

    public static void main(String[] args) {
        launch(args);
    }
}