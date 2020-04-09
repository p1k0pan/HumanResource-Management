package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.StageManagement;

public class conynAlert {
    @FXML
    private Label message;
    @FXML
    private Button yes;
    @FXML
    private Button no;

    public void confirm(ActionEvent actionEvent) {
        StageManagement.ynflag=true;
        Stage stage = (Stage)yes.getScene().getWindow();
        stage.close();
    }

    public void cancell(ActionEvent actionEvent) {
        StageManagement.ynflag=false;
        Stage stage = (Stage)no.getScene().getWindow();
        stage.close();
    }
}