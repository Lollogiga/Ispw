module com.example.login {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.example.GreenPear to javafx.fxml;
    exports com.example.GreenPear;
}