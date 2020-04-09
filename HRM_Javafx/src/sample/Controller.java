package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class Controller {


    @FXML
    private TableView table;
    @FXML
    private TableColumn c1;
    @FXML
    private TableColumn c2;

    @FXML
    private void initialize() throws IOException {

        ObservableList<User> list = FXCollections.observableArrayList();
        User user = new User();//构建值对象
        user.setName("小六");
        user.setPassword("123");
        User user1 = new User();//构建值对象
        user1.setName("东方色");
        user1.setPassword("92");


        c1.setCellValueFactory(new PropertyValueFactory("name"));//映射
        c2.setCellValueFactory(new PropertyValueFactory("password"));

        list.addAll(user,user1);        //list添加值对象
        table.setItems(list); //tableview添加list}


    }
}
