package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class WorkerSarlary {
    private final Stage stage=new Stage();
    public WorkerSarlary(String name,String password){
        BorderPane borderPane =new BorderPane();
        Scene scene =new Scene(borderPane);
        Text text=new Text("账号："+name+"  "+"密码："+password);

        borderPane.setCenter(text);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(600);
        stage.show();

    }

}
