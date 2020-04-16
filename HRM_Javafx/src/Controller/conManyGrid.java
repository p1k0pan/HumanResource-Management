package Controller;

import dao.selectSQLCommand;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import utils.StageManagement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class conManyGrid {
    @FXML
    private GridPane mainGrid;
    @FXML
    private void initialize() throws IOException, SQLException {

//        System.out.println(mainGrid.getPrefHeight());
//        System.out.println(mainGrid.getPrefWidth());
        ResultSet rs= selectSQLCommand.OptionDate(StageManagement.sqlquery);
        int total=0;
        int num=0;
        List<String> totalname=new ArrayList();
        List<String> totalid=new ArrayList();
        while (rs.next())
        {
            total=rs.getRow();
            totalname.add(rs.getString("name"));
            totalid.add(rs.getString("id"));
        }
//        total=20;
        int row=0;
        while(num<total){
            for(int col=0;col<7;col++)
            {
                if(num+1>total)
                    break;
                VBox name = FXMLLoader.load(getClass().getResource("/View/oneSingle.fxml"));
//                name.setPrefWidth(160);
//                name.setPrefHeight(150);
                conOneSingle cos=(conOneSingle) StageManagement.CONTROLLER.get("onesingle");
                cos.name.setText(totalname.get(num));
                cos.id.setText(totalid.get(num));

//                StageManagement.SINGLE.put(totalid.get(num),cos);

                mainGrid.add(name, col, row);
                mainGrid.setPadding(new Insets(20,0,0,0));

                num++;
//                System.out.println(name.getPrefHeight());
//                System.out.println(name.getPrefWidth());
            }
            row+=1;
        }
//        System.out.println("girdheight="+mainGrid.getPrefHeight());
//        System.out.println("gridwidth="+mainGrid.getPrefWidth());

    }
}
