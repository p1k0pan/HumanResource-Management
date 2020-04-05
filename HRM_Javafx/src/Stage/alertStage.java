package Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class alertStage {
    public static void display() throws IOException {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root1 = FXMLLoader.load(alertStage.class.getResource("/View/Alert.fxml"));
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
