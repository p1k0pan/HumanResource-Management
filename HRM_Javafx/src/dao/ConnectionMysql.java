package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMysql {
    public Connection ConnectionMysql() {
        String url = "jdbc:mysql://localhost:3306/hrm?serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pswd = "123321";

        Connection con = null;
        try {
            System.out.println(Class.forName(driver));
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not Found!");
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, user, pswd);
            System.out.println("Succeed!");

//            String query = "select * from 账户;";
////            Statement stmt = con.createStatement();
//            PreparedStatement stmt=con.prepareStatement(query);
//
////            ResultSet rs = stmt.executeQuery(query);
//            ResultSet rs=stmt.executeQuery();
//
//            while (rs.next()){
//                System.out.println(rs.getString(1) + "\t"
//                        + rs.getString(2) + "\t"
//                        + rs.getString(3) + "\t");
//            }
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed Connection!");
        }
        finally {
            System.out.println("\t\t\t\t\t\t\t\t获取数据库数据完毕！!！");
            return con;
        }
        }
}

