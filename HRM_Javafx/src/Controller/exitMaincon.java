package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.StageManagement;

public class exitMaincon {
    @FXML
    private Button exit;
    @FXML
    private Label id;
    @FXML
    private Label part;
    @FXML
    private void initialize(){
        Image btnImg = new Image("/img/退出.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        //给按钮设置图标
        exit.setGraphic(imageView);
        id.setText( StageManagement.headid);
        part.setText(StageManagement.headpart);
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage=(Stage)exit.getScene().getWindow();
        stage.close();
    }
}
