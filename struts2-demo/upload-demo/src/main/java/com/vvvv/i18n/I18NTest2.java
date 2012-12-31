package com.vvvv.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest2
{
	public static void main(String[] args)
	{
		System.out.println(Locale.getDefault());
		
		ResourceBundle bundle = ResourceBundle.getBundle("shengsiyuan", Locale.FRANCE);
		
		String value = bundle.getString("hello");
		
		System.out.println(value);
	}
}
