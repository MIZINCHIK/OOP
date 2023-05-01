package io.github.mizinchik;

import io.github.mizinchik.utils.Point;
import io.github.mizinchik.utils.Settings;
import io.github.mizinchik.utils.Snake;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.github.mizinchik.utils.JsonReader.readLevel;

public class SnakeModel {
    private final int rows;
    private final int columns;
    private final List<Point> walls;
    private final List<Snake> competitors;
    private final int goal;
    private final int speed;
    private final Snake userSnake;
    private final SnakeController controller;
    private final Point food;
    private boolean gameOver = false;
    private boolean foodEaten = false;

    public SnakeModel(SnakeController controller, int levelId) {
        Settings settings = buildFromFile(levelId);
        this.rows = settings.rows();
        this.columns = settings.columns();
        goal = settings.goal();
        speed = settings.speed();
        walls = new ArrayList<>();
        for (int[] wall : settings.walls()) {
            walls.add(new Point(wall[0], wall[1]));
        }
        competitors = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < settings.competitors(); i++) {
            competitors.add(new Snake(random.nextInt(columns), random.nextInt(rows), 3, false));
        }
        this.userSnake = new Snake(5, rows / 2, 3, true);
        this.controller = controller;
        this.food = new Point(0, 0);
    }

    private Settings buildFromFile(int levelId) {
        Settings settings;
        try {
            settings = readLevel(levelId);
        } catch (FileNotFoundException e) {
            settings = new Settings(15, 15, 1, 10, 135, new int[0][0]);
        }
        return settings;
    }

    public void run() {
        generateFood();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed), e -> cycle()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void cycle() {
        if (gameOver) {
            controller.gameOver();
            return;
        }
        controller.prepareField(food, userSnake, userSnake.getSnakeBody(),
                userSnake.getScore(), walls, competitors, rows, columns);
        userSnake.move(foodEaten);
        foodEaten = false;
        moveSnakes();
        competitors.removeIf(this::checkCollision);
        userSnake.moveDirectly(controller.getCurrentDirection());
        gameOver();
        eatFood(userSnake);
        for(Snake competitor : competitors){
            eatFood(competitor);
        }

    }

    private void moveSnakes() {
        for (Snake competitor : competitors) {
            competitor.moveDirectly(SnakeController.Direction.randomDirection());
        }
    }

    private boolean checkCollision(Point point) {
        return (userSnake.collides(point) || competitors.stream().
                filter(snake -> snake.collides(point)).findFirst().orElse(null)
                != null || walls.stream().filter(point::equals).
                findFirst().orElse(null) != null);
    }

    private void generateFood() {
        while (true) {
            food.setX((int) (Math.random() * columns));
            food.setY((int) (Math.random() * rows));
            if (!userSnake.collides(food)) {
                break;
            }
        }
    }

    private void eatFood(Snake snake) {
        if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
            snake.enlarge();
            generateFood();
            snake.increaseScore();
            foodEaten = true;
        }
    }

    public void gameOver() {
        double squareWidth = controller.getSquareWidth(columns);
        double squareHeight = controller.getSquareHeight(rows);
        double width = controller.getWidth();
        double height = controller.getHeight();
        if (userSnake.getX() < 0 || userSnake.getY() < 0 || userSnake.getX() * squareWidth >= width
                || userSnake.getY() * squareHeight >= height ||
                userSnake.collideItself() ||
                competitors.stream().filter(userSnake::collides).
                findFirst().orElse(null) != null ||
        walls.stream().filter(userSnake::equals).findFirst().orElse(null) != null) {
            gameOver = true;
        }

    }
}
