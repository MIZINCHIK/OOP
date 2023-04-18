package io.github.mizinchik.utils;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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

    public void draw(GraphicsContext graphicsContext, int size) {
        graphicsContext.setFill(Color.web("4674E9"));
        graphicsContext.fillRoundRect(getX() * size, getY() * size,
                size - 1, size - 1, 35, 35);

        for (Point snakeBodyPart : snakeBody) {
            graphicsContext.fillRoundRect(snakeBodyPart.getX() * size, snakeBodyPart.getY() * size,
                    size - 1, size - 1, 20, 20);
        }
    }

    public void move() {
        for (int i = snakeBody.size() - 1; i >= 1; i--) {
            snakeBody.get(i).setX(snakeBody.get(i - 1).getX());
            snakeBody.get(i).setY(snakeBody.get(i - 1).getY());
        }
        snakeBody.get(0).setX(getX());
        snakeBody.get(0).setY(getY());
    }
}
