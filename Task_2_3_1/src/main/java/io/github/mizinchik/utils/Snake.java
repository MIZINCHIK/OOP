package io.github.mizinchik.utils;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Snake extends Point {
    private static final Color userHead = new Color(0.146, 0.228, 0.282, 1);
    private static final Color botHead = new Color(0.42, 0.22, 0.1337, 1);
    private final List<Point> snakeBody = new ArrayList<>();


    public Snake(int xCoord, int yCoord, int length, boolean isUser) {
        super(xCoord, yCoord, isUser ? userHead : botHead);
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

    public void move(boolean foodEaten) {
        snakeBody.add(0, new Point(getX(), getY(), foodEaten ? full : empty));
        snakeBody.remove(snakeBody.size() - 1);
    }
}
