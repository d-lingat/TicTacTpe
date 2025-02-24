module com.example.tictactpe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactpe to javafx.fxml;
    exports com.example.tictactpe;
}