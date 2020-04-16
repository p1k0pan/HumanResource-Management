package Controller;

import dao.selectSQLCommand;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import store.AlertStage;
import utils.StageManagement;
import utils.keycodMap;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conMainSalary {
    @FXML
    private Label l1;
    @FXML
    private AnchorPane an;
    @FXML
    private TextField textsearch;
    @FXML
    private ComboBox cbb;
    @FXML
    private HBox hbox;
    @FXML
    private Button backbtn;
    @FXML
    private Button search;
    @FXML
    private Button generad;
    @FXML
    private Button reset;
    @FXML
    private VBox vbox;

    @FXML
    private GridPane gridpane;
    String choosen ="";
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
    private void initialize() throws IOException, SQLException {
        vbox.setAlignment(Pos.CENTER);

        Image btnImg3 = new Image("/img/搜索.png");
        ImageView imageView3 = new ImageView(btnImg3);
        imageView3.setFitHeight(20);
        imageView3.setFitWidth(20);
        search.setGraphic(imageView3);
        Image btnImg2 = new Image("/img/生成.png");
        ImageView imageView2 = new ImageView(btnImg2);
        imageView2.setFitHeight(20);
        imageView2.setFitWidth(20);
        generad.setGraphic(imageView2);
        Image btnImg = new Image("/img/重置.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        reset.setGraphic(imageView);
        String sql="select id,name from staff";
        showList(sql);
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
                choosen =(String)newValue;
                System.out.println(choosen);
            }
        });

    }


    public void clickSearch(ActionEvent actionEvent) throws IOException, SQLException {

        searchver1();
    }
    public void refresh(ActionEvent actionEvent) throws IOException, SQLException {
        String sql="SELECT id,name FROM staff ";
        showList(sql);
        textsearch.setText("");
        cbb.getSelectionModel().clearSelection();
    }

    public void backToSearch(ActionEvent actionEvent) {
        hbox.getChildren().remove(2);
        hbox.getChildren().add(gridpane);
    }

    public void generad(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.APPLICATION_MODAL);
        StageManagement.singlename="全体员工";
        StageManagement.sqlquery="select * from salary";
        Parent root1 = FXMLLoader.load(getClass().getResource("/View/salaryFormat.fxml"));
        stage.setScene(new Scene(root1,822,600));
        stage.setTitle("工资表");
        stage.getIcons().add(new Image("/img/icon2.png"));
        stage.show();
    }
    public void showList(String sql) throws SQLException {
        Image image=new Image("/img/retry.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        backbtn.setGraphic(imageView);
        ObservableList<String> strList = FXCollections.observableArrayList();
        ResultSet rs= selectSQLCommand.OptionDate(sql);
        while(rs.next())
        {
            strList.add("\t\t"+rs.getString("id")+"          "+rs.getString("name"));
        }

        ListView<String> listView = new ListView<>(strList);
        an.getChildren().add(listView);
        AnchorPane.setTopAnchor(listView,50.0);
        AnchorPane.setBottomAnchor(listView,0.0);
        AnchorPane.setRightAnchor(listView,150.0);listView.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) ->{

                    hbox.getChildren().remove(2);
                    String id=newValue.substring(2,6);
                    String name=newValue.substring(16);
                    StageManagement.singleid=id;
                    StageManagement.singlename=name;
                    AnchorPane sf= null;
                    try {
                        StageManagement.sqlquery="select * from salary where id=" + StageManagement.singleid;
                        sf = FXMLLoader.load(getClass().getResource("/View/salaryFormat.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    hbox.getChildren().add(sf);

                });
    }

    public void pressSearch(KeyEvent keyEvent) throws IOException, SQLException {
//        if(keyEvent.getCode()== KeyCode.ENTER)
//            search();
//        for (KeyCode c : KeyCode.values())
//            System.out.println(c);
//        System.out.println(keyEvent.getCode().getChar());
        if(keycodMap.map.contains(keyEvent.getCode().getChar()) || keyEvent.getCode()== KeyCode.ENTER || keyEvent.getCode()==KeyCode.BACK_SPACE){
            searchver2();
            if("".equals(textsearch.getText()))
            {
                String sql="SELECT id,name FROM staff ";
                showList(sql);
            }
        }

//        System.out.println(keyEvent.getCode().getChar());
    }
    public void searchver1() throws IOException, SQLException {

        if("".equals(textsearch.getText()) && "".equals(choosen) )
        {
            StageManagement.message="你没有输入查询内容";
            AlertStage.onedisplay();
        }
        else if("".equals(textsearch.getText()) && !"".equals(choosen) )
        {
            String sql="SELECT id,name FROM staff where position= \""+choosen+"\"";
            System.out.println(sql);
            showList(sql);
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
                String sql = "SELECT id,name FROM staff where name like \"%" + key + "%\"";
                showList(sql);
            }
            else if(!is[0] && is[1]){
                String sql="SELECT id,name FROM staff where id like \"%"+key+"%\"";
                showList(sql);
            }
        }

        else if(!"".equals(textsearch.getText()) && !"".equals(choosen) ){
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
                String sql = "SELECT id,name FROM staff where name like \"%" + key + "%\"";
                showList(sql);
            }
            else if(!is[0] && is[1]){
                String sql="SELECT * FROM salary where id like \"%"+key+"%\"";
                showList(sql);
            }

        }
    }

    public void searchver2() throws IOException, SQLException {

        if ("".equals(textsearch.getText()) && !"".equals(choosen)) {
            String sql = "SELECT id,name FROM staff where position= \"" + choosen + "\"";
            System.out.println(sql);
            showList(sql);
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
                String sql = "SELECT id,name FROM staff where name like \"%" + key + "%\"";
                showList(sql);
            } else if (!is[0] && is[1]) {
                String sql = "SELECT id,name FROM staff where id like \"%" + key + "%\"";
                showList(sql);
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
                String sql = "SELECT id,name FROM staff where name like \"%" + key + "%\"";
                showList(sql);
            } else if (!is[0] && is[1]) {
                String sql = "SELECT * FROM salary where id like \"%" + key + "%\"";
                showList(sql);
            }

        }
    }
}
