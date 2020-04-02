package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertMes {
    public static Boolean bl;
    public static boolean display()
    {
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Button buttonyes=new Button("yes");
        Button buttonno=new Button("no");
        Label l1=new Label();
        l1.setText("do you want to close window？");
        VBox vb=new VBox();
        vb.getChildren().addAll(buttonyes,buttonno,l1);
        vb.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(vb,400,400));
        stage.setTitle("close window?");
        buttonno.setOnMouseClicked(mouseEvent -> {
            bl=false;
            stage.close();
        });
        buttonyes.setOnMouseClicked(mouseEvent -> {
            bl=true;
            stage.close();
        });
        stage.showAndWait();
        return bl;

    }

}
