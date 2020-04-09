package Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class StatisticCon {
    final static String a1 ="平均工资";
    final static String a2 ="工资总量";
    final static String a3 ="最高工资";
    final static String a4 ="最低工资";
    final static String b1 ="经理";
    final static String b2 ="主管";
    final static String b3 ="正式员工";
    final static String b4 ="临时工";
    final static String b5 ="退休员工";
    @FXML
    private Label head;
    @FXML
    private Tab tab2;
    @FXML
    private Tab tab1;
    @FXML
    private void initialize() throws IOException, SQLException {
        BarChart<String,Number> bar1=showChart1();
        BarChart<String,Number> bar2=showChart2();

        tab1.setContent(bar1);
        tab2.setContent(bar2);



    }
    public double salaryDiji(String key) throws SQLException {
        ResultSet resultSet=dao.selectSQLCommand.OptionDate("SELECT * from salary where id = any(select id from staff where position=\""+key+"\" and title=\"低级\")");
        double total=0;
        while(resultSet.next())
        {
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total+=basic+pos+subsidy+gov;
        }
        return total;
    }
    public double salaryZhongji(String key) throws SQLException {
        ResultSet resultSet=dao.selectSQLCommand.OptionDate("SELECT * from salary where id = any(select id from staff where position=\""+key+"\" and title=\"中级\")");
        double total=0;
        while(resultSet.next())
        {
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total+=basic+pos+subsidy+gov;
        }
        return total;
    }
    public double salaryGaoji(String key) throws SQLException {
        ResultSet resultSet=dao.selectSQLCommand.OptionDate("SELECT * from salary where id = any(select id from staff where position=\""+key+"\" and title=\"高级\")");
        double total=0;
        while(resultSet.next())
        {
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total+=basic+pos+subsidy+gov;
        }
        return total;
    }

    public BarChart<String,Number> showChart1() throws SQLException {

        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                a1, a2, a3, a4)));
        xAxis.tickLabelFontProperty().set(Font.font(18));
        yAxis.tickLabelFontProperty().set(Font.font(18));

        yAxis.setLabel("工资/元");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("公司本月工资分布统计图");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("经理");
        series1.getData().add(new XYChart.Data<>(a1, salaryStat1("经理")));
        series1.getData().add(new XYChart.Data<>(a2, salaryStat4("经理")));
        series1.getData().add(new XYChart.Data<>(a3, salaryStat3("经理")));
        series1.getData().add(new XYChart.Data<>(a4, salaryStat2("经理")));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("主管");
        series2.getData().add(new XYChart.Data<>(a1, salaryStat1("主管")));
        series2.getData().add(new XYChart.Data<>(a2, salaryStat4("主管")));
        series2.getData().add(new XYChart.Data<>(a3, salaryStat3("主管")));
        series2.getData().add(new XYChart.Data<>(a4, salaryStat2("主管")));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("临时工");
        series3.getData().add(new XYChart.Data<>(a1, salaryStat1("临时工")));
        series3.getData().add(new XYChart.Data<>(a2, salaryStat4("临时工")));
        series3.getData().add(new XYChart.Data<>(a3, salaryStat3("临时工")));
        series3.getData().add(new XYChart.Data<>(a4, salaryStat2("临时工")));

        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        series4.setName("正式员工");
        series4.getData().add(new XYChart.Data<>(a1, salaryStat1("正式员工")));
        series4.getData().add(new XYChart.Data<>(a2, salaryStat4("正式员工")));
        series4.getData().add(new XYChart.Data<>(a3, salaryStat3("正式员工")));
        series4.getData().add(new XYChart.Data<>(a4, salaryStat2("正式员工")));

        XYChart.Series<String, Number> series5 = new XYChart.Series<>();
        series5.setName("退休员工");
        series5.getData().add(new XYChart.Data<>(a1, salaryStat1("退休员工")));
        series5.getData().add(new XYChart.Data<>(a2, salaryStat4("退休员工")));
        series5.getData().add(new XYChart.Data<>(a3, salaryStat3("退休员工")));
        series5.getData().add(new XYChart.Data<>(a4, salaryStat2("退休员工")));

        barChart.getData().addAll(series1, series2, series3,series4,series5);
        barChart.setCategoryGap(50.0);
        return barChart;

    }

    public BarChart<String,Number> showChart2() throws SQLException {
        final NumberAxis yAxis = new NumberAxis();
        final CategoryAxis xAxis = new CategoryAxis();

        xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                b1, b2, b3, b4,b5)));
        xAxis.tickLabelFontProperty().set(Font.font(18));
        yAxis.tickLabelFontProperty().set(Font.font(18));

        yAxis.setLabel("工资/元");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("不同职称员工在同一职位工资情况");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("高级");
        series1.getData().add(new XYChart.Data<>(b1, salaryGaoji(b1)));
        series1.getData().add(new XYChart.Data<>(b2, salaryGaoji(b2)));
        series1.getData().add(new XYChart.Data<>(b3, salaryGaoji(b3)));
        series1.getData().add(new XYChart.Data<>(b4, salaryGaoji(b4)));
        series1.getData().add(new XYChart.Data<>(b5, salaryGaoji(b5)));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("中级");
        series1.getData().add(new XYChart.Data<>(b1, salaryZhongji(b1)));
        series1.getData().add(new XYChart.Data<>(b2, salaryZhongji(b2)));
        series1.getData().add(new XYChart.Data<>(b3, salaryZhongji(b3)));
        series1.getData().add(new XYChart.Data<>(b4, salaryZhongji(b4)));
        series1.getData().add(new XYChart.Data<>(b5, salaryZhongji(b5)));

        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        series3.setName("低级");
        series1.getData().add(new XYChart.Data<>(b1, salaryDiji(b1)));
        series1.getData().add(new XYChart.Data<>(b2, salaryDiji(b2)));
        series1.getData().add(new XYChart.Data<>(b3, salaryDiji(b3)));
        series1.getData().add(new XYChart.Data<>(b4, salaryDiji(b4)));
        series1.getData().add(new XYChart.Data<>(b5, salaryDiji(b5)));


        barChart.getData().addAll(series1, series2, series3);
        barChart.setCategoryGap(50.0);
        return barChart;
    }

    //最高工资
    public double salaryStat2(String key) throws SQLException {
        String sql="SELECT * from salary where id = any(select id from staff where position=\""+key+"\")";
        ResultSet resultSet=dao.selectSQLCommand.OptionDate(sql);
        double total=0;
        double highest=0;
        while(resultSet.next())
        {
            total=0;
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total+=basic+pos+subsidy+gov;
            if(highest<total)
                highest=total;
        }
        System.out.println(highest);
        return highest;
    }
    //最低工资
    public double salaryStat3(String key) throws SQLException {
        String sql="SELECT * from salary where id = any(select id from staff where position=\""+key+"\")";
        ResultSet resultSet=dao.selectSQLCommand.OptionDate(sql);
        double total=0;
        double lowest=1000000;
        while(resultSet.next())
        {
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total=basic+pos+subsidy+gov;
            if(lowest>total)
                lowest=total;
        }
        System.out.println(lowest);
        return lowest;
    }
    //平均工资
    public double salaryStat1(String key) throws SQLException {
        String sql="SELECT * from salary where id = any(select id from staff where position=\""+key+"\")";
        ResultSet resultSet=dao.selectSQLCommand.OptionDate(sql);
        double total=0;
        int n=0;
        while(resultSet.next())
        {
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total+=basic+pos+subsidy+gov;
            n++;
        }
        System.out.println(total/n);
        return total/n;
    }
    //工资总量
    public double salaryStat4(String key) throws SQLException {
        String sql="SELECT * from salary where id = any(select id from staff where position=\""+key+"\")";
        ResultSet resultSet=dao.selectSQLCommand.OptionDate(sql);
        double total=0;
        while(resultSet.next())
        {
            double basic = resultSet.getDouble("basic");
            double pos = resultSet.getDouble("pos_allowance");
            double subsidy = resultSet.getDouble("subsidy");
            double gov = resultSet.getDouble("gov_allowance");
            total+=basic+pos+subsidy+gov;
        }
        System.out.println(total);
        return total;
    }
}
