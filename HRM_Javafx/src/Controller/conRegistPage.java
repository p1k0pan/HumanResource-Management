package Controller;

import Stage.Main;
import dao.selectSQLCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import utils.StageManagement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conRegistPage {
    public String nameReg="";
    public String passReg="";
    public String passReg2="";
    @FXML
    public TextField text_name2;
    @FXML
    public TextField text_pass1;
    @FXML
    public TextField text_pass2;
    @FXML
    private Label userconfirm;

    @FXML
    private Button logbtn;
    @FXML
    private Button clearbtn;

    @FXML
    private void initialize() {
        Image btnImg = new Image("/img/reg.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        //给按钮设置图标
        logbtn.setGraphic(imageView);
    }
    public void Clear(){
        text_name2.clear();
        text_pass2.clear();
        text_pass1.clear();
        text_name2.requestFocus();
    }

    // 点击注册按钮
    public void Regist(ActionEvent actionEvent) throws IOException, SQLException {
        nameReg=text_name2.getText();
        passReg=text_pass1.getText();
        passReg2=text_pass2.getText();
        StageManagement.CONTROLLER.put("reg",this);
        utils.registConfirm.confirm(nameReg,passReg,passReg2);
//        System.out.println(passReg);
//        System.out.println(passReg2);
    }

    public void checkexist(ActionEvent actionEvent) throws SQLException {
        ResultSet rs = null;
        if ("".equals(text_name2.getText())) {
            userconfirm.setText("请输入用户名");
            userconfirm.setTextFill(Paint.valueOf("#EE0000"));
        }
        else {
            rs = selectSQLCommand.OptionDate("select id,password from account where id = " + text_name2.getText());
            if (rs.next()) {
                if (rs.getString("password") == null) {
                    userconfirm.setText("此id可以被注册");
                    userconfirm.setTextFill(Paint.valueOf("#32CD32"));
                } else {
                    userconfirm.setText("已注册请直接登陆");
                    userconfirm.setTextFill(Paint.valueOf("#EE0000"));
                    logbtn.setDisable(true);
                    text_pass1.setDisable(true);
                    text_pass2.setDisable(true);

                }
            } else {
                userconfirm.setText("id不存在");
                userconfirm.setTextFill(Paint.valueOf("#EE0000"));
            }
        }
    }
    public void clear_showShadow(MouseEvent mouseEvent) {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(2.0);
        clearbtn.setEffect(shadow);

    }

    public void clear_shutShadow(MouseEvent mouseEvent) {
        clearbtn.setEffect(null);
    }

    public void showShadow(MouseEvent mouseEvent) {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(2.0);
        logbtn.setEffect(shadow);
    }

    public void shutShadow(MouseEvent mouseEvent) {
        logbtn.setEffect(null);
    }

    public void goToLogin(ActionEvent actionEvent) throws IOException {
        VBox root=new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
        VBox down= FXMLLoader.load(getClass().getResource("/View/loginpage.fxml"));
        root.getChildren().addAll(top,down);
        Stage stage= StageManagement.STAGE.get("home");
        stage.setScene(new Scene(root));
    }

    public void goToLogin() throws IOException {
        VBox root=new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
        VBox down= FXMLLoader.load(getClass().getResource("/View/loginpage.fxml"));
        root.getChildren().addAll(top,down);
        Stage stage= StageManagement.STAGE.get("home");
        stage.setScene(new Scene(root));
    }
}

