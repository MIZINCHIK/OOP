package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.github.mizinchik.SnakeController.*;

public class SnakeApplication extends Application {
    private static final String images = "io/github/mizinchik/img/";
    private static SnakeController controller;
    private static final int ROWS = 20;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / ROWS;
    private static final String[] FOODS_IMAGE = new String[]{images + "pizza.png"};
    private final List<Point> snakeBody = new ArrayList<>();
    private Point snakeHead;
    private Image foodImage;
    private int foodX;
    private int foodY;
    private boolean gameOver;
    private int score = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("snake-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
        for (int i = 0; i < 3; i++) {
            snakeBody.add(new Point(5, ROWS / 2));
        }
        snakeHead = snakeBody.get(0);
        generateFood();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> run()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void generateFood() {
        start:
        while (true) {
            foodX = (int) (Math.random() * ROWS);
            foodY = (int) (Math.random() * COLUMNS);

            for (Point snake : snakeBody) {
                if (snake.getX() == foodX && snake.getY() == foodY) {
                    continue start;
                }
            }
            foodImage = new Image(FOODS_IMAGE[(int) (Math.random() * FOODS_IMAGE.length)]);
            break;
        }
    }

    private void run() {
        if (gameOver) {
            graphicsContext.setFill(Color.RED);
            graphicsContext.setFont(new Font("Digital-7", 70));
            graphicsContext.fillText("Game Over", (double) WIDTH / 3.5, (double) HEIGHT / 2);
            return;
        }
        drawBackground();
        drawFood();
        drawSnake();
        drawScore();

        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).setX(snakeBody.get(i - 1).getX());
            snakeBody.get(i).setY(snakeBody.get(i - 1).getY());
        }

        switch (controller.getCurrentDirection()) {
            case RIGHT -> snakeHead.moveRight();
            case LEFT -> snakeHead.moveLeft();
            case UP -> snakeHead.moveUp();
            case DOWN -> snakeHead.moveDown();
        }

        gameOver();
        eatFood();
    }

    public void gameOver() {
        if (snakeHead.getX() < 0 || snakeHead.getY() < 0 || snakeHead.getX() * SQUARE_SIZE >= WIDTH
                || snakeHead.getY() * SQUARE_SIZE >= HEIGHT) {
            gameOver = true;
        }

        //destroy itself
        for (int i = 1; i < snakeBody.size(); i++) {
            if (snakeHead.getX() == snakeBody.get(i).getX()
                    && snakeHead.getY() == snakeBody.get(i).getY()) {
                gameOver = true;
                break;
            }
        }
    }

    private void eatFood() {
        if (snakeHead.getX() == foodX && snakeHead.getY() == foodY) {
            snakeBody.add(new Point(-1, -1));
            generateFood();
            score += 5;
        }
    }

    private void drawScore() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(new Font("Digital-7", 35));
        graphicsContext.fillText("Score: " + score, 10, 35);
    }

    private void drawBackground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.web("AAD751"));
                } else {
                    graphicsContext.setFill(Color.web("A2D149"));
                }
                graphicsContext.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    private void drawFood() {
        graphicsContext.drawImage(foodImage, foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }

    private void drawSnake() {
        graphicsContext.setFill(Color.web("4674E9"));
        graphicsContext.fillRoundRect(snakeHead.getX() * SQUARE_SIZE, snakeHead.getY() * SQUARE_SIZE, SQUARE_SIZE - 1, SQUARE_SIZE - 1, 35, 35);

        for (int i = 1; i < snakeBody.size(); i++) {
            graphicsContext.fillRoundRect(snakeBody.get(i).getX() * SQUARE_SIZE, snakeBody.get(i).getY() * SQUARE_SIZE, SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 20, 20);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}