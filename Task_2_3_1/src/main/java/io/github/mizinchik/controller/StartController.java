package io.github.mizinchik.controller;

import io.github.mizinchik.SnakeApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Controls events happening on the start screen.
 *
 * @author MIZINCHIK
 */
public class StartController extends Controller {
    /**
     * Starts the respective level of the game.
     *
     * @param event on the canvas
     * @param levelId to start
     * @throws IOException if smth gone wrong with opening fxml or smth
     */
    private void goGame(ActionEvent event, int levelId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication
                .class.getResource("SnakeView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SnakeController snakeController = fxmlLoader.getController();
        snakeController.takeControl(root, stage, levelId);
    }

    /**
     * Starts the first level.
     *
     * @param event on the canvas
     * @throws IOException if smth gone wrong with opening fxml or smth
     */
    @FXML
    private void level1(ActionEvent event) throws IOException {
        goGame(event, 1);
    }

    /**
     * Starts the second level.
     *
     * @param event on the canvas
     * @throws IOException if smth gone wrong with opening fxml or smth
     */
    @FXML
    private void level2(ActionEvent event) throws IOException {
        goGame(event, 2);
    }

    /**
     * Starts the third level.
     *
     * @param event on the canvas
     * @throws IOException if smth gone wrong with opening fxml or smth
     */
    @FXML
    private void level3(ActionEvent event) throws IOException {
        goGame(event, 3);
    }

    /**
     * Closes the application.
     */
    @FXML
    public void closeGame() {
        Platform.exit();
    }
}
