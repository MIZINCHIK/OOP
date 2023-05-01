package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Snake;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SnakeModel {
    private final int rows;
    private final int columns;
    private final Snake userSnake;
    private final SnakeController controller;
    private final Point food;
    private int score;
    private boolean gameOver = false;

    public SnakeModel(int rows, int columns, SnakeController controller) {
        this.rows = rows;
        this.columns = columns;
        this.userSnake = new Snake(5, rows / 2, 3);
        this.controller = controller;
        this.food = new Point(0, 0);
        this.score = 0;
    }

    public void run() {
        generateFood();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(130), e -> cycle()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void cycle() {
        if (gameOver) {
            controller.gameOver();
            return;
        }
        controller.prepareField(food, rows, userSnake, userSnake.getSnakeBody(), score);
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

    private void generateFood() {
        while (true) {
            food.setX((int) (Math.random() * rows));
            food.setY((int) (Math.random() * columns));
            if (userSnake.getX() == food.getX() && userSnake.getY() == food.getY()) {
                continue;
            }
            boolean eligible = true;
            for (Point snake : userSnake.getSnakeBody()) {
                if (snake.getX() == food.getX() || snake.getY() == food.getY()) {
                    eligible = false;
                    break;
                }
            }
            if (eligible) {
                break;
            }
        }
    }

    private void eatFood() {
        if (userSnake.getX() == food.getX() && userSnake.getY() == food.getY()) {
            userSnake.enlarge();
            generateFood();
            score += 5;
        }
    }

    public void gameOver() {
        int squareSize = controller.getSquareSize(rows);
        double width = controller.getWidth();
        double height = controller.getHeight();
        if (userSnake.getX() < 0 || userSnake.getY() < 0 || userSnake.getX() * squareSize >= width
                || userSnake.getY() * squareSize >= height) {
            gameOver = true;
        }

        if (userSnake.collideItself()) {
            gameOver = true;
        }
    }
}
