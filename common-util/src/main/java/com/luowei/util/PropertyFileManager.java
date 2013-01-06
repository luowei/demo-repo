package com.luowei.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-6
 * Time: 上午11:06
 * To change this template use File | Settings | File Templates.
 */
public class PropertyFileManager {
    private static final String SEPERATOR = "=";
    static Logger log = LoggerFactory.getLogger("PropertyFileManager");

    /**
     * Read in a standard properties file to a TreeMap
     * @param fileLoc
     * @return a treeMap which matches the properties file
     * @throws IOException
     */
    public static TreeMap loadPropertiesFile(String fileLoc) throws IOException {
        TreeMap map = new TreeMap();
        File inputFile = new File(fileLoc);
        if (!inputFile.exists()) {
            log.error("input property file not found: "+fileLoc);
            return null;
        }
        RandomAccessFile ram = new RandomAccessFile(inputFile,"r");
        String line = null;
        while ((line=ram.readLine())!=null) {
            if (line.startsWith("#")) continue;
            int seperatorLoc = line.indexOf(SEPERATOR);
            if (seperatorLoc<=0) continue;
            String key = line.substring(0,seperatorLoc);
            String value = line.substring(seperatorLoc+1);
            map.put(key,value);
        }
        ram.close();
        return map;
    }

    /**
     * Wrtie a TreeMap to a properties file. If the file exists I think it will
     * overwrite it.
     * @param properties
     * @param fileLoc
     * @throws IOException
     */
    public static void storePropertiesFile(Map properties, String fileLoc) throws IOException {
        File inputFile = new File(fileLoc);
        inputFile.delete();
        RandomAccessFile ram = new RandomAccessFile(inputFile,"rw");
        Set keys = properties.keySet();
        for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            String value = (String) properties.get(key);
            ram.writeBytes(key+SEPERATOR+value+"\n");
        }
//        for (String key: keys) {
//            String value = (String) properties.get(key);
//            ram.writeBytes(key+SEPERATOR+value+"\n");
//        }
        ram.close();
    }
}
