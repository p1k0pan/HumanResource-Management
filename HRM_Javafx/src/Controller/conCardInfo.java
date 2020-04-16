package Controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import dao.updateSQLCommand;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import store.AlertStage;
import utils.Datetransform;
import utils.StageManagement;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    ComboBox cbpos = new ComboBox();
    ComboBox cbtitle = new ComboBox();
    TextField tfage = new TextField();
    //    TextField tfposition=new TextField();
//    TextField tftitle=new TextField();
//    TextField tfentry=new TextField();
//    TextField tfquit=new TextField();
//    TextField tfretire=new TextField();
    DatePicker dpentry = new DatePicker();
    DatePicker dpquit = new DatePicker();
    DatePicker dpretire = new DatePicker();
    boolean isdisable = false;

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
        StageManagement.CONTROLLER.put("card", this);
        Stage stage = (Stage) StageManagement.STAGE.get("cardwindow");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    winClose();
                    System.out.println("ok");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void modify(ActionEvent actionEvent) {
        modi.setDisable(true);
        isdisable = true;
        tfage = new TextField(age.getText());
//         tfposition=new TextField(position.getText());
//         tftitle=new TextField(title.getText());
//         tfentry=new TextField(entry.getText());
//         tfquit=new TextField(quit.getText());
//        tfretire=new TextField(retire.getText());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dpentry.setValue(LocalDate.parse(Datetransform.blank2Null(entry.getText()), fmt));
        if (Datetransform.blank2Null(quit.getText()) != null)
            dpquit.setValue(LocalDate.parse(Datetransform.blank2Null(quit.getText()), fmt));
        if (Datetransform.blank2Null(retire.getText()) != null)
            dpretire.setValue(LocalDate.parse(Datetransform.blank2Null(retire.getText()), fmt));
        cbpos.getItems().addAll("正式员工",
                "经理",
                "主管",
                "退休员工",
                "临时工");
        cbtitle.getItems().addAll("高级",
                "低级",
                "中级"
        );
        dpretire.setPrefWidth(139);
        dpentry.setPrefWidth(139);
        dpquit.setPrefWidth(139);
        cbpos.setPrefWidth(139);
        cbtitle.setPrefWidth(139);
        cbpos.setValue(position.getText());
        cbtitle.setValue(title.getText());
        confirm.setDisable(false);
        detail.add(tfage, 2, 1);
        detail.add(cbpos, 2, 2);
        detail.add(cbtitle, 2, 3);
        detail.add(dpentry, 2, 4);
        detail.add(dpquit, 2, 5);
        detail.add(dpretire, 2, 6);
    }

    public void modifyConfirm(ActionEvent actionEvent) throws IOException, SQLException {
        modi.setDisable(true);
        String mage = tfage.getText();
        String mposition = (String) cbpos.getValue();
        String mtitle = (String) cbtitle.getValue();
        String mentry = dpentry.getValue().toString();

        String mquit = "";
        String mretire = "";
        if (dpquit.getValue() != null)
            mquit = dpquit.getValue().toString();
        if (dpretire.getValue() != null)
            mretire = dpretire.getValue().toString();
        StageManagement.ynMessage = "数据是否确认修改";
        AlertStage.doubledisplay();
//        System.out.println(mquit);
//        System.out.println(mretire);

        boolean bl = false;
        if (StageManagement.ynflag == true) {
            bl = saveData(mquit, mretire, mage, mentry, mposition, mtitle);
        }
        if (bl) {
            age.setText(tfage.getText());
//        position.setText(tfposition.getText());
            position.setText((String) cbpos.getValue());
//        title.setText(tftitle.getText());
            title.setText((String) cbtitle.getValue());
//        entry.setText(tfentry.getText);
//        quit.setText(tfquit.getText());
//        retire.setText(tfretire.getText());
            entry.setText(Datetransform.null2Blank(dpentry));
            quit.setText(Datetransform.null2Blank(dpquit));
            retire.setText(Datetransform.null2Blank(dpretire));

            int start = 0;
            start = Integer.parseInt(entry.getText().trim().substring(0, 4));
            int stop = workyearCount(mquit, mretire);
            workyear.setText(Integer.toString(stop - start));
            deleteGridChildren();
        }
        else
        {

            deleteGridChildren();
        }

    }

    public int workyearCount(String wquit, String wretire) {

        int stop = 0;
        //计算工龄
        if ("".equals(wquit) && "".equals(wretire)) {
//                cci.end.setText("-----------");
            stop = Calendar.YEAR;

        } else if (!"".equals(wquit) && "".equals(wretire)) {
            stop = Integer.parseInt(quit.getText().trim().substring(0, 4));

        } else if ("".equals(wquit) && !"".equals(wretire)) {
            stop = Integer.parseInt(retire.getText().trim().substring(0, 4));
        }
        return stop;
    }

    public boolean saveData(String mquit, String mretire, String mage, String mentry, String mposition, String mtitle) throws IOException, SQLException {

//            if (mage.equals(age.getText()) && mposition.equals(position.getText()) && mtitle.equals(title.getText()) && mentry.equals(entry.getText()) && mquit.equals(quit.getText()) && mworkyear.equals(workyear.getText()) && mretire.equals(retire.getText())
//            && (!"".equals(mquit) && !"".equals(mretire))) {
        //退休和离职冲突
        if (!"".equals(mquit) && !"".equals(mretire)) {
            //show alert
            StageManagement.message = "数据冲突";
            AlertStage.onedisplay();
            return false;
        }
        else {
//                if ("离职时间：".equals(quit_retire.getText())) {
            //sql语句在有离职和无离职之间进行选择,因为如果在职员工显示的是离职时间而不是退休时间，只有退休人员才显示退休时间
            String sql = "";
            if ("".equals(mquit) && "".equals(mretire)) {
                sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
            } else if ("".equals(mquit) && !"".equals(mretire)) {
                sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",retire=\"" + mquit + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
            } else if (!"".equals(mquit) && "".equals(mretire)) {
                sql = "update staff set age=" + mage + ",entry=\"" + mentry + "\",quit=\"" + mquit + "\",position=\"" + mposition + "\",title=\"" + mtitle + "\" where id =" + StageManagement.singleid;
            }

            System.out.println(sql);
            try {
                int resultline = updateSQLCommand.OptionDate(sql);
                if (resultline > 0) {
                    StageManagement.message = "修改成功";
                    AlertStage.onedisplay();
                    return true;
//                        String str[]=new String [6];
//                        str[0]=mquit;
//                        str[1]=mretire;
//                        str[2]=mage;
//                        str[3]=mentry;
//                        str[4]=mposition;
//                        str[5]=mtitle;
//                        return str;
                }
            } catch (MysqlDataTruncation e) {
                String ex = e.toString();
                if (ex.indexOf("Incorrect date value", 0) != -1) {
                    StageManagement.message = "数据输入有误";
                    AlertStage.onedisplay();
                    return false;
                }
            }
        }
        System.out.println("wts going on");
        return false;

    }





    public void back(ActionEvent actionEvent) throws IOException, SQLException {
        if(!isdisable) {
            Stage stage = (Stage) modi.getScene().getWindow();
            stage.close();
        }
        else
        {
            if(cbpos.getValue().toString().equals(position.getText())&& cbtitle.getValue().toString().equals(title.getText())&&tfage.getText().equals(age.getText())
            && Datetransform.null2Blank(dpquit).equals(quit.getText()) && Datetransform.null2Blank(dpretire).equals(retire.getText()) && dpentry.getValue().toString().equals(entry.getText()) ){
               deleteGridChildren();
            }
            else {

                StageManagement.ynMessage="数据已修改是否保存";
                AlertStage.doubledisplay();
                if(StageManagement.ynflag==true){
                    String mage=tfage.getText();
                    String mposition=(String)cbpos.getValue();
                    String mtitle=(String)cbtitle.getValue();
                    String mentry=dpentry.getValue().toString();

                    String mquit="";
                    String mretire="";
                    saveData(mquit,mretire,mage,mentry,mposition,mtitle);

                    age.setText(tfage.getText());
//        position.setText(tfposition.getText());
                    position.setText((String)cbpos.getValue());
//        title.setText(tftitle.getText());
                    title.setText((String)cbtitle.getValue());
//        entry.setText(tfentry.getText);
//        quit.setText(tfquit.getText());
//        retire.setText(tfretire.getText());
                    entry.setText(Datetransform.null2Blank(dpentry));
                    quit.setText(Datetransform.null2Blank(dpquit));
                    retire.setText(Datetransform.null2Blank(dpretire));

                    int start=0;
                    start = Integer.parseInt(entry.getText().trim().substring(0, 4));
                    int stop=workyearCount(mquit,mretire);
                    workyear.setText(Integer.toString(stop - start));

//        detail.getChildren().remove(tfposition);
                    detail.getChildren().remove(cbpos);
//        detail.getChildren().remove(tftitle);
                    detail.getChildren().remove(cbtitle);
                    detail.getChildren().remove(tfage);
//        detail.getChildren().remove(tfquit);
//        detail.getChildren().remove(tfentry);
//        detail.getChildren().remove(tfretire);
                    detail.getChildren().remove(dpquit);
                    detail.getChildren().remove(dpentry);
                    detail.getChildren().remove(dpretire);
                    modi.setDisable(false);
                }

                else{
                   deleteGridChildren();
                }
            }
        }
    }

    public void winClose() throws IOException, SQLException {
        if(!isdisable) {
            Stage stage = (Stage) modi.getScene().getWindow();
            stage.close();
        }
        else
        {
            if(cbpos.getValue().toString().equals(position.getText())&& cbtitle.getValue().toString().equals(title.getText())&&tfage.getText().equals(age.getText())
                    && Datetransform.null2Blank(dpquit).equals(quit.getText()) && Datetransform.null2Blank(dpretire).equals(retire.getText()) && dpentry.getValue().toString().equals(entry.getText()) ){
                deleteGridChildren();
            }
            else {

                StageManagement.ynMessage="数据已修改是否保存";
                AlertStage.doubledisplay();
                if(StageManagement.ynflag==true){
                    String mage=tfage.getText();
                    String mposition=(String)cbpos.getValue();
                    String mtitle=(String)cbtitle.getValue();
                    String mentry=dpentry.getValue().toString();

                    String mquit="";
                    String mretire="";
                    saveData(mquit,mretire,mage,mentry,mposition,mtitle);

                    age.setText(tfage.getText());
//        position.setText(tfposition.getText());
                    position.setText((String)cbpos.getValue());
//        title.setText(tftitle.getText());
                    title.setText((String)cbtitle.getValue());
//        entry.setText(tfentry.getText);
//        quit.setText(tfquit.getText());
//        retire.setText(tfretire.getText());
                    entry.setText(Datetransform.null2Blank(dpentry));
                    quit.setText(Datetransform.null2Blank(dpquit));
                    retire.setText(Datetransform.null2Blank(dpretire));

                    int start=0;
                    start = Integer.parseInt(entry.getText().trim().substring(0, 4));
                    int stop=workyearCount(mquit,mretire);
                    workyear.setText(Integer.toString(stop - start));

//        detail.getChildren().remove(tfposition);
                    detail.getChildren().remove(cbpos);
//        detail.getChildren().remove(tftitle);
                    detail.getChildren().remove(cbtitle);
                    detail.getChildren().remove(tfage);
//        detail.getChildren().remove(tfquit);
//        detail.getChildren().remove(tfentry);
//        detail.getChildren().remove(tfretire);
                    detail.getChildren().remove(dpquit);
                    detail.getChildren().remove(dpentry);
                    detail.getChildren().remove(dpretire);
                    modi.setDisable(false);
                }

                else{
                    Stage stage = (Stage) modi.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
    public void deleteGridChildren(){
        detail.getChildren().remove(cbpos);
//        detail.getChildren().remove(tftitle);
        detail.getChildren().remove(cbtitle);
        detail.getChildren().remove(tfage);
//        detail.getChildren().remove(tfquit);
//        detail.getChildren().remove(tfentry);
//        detail.getChildren().remove(tfretire);
        detail.getChildren().remove(dpquit);
        detail.getChildren().remove(dpentry);
        detail.getChildren().remove(dpretire);
        modi.setDisable(false);
        isdisable = false;

    }
}
