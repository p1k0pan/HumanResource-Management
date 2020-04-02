package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class LoginPage {
    @FXML
    private ComboBox cb;
    @FXML
    private TextField text_name;
    @FXML
    private TextField text_pass;
   @FXML
    private void initialize(){
        cb.getItems().addAll("人事部","管理部");
    }
    public void Log(){
       String name="";
       String pass="";
       String pri="";
       int selectedIndex=0;
       pass =text_pass.getText();
       name=text_name.getText();
       selectedIndex=cb.getSelectionModel().getSelectedIndex();
       System.out.println(pass);
       System.out.println(pri);
//       dao.connectAccount.getAccount();
        System.out.println(name);
    }

}
