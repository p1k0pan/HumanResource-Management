package Controller;

import dao.selectSQLCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.StageManagement;
import utils.singleSalary;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class conSalaryFormat {
    @FXML
    private Label name;
    @FXML
    private VBox vbox;
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
        name.setText(StageManagement.singlename + "的本月工资单");
        vbox.setAlignment(Pos.CENTER);
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
//        for(int i=0;i<20;i++) {

//            showTable("no."+i, "inf", 0, 0, 0, 0, 0, 0);
//        }
        tableView.setItems(data);
    }


    public void Print(ActionEvent actionEvent) {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if(job != null) {
            Stage stage=(Stage)vbox.getScene().getWindow();
            job.showPrintDialog(stage);
            job.printPage(vbox);
            job.endJob();
        }
    }
    public void savePic(int i){
        WritableImage image = vbox.snapshot(new SnapshotParameters(), null);
        try {
            String url = "salary"+i+".png";
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(url));
            System.out.println("保存成功");
        } catch (IOException ex) {
            System.out.println("保存失败:" + ex.getMessage());
        }
    }

    public void export(ActionEvent actionEvent) throws IOException {
        ObservableList<singleSalary> dataBackup = FXCollections.observableArrayList(data);
        tableView.setItems(dataBackup);
        int e=0;
        savePic(e);
        System.out.println("--------");
        int i=0;
        while(!dataBackup.isEmpty()){
            if(i<=9) {
                dataBackup.remove(0);
                i++;
            }
            else
            {
                savePic(++e);
                System.out.println("--------");
                i=0;
            }
        }
        StageManagement.message="导出成功";
        store.AlertStage.onedisplay();
        tableView.setItems(data);

    }
}
