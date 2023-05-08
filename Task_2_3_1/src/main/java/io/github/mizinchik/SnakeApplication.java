package io.github.mizinchik;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Runs my snake game.
 *
 * @author MIZINCHIK
 */
public class SnakeApplication extends Application {
    private static final String images = "io/github/mizinchik/img/";
    private Stage stage;

    /**
     * Starts the game.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     * @throws IOException if file corrupted or smth idk
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
        stage.getIcons().add(new Image(images + "icon.png"));
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeApplication
                .class.getResource("StartWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Don't Tread on Me");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles all the uncaught exceptions (prints the message into a small window).
     */
    private class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        /**
         * Creates the window with the output message.
         *
         * @param t the thread
         * @param e the exception
         */
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

    /**
     * Launches the game from cmd.
     *
     * @param args of command line
     */
    public static void main(String[] args) {
        launch(args);
    }
}