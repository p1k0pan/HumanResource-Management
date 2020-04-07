package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utils.StageManagement;

import java.io.IOException;
import java.sql.SQLException;

public class conNoclickSingle {


    @FXML
    public Label id;
    @FXML
    public Label name;
    @FXML
    public VBox wholePic;
    @FXML
    private void initialize() throws IOException, SQLException {
        StageManagement.CONTROLLER.put("noclickSingle",this);
    }
}
