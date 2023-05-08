package io.github.mizinchik.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller handling the basic help visualisation.
 *
 * @author MIZINCHIK
 */
public abstract class Controller {
    @FXML
    protected MenuBar menuBar;

    /**
     * Shows help window.
     */
    @FXML
    private void showHelp() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(menuBar.getScene().getWindow());
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("WASD/←↑↓→ to move" + System.lineSeparator() +
                "P to pause" + System.lineSeparator() +
                "please close this game it's crap"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
