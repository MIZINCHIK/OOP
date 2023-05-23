package io.github.mizinchik.model;

import io.github.mizinchik.utils.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Snake. Eats food, grows, dies.
 *
 * @author MIZINCHIK
 */
public class Snake {
    private final List<Point> snakeBody;
    private final Point head;
    private int score = 0;
    private boolean foodEaten = false;

    /**
     * Creates a snake.
     *
     * @param xcoord on the virtual field
     * @param ycoord on the virtual field
     * @param length of the body, head excluded
     */
    public Snake(int xcoord, int ycoord, int length) {
        head = new Point(xcoord, ycoord);
        snakeBody = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            snakeBody.add(new Point(xcoord - i, ycoord));
        }
    }

    /**
     * Returns the body of the snake.
     *
     * @return list of points where the body is
     */
    public List<Point> getSnakeBody() {
        return snakeBody;
    }

    /**
     * Checks if the snake collides with itslef.
     *
     * @return true if the snake collides with itself
     */
    public boolean collideItself() {
        for (Point snakePart : snakeBody) {
            if (head.getXcoord() == snakePart.getXcoord()
                    && head.getYcoord() == snakePart.getYcoord()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a point to the body.
     */
    public void enlarge() {
        snakeBody.add(new Point(-1, -1));
    }

    /**
     * Moves the body in the direction of the head.
     */
    public void move() {
        snakeBody.add(0, new Point(head.getXcoord(), head.getYcoord(), foodEaten));
        foodEaten = false;
        snakeBody.remove(snakeBody.size() - 1);
    }

    /**
     * Returns score.
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Increases score.
     */
    public void increaseScore() {
        score++;
    }

    /**
     * Checks if the snake collides with the point given.
     *
     * @param point to check for collision
     * @return true if the snake collides with the point
     */
    public boolean collides(Point point) {
        return head.equals(point);
    }

    /**
     * Checks if the snake collides with the snake given.
     *
     * @param snake to check for collision
     * @return true if the snake collides with the snake
     */
    public boolean collides(Snake snake) {
        return collides(snake.head) || snake.getSnakeBody().stream().anyMatch(this::collides);
    }

    /**
     * Returns x coordinate of the head.
     *
     * @return x virtual field coordinate
     */
    public int getX() {
        return head.getXcoord();
    }

    /**
     * Returns y coordinate of the head.
     *
     * @return y virtual field coordinate
     */
    public int getY() {
        return head.getYcoord();
    }

    /**
     * Returns the head.
     *
     * @return head
     */
    public Point getHead() {
        return head;
    }

    /**
     * Tells if the snake has eaten the food.
     *
     * @param value whether the snake eat the food.
     */
    public void setFoodEaten(boolean value) {
        foodEaten = value;
    }
}
