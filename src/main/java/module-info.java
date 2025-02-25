module com.example.tictactpe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.tictactpe to javafx.fxml;
    exports com.example.tictactpe;
}