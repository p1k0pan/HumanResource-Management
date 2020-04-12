package Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import store.AlertStage;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import dao.updateSQLCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.StageManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class conCardInfo {
    @FXML
    public Button back;
    @FXML
    public Button modi;
    @FXML
    public GridPane detail;
    @FXML
    public GridPane picture;
    @FXML
    public Label age;
    @FXML
    public Label position;
    @FXML
    public Label title;
    @FXML
    public Label quit;
    @FXML
    public Label retire;
    @FXML
    public Label entry;
    @FXML
    public Label workyear;
    @FXML
    public Button confirm;
    @FXML
    private void initialize() throws IOException, SQLException {

        Image btnImg3 = new Image("/img/返回.png");
        ImageView imageView3 = new ImageView(btnImg3);
        imageView3.setFitHeight(20);
        imageView3.setFitWidth(20);
        //给按钮设置图标
        back.setGraphic(imageView3);
        Image btnImg2 = new Image("/img/修改.png");
        ImageView imageView2 = new ImageView(btnImg2);
        imageView2.setFitHeight(20);
        imageView2.setFitWidth(20);
        //给按钮设置图标
        modi.setGraphic(imageView2);
        Image btnImg = new Image("/img/确认.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        //给按钮设置图标
        confirm.setGraphic(imageView);
        if(StageManagement.headpart.equals("管理部"))
        {
            modi.setVisible(false);
        }
        // 如果有头像系统，则图片的二进制保存在数据库中，所有的VBOX，label和布局都要重新new
        StageManagement.CONTROLLER.put("card",this);
    }

    TextField tfage=new TextField();
    TextField tfposition=new TextField();
    TextField tftitle=new TextField();
    TextField tfentry=new TextField();
    TextField tfquit=new TextField();
    TextField tfretire=new TextField();
     boolean isdisable=false;
    public void modify(ActionEvent actionEvent) {
        modi.setDisable(true);
        isdisable=true;
         tfage=new TextField(age.getText());
         tfposition=new TextField(position.getText());
         tftitle=new TextField(title.getText());
         tfentry=new TextField(entry.getText());
         tfquit=new TextField(quit.getText());
        tfretire=new TextField(retire.getText());
        confirm.setDisable(false);
        detail.add(tfage, 2, 1);
        detail.add(tfposition, 2, 2);
        detail.add(tftitle, 2, 3);
        detail.add(tfentry, 2, 4);
        detail.add(tfquit, 2, 5);
        detail.add(tfretire, 2, 6);
    }

    public void modifyConfirm(ActionEvent actionEvent) throws IOException, SQLException {
        modi.setDisable(true);
        String mage=tfage.getText();
        String mposition=tfposition.getText();
        String mtitle=tftitle.getText();
        String mentry=tfentry.getText();
        String mquit=tfquit.getText();
        String mretire=tfretire.getText();
        String sql = "";
        AlertStage.doubledisplay();
        System.out.println(mquit);
        System.out.println(mretire);
        if(StageManagement.ynflag==true) {
//            if (mage.equals(age.getText()) && mposition.equals(position.getText()) && mtitle.equals(title.getText()) && mentry.equals(entry.getText()) && mquit.equals(quit.getText()) && mworkyear.equals(workyear.getText()) && mretire.equals(retire.getText())
//            && (!"".equals(mquit) && !"".equals(mretire))) {
            //退休和离职冲突
            if(!"".equals(mquit)&& !"".equals(mretire)){
                //show alert
                StageManagement.message = "数据冲突";
                AlertStage.onedisplay();
            }
            else {
//                if ("离职时间：".equals(quit_retire.getText())) {
                    //sql语句在有离职和无离职之间进行选择,因为如果在职员工显示的是离职时间而不是退休时间，只有退休人员才显示退休时间
                    if ("".equals(mquit) && "".equals(mretire)) {
                        sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
                    }
                    else if("" .equals( mquit )&&  !"".equals(mretire)){
                        sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",retire=\"" + mquit + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
                    }
                    else if(!"".equals(mquit) && "".equals(mretire)){
                        sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",quit=\"" + mquit + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
                    }

                    System.out.println(sql);
                    try {
                        int resultline = updateSQLCommand.OptionDate(sql);
                        if (resultline > 0) {
                            StageManagement.message = "修改成功";
                            AlertStage.onedisplay();
                        }
                    } catch (MysqlDataTruncation e) {
                        String ex = e.toString();
                        if (ex.indexOf("Incorrect date value", 0) != -1) {
                            StageManagement.message = "数据输入有误";
                            AlertStage.onedisplay();
                        }
                    }
                }
//                else {
//                    sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",retire=\"" + mend + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
//                    try {
//                        int resultline = dao.updateSQLCommand.OptionDate(sql);
//                        if (resultline > 0) {
//                            StageManagement.message = "修改成功";
//                            AlertStage.onedisplay();
//                        }
//                    } catch (MysqlDataTruncation e) {
//                        String ex = e.toString();
//                        if (ex.indexOf("Incorrect date value", 0) != -1) {
//                            StageManagement.message = "数据输入有误";
//                            AlertStage.onedisplay();
//                        }
//                    }
//                }

            }

        age.setText(tfage.getText());
        position.setText(tfposition.getText());
        title.setText(tftitle.getText());
        entry.setText(tfentry.getText());
        quit.setText(tfquit.getText());
        retire.setText(tfretire.getText());

        int start=0,stop=0;
        //计算工龄
        start = Integer.parseInt(entry.getText().trim().substring(0, 4));
        if ("".equals(mquit) && "".equals(mretire)) {
//                cci.end.setText("-----------");
            stop = Calendar.YEAR;

        } else if (!"".equals(mquit) && "".equals(mretire)) {
            stop = Integer.parseInt(quit.getText().trim().substring(0, 4));

        } else if ("".equals(mquit) && !"".equals(mretire)) {
            stop = Integer.parseInt(retire.getText().trim().substring(0, 4));
        }
        workyear.setText(Integer.toString(stop - start));
        detail.getChildren().remove(tfposition);
        detail.getChildren().remove(tftitle);
        detail.getChildren().remove(tfage);
        detail.getChildren().remove(tfquit);
        detail.getChildren().remove(tfentry);
        detail.getChildren().remove(tfretire);
        modi.setDisable(false);

    }


    public void back(ActionEvent actionEvent) {
        if(!isdisable) {
            Stage stage = (Stage) modi.getScene().getWindow();
            stage.close();
        }
        else
        {
            modi.setDisable(false);
            isdisable=false;
        }
    }
}
