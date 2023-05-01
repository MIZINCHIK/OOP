package io.github.mizinchik.utils;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Point {
    private final List<Point> snakeBody = new ArrayList<>();

    public Snake(int xCoord, int yCoord, int length) {
        super(xCoord, yCoord);
        for (int i = 0; i < length; i++) {
            snakeBody.add(new Point(xCoord, yCoord));
        }
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }

    public int getLength() {
        return snakeBody.size();
    }

    public boolean collideItself() {
        for (Point snakePart : snakeBody) {
            if (getX() == snakePart.getX()
                    && getY() == snakePart.getY()) {
                return true;
            }
        }
        return false;
    }

    public void enlarge() {
        snakeBody.add(new Point(-1, -1));
    }

    public void move() {
        snakeBody.add(0, new Point(getX(), getY()));
        snakeBody.remove(snakeBody.size() - 1);
    }
}
