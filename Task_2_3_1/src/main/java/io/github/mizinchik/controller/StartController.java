package io.github.mizinchik.controller;

import io.github.mizinchik.SnakeApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;

public class StartController extends Controller {
    private void goGame(ActionEvent event, int levelId) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("SnakeView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        SnakeController snakeController = fxmlLoader.getController();
        snakeController.takeControl(root, stage, levelId);
    }

    @FXML
    private void level1(ActionEvent event) throws IOException {
        goGame(event, 1);
    }

    @FXML
    private void level2(ActionEvent event) throws IOException {
        goGame(event, 2);
    }

    @FXML
    private void level3(ActionEvent event) throws IOException {
        goGame(event, 3);
    }

    @FXML
    public void closeGame() {
        Platform.exit();
    }
}
