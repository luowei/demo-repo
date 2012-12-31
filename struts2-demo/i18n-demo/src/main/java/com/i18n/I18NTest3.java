package com.i18n;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
public class I18NTest3
{
    public static void main(String[] args)
    {
        Locale locale = Locale.CHINA;	//将locale定义为CHINA
        //绑定到资源文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);
        //获取资源文件中的字符串对象
        String value = bundle.getString("hello");
        //格式化字符串对象
        String result = MessageFormat.format(value, new Object[]{"维唯为为"});
        System.out.println(result);
    }
}
