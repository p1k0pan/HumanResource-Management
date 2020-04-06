package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import utils.StageManagement;

import java.io.IOException;

public class conLoad {
    @FXML
    private VBox root;

    public BorderPane center =new BorderPane();
    @FXML
    private void initialize() throws IOException {
        AnchorPane header= FXMLLoader.load(getClass().getResource("/View/mainHeader.fxml"));
        GridPane c_left=FXMLLoader.load(getClass().getResource("/View/side.fxml"));
//        VBox c_right=FXMLLoader.load(getClass().getResource("/View/manManage.fxml"));
        center.setLeft(c_left);
//        center.setCenter(c_right);
        root.getChildren().addAll(header,center);
        StageManagement.CONTROLLER.put("centerboard",this);
    }
}
