module io.github.mizinchik {
    requires javafx.controls;
    requires javafx.fxml;


    opens io.github.mizinchik to javafx.fxml;
    exports io.github.mizinchik;
    exports io.github.mizinchik.utils;
    opens io.github.mizinchik.utils to javafx.fxml;
}