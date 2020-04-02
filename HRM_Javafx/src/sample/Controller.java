package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class Controller {


    @FXML
    private AnchorPane rootPane;

    public void loadSecond() throws IOException {

        FlowPane pane = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        rootPane.getChildren().setAll(pane);

    }
    public void show(){
        System.out.println(this);
    }


}
