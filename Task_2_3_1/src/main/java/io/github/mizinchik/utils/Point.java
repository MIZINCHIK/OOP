package io.github.mizinchik.utils;

/**
 * Just a point on the field.
 *
 * @author MIZINCHIK
 */
public class Point {
    private boolean full;
    private int xcoord;
    private int ycoord;

    /**
     * Creates a point.
     *
     * @param xcoord virtual coordinate on the field
     * @param ycoord virtual coordinate on the field
     * @param full if it's full (i.e. contains food)
     */
    public Point(int xcoord, int ycoord, boolean full) {
        this.setXcoord(xcoord);
        this.setYcoord(ycoord);
        this.full = full;
    }

    /**
     * Creates a point.
     *
     * @param xcoord virtual coordinate on the field
     * @param ycoord virtual coordinate on the field
     */
    public Point(int xcoord, int ycoord) {
        this.setXcoord(xcoord);
        this.setYcoord(ycoord);
    }

    /**
     * Returns x coordinate.
     *
     * @return x coordinate
     */
    public int getXcoord() {
        return xcoord;
    }

    /**
     * Sets x coordinate.
     *
     * @param xcoord x coordinate
     */
    public void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    /**
     * Returns y coordinate.
     *
     * @return y coordinate
     */
    public int getYcoord() {
        return ycoord;
    }

    /**
     * Sets y coordinate.
     *
     * @param ycoord y coordinate
     */
    public void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    /**
     * Moves the point right on the field.
     */
    public void moveRight() {
        setXcoord(getXcoord() + 1);
    }

    /**
     * Moves the point left on the field.
     */
    public void moveLeft() {
        setXcoord(getXcoord() - 1);
    }

    /**
     * Moves the point up on the field.
     */
    public void moveUp() {
        setYcoord(getYcoord() - 1);
    }

    /**
     * Moves the point down on the field.
     */
    public void moveDown() {
        setYcoord(getYcoord() + 1);
    }

    /**
     * Checks if the point are of the same coordinates.
     *
     * @param point another point
     * @return true if are equal
     */
    public boolean equals(Point point) {
        return point.getYcoord() == getYcoord()
                && point.getXcoord() == getXcoord();
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
