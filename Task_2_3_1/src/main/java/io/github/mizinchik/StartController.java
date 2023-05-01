package io.github.mizinchik;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController extends Controller {
    private void goGame(ActionEvent event, int levelId) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("SnakeView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            SnakeController snakeController = fxmlLoader.getController();
            snakeController.takeControl(root, stage, levelId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void level1(ActionEvent event) {
        goGame(event, 1);
    }

    @FXML
    private void level2(ActionEvent event) {
        goGame(event, 2);
    }

    @FXML
    private void level3(ActionEvent event) {
        goGame(event, 3);
    }
}
