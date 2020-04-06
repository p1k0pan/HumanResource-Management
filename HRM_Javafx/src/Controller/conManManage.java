package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class conManManage {
//    @FXML
//    private AnchorPane ap;
//    @FXML
//    private ScrollPane sp;
    @FXML
    private ScrollPane sp;
    @FXML
    private GridPane mainGrid;
    @FXML

    private void initialize() throws IOException {
        GridPane gp= FXMLLoader.load(getClass().getResource("/View/manyGrid.fxml"));
        gp.setPrefHeight(450);
        gp.setPrefWidth(960);
        BorderPane bp=new BorderPane();
        bp.setPrefHeight(sp.getPrefHeight());
        bp.setPrefWidth(sp.getPrefWidth());
        bp.setCenter(gp);
        sp.setContent(bp);
    }
}
