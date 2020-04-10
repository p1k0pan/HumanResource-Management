package Controller;

import store.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.StageManagement;

import java.io.IOException;
import java.sql.SQLException;

public class conLoginPage {

    public String nameLog="";
    public String passLog="";
    public String priLog="人事部";
    @FXML
    private TextField text_name;
    @FXML
    private TextField text_pass;
    @FXML
    private RadioButton renshi;
    @FXML
    private RadioButton guanli;

    @FXML
    private Button logbtn;
    @FXML
    private Button clearbtn;
    @FXML
    private Button sellogin;
    @FXML
    private Button selregist;
    @FXML
    private void initialize() {
        ToggleGroup group = new ToggleGroup();
        renshi.setToggleGroup(group);
        guanli.setToggleGroup(group);
        renshi.setSelected(true);
        try {
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                @Override
                public void changed(ObservableValue<? extends Toggle> changed, Toggle oldVal, Toggle newVal) {
                    RadioButton temp_rb = (RadioButton) newVal;
                    priLog = temp_rb.getText();
                    System.out.println(priLog);
                }
            });
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        Image btnImg = new Image("/img/log.png");
        ImageView imageView = new ImageView(btnImg);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        //给按钮设置图标
        logbtn.setGraphic(imageView);
    }
    public void Log() throws SQLException, IOException {
        passLog = text_pass.getText();
        nameLog = text_name.getText();
        StageManagement.headid=nameLog;
        StageManagement.headpart=priLog;
        StageManagement.CONTROLLER.put("log",this);
        utils.loginConfirm.confirm(nameLog, passLog, priLog);

    }

    public void Clear(){
        text_name.clear();
        text_pass.clear();
        text_name.requestFocus();
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

    public void goToRegist(ActionEvent actionEvent) throws IOException {
        VBox root=new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
        VBox down= FXMLLoader.load(getClass().getResource("/View/registpage.fxml"));
        root.getChildren().addAll(top,down);
        Stage stage= StageManagement.STAGE.get("home");
        stage.setScene(new Scene(root));
    }

    public void goToRegist() throws IOException {
        VBox root=new VBox();
        root.setId("root");
        root.getStylesheets().add(Main.class.getResource("/css/closing.css").toString());
        VBox top = FXMLLoader.load(getClass().getResource("/View/headModel.fxml"));
        VBox down= FXMLLoader.load(getClass().getResource("/View/registpage.fxml"));
        root.getChildren().addAll(top,down);
        Stage stage= StageManagement.STAGE.get("home");
        stage.setScene(new Scene(root));
    }
}


