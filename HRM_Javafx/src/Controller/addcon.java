package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import store.Main;
import utils.StageManagement;

import java.io.IOException;
import java.sql.SQLException;

public class addcon {
    @FXML
    private TextField age;
    @FXML
    private TextField name;
    @FXML
    private TextField position;
    @FXML
    private TextField title;
    @FXML
    private TextField entry;
    @FXML
    private TextField quit;
    @FXML
    private TextField retire;
    @FXML
    private TextField id;
    @FXML
    private TextField salpos;
    @FXML
    private TextField salbasic;
    @FXML
    private TextField salother;
    @FXML
    private TextField salgov;
    @FXML
    private Label saltotal;
    @FXML
    private VBox vbox;
    @FXML
    private Button confirm;
    @FXML
    private Button back;
    @FXML
    private void initialize() throws IOException, SQLException {
        Image btnImg = new Image("/img/添加.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        //给按钮设置图标
        confirm.setGraphic(imageView);
        Image btnImg2 = new Image("/img/添加.png");
        ImageView imageView2 = new ImageView(btnImg);
        imageView2.setFitHeight(20);
        imageView2.setFitWidth(20);
        //给按钮设置图标
        back.setGraphic(imageView2);

        Stage stage = new Stage();
        VBox root = new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
        root.getChildren().addAll(top, vbox);
        stage.setScene(new Scene(root));
        stage.show();
        StageManagement.CONTROLLER.put("add",this);

    }
    public void addConfirm(ActionEvent actionEvent) throws IOException, SQLException {

         if(name.getText().equals("") || position.getText().equals("")||title.getText().equals("")||entry.getText().equals("")||id.getText().equals("")||salbasic.getText().equals("")||salgov.getText().equals("")||salother.getText().equals("")||salpos.getText().equals(""))
        {
            StageManagement.message="信息为空";
            store.AlertStage.onedisplay();
        }
        else
        {
            String sql="insert into staff (id,name,age,entry,position,title) values ("+id.getText()+",\""+name.getText()+"\","+
                    age.getText()+",\""+entry.getText()+"\",\""+position.getText()+"\",\""+title.getText()+"\""+")";
            int len=dao.insertSQLCommand.OptionDate(sql);
            System.out.println(sql);

            String sql2="insert into salary (id,basic,subsidy,gov_allowance,pos_allowance) values ("+id.getText()+","+salbasic.getText()+","+
                    salother.getText()+","+salgov.getText()+","+salpos.getText()+")";
            System.out.println(sql2);
            int len2=dao.insertSQLCommand.OptionDate(sql2);
            if(len2>0 && len>0)
            {
                    StageManagement.message="添加成功";
                    store.AlertStage.onedisplay();
                    Stage stage = (Stage)salpos.getScene().getWindow();
                    stage.close();
            }
            else {
                StageManagement.message = "添加失败";
                store.AlertStage.onedisplay();
                age.clear();
                name.clear();
                position.clear();
                title.clear();
                entry.clear();
                id.clear();
                salpos.clear();
                salbasic.clear();
                salother.clear();
                salgov.clear();
            }

        }

    }

    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage)salpos.getScene().getWindow();
        stage.close();
    }
}
