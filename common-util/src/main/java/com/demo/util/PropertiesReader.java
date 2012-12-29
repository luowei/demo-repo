package com.demo.util;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 本类用提供一个线程同步的方法,读取资源文件中的配置信息
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 12-12-29
 * Time: 下午8:36
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesReader {

    private String propertiesFile;
    private Properties properties;
    private Locale locale = null;
    private ResourceBundle resourceBundle = null;

    /**
     * 构造 PropertysReader
     * @param {String} path 相对于classes的文件路径
     */
    public PropertiesReader(String path)
    {
        this.propertiesFile = path;
        this.properties = new Properties();
    }

    public PropertiesReader (String propertiesFile, Locale locale)
    {
        this.setLocale (locale);
        this.setPropertiesFile (propertiesFile);
        this.setResourceBundle ( ResourceBundle.getBundle(this.getPropertiesFile(), this.getLocale()) );
    }

    public PropertiesReader (){}

    /**
     * <p>
     * 本方法根据资源名获取资源内容
     * <p>
     *
     * @param {String} key 资源文件内key
     * @param {Stirng} defaultValue 默认值
     *
     * @reaurn String key对应的资源内容
     */
    public synchronized String getProperty(String key, String defaultValue)
    {
        try
        {
            InputStream in = this.getClass().getClassLoader()
                    .getResourceAsStream(this.propertiesFile);

            properties.load(in);

        }
        catch (Exception ex1)
        {
            System.out.println("没有找到资源文件:" + this.propertiesFile);
        }
        return properties.getProperty(key, defaultValue);
    }

    /**
     * <p>
     * 本方法根据资源名获取资源内容
     * <p>
     *
     * @param {String} key 资源文件内key
     * @param {Stirng} defaultValue 默认值
     * @param {boolean} isnull 如果配置文件value为空，是否使用默认值
     *
     * @reaurn String key对应的资源内容
     */
    public synchronized String getProperty(String key, String defaultValue,boolean isnull)
    {
        String value = null;
        value = getProperty(key,defaultValue);
        if(isnull && (value == null || "".equals(value.trim()) )  )
            value = defaultValue;
        return value;
    }

    public void setLocale (Locale locale)
    {
        this.locale = locale;
    }

    public Locale getLocale ()
    {
        return this.locale;
    }

    public void setPropertiesFile (String propertiesFile)
    {
        this.propertiesFile = propertiesFile;
    }

    public String getPropertiesFile ()
    {
        return this.propertiesFile;
    }

    public void setResourceBundle (ResourceBundle resourceBundle)
    {
        this.resourceBundle = resourceBundle;
    }

    public ResourceBundle getResourceBundle ()
    {
        return this.resourceBundle;
    }

    public String getProperty (String propertyName)
    {
        String propertyValue = this.getResourceBundle().getString(propertyName);
        return propertyValue;
    }


    public static void main(String[] args)
    {
        PropertiesReader preader = new PropertiesReader("log4j.properties");
        String rootLogger = preader.getProperty("aaa", "defaul");
        System.out.println(rootLogger);
    }

}
