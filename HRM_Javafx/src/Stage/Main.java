package Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.StageManagement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getResource("/View/blankBoard.fxml"));

        primaryStage.setScene(new Scene(root1));
        primaryStage.setTitle("人事工资管理系统");
        StageManagement.STAGE.put("home",primaryStage);
        primaryStage.setMaximized(true);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(850);
        primaryStage.show();

//        VBox root=new VBox();
//        root.setId("root");
//        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
//        VBox down= FXMLLoader.load(getClass().getResource("/View/logingpage.fxml"));
//        root.getChildren().addAll(top,down);
//        primaryStage.setScene(new Scene(root));
//        StageManagement.STAGE.put("home",primaryStage);
//        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
