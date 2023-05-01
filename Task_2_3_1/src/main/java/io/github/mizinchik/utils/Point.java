package io.github.mizinchik.utils;

import javafx.scene.paint.Color;

public class Point {
    protected final Color empty = Color.web("4674E9");
    protected final Color full = new Color(0.174, 0.51, 0.199, 1);
    private int x;
    private int y;
    private Color color;

    public Point(int x, int y, Color color) {
        this.setX(x);
        this.setY(y);
        this.color = color;
    }

    public Point(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.color = empty;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Color getColor() {
        return color;
    }

    public void moveRight() {
        setX(getX() + 1);
    }

    public void moveLeft() {
        setX(getX() - 1);
    }

    public void moveUp() {
        setY(getY() - 1);
    }

    public void moveDown() {
        setY(getY() + 1);
    }

    public boolean equals(Point point) {
        return point.getY() == getY() && point.getX() == getX();
    }
}
