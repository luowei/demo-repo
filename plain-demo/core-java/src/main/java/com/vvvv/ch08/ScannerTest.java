package com.vvvv.ch08;

import java.io.PrintWriter;
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        System.out.println("plz input something:");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.exit(0);
            }
            pw.println("You just input:" + input);
            pw.flush(); //刷新，对于支持缓存的流来说，要及时的进行刷新，

        }
        pw.close();    //关闭输出流
        scanner.close();    //关闭输入流
    }
}