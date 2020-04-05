package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.StageManagement;


public class conCloseIcon {
    @FXML
    private ImageView closeIcon;
    public void changeRed(MouseEvent mouseEvent) {
        Image image =new Image("/img/closered.png");
        closeIcon.setImage(image);

    }

    public void changeGray(MouseEvent mouseEvent) {
        Image image =new Image("/img/closegray.png");
        closeIcon.setImage(image);
    }

    public void windowClose(MouseEvent mouseEvent) {
        Stage stage=StageManagement.STAGE.get("home");
        stage.close();

    }
}
