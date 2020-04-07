package Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AlertStage {
    public static void onedisplay() throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root1 = FXMLLoader.load(AlertStage.class.getResource("/View/onebAlert.fxml"));
        stage.setScene(new Scene(root1, 200, 200));
        stage.setTitle("Alert");
//    buttonno.setOnMouseClicked(mouseEvent -> {
//        bl=false;
//        stage.close();
//    });
//    buttonyes.setOnMouseClicked(mouseEvent -> {
//        bl=true;
//        stage.close();
//    });
        stage.showAndWait();

    }
    public static void doubledisplay() throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root1 = FXMLLoader.load(AlertStage.class.getResource("/View/doublebAlert.fxml"));
        stage.setScene(new Scene(root1, 200, 200));
        stage.setTitle("Alert");
//    buttonno.setOnMouseClicked(mouseEvent -> {
//        bl=false;
//        stage.close();
//    });
//    buttonyes.setOnMouseClicked(mouseEvent -> {
//        bl=true;
//        stage.close();
//    });
        stage.showAndWait();

    }

}
