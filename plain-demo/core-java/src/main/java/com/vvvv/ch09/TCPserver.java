package com.vvvv.ch09;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPserver {
    public static void main(String[] args) {
        Socket socket = null;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(9999);
            boolean flag = true;
            while (flag) {
                Scanner scanner = new Scanner(System.in);
                socket = ss.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();

                System.out.println(socket.getInetAddress().getHostAddress() + " connected");
                PrintWriter pw = new PrintWriter(out);
                pw.println(new java.util.Date().toLocaleString());
                pw.flush();
                pw.close();

                if ("shutdown".equals(scanner.nextLine())) {
                    socket.close();
                    ss.close();
                    System.out.println("已关闭连接！");
                    flag = false;
                    break;
                }
                //socket.close();
                //ss.close();
            }
            //socket.close();
            //ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

//telnet 127.0.0.1 9999