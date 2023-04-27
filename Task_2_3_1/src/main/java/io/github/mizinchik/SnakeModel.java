package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Snake;

public class SnakeModel {
    private final int rows;
    private final int columns;
    private final Snake userSnake;
    private final Point food;
    private int score;
    private boolean gameOver = false;

    public SnakeModel(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.userSnake = new Snake(5, rows / 2, 3);
        this.food = new Point(0, 0);
        this.score = 0;
    }

    public void run() {
        generateFood();

    }

    private void generateFood() {
        while (true) {
            food.setX((int) (Math.random() * rows));
            food.setY((int) (Math.random() * columns));
            boolean eligible = true;
            for (Point snake : userSnake.getSnakeBody()) {
                if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
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

    public void gameOver(int squareSize, int width, int height) {
        if (userSnake.getX() < 0 || userSnake.getY() < 0 || userSnake.getX() * squareSize >= width
                || userSnake.getY() * squareSize >= height) {
            gameOver = true;
        }

        if (userSnake.collideItself()) {
            gameOver = true;
        }
    }
}
