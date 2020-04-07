package Controller;

import Stage.Main;
import dao.selectSQLCommand;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.StageManagement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class conOneSingle  {

    @FXML
    public Label id;
    @FXML
    public Label name;
    @FXML
    public VBox wholePic;
    @FXML
    private void initialize() throws IOException, SQLException {
        StageManagement.CONTROLLER.put("onesingle",this);
    }

    public void showCard(MouseEvent mouseEvent) throws IOException, SQLException {
        //打开card窗口
        Stage stage = new Stage();
        StageManagement.singleid = id.getText();
        VBox root = new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));

        //card窗口
        VBox down = FXMLLoader.load(getClass().getResource("/View/card.fxml"));
        conCardInfo cci = (conCardInfo) StageManagement.CONTROLLER.get("card");

        //插入图片到gridpane里，并且没有点击事件
        VBox cv = FXMLLoader.load(getClass().getResource("/View/noclickSingle.fxml"));
        conNoclickSingle cns = (conNoclickSingle) StageManagement.CONTROLLER.get("noclickSingle");
        cns.id.setText("ID：" + id.getText());
        cns.name.setText("姓名：" + name.getText());
        cns.id.setFont(new Font("Microsoft YaHei", 16));
        cns.name.setFont(new Font("Microsoft YaHei", 16));
        cci.picture.add(cv, 0, 1);

        //创建label查询出来的数值
        
        int start = 0;
        int stop = 0;
        Calendar cal=Calendar.getInstance();


        //读取数据
        ResultSet rs = selectSQLCommand.OptionDate("select * from staff where id=" + id.getText());
        if (rs.next()) {
            cci.age.setText(rs.getString("age"));
            cci.position.setText(rs.getString("position"));
            cci.title.setText(rs.getString("title"));
            cci.entry.setText(rs.getString("entry"));
            //计算工龄
            start = Integer.parseInt(cci.entry.getText().trim().substring(0, 4));
            if (rs.getString("quit") == null && rs.getString("retire") == null) {
//                cci.end.setText("-----------");
                stop = cal.get(Calendar.YEAR);

            } else if (rs.getString("quit") != null && rs.getString("retire") == null) {
                cci.quit.setText(rs.getString("quit"));
                stop = Integer.parseInt(cci.quit.getText().trim().substring(0, 4));

            } else if (rs.getString("quit") == null && rs.getString("retire") != null) {
                cci.retire.setText(rs.getString("retire"));
                stop = Integer.parseInt(cci.retire.getText().trim().substring(0, 4));
            }

        }

            //label设置字体并放入detail网格中
            cci.age.setFont(new Font("Microsoft YaHei", 18));
            cci.position.setFont(new Font("Microsoft YaHei", 18));
            cci.title.setFont(new Font("Microsoft YaHei", 18));
            cci.entry.setFont(new Font("Microsoft YaHei", 18));
            cci.quit.setFont(new Font("Microsoft YaHei", 18));
            cci.retire.setFont(new Font("Microsoft YaHei", 18));
            cci.workyear.setFont(new Font("Microsoft YaHei", 18));

            //组装
            root.getChildren().addAll(top, down);
            stage.setScene(new Scene(root));
            stage.show();
            cv.setPrefWidth(200);
            cv.setPrefHeight(200);

        }
    }
//    @Override
//    public Object clone() {
//        conOneSingle clonevb = null;
//        try{
//            clonevb = (conOneSingle) super.clone();
//        }catch(CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return clonevb;
