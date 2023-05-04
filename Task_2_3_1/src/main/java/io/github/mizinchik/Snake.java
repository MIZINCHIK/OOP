package io.github.mizinchik;

import io.github.mizinchik.utils.Direction;
import io.github.mizinchik.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private final List<Point> snakeBody;
    private final Point head;
    private int score = 0;

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

    public void move(boolean foodEaten) {
        snakeBody.add(0, new Point(head.getX(), head.getY(), foodEaten));
        snakeBody.remove(snakeBody.size() - 1);
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public boolean collides(Point point) {
        return head.equals(point) || getSnakeBody().stream().anyMatch(point::equals);
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

    public void moveDirectly(Direction direction, boolean foodEaten) {
        move(foodEaten);
        switch (direction) {
            case RIGHT -> head.moveRight();
            case LEFT -> head.moveLeft();
            case UP -> head.moveUp();
            case DOWN -> head.moveDown();
        }
    }
}
