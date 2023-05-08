package io.github.mizinchik.model;

import io.github.mizinchik.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Point> snakeBody;
    private final Point head;
    private int score = 0;
    private boolean foodEaten = false;

    public Snake(int xCoord, int yCoord, int length) {
        head = new Point(xCoord, yCoord);
        snakeBody = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            snakeBody.add(new Point(xCoord - i, yCoord));
        }
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }
    
    public boolean collideItself() {
        for (Point snakePart : snakeBody) {
            if (head.getX() == snakePart.getX()
                    && head.getY() == snakePart.getY()) {
                return true;
            }
        }
        return false;
    }

    public void enlarge() {
        snakeBody.add(new Point(-1, -1));
    }

    public void move() {
        snakeBody.add(0, new Point(head.getX(), head.getY(), foodEaten));
        foodEaten = false;
        snakeBody.remove(snakeBody.size() - 1);
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public boolean collides(Point point) {
        return head.equals(point);
    }

    public boolean collides(Snake snake) {
        return collides(snake.head) || snake.getSnakeBody().stream().anyMatch(this::collides);
    }

    public int getX() {
        return head.getX();
    }

    public int getY() {
        return head.getY();
    }

    public Point getHead() {
        return head;
    }

    public void setFoodEaten(boolean value) {
        foodEaten = value;
    }
}
