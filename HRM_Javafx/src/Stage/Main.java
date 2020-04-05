package Stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.StageManagement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root1 = FXMLLoader.load(getClass().getResource("/View/loginpage.fxml"));
//        primaryStage.setScene(new Scene(root1));
//        primaryStage.setResizable(false);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setTitle("人事工资管理系统");
//        StageManagement.STAGE.put("home",primaryStage);
//        primaryStage.show();
        VBox root=new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
        VBox down= FXMLLoader.load(getClass().getResource("/View/loginpage.fxml"));
        root.getChildren().addAll(top,down);
        primaryStage.setScene(new Scene(root));
        StageManagement.STAGE.put("home",primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
