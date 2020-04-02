package sample;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        MySqlCommand mySqlCommand =new MySqlCommand();
        ResultSet resultSet= mySqlCommand.OptionDate("select * from 账户");
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
//        }


        GridPane gr =new GridPane();
        gr.setStyle("-fx-background-color:#BFEFFF");
        gr.setAlignment(Pos.CENTER);

        Label name =new Label("账号:");
        name.setFont(Font.font(20));
        Label password = new Label("密码:");
        password.setFont(Font.font(20));

        TextField t_name=new TextField();
        t_name.setTooltip(new Tooltip("请输入账号"));
        t_name.setPromptText("请输入小于7个数字");
        t_name.setFocusTraversable(true);
        Label tip1=new Label();
        tip1.setText("\' \'");



        PasswordField p_password =new PasswordField();
        p_password.setTooltip(new Tooltip("请输入密码"));
        p_password.setPromptText("请输入密码");
        p_password.setFocusTraversable(true);//焦点设置
        Label tip2=new Label();
        tip2.setText("\' \'");




        RadioButton power0 =new RadioButton("管理部人员");
        RadioButton power1 =new RadioButton("人事部人员");
        final ToggleGroup group =new ToggleGroup();
        power0.setToggleGroup(group);
        power1.setToggleGroup(group);


//        power0.setSelected(true); //设置选中
        group.getSelectedToggle();
//        power0.requestFocus();//设置焦点

        Button login=new Button("登录");
        login.setFont(Font.font(20));
        Button clear =new Button("清除");
        clear.setFont(Font.font(20));
        final String[] selected = {""};
        t_name.setUserData("0");
        p_password.setUserData("0");



        gr.add(name,0,0);
        gr.add(password,0,1);
        gr.add(t_name,1,0);
        gr.add(p_password,1,1);
        gr.add(tip1,2,0);
        gr.add(tip2,2,1);
        gr.add(power0,0,2);
        gr.add(power1,1,2);

        gr.add(clear,0,3);
        gr.add(login,1,3);


        gr.setMargin(t_name,new Insets(0,0,0,-40));
        gr.setMargin(p_password,new Insets(0,0,0,-40));
        gr.setMargin(power0,new Insets(15,0,0,20));
        gr.setMargin(power1,new Insets(15,0,0,20));

        gr.setMargin(tip1,new Insets(0,0,0,20));
        gr.setMargin(tip2,new Insets(0,0,0,20));

        gr.setMargin(login,new Insets(30,0,0,40));
        gr.setMargin(clear,new Insets(30,0,0,10));
        gr.setHgap(0);
        gr.setVgap(10);

        Scene scene =new Scene(gr);

        primaryStage.setScene(scene);
        primaryStage.setTitle("登录");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        primaryStage.show();
        primaryStage.setResizable(false);


        t_name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                tip1.setText("\""+t1+"\"");
                if(t1.length()>6){
                    t_name.setText(s);
                }
            }
        });

        p_password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                tip2.setText("\""+t1+"\"");
                if(t1.length()>12){
                    p_password.setText(s);
                }
            }
        });
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if(group.getSelectedToggle()!=null){
                    if(power0.isSelected())
                        selected[0]="管理部人员";
                    else
                        selected[0]="人事部人员";
                    System.out.println(selected[0]);
                }
            }
        });
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name=t_name.getText();
                String password=p_password.getText();
                if(name.equals("")||password.equals("")){
                    System.out.println("请输入账号与密码");
                }

                else{
                        try {
                            int flag=0;
                            while(resultSet.next()){
                                if (resultSet.getString(1).equals(name) && resultSet.getString(2).equals(password)&&resultSet.getString(3).equals(selected[0])) {
                                    System.out.println("登陆成功！");
                                    primaryStage.setTitle("登陆成功！");
                                    flag = 1;

                                    WorkerSarlary workerSarlary = new WorkerSarlary(name, password);
                                    primaryStage.close();
                                    break;
                                }
                            }
                            if(flag==0) {
                                System.out.println("登陆失败");
                                FadeTransition fadeTransition = new FadeTransition();
                                fadeTransition.setDelay(Duration.seconds(0.1));
                                fadeTransition.setNode(gr);
                                fadeTransition.setFromValue(0);
                                fadeTransition.setToValue(1);
                                fadeTransition.play();
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
                }

        });
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                t_name.setText("");
                p_password.setText("");
            }
        });

    }


    public static void main(String[] args) {

        launch(args);
    }
}
