package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class HomeCon {
    @FXML
    private Label title;
    @FXML
    private GridPane gridpane;
    @FXML
    private void initialize() throws IOException, SQLException {
        BarChart<String,Number> Bar= showBarChart();
        gridpane.add(Bar,0,0);
        PieChart pie=showPieChart();
        gridpane.add(pie,1,0);
        Bar.getStylesheets().add("css/bar.css");
        gridpane.setHalignment(Bar, HPos.RIGHT);
        gridpane.setHalignment(pie, HPos.RIGHT);



    }
    public BarChart<String,Number> showBarChart() throws SQLException {

        ResultSet resultSetgaoji=dao.selectSQLCommand.OptionDate("select * from staff where title=\"高级\"");
        int rowgaoji=0;
        while(resultSetgaoji.next())
        {
            rowgaoji++;
        }

        ResultSet resultSetzhongji=dao.selectSQLCommand.OptionDate("select * from staff where title =\"中级\"");
        int rowzhongji=0;
        while(resultSetzhongji.next())
        {
            rowzhongji++;
        }
        ResultSet resultSetdiji=dao.selectSQLCommand.OptionDate("select * from staff where title=\"低级\"");
        int rowdiji=0;
        while(resultSetdiji.next())
        {
            rowdiji++;
        }
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "高级员工", "中级员工", "低级员工" )));
        xAxis.tickLabelFontProperty().set(Font.font(18));
        yAxis.tickLabelFontProperty().set(Font.font(18));

        yAxis.setLabel("人数/人");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("公司本月工资分布统计图");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("员工职称");
        series1.getData().add(new XYChart.Data<>("高级员工", rowgaoji));
        series1.getData().add(new XYChart.Data<>("中级员工", rowzhongji));
        series1.getData().add(new XYChart.Data<>("低级员工",rowdiji));


        barChart.getData().addAll(series1);
        barChart.setCategoryGap(50.0);
        return barChart;

    }

    public PieChart showPieChart() throws SQLException {
        ResultSet resultSetgaoji=dao.selectSQLCommand.OptionDate("select * from staff where title=\"高级\"");
        int rowgaoji=0;
        while(resultSetgaoji.next())
        {
            rowgaoji++;
        }

        ResultSet resultSetzhongji=dao.selectSQLCommand.OptionDate("select * from staff where title =\"中级\"");
        int rowzhongji=0;
        while(resultSetzhongji.next())
        {
            rowzhongji++;
        }
        ResultSet resultSetdiji=dao.selectSQLCommand.OptionDate("select * from staff where title=\"低级\"");
        int rowdiji=0;
        while(resultSetdiji.next())
        {
            rowdiji++;
        }
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("高级员工", rowgaoji),
                        new PieChart.Data("中级员工", rowzhongji),
                        new PieChart.Data("低级员工", rowdiji));

        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("员工职称比例");
        return chart;

    }

}
