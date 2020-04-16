package utils;

import javafx.scene.control.DatePicker;

public class Datetransform {
    public static String null2Blank(DatePicker dp)
    {
        try{
            return dp.getValue().toString();
        }
        catch (NullPointerException e)
        {
            return "";

        }

    }
    public static String blank2Null(String str)
    {
        if("".equals(str))
        {
            return null;
        }
        else
        {
            return str;
        }
    }

}
