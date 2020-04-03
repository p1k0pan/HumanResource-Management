package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class selectSQLCommand {


    public static ResultSet OptionDate(String query) throws SQLException {
        ConnectionMysql con =new ConnectionMysql();
        Connection conn=con.ConnectionMysql();
        Statement stmt = (Statement) conn.prepareStatement(query);
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
