package utils;

import Stage.AlertStage;
import dao.selectSQLCommand;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class registConfirm {
    public static void confirm(String name,String pass1,String pass2) throws IOException, SQLException {
        //先判断是否有空
        if("".equals(name) || "".equals(pass1) || "".equals(pass2)){
//            System.out.println("请输入账号与密码");
            StageManagement.message="请输入账号与密码";
            AlertStage.onedisplay();
        }
        else {
            //不为空情况下判断两次密码是否一致
            if (!pass1.equals(pass2)) {
                StageManagement.message = "两次密码输入不一致";
                AlertStage.onedisplay();
            }
            else {
                    ResultSet rs = selectSQLCommand.OptionDate("select * from account where id=" + name);
                    //判断此id是否需要注册
                    if (rs.next()) {
                        if (rs.getString("password") == null) {
                            //id可以注册
                            String sql="update account set password="+pass1+" where id ="+name;
                            int len=dao.updateSQLCommand.OptionDate(sql);
                            if(len >0) {
                                StageManagement.message = "注册成功";
                                AlertStage.onedisplay();
                            }
                            else {
                                StageManagement.message = "失败";
                                AlertStage.onedisplay();
                            }

                        }
                        else {
                            //id已注册过
                            StageManagement.message = "已注册请直接登陆";
                            AlertStage.onedisplay();
                        }
                    }
                    else {
                        StageManagement.message = "id不存在";
                        AlertStage.onedisplay();
                    }
                }
        }
        }
    }

