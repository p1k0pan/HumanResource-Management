package utils;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageManagement {
    public static Map<String, Stage> STAGE=new HashMap<String, Stage>();
    public static Map<String, Object> CONTROLLER=new HashMap<String, Object>();
    public static String message;
    public static String singleid;
    public static String sqlquery;
    public static Map<String, Object> SINGLE=new HashMap<String, Object>();
    public static boolean ynflag; //y-true , n-false
}
