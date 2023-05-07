package io.github.mizinchik;

import io.github.mizinchik.utils.Direction;
import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Settings;
import java.util.ArrayList;
import java.util.List;

public class SnakeModel {
    private final int rows;
    private final int columns;
    private final List<Point> walls;
    private final List<Snake> competitors;
    private final int speed;
    private final Snake userSnake;
    private final SnakeController controller;
    private final Point food;
    private boolean gameOver = false;
    private boolean foodEaten = false;

    public SnakeModel(SnakeController controller, Settings settings) {
        rows = settings.rows();
        columns = settings.columns();
        speed = settings.speed();
        walls = new ArrayList<>();
        for (int[] wall : settings.walls()) {
            walls.add(new Point(wall[0], wall[1]));
        }
        competitors = new ArrayList<>();
        for (int i = 0; i < settings.competitors(); i++) {
            competitors.add(new Snake((int) (Math.random() * columns), (int) (Math.random() * rows), 3));
        }
        userSnake = new Snake(5, rows / 2, 3);
        this.controller = controller;
        food = new Point(0, 0);
    }

    public void makeMove(Direction direction) {
        userSnake.move(foodEaten);
        foodEaten = false;
        moveSnakes();
        moveSnake(userSnake, direction, false);
        updateGameOver();
        eatFood(userSnake);
        for(Snake competitor : competitors){
            eatFood(competitor);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public Point getFood() {
        return food;
    }

    public Snake getUserSnake() {
        return userSnake;
    }

    public List<Point> getWalls() {
        return walls;
    }

    public List<Snake> getCompetitors() {
        return competitors;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private void moveSnakes() {
        for (Snake competitor : competitors) {
            if (food.getX() > competitor.getX()) {
                moveSnake(competitor, Direction.RIGHT, false);
            } else if (food.getX() < competitor.getX()) {
                moveSnake(competitor, Direction.LEFT, false);
            } else if (food.getY() < competitor.getY()) {
                moveSnake(competitor, Direction.UP, false);
            } else {
                moveSnake(competitor, Direction.DOWN, false);
            }
        }
    }

    private void generateFood() {
        do {
            food.setX((int) (Math.random() * columns));
            food.setY((int) (Math.random() * rows));
        } while (userSnake.collides(food));
    }

    private void eatFood(Snake snake) {
        if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
            snake.enlarge();
            generateFood();
            snake.increaseScore();
            foodEaten = true;
        }
    }

    public void updateGameOver() {
        double squareWidth = controller.getSquareWidth(columns);
        double squareHeight = controller.getSquareHeight(rows);
        double width = controller.getWidth();
        double height = controller.getHeight();
        if (userSnake.getX() < 0 || userSnake.getY() < 0 || userSnake.getX() * squareWidth >= width
                || userSnake.getY() * squareHeight >= height ||
                userSnake.collideItself() ||
                competitors.stream().anyMatch(userSnake::collides) ||
        walls.stream().anyMatch(userSnake::collides)) {
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void moveSnake(Snake snake, Direction direction, boolean foodEaten) {
        snake.move(foodEaten);
        Point head = snake.getHead();
        switch (direction) {
            case RIGHT -> head.moveRight();
            case LEFT -> head.moveLeft();
            case UP -> head.moveUp();
            case DOWN -> head.moveDown();
        }
    }
}
