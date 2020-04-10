package utils;

import store.AlertStage;
import dao.selectSQLCommand;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginConfirm {
    public static void confirm(String name , String password,String pri) throws SQLException, IOException {

//        String id = resultSet.getString("id");
//        String pass=resultSet.getString("password");
//        String priority=resultSet.getString("priority");
        if("".equals(name) || "".equals(password)){
//            System.out.println("请输入账号与密码");
            StageManagement.message="请输入账号与密码";
            AlertStage.onedisplay();
        }


        else{
                ResultSet resultSet = selectSQLCommand.OptionDate("select * from account where id="+name);
                System.out.println(name+password+pri);
            if(resultSet.next()) {

                System.out.println(resultSet.getString("password"));
                System.out.println(resultSet.getString("id"));
                System.out.println(resultSet.getString("priority"));
                    if(resultSet.getString("password")==null)
                    {
                        StageManagement.message="此用户还没有注册";
                        AlertStage.onedisplay();
                    }
                    else if (resultSet.getString("id").equals(name) && resultSet.getString("password").equals(password) && resultSet.getString("priority").equals(pri)) {
//                        System.out.println("登陆成功！");
                        StageManagement.message="登陆成功";
                        AlertStage.onedisplay();
                    } else {
//                        System.out.println("登陆失败");
                        StageManagement.message="登陆失败";
                        AlertStage.onedisplay();
                        FadeTransition fadeTransition = new FadeTransition();
                        fadeTransition.setDelay(Duration.seconds(0.1));
                        //                    fadeTransition.setNode(gr);
                        fadeTransition.setFromValue(0);
                        fadeTransition.setToValue(1);
                        fadeTransition.play();
                    }
                }
                else{
                    StageManagement.message="用户名不存在";
                    AlertStage.onedisplay();
                }


        }
    }

    }



