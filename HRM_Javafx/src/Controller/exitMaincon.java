package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        id.setText( StageManagement.headid);
        part.setText(StageManagement.headpart);
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage=(Stage)exit.getScene().getWindow();
        stage.close();
    }
}
