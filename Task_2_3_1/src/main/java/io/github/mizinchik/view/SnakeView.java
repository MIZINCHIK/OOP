package io.github.mizinchik.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * View class for my snake game.
 *
 * @author MIZINCHIK
 */
public class SnakeView {

    /**
     * Draws a rectangular point.
     *
     * @param graphicsContext where to draw
     * @param x coordinate
     * @param y coordinate
     * @param squareWidth of a rectangle on the field
     * @param squareHeight of a rectangle on the field
     * @param color of the point
     */
    public static void drawPoint(GraphicsContext graphicsContext, double x, double y,
                                 double squareWidth, double squareHeight, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, squareWidth, squareHeight);
    }

    /**
     * Draws a rectangular point with round edges.
     *
     * @param graphicsContext where to draw
     * @param x coordinate
     * @param y coordinate
     * @param squareWidth of a rectangle on the field
     * @param squareHeight of a rectangle on the field
     * @param color of the point
     */
    public static void drawRoundPoint(GraphicsContext graphicsContext, double x, double y,
                                 double squareWidth, double squareHeight, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRoundRect(x, y, squareWidth - 1, squareHeight - 1,
                35, 35);
    }

    /**
     * Draws the text.
     *
     * @param graphicsContext where to draw
     * @param text to write
     * @param x coordinate
     * @param y coordinate
     * @param color of the text
     * @param font of the text
     */
    public static void drawString(GraphicsContext graphicsContext, String text,
                                  double x, double y, Color color, Font font) {
        graphicsContext.setFill(color);
        graphicsContext.setFont(font);
        graphicsContext.fillText(text, x, y);
    }

    /**
     * Draws an image in a rectangular point on the field.
     *
     * @param graphicsContext where to draw
     * @param x coordinate
     * @param y coordinate
     * @param squareWidth of a rectangle on the field
     * @param squareHeight of a rectangle on the field
     * @param image to draw
     */
    public static void drawImage(GraphicsContext graphicsContext, double x, double y,
                                 double squareWidth, double squareHeight, Image image) {
        graphicsContext.drawImage(image, x,
                y, squareWidth, squareHeight);
    }
}
