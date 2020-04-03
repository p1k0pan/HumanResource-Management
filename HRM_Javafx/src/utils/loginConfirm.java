package utils;

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
            Stage.StageManagement.message="请输入账号与密码";
            Stage.alertStage.display();
        }


        else{
                ResultSet resultSet = selectSQLCommand.OptionDate("select * from account where id="+name);
                if(resultSet.next()) {
                    if(resultSet.getString("password")==null)
                    {
                        Stage.StageManagement.message="此用户还没有注册";
                        Stage.alertStage.display();
                    }
                    else if (resultSet.getString("id").equals(name) && resultSet.getString("password").equals(password) && resultSet.getString("priority").equals(pri)) {
//                        System.out.println("登陆成功！");
                        Stage.StageManagement.message="登陆成功";
                        Stage.alertStage.display();
                    } else {
//                        System.out.println("登陆失败");
                        Stage.StageManagement.message="登陆失败";
                        Stage.alertStage.display();
                        FadeTransition fadeTransition = new FadeTransition();
                        fadeTransition.setDelay(Duration.seconds(0.1));
                        //                    fadeTransition.setNode(gr);
                        fadeTransition.setFromValue(0);
                        fadeTransition.setToValue(1);
                        fadeTransition.play();
                    }
                }
                else{
                    Stage.StageManagement.message="用户名不存在";
                    Stage.alertStage.display();
                }


        }
    }

    }



