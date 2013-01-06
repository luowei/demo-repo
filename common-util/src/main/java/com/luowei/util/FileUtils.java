package com.luowei.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-6
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {
    static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    /**
     * Make sure the output directory exists
     */
    public static void createOutputDirIfNeeded() {
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }
    }

    /**
     * Reads a text file into a String object line by line, converting line breaks to the local format.
     *
     * @todo The character set is hard coded to UTF-8, but it should be configurable by the user.
     * The best thing would be to fill a combo box with all available character sets, obtained from
     * Charset.availableCharsets().
     *
     * @param inputFile The name of and path to the file
     * @return a String with the file contents.
     * @throws java.io.IOException
     */
    public static String readTextFile(File inputFile) throws IOException {
        FileInputStream fis = new FileInputStream(inputFile);
        Charset charset = Charset.forName("UTF-8");
        InputStreamReader isr = new InputStreamReader(fis, charset);
        BufferedReader reader = new BufferedReader(isr);

        StringBuffer contents = new StringBuffer();
        String line;
        String separator = System.getProperty("line.separator");
        while (( line = reader.readLine()) != null){
            contents.append(line).append(separator);
        }
        fis.close();
        isr.close();
        return contents.toString();
    }

    /**
     * Creates or truncates a file and then writes a string to it.
     *
     * Note that errors are not reported from this method.
     *
     * @param text The text to be written
     * @param filePath The file name or path
     */
    public static void writeFile(String text, String filePath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
        } catch (IOException e) {
            log.error("Error writing to file " + filePath, e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ignored) {
                    // Do nothing
                }
            }
        }
    }

    /**
     * Returns the contents of the file in a byte array. A byte array is
     * what we can pass to XMLRPC-Confluence to upload an attachment
     * (copied from O'Reilly)
     */
    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
