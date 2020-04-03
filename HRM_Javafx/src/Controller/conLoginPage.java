package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class conLoginPage {

    public ToggleGroup group= new ToggleGroup();
    public String name="";
    public String pass="";
    public String pri="人事部";
    @FXML
    private TextField text_name;
    @FXML
    private TextField text_pass;
    @FXML
    private RadioButton renshi;
    @FXML
    private RadioButton guanli;

    @FXML
    private void initialize(){
        renshi.setToggleGroup(group);
        guanli.setToggleGroup(group);
        renshi.setSelected(true);
        group.selectedToggleProperty().addListener(
                new ChangeListener<Toggle>() {
                    public void changed(
                            ObservableValue<? extends Toggle> ov,
                            Toggle old_toggle, Toggle new_toggle) {
                        if (group.getSelectedToggle() != null) {
                            if (renshi.isSelected())
                            {
                                pri="人事部";
                            }
                            else
                            {
                                pri="管理部";
                            }
                        }
                    }
                });

//       text_name.focusedProperty().addListener(new ChangeListener<Boolean>(){
//            public void changed(ObservableValue<? extends Boolean> boolVal,
//                                Boolean oldVal, Boolean newVal) {
//                if (newVal == true)
//                {
//                    ResultSet rs = null;
//                    try {
//                        rs = dao.MySqlCommand.OptionDate(name);
//                        if(!rs.next()){
//                            userconfirm.setText("id不存在");
//                        }
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        } );


//        userconfirm.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                tip1.setText("\""+t1+"\"");
//                if(t1.length()>6){
//                    t_name.setText(s);
//                }
//            }
//        });
    }
    public void Log() throws SQLException, IOException {
       pass =text_pass.getText();
       name=text_name.getText();

       utils.loginConfirm.confirm(name,pass,pri);
//           System.out.println(rs.getString("id"));
//           System.out.println(rs.getString("password"));
//           System.out.println(rs.getString("priority"));
                        }
//        List<String> result=dao.connectAccount.getAccount(name);
//        Iterator<String> it= result.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next());
//        }




}


