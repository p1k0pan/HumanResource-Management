package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import utils.StageManagement;

import java.io.IOException;

public class conSideClick {
    public void showMan(MouseEvent mouseEvent) throws IOException {
        conLoad cl=(conLoad) StageManagement.CONTROLLER.get("centerboard");
        VBox c_right= FXMLLoader.load(getClass().getResource("/View/manManage.fxml"));
        cl.center.setCenter(c_right);
    }

    public void showSalary(MouseEvent mouseEvent) throws IOException {
        conLoad cl=(conLoad) StageManagement.CONTROLLER.get("centerboard");
        VBox c_right= FXMLLoader.load(getClass().getResource("/View/mainSalary.fxml"));
        cl.center.setCenter(c_right);
    }
}
