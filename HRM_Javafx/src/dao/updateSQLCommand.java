package dao;


import java.sql.*;

public class updateSQLCommand {


    public static int OptionDate(String query) throws SQLException {
        ConnectionMysql con =new ConnectionMysql();
        Connection conn=con.ConnectionMysql();
//            PreparedStatement stmt=con.prepareStatement(query);
        PreparedStatement pst = conn.prepareStatement(query);
        int len =pst.executeUpdate();
        return len;
//            ResultSet rs=stmt.executeQuery();
//        conn.close();
//        while (rs.next()){
//            System.out.println(rs.getString(1) + "\t"
//                    + rs.getString(2) + "\t"
//                    + rs.getString(3) + "\t");
//            }


    }
}
