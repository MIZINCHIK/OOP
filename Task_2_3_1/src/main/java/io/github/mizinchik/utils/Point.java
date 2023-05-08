package io.github.mizinchik.utils;

/**
 * Just a point on the field.
 *
 * @author MIZINCHIK
 */
public class Point {
    private boolean full;
    private int x;
    private int y;

    /**
     * Creates a point.
     *
     * @param x virtual coordinate on the field
     * @param y virtual coordinate on the field
     * @param full if it's full (i.e. contains food)
     */
    public Point(int x, int y, boolean full) {
        this.setX(x);
        this.setY(y);
        this.full = full;
    }

    /**
     * Creates a point.
     *
     * @param x virtual coordinate on the field
     * @param y virtual coordinate on the field
     */
    public Point(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Returns x coordinate.
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x coordinate.
     *
     * @param x x coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns y coordinate
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y coordinate.
     *
     * @param y y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves the point right on the field.
     */
    public void moveRight() {
        setX(getX() + 1);
    }

    /**
     * Moves the point left on the field.
     */
    public void moveLeft() {
        setX(getX() - 1);
    }

    /**
     * Moves the point up on the field.
     */
    public void moveUp() {
        setY(getY() - 1);
    }

    /**
     * Moves the point down on the field.
     */
    public void moveDown() {
        setY(getY() + 1);
    }

    /**
     * Checks if the point are of the same coordinates.
     *
     * @param point another point
     * @return true if are equal
     */
    public boolean equals(Point point) {
        return point.getY() == getY() && point.getX() == getX();
    }

    /**
     * Checks for fullness.
     *
     * @return true if full
     */
    public boolean full() {
        return full;
    }
}
