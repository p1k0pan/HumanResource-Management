package Controller;

import dao.selectSQLCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.StageManagement;
import utils.singleSalary;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conSalaryFormat {
    @FXML
    private Label name;
    @FXML
    private TableColumn id_col;
    @FXML
    private TableColumn name_col;
    @FXML
    private TableColumn basic_col;
    @FXML
    private TableColumn pos_col ;
    @FXML
    private TableColumn other_col;
    @FXML
    private TableColumn gov_col ;
    @FXML
    private TableColumn self_col;
    @FXML
    private TableColumn total_col;
    @FXML
   private TableView tableView;
    public ObservableList<singleSalary> data = FXCollections.observableArrayList();
    public void showTable(String id,String name,double basic,double pos,double subsidy,double gov,double self,double total)
    {
        singleSalary one=new singleSalary(id,name,basic,subsidy,gov,pos,self,total);
        data.add(one);
        id_col.setCellValueFactory(new PropertyValueFactory("id"));
        name_col.setCellValueFactory(new PropertyValueFactory("name"));
        basic_col.setCellValueFactory(new PropertyValueFactory("basic"));
        pos_col.setCellValueFactory(new PropertyValueFactory("pos"));
        other_col.setCellValueFactory(new PropertyValueFactory("subsidy"));
        gov_col.setCellValueFactory(new PropertyValueFactory("gov"));
        self_col.setCellValueFactory(new PropertyValueFactory("self"));
        total_col.setCellValueFactory(new PropertyValueFactory("total"));

    }

    @FXML
    private void initialize() throws IOException, SQLException {
        name.setText(StageManagement.singlename+"的本月工资单");
        ResultSet resultSet = selectSQLCommand.OptionDate(StageManagement.sqlquery);
        while (resultSet.next()) {
            double basic = resultSet.getInt("basic");
            double pos = resultSet.getInt("pos_allowance");
            double subsidy = resultSet.getInt("subsidy");
            double gov = resultSet.getInt("gov_allowance");
            double fourplus = basic + pos + subsidy + gov;
            double self = fourplus * 0.25;
            double total = fourplus * 0.75;
//                            id_col.setCellValueFactory(
//                                new PropertyValueFactory<>("just id"));
            String id=resultSet.getString("id");
            String name="";
            ResultSet rs=selectSQLCommand.OptionDate("select name from staff where id="+id);
            if(rs.next())
            {

                name=rs.getString("name");
            }
            showTable(id, name, basic, pos, subsidy, gov, self, total);
        }
        tableView.setItems(data);
    }



}
