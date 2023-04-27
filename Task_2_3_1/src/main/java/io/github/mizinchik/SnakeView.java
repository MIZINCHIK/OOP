package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SnakeView {
    private static final String images = "io/github/mizinchik/img/";
    private static final String oddColor = "A2D149";
    private static final String evenColor = "AAD751";
    private static final String font = "Comic Sans MS";
    private static final Image foodImage = new Image(images + "food.png");

    public static void drawFood(Point point, GraphicsContext graphicsContext, int squareSize) {
        graphicsContext.drawImage(foodImage, point.getX() * squareSize,
                point.getY() * squareSize, squareSize, squareSize);
    }

    public static void drawPlayground(GraphicsContext graphicsContext, int squareSize, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(Color.web(evenColor));
                } else {
                    graphicsContext.setFill(Color.web(oddColor));
                }
                graphicsContext.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }
    }

    public static void drawScore(GraphicsContext graphicsContext, int score) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font(font, 35));
        graphicsContext.fillText("Score: " + score, 10, 35);
    }
}
