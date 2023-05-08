package io.github.mizinchik.model;

import io.github.mizinchik.utils.Direction;
import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Settings;
import java.util.ArrayList;
import java.util.List;

/**
 * Model of the snake game. Responsible for making a single move.
 *
 * @author MIZINCHIK
 */
public class SnakeModel {
    private final int goal;
    private final int rows;
    private final int columns;
    private final List<Point> walls;
    private final List<Snake> competitors;
    private final int time;
    private final Snake userSnake;
    private final Point food;
    private boolean gameOver = false;
    private Direction lastDirection = Direction.RIGHT;

    /**
     * Returns time between frames.
     *
     * @return time between frames
     */
    public int getTime() {
        return time;
    }

    /**
     * Returns food.
     *
     * @return food (respective point)
     */
    public Point getFood() {
        return food;
    }

    /**
     * Return the snake of the player.
     *
     * @return user's snake
     */
    public Snake getUserSnake() {
        return userSnake;
    }

    /**
     * Returns walls, where no snake can move.
     *
     * @return list of points where walls are
     */
    public List<Point> getWalls() {
        return walls;
    }

    /**
     * Returns competitors.
     *
     * @return list of AI snakes
     */
    public List<Snake> getCompetitors() {
        return competitors;
    }

    /**
     * Returns rows.
     *
     * @return number of rows on the field
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns columns.
     *
     * @return number of columns on the field
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Returns true if game is over.
     *
     * @return true if game is over
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Creates an instance of the model (the game itself).
     *
     * @param settings level config
     */
    public SnakeModel(Settings settings) {
        rows = settings.rows();
        columns = settings.columns();
        time = settings.time();
        walls = new ArrayList<>();
        goal = settings.goal();
        for (int[] wall : settings.walls()) {
            walls.add(new Point(wall[0], wall[1]));
        }
        competitors = new ArrayList<>();
        for (int i = 0; i < settings.competitors(); i++) {
            competitors.add(new Snake((int) (Math.random() * columns),
                    (int) (Math.random() * rows), 3));
        }
        userSnake = new Snake(5, rows / 2, 3);
        food = new Point(0, 0);
    }

    /**
     * Makes a single move in the game.
     *
     * @param direction where user wants to move the snake
     */
    public void makeMove(Direction direction) {
        lastDirection = correctDirection(direction);
        moveSnake(userSnake, lastDirection);
        moveSnakes();
        updateSnakes();
        updateGameOver();
        eatFood(userSnake);
        for (Snake competitor : competitors) {
            eatFood(competitor);
        }
    }

    /**
     * Moves AI snakes closer to the food if possible.
     */
    private void moveSnakes() {
        for (Snake competitor : competitors) {
            if (food.getxCoordinate() > competitor.getX()) {
                if (checkMoveDirection(competitor, Direction.RIGHT)) {
                    continue;
                }
            } else if (food.getxCoordinate() < competitor.getX()) {
                if (checkMoveDirection(competitor, Direction.LEFT)) {
                    continue;
                }
            }
            if (food.getyCoordinate() < competitor.getY()) {
                if (checkMoveDirection(competitor, Direction.UP)) {
                    continue;
                }
            } else if (food.getyCoordinate() > competitor.getY()) {
                if (checkMoveDirection(competitor, Direction.DOWN)) {
                    continue;
                }
            }
            moveWhereFree(competitor);
        }
    }

    /**
     * Moves snake in some free spot if possible.
     *
     * @param snake to move
     */
    public void moveWhereFree(Snake snake) {
        boolean done = checkMoveDirection(snake, Direction.RIGHT)
                || checkMoveDirection(snake, Direction.LEFT)
                || checkMoveDirection(snake, Direction.UP)
                || checkMoveDirection(snake, Direction.DOWN);
        if (!done) {
            moveSnake(snake, Direction.RIGHT);
        }
    }

    /**
     * Checks if there are no obstacles in the direction.
     *
     * @param snake to move
     * @param direction where to move
     * @return true if it can be moved without collision
     */
    public boolean checkMoveDirection(Snake snake, Direction direction) {
        Snake newSnake = new Snake(snake.getX(), snake.getY(), 1);
        moveSnake(newSnake, direction);
        if (!outOfBounds(newSnake) && !newSnake.collides(userSnake)) {
            moveSnake(snake, direction);
            return true;
        }
        return false;
    }

    /**
     * Puts food in a random spot.
     */
    private void generateFood() {
        do {
            food.setxCoordinate((int) (Math.random() * columns));
            food.setyCoordinate((int) (Math.random() * rows));
        } while (userSnake.collides(food));
    }

    /**
     * Checks if snake can eat food and does the respective work.
     *
     * @param snake that consumes food
     */
    private void eatFood(Snake snake) {
        if (snake.getX() == food.getxCoordinate() && snake.getY() == food.getyCoordinate()) {
            snake.enlarge();
            generateFood();
            snake.increaseScore();
            snake.setFoodEaten(true);
        }
    }

    /**
     * Checks if the game is over for the player.
     */
    public void updateGameOver() {
        if (outOfBounds(userSnake) || competitors.stream().
                anyMatch(competitor -> competitor.getScore() >= goal)) {
            gameOver = true;
        }
    }

    /**
     * Check what snakes have died.
     */
    public void updateSnakes() {
        competitors.removeIf(competitor -> outOfBounds(competitor)
                || competitor.collides(userSnake));
    }

    /**
     * Checks if snake has collided with anything.
     *
     * @param snake to check
     * @return true if snake is dead
     */
    public boolean outOfBounds(Snake snake) {
        return snake.getX() < 0 || snake.getY() < 0 || snake.getX() >= rows
                || snake.getY() >= columns || snake.collideItself()
                || competitors.stream().
                anyMatch(competitor -> snake.collides(competitor) && !snake.equals(competitor))
                || walls.stream().anyMatch(snake::collides);
    }

    /**
     * Moves snake in a direction.
     *
     * @param snake to move
     * @param direction where to move
     */
    public void moveSnake(Snake snake, Direction direction) {
        snake.move();
        Point head = snake.getHead();
        switch (direction) {
            case LEFT -> head.moveLeft();
            case UP -> head.moveUp();
            case DOWN -> head.moveDown();
            default -> head.moveRight();
        }
    }

    /**
     * Returns an appropriate direction for user's snake.
     *
     * @param direction where user wants to move the snake
     * @return direction that the snake may move in
     */
    public Direction correctDirection(Direction direction) {
        return oppositeDirection(direction) ? lastDirection : direction;
    }

    /**
     * Checks if the direction given is opposite to
     * the current direction of the user.
     *
     * @param direction to check
     * @return true if the direction given is opposite to current
     */
    public boolean oppositeDirection(Direction direction) {
        return switch (lastDirection) {
            case DOWN -> direction == Direction.UP;
            case LEFT -> direction == Direction.RIGHT;
            case UP -> direction == Direction.DOWN;
            case RIGHT -> direction == Direction.LEFT;
        };
    }
}
