package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.StageManagement;

import java.io.IOException;

public class conShowAlert {
    @FXML
    private Label message;
    @FXML
    private Button exitButton;
    @FXML
    private void initialize(){
        message.setText(StageManagement.message);
    }
    public void exitclick() throws IOException {
        //通过stage方式操作窗口，因为一个新的窗口就是一个新的stage
        Stage stage = (Stage)exitButton.getScene().getWindow();
        stage.close();
        if(StageManagement.message=="登陆失败"|| StageManagement.message=="用户名不存在"){
            conLoginPage clp=(conLoginPage) StageManagement.CONTROLLER.get("log");
            clp.Clear();
//            ch.text_name.setText("");
//            ch.text_pass.setText("");

        }
        else if(StageManagement.message=="登陆成功" ){
            Stage priStage= StageManagement.STAGE.get("home");
            priStage.close();
            Stage newstage=new Stage();

            Parent root1 = FXMLLoader.load(getClass().getResource("/View/blankBoard.fxml"));
            newstage.setScene(new Scene(root1));
            newstage.setTitle("人事工资管理系统");
            StageManagement.STAGE.put("home",newstage);
            newstage.setMaximized(true);
            newstage.setMinHeight(400);
            newstage.setMinWidth(850);
            newstage.getIcons().add(new Image("/img/icon2.png"));
            newstage.show();
        }

        else if(StageManagement.message=="此用户还没有注册" ) {

            conLoginPage clp=(conLoginPage) StageManagement.CONTROLLER.get("log");
            clp.goToRegist();
        }

        else if(StageManagement.message=="已注册请直接登陆"|| StageManagement.message=="注册成功") {
            conRegistPage crp=(conRegistPage) StageManagement.CONTROLLER.get("reg");
            crp.goToLogin();
        }
        //实现注册成功后，获取焦点到登陆tab
        //
    }

}

