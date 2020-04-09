package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;

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

    public ObservableList<User> list = FXCollections.observableArrayList();
    @FXML
    private void initialize() throws IOException {

        for(int i=0;i<20;i++) {
            User user = new User();//构建值对象
            user.setName("this is "+i+"小六");
            user.setPassword("this is "+i+"123");
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

}
