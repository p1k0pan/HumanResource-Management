package Controller;

import utils.StageManagement;
import dao.selectSQLCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conHome {

    public ToggleGroup groupLog= new ToggleGroup();
    public ToggleGroup groupReg= new ToggleGroup();
    public String nameReg="";
    public String passReg="";
    public String passReg2="";
    public String priReg="人事部";
    public String nameLog="";
    public String passLog="";
    public String priLog="人事部";
    @FXML
    public TextField text_name2;
    @FXML
    public TextField text_pass1;
    @FXML
    public TextField text_pass2;
    @FXML
    public TextField text_name;
    @FXML
    public TextField text_pass;
    @FXML
    private RadioButton renshi;
    @FXML
    private RadioButton guanli;


    @FXML
    private Label userconfirm;

    @FXML
    private void initialize(){

        //添加部门选项，登陆
        ToggleGroup group = new ToggleGroup();
        renshi.setToggleGroup(group);
        guanli.setToggleGroup(group);
        renshi.setSelected(true);
        try {
            groupLog.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> changed, Toggle oldVal, Toggle newVal) {
                    RadioButton temp_rb = (RadioButton) newVal;
                    priLog = temp_rb.getText();
                }
            });
        }
        catch (NullPointerException e) {
            System.out.println(e);
        }


    }
    //点击登录事件
    public void Log() throws SQLException, IOException {
        passLog = text_pass.getText();
        nameLog = text_name.getText();
        StageManagement.CONTROLLER.put("home",this);
        utils.loginConfirm.confirm(nameLog, passLog, priLog);

    }

    public void Clear(){
        text_name.clear();
        text_pass.clear();
        text_name.requestFocus();
    }

// 点击注册按钮
    public void Regist(ActionEvent actionEvent) throws IOException, SQLException {
        nameReg=text_name2.getText();
        passReg=text_pass1.getText();
        passReg2=text_pass2.getText();
        utils.registConfirm.confirm(nameReg,passReg,passReg2);
//        System.out.println(passReg);
//        System.out.println(passReg2);
    }

    public void checkexist(ActionEvent actionEvent) throws SQLException {
        ResultSet rs = null;
            if ("".equals(text_name2.getText()))
                userconfirm.setText("请输入用户名");
            else {
                rs = selectSQLCommand.OptionDate("select id,password from account where id = " + text_name2.getText());
                if (rs.next()) {
                    if (rs.getString("password") == null) {
                        userconfirm.setText("此id可以被注册");
                    } else {
                        userconfirm.setText("已注册请直接登陆");

                    }
                } else {
                    userconfirm.setText("id不存在");
                }
            }
    }
}


