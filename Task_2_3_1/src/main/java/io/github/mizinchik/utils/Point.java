package io.github.mizinchik.utils;

/**
 * Just a point on the field.
 *
 * @author MIZINCHIK
 */
public class Point {
    private boolean full;
    private int xCoordinate;
    private int yCoordinate;

    /**
     * Creates a point.
     *
     * @param xCoordinate virtual coordinate on the field
     * @param yCoordinate virtual coordinate on the field
     * @param full if it's full (i.e. contains food)
     */
    public Point(int xCoordinate, int yCoordinate, boolean full) {
        this.setxCoordinate(xCoordinate);
        this.setyCoordinate(yCoordinate);
        this.full = full;
    }

    /**
     * Creates a point.
     *
     * @param xCoordinate virtual coordinate on the field
     * @param yCoordinate virtual coordinate on the field
     */
    public Point(int xCoordinate, int yCoordinate) {
        this.setxCoordinate(xCoordinate);
        this.setyCoordinate(yCoordinate);
    }

    /**
     * Returns x coordinate.
     *
     * @return x coordinate
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Sets x coordinate.
     *
     * @param xCoordinate x coordinate
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Returns y coordinate.
     *
     * @return y coordinate
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    /**
     * Sets y coordinate.
     *
     * @param yCoordinate y coordinate
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Moves the point right on the field.
     */
    public void moveRight() {
        setxCoordinate(getxCoordinate() + 1);
    }

    /**
     * Moves the point left on the field.
     */
    public void moveLeft() {
        setxCoordinate(getxCoordinate() - 1);
    }

    /**
     * Moves the point up on the field.
     */
    public void moveUp() {
        setyCoordinate(getyCoordinate() - 1);
    }

    /**
     * Moves the point down on the field.
     */
    public void moveDown() {
        setyCoordinate(getyCoordinate() + 1);
    }

    /**
     * Checks if the point are of the same coordinates.
     *
     * @param point another point
     * @return true if are equal
     */
    public boolean equals(Point point) {
        return point.getyCoordinate() == getyCoordinate() && point.getxCoordinate() == getxCoordinate();
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
