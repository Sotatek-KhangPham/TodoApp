package com.example.chien.todoapp.Common;

public class Common {
    private static Common common;
    public static String token;
    public static String id;
    public static String email;
    public static String password;

    public static Common getInstance()
    {
        if(common == null)
        {
            Common newCommon = new Common();
            return newCommon;
        }
        return common;
    }
}
