package com.i18n;
import java.util.Locale;
public class I18NTest1
{
    public static void main(String[] args)
    {
        Locale[] locales = Locale.getAvailableLocales();

        for (Locale locale : locales)
        {
            System.out.println(locale.getDisplayCountry() + " : "
                    + locale.getCountry());		//显示国家及国家代码
//			System.out.println(locale.getDisplayLanguage() + " : " + locale.getLanguage());
        }
    }
}

