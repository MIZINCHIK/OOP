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
    private final Point food;
    private boolean gameOver = false;
    private Direction lastDirection = Direction.RIGHT;
    double width;
    double height;
    double squareWidth;
    double squareHeight;

    public SnakeModel(Settings settings) {
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
        food = new Point(0, 0);
    }

    public void makeMove(Direction direction, double width, double height, double squareWidth, double squareHeight) {
        this.width = width;
        this.height = height;
        this.squareWidth = squareWidth;
        this.squareHeight = squareHeight;
        lastDirection = correctDirection(direction);
        moveSnakes();
        moveSnake(userSnake, lastDirection);
        updateSnakes();
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
                moveSnake(competitor, Direction.RIGHT);
            } else if (food.getX() < competitor.getX()) {
                moveSnake(competitor, Direction.LEFT);
            } else if (food.getY() < competitor.getY()) {
                moveSnake(competitor, Direction.UP);
            } else {
                moveSnake(competitor, Direction.DOWN);
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
            snake.setFoodEaten(true);
        }
    }

    public void updateGameOver() {
        if (outOfBounds(userSnake)) {
            gameOver = true;
        }
    }

    public void updateSnakes() {
        competitors.removeIf(this::outOfBounds);
    }

    public boolean outOfBounds(Snake snake) {
        return snake.getX() < 0 || snake.getY() < 0 || snake.getX() * squareWidth >= width
                || snake.getY() * squareHeight >= height || snake.collideItself() ||
                competitors.stream().anyMatch(competitor -> snake.collides(competitor) && !snake.equals(competitor)) ||
                walls.stream().anyMatch(snake::collides);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void moveSnake(Snake snake, Direction direction) {
        snake.move();
        Point head = snake.getHead();
        switch (direction) {
            case RIGHT -> head.moveRight();
            case LEFT -> head.moveLeft();
            case UP -> head.moveUp();
            case DOWN -> head.moveDown();
        }
    }

    public Direction correctDirection(Direction direction) {
        return oppositeDirection(direction) ? lastDirection : direction;
    }

    public boolean oppositeDirection(Direction direction) {
        return switch (lastDirection) {
            case DOWN -> direction == Direction.UP;
            case LEFT -> direction == Direction.RIGHT;
            case UP -> direction == Direction.DOWN;
            case RIGHT -> direction == Direction.LEFT;
        };
    }
}
