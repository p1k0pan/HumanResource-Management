package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class conManM {
    @FXML
    private GridPane mainGrid;
    @FXML
    private void initialize() throws IOException {
        VBox one= FXMLLoader.load(getClass().getResource("/View/oneSingle.fxml"));
        mainGrid.add(one,0,0);
    }
}
