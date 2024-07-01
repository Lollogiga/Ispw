module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens com.example.greenpear to javafx.fxml;
    exports com.example.greenpear;
    exports com.example.greenpear.controllergrafico;
    opens com.example.greenpear.controllergrafico to javafx.fxml;
    exports com.example.greenpear.exception;
    opens com.example.greenpear.exception to javafx.fxml;
    exports com.example.greenpear.controllergrafico.buydietcontrollergrafico;
    opens com.example.greenpear.controllergrafico.buydietcontrollergrafico to javafx.fxml;
}