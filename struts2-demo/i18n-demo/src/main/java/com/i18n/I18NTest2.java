package com.i18n;
import java.util.Locale;
import java.util.ResourceBundle;
public class I18NTest2
{
    public static void main(String[] args)
    {
        System.out.println(Locale.getDefault());
        //绑定一个base name 为i18n的资源文件，Locale使用FRANCE(法国)
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.FRANCE);
        //获取hello字符串的值
        String value = bundle.getString("hello");
        System.out.println(value);
    }
}
