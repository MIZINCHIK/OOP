module io.github.mizinchik {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens io.github.mizinchik to javafx.fxml;
    exports io.github.mizinchik;
    exports io.github.mizinchik.utils;
    opens io.github.mizinchik.utils to javafx.fxml;
    exports io.github.mizinchik.controller;
    opens io.github.mizinchik.controller to javafx.fxml;
    exports io.github.mizinchik.view;
    opens io.github.mizinchik.view to javafx.fxml;
    exports io.github.mizinchik.model;
    opens io.github.mizinchik.model to javafx.fxml;
}