package com.example.greenpear.controllergrafico;

import com.example.greenpear.bean.LoginBean;
import com.example.greenpear.entities.UserProfile;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class RicettarioGraphicController extends GraphicControllerGeneric{
    @FXML
    private Circle colazioneImage;
    @FXML
    private Circle merendaImage;
    @FXML
    private Circle primiImage;
    @FXML
    private Circle dolciImage;
    @FXML
    private Circle drinksImage;
    @FXML
    private Circle biscottiImage;

    @FXML
    public void initialize(LoginBean userBean){
        this.userBean = userBean;

        Image colazioneJPG = new Image(getClass().getResource("/com/example/greenpear/images/imagesRicette/colazione.jpg").toExternalForm());
        colazioneImage.setFill(new ImagePattern(colazioneJPG));

        Image merendaJPG = new Image(getClass().getResource("/com/example/greenpear/images/imagesRicette/merenda.jpg").toExternalForm());
        merendaImage.setFill(new ImagePattern(merendaJPG));

        Image primiJPG = new Image(getClass().getResource("/com/example/greenpear/images/imagesRicette/primi.jpg").toExternalForm());
        primiImage.setFill(new ImagePattern(primiJPG));

        Image dolciJPG = new Image(getClass().getResource("/com/example/greenpear/images/imagesRicette/dolci.jpg").toExternalForm());
        dolciImage.setFill(new ImagePattern(dolciJPG));

        Image drinksJPG = new Image(getClass().getResource("/com/example/greenpear/images/imagesRicette/drinks.jpg").toExternalForm());
        drinksImage.setFill(new ImagePattern(drinksJPG));

        Image biscottiJPG = new Image(getClass().getResource("/com/example/greenpear/images/imagesRicette/biscotti.jpg").toExternalForm());
        biscottiImage.setFill(new ImagePattern(biscottiJPG));

    }

}
