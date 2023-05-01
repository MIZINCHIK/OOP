package io.github.mizinchik;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StartController extends Controller {
    @FXML
    private void goGame(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("SnakeView.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            SnakeController snakeController = fxmlLoader.getController();
            snakeController.takeControl(root, stage, snakeController.getCanvas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
