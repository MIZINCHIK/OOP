package io.github.mizinchik.utils;

public class Point {
    private boolean full;
    private int x;
    private int y;

    public Point(int x, int y, boolean full) {
        this.setX(x);
        this.setY(y);
        this.full = full;
    }

    public Point(int x, int y) {
        this.setX(x);
        this.setY(y);
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

    public boolean full() {
        return full;
    }
}
