package sample;


import com.mysql.jdbc.Statement;

import java.sql.*;

import java.sql.ResultSet;

public class MySqlCommand {


    public  ResultSet OptionDate(String juery) throws SQLException {
        ConnectionMysql con =new ConnectionMysql();
        Connection conn=con.ConnectionMysql();
        String query = "select * from 账户;";
        Statement stmt = (Statement) conn.prepareStatement(juery);
//            PreparedStatement stmt=con.prepareStatement(query);

        ResultSet rs = stmt.executeQuery(query);
//            ResultSet rs=stmt.executeQuery();
//        conn.close();
        return rs;
//        while (rs.next()){
//            System.out.println(rs.getString(1) + "\t"
//                    + rs.getString(2) + "\t"
//                    + rs.getString(3) + "\t");
//            }


    }
}
