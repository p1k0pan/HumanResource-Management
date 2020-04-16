package sample;

import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.SpreadsheetInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import utils.Datetransform;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Controller {


    @FXML
    private TableView table;
    @FXML
    private TableColumn c1;
    @FXML
    private TableColumn c2;
    @FXML
    private VBox vbox;
    @FXML
    private DatePicker dp;
    @FXML
    private ComboBox position;

    public ObservableList<User> list = FXCollections.observableArrayList();
    @FXML
    private void initialize() throws IOException {
        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");
        position.getItems().addAll( "正式员工",
                "经理",
                "主管",
                "退休员工",
                "临时工");
        for(int i=0;i<20;i++) {
            User user = new User();//构建值对象
            user.setName(i+"小六");
            user.setPassword(i+"123");
            list.add(user);        //list添加值对象
        }


        c1.setCellValueFactory(new PropertyValueFactory("name"));//映射
        c2.setCellValueFactory(new PropertyValueFactory("password"));

        table.setItems(list); //tableview添加list}


    }

    public void export(ActionEvent actionEvent) {
//        WritableImage image = table.snapshot(new SnapshotParameters(), null);
//        try {
//            String url = "C:\\Users\\64575\\Desktop\\test.png";
//            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(url));
//            System.out.println("保存成功");
//        } catch (IOException ex) {
//            System.out.println("保存失败:" + ex.getMessage());
//        }
        savePic();
    }
    public void export(int i)
    {
        WritableImage image = table.snapshot(new SnapshotParameters(), null);
        try {
            String url = "C:\\Users\\64575\\Desktop\\test"+i+".png";
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", new File(url));
            System.out.println("保存成功");
        } catch (IOException ex) {
            System.out.println("保存失败:" + ex.getMessage());
        }
    }

    public void savePic()
    {
        System.out.println(list.size());
        int e=0;
        export(e );

        System.out.println("--------");
        int i=0;
        while(!list.isEmpty()){
            if(i<=6) {
                list.remove(0);
                i++;
            }
            else
            {
                export(++e);
                System.out.println("--------");
                i=0;
            }
        }

    }

    public void show(ActionEvent actionEvent) {
//        dp.setValue(null);
//        position.setValue("");
        System.out.println(Datetransform.null2Blank(dp));
//        System.out.println(position.getValue());

    }

    public void excel(ActionEvent actionEvent) throws IOException {

            ExcelFile file = new ExcelFile();
            ExcelWorksheet worksheet = file.addWorksheet("sheet");

            worksheet.getCell(0, 0).setValue("c1");
        worksheet.getCell(0, 1).setValue("c2");
        for (int row = 0; row < table.getItems().size(); row++) {
                User cells = (User) table.getItems().get(row);
                for (int column = 0; column < 2; column++) {
                    if(column==0){
                        worksheet.getCell(row+1, column).setValue(cells.getName());
                    }
                    else
                    {
                        worksheet.getCell(row+1, column).setValue(cells.getPassword());
                    }

//                    if (cells.get(column) != null)
//                        worksheet.getCell(row, column).setValue(cells.get(column).toString());
                }
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx"),
                    new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls"),
                    new FileChooser.ExtensionFilter("ODS files (*.ods)", "*.ods"),
                    new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("HTML files (*.html)", "*.html")
            );
            fileChooser.setTitle("选择保存路径");
            fileChooser.setInitialFileName("Untitle.xlsx");
            File saveFile = fileChooser.showSaveDialog(table.getScene().getWindow());

            file.save(saveFile.getAbsolutePath());
    }
}
