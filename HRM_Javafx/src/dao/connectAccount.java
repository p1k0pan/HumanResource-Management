package dao;

import java.sql.*;

public class connectAccount {
    public static void getAccount(String name, String pass,String pri){
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/hrm?serverTimezone=UTC";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123321";
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
            String sql = "select * from account";
            // 3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);

            int ID = 0;


            while (rs.next()) {
                // 获取 ID 这列数据
                ID = rs.getInt("id");
                // 获取 Name 这列数据
                pass = rs.getString("password");
                // 获取 Sex 这列数据
                pri = rs.getString("priority");
                // 输出结果
                System.out.println("\t\t|\t" + ID + "\t" + pass + "\t" + pri + "\t|\t\t");
            }
            System.out.println("\t\t-----------------------------------------------------------------");
            rs.close();
            con.close();
        }
        catch (ClassNotFoundException e) {
            // 数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
        catch (SQLException e) {
            // 数据库连接失败异常处理
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        finally {
            System.out.println("\t\t\t\t\t\t\t\t获取数据库数据完毕！!！");
        }
    }
}
