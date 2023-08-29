module com.example.javafxtictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafxtictactoe to javafx.fxml;
    exports com.example.javafxtictactoe;
}