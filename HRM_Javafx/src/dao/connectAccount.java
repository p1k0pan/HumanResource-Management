package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connectAccount {
    public static ResultSet getAccount(String ID) {
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/hrm?serverTimezone=UTC";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123321";
        List<String> result = new ArrayList();
        ResultSet rs = null;
        try {
            // 加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("\n\t\t成功以 " + user + " 身份连接到数据库！！！");

            // 2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            // 要执行的SQL语句
            String sql = "select * from account where id=" + ID;
            System.out.println(sql);
            // 3.ResultSet类，用来存放获取的结果集！！
            rs = statement.executeQuery(sql);
//            String pass="";
//            String pri="";
//
//
//            while (rs.next()) {
//                // 获取 ID 这列数据
//                ID = rs.getString("id");
//                // 获取 Name 这列数据
//                pass = rs.getString("password");
//                // 获取 Sex 这列数据
//                pri = rs.getString("priority");
//                // 输出结果
//                result.add(ID);
//                result.add(pass);
//                result.add(pri);
//                System.out.println("\t\t|\t" + ID + "\t" + pass + "\t" + pri + "\t|\t\t");
//            }
//            System.out.println("\t\t-----------------------------------------------------------------");
//            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            // 数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            // 数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("\t\t\t\t\t\t\t\t获取数据库数据完毕！!！");
            return rs;
        }
    }
}
