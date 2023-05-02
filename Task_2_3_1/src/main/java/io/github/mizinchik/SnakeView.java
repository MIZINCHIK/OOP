package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Snake;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class SnakeView {
    private static final String images = "io/github/mizinchik/img/";
    private static final Color oddColor = Color.web("A2D149");
    private static final Color evenColor = Color.web("AAD751");
    private static final Color wallColor = Color.web("#45a6fc");
    private static final String font = "Comic Sans MS";
    private static final Image foodImage = new Image(images + "food.png");

    public static void drawFood(GraphicsContext graphicsContext, Point point, double squareWidth, double squareHeight) {
        graphicsContext.drawImage(foodImage, point.getX() * squareWidth,
                point.getY() * squareHeight, squareWidth, squareHeight);
    }

    public static void drawPlayground(GraphicsContext graphicsContext, double squareWidth, double squareHeight, int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    graphicsContext.setFill(evenColor);
                } else {
                    graphicsContext.setFill(oddColor);
                }
                graphicsContext.fillRect(j * squareWidth, i * squareHeight, squareWidth, squareHeight);
            }
        }
    }

    public static void drawScore(GraphicsContext graphicsContext, int score) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font(font, 35));
        graphicsContext.fillText("Score: " + score, 10, 35);
    }

    public static void drawGameOver(GraphicsContext graphicsContext, double width, double height) {
        graphicsContext.setFill(Color.RED);
        graphicsContext.setFont(Font.font(font, 70));
        graphicsContext.fillText("Game Over", width / 3.5,
                height / 2);
    }

    public static void drawSnakeHead(GraphicsContext graphicsContext, Point head, double squareWidth, double squareHeight) {
        graphicsContext.setFill(head.getColor());
        graphicsContext.fillRoundRect(head.getX() * squareWidth, head.getY() * squareHeight,
                squareWidth - 1, squareHeight - 1, 35, 35);
    }

    public static void drawSnakeBody(GraphicsContext graphicsContext, List<Point> body, double squareWidth, double squareHeight) {
        for (Point snakeBodyPart : body) {
            graphicsContext.setFill(snakeBodyPart.getColor());
            graphicsContext.fillRoundRect(snakeBodyPart.getX() * squareWidth, snakeBodyPart.getY() * squareHeight,
                    squareWidth - 1, squareHeight - 1, 20, 20);
        }
    }

    public static void drawWalls(GraphicsContext graphicsContext, List<Point> walls, double squareWidth, double squareHeight) {
        graphicsContext.setFill(wallColor);
        for (Point wall : walls) {
            graphicsContext.fillRoundRect(wall.getX() * squareWidth, wall.getY() * squareHeight,
                    squareWidth - 1, squareHeight - 1, 20, 20);
        }
    }

    public static void drawSnake(GraphicsContext graphicsContext, Snake snake, double squareWidth, double squareHeight) {
        drawSnakeHead(graphicsContext, snake, squareWidth, squareHeight);
        drawSnakeBody(graphicsContext, snake.getSnakeBody(), squareWidth, squareHeight);
    }
}
