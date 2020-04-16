package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import store.AlertStage;
import utils.StageManagement;
import utils.keycodMap;

import java.io.IOException;

public class conManManage {
//    @FXML
//    private AnchorPane ap;
//    @FXML
//    private ScrollPane sp;

    @FXML
    private Button search ;
    @FXML
    private Button reset;
    @FXML
    private Button addbtn;
    @FXML
    private ScrollPane sp;
    @FXML
    private ComboBox cbb;
    @FXML
    private TextField textsearch;
    @FXML
    private GridPane mainGrid;
    String choosen="";
    public void showGridpane() throws IOException {
        GridPane gp= FXMLLoader.load(getClass().getResource("/View/manyGrid.fxml"));
        gp.setPrefHeight(450);
        gp.setPrefWidth(960);
        BorderPane bp=new BorderPane();
        bp.setPrefHeight(sp.getPrefHeight());
        bp.setPrefWidth(sp.getPrefWidth());
        bp.setCenter(gp);
        sp.setContent(bp);
    }
    public boolean[] isNumeric(String key){
        boolean is[]=new boolean[2];
        is[0]=false;
        is[1]=false;
        for (int i = key.length(); --i >= 0;) {
            if (!Character.isDigit(key.charAt(i))) {
                is[0]=true;
            }
            else
            {
                is[1]=true;
            }
        }
        return is;
    }


    @FXML
    private void initialize() throws IOException {
        Image btnImg3 = new Image("/img/搜索.png");
        ImageView imageView3 = new ImageView(btnImg3);
        imageView3.setFitHeight(20);
        imageView3.setFitWidth(20);
        search.setGraphic(imageView3);
        Image btnImg2 = new Image("/img/添加.png");
        ImageView imageView2 = new ImageView(btnImg2);
        imageView2.setFitHeight(20);
        imageView2.setFitWidth(20);
        addbtn.setGraphic(imageView2);
        Image btnImg = new Image("/img/重置.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        reset.setGraphic(imageView);
        if(StageManagement.headpart.equals("管理部"))
        {
            addbtn.setVisible(false);
        }
        StageManagement.sqlquery="SELECT id,name FROM staff ";
       showGridpane();
        cbb.getItems().addAll(
                "正式员工",
                "经理",
                "主管",
                "退休员工",
                "临时工"
        );
        cbb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                choosen=(String)newValue;
            }
        });
    }

    public void clickSearch(ActionEvent actionEvent) throws IOException {
        searchver1();
    }


    public void refresh(ActionEvent actionEvent) throws IOException {
        StageManagement.sqlquery="SELECT id,name FROM staff ";
        showGridpane();
        textsearch.setText("");
        cbb.getSelectionModel().clearSelection();
    }

    public void add(ActionEvent actionEvent) throws IOException {

        VBox top = FXMLLoader.load(getClass().getResource("/View/add.fxml"));
    }

    public void searchver1() throws IOException {

        if("".equals(textsearch.getText()) && "".equals(choosen))
        {
            StageManagement.message="你没有输入查询内容";
            AlertStage.onedisplay();
        }
        else if("".equals(textsearch.getText()) && !"".equals(choosen))
        {
            StageManagement.sqlquery="SELECT id,name FROM staff where position= \""+choosen+"\"";
            showGridpane();
        }
        else if(!"".equals(textsearch.getText()) && "".equals(choosen)){

            String key=textsearch.getText();
            boolean is[]=isNumeric(key);
//
//            System.out.println(is[0]);
//            System.out.println(is[1]);
            if(is[0] && is[1])
            {
                StageManagement.message="名字和数字重复";
                AlertStage.onedisplay();
            }
            else if(is[0]&& !is[1]) {
                StageManagement.sqlquery = "SELECT id,name FROM staff where name like \"%" + key + "%\"";
                showGridpane();
            }
            else if(!is[0] && is[1]){
                StageManagement.sqlquery="SELECT id,name FROM staff where id like \"%"+key+"%\"";
                showGridpane();
            }
        }

        else if(!"".equals(textsearch.getText()) && !"".equals(choosen)){
            String key =textsearch.getText();
            boolean is[]=isNumeric(key);
//
//            System.out.println(is[0]);
//            System.out.println(is[1]);
            if(is[0] && is[1])
            {
                StageManagement.message="名字和数字重复";
                AlertStage.onedisplay();
            }
            else if(is[0]&& !is[1]) {
                StageManagement.sqlquery = "SELECT id,name FROM staff where name like \"%" + key + "%\""+" and position=\""+choosen+"\"";
                showGridpane();
            }
            else if(!is[0] && is[1]){
                StageManagement.sqlquery="SELECT id,name FROM staff where id like \"%"+key+"%\"";
                showGridpane();
            }

        }
    }
    public void searchver2() throws IOException {


         if ("".equals(textsearch.getText()) && !"".equals(choosen)) {
            StageManagement.sqlquery = "SELECT id,name FROM staff where position= \"" + choosen + "\"";
            showGridpane();
        } else if (!"".equals(textsearch.getText()) && "".equals(choosen)) {

            String key = textsearch.getText();
            boolean is[] = isNumeric(key);
//
//            System.out.println(is[0]);
//            System.out.println(is[1]);
            if (is[0] && is[1]) {
                StageManagement.message = "名字和数字重复";
                AlertStage.onedisplay();
            } else if (is[0] && !is[1]) {
                StageManagement.sqlquery = "SELECT id,name FROM staff where name like \"%" + key + "%\"";
                showGridpane();
            } else if (!is[0] && is[1]) {
                StageManagement.sqlquery = "SELECT id,name FROM staff where id like \"%" + key + "%\"";
                showGridpane();
            }
        } else if (!"".equals(textsearch.getText()) && !"".equals(choosen)) {
            String key = textsearch.getText();
            boolean is[] = isNumeric(key);
//
//            System.out.println(is[0]);
//            System.out.println(is[1]);
            if (is[0] && is[1]) {
                StageManagement.message = "名字和数字重复";
                AlertStage.onedisplay();
            } else if (is[0] && !is[1]) {
                StageManagement.sqlquery = "SELECT id,name FROM staff where name like \"%" + key + "%\"" + " and position=\"" + choosen + "\"";
                showGridpane();
            } else if (!is[0] && is[1]) {
                StageManagement.sqlquery = "SELECT id,name FROM staff where id like \"%" + key + "%\"";
                showGridpane();
            }

        }

    }

    public void pressSearch(KeyEvent keyEvent) throws IOException {
//        System.out.println(keyEvent.getCode().getChar());
        if(keycodMap.map.contains(keyEvent.getCode().getChar()) || keyEvent.getCode()== KeyCode.ENTER || keyEvent.getCode()==KeyCode.BACK_SPACE){
            searchver2();
            if("".equals(textsearch.getText()))
            {
                StageManagement.sqlquery="SELECT id,name FROM staff ";
                showGridpane();
            }
        }
        }


}
