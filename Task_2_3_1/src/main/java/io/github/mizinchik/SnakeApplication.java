package io.github.mizinchik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeApplication extends Application {
    private static final String images = "io/github/mizinchik/img/";
    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        stage.getIcons().add(new Image(images + "icon.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication.class.getResource("StartWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
    }

    private class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text(e.getMessage()));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}