package com.example.greenpear.controllergrafico;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class DietGraphicController extends GraphicControllerGeneric{
    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        gridPane.setGridLinesVisible(false); // Disattiva le linee della griglia di default
        gridPane.setStyle("-fx-grid-lines-visible: true;"); // Riattiva le linee della griglia tramite CSS

        // CSS per mostrare solo le linee della griglia orizzontali
        gridPane.setStyle(
                "-fx-grid-lines-visible: true;" +
                        "-fx-grid-line-color: black;" +
                        "-fx-hgap: 1;" +
                        "-fx-vgap: 1;"
        );
    }
}
