package io.github.mizinchik.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeView {

    public static void drawPoint(GraphicsContext graphicsContext, double x, double y,
                                 double squareWidth, double squareHeight, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, squareWidth, squareHeight);
    }

    public static void drawRoundPoint(GraphicsContext graphicsContext, double x, double y,
                                 double squareWidth, double squareHeight, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRoundRect(x, y, squareWidth - 1, squareHeight - 1,
                35, 35);
    }

    public static void drawString(GraphicsContext graphicsContext, String text,
                                  double x, double y, Color color, Font font){
        graphicsContext.setFill(color);
        graphicsContext.setFont(font);
        graphicsContext.fillText(text, x, y);
    }

    public static void drawImage(GraphicsContext graphicsContext, double x, double y,
                                 double squareWidth, double squareHeight, Image image) {
        graphicsContext.drawImage(image, x,
                y, squareWidth, squareHeight);
    }
}
