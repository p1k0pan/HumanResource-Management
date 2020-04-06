package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import utils.StageManagement;

import java.io.IOException;
import java.sql.SQLException;

public class conOnSingle {

    @FXML
    public Label id;
    @FXML
    public Label name;
    @FXML
    private void initialize() throws IOException, SQLException {
        StageManagement.CONTROLLER.put("onesingle",this);
    }

    public void showCard(MouseEvent mouseEvent) {
        System.out.println("click");
    }
}