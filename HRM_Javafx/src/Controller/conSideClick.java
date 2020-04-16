package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import utils.StageManagement;

import java.io.IOException;

public class conSideClick {
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    public void showMan(MouseEvent mouseEvent) throws IOException {
        conLoad cl=(conLoad) StageManagement.CONTROLLER.get("centerboard");
        VBox c_right= FXMLLoader.load(getClass().getResource("/View/manManage.fxml"));
        cl.center.setCenter(c_right);
        l2.setTextFill(Paint.valueOf("white"));
        l1.setTextFill(Paint.valueOf("#c9c9c9"));
        l3.setTextFill(Paint.valueOf("#c9c9c9"));
        l4.setTextFill(Paint.valueOf("#c9c9c9"));
        l2.setStyle("-fx-background-color: #003da0;-fx-font-weight: bold");
        l1.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l3.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l4.setStyle("-fx-background-color: null;-fx-font-weight: regular");
    }

    public void showSalary(MouseEvent mouseEvent) throws IOException {
        conLoad cl=(conLoad) StageManagement.CONTROLLER.get("centerboard");
        VBox c_right= FXMLLoader.load(getClass().getResource("/View/mainSalary.fxml"));
        cl.center.setCenter(c_right);
        l3.setTextFill(Paint.valueOf("white"));
        l2.setTextFill(Paint.valueOf("#c9c9c9"));
        l1.setTextFill(Paint.valueOf("#c9c9c9"));
        l4.setTextFill(Paint.valueOf("#c9c9c9"));
        l3.setStyle("-fx-background-color: #003da0;-fx-font-weight: bold");
        l2.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l1.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l4.setStyle("-fx-background-color: null;-fx-font-weight: regular");
    }

    public void showStatistic(MouseEvent mouseEvent) throws IOException {
        conLoad cl=(conLoad) StageManagement.CONTROLLER.get("centerboard");
        VBox c_right= FXMLLoader.load(getClass().getResource("/View/statistic.fxml"));
        cl.center.setCenter(c_right);
        l4.setTextFill(Paint.valueOf("white"));
        l2.setTextFill(Paint.valueOf("#c9c9c9"));
        l3.setTextFill(Paint.valueOf("#c9c9c9"));
        l1.setTextFill(Paint.valueOf("#c9c9c9"));
        l4.setStyle("-fx-background-color: #003da0;-fx-font-weight: bold");
        l2.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l3.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l1.setStyle("-fx-background-color: null;-fx-font-weight: regular");
    }

    public void showHome(MouseEvent mouseEvent) throws IOException {
        conLoad cl=(conLoad) StageManagement.CONTROLLER.get("centerboard");
        AnchorPane c_right= FXMLLoader.load(getClass().getResource("/View/home.fxml"));
        cl.center.setCenter(c_right);
        l1.setTextFill(Paint.valueOf("white"));
        l2.setTextFill(Paint.valueOf("#c9c9c9"));
        l3.setTextFill(Paint.valueOf("#c9c9c9"));
        l4.setTextFill(Paint.valueOf("#c9c9c9"));
        l1.setStyle("-fx-background-color: #003da0;-fx-font-weight: bold");
        l2.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l3.setStyle("-fx-background-color: null;-fx-font-weight: regular");
        l4.setStyle("-fx-background-color: null;-fx-font-weight: regular");
    }

    public void getGlow1(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(10);
        l1.setEffect(glow);
    }

    public void shutGlow1(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.3);
        l1.setEffect(glow);
    }

    public void getGlow2(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(10);
        l2.setEffect(glow);
    }

    public void shutGlow2(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.3);
        l2.setEffect(glow);
    }

    public void getGlow3(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(10);
        l3.setEffect(glow);
    }

    public void shutGlow3(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.3);
        l3.setEffect(glow);
    }

    public void showGlow4(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(10);
        l4.setEffect(glow);
    }

    public void shutGlow4(MouseEvent mouseEvent) {
        Glow glow = new Glow();
        glow.setLevel(0.3);
        l4.setEffect(glow);
    }
}
