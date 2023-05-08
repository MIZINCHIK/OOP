package io.github.mizinchik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeApplication extends Application {
    private static final String images = "io/github/mizinchik/img/";

    @Override
    public void start(Stage stage) throws IOException {
        stage.getIcons().add(new Image(images + "icon.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}