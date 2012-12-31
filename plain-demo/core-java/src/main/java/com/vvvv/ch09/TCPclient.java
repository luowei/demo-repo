package com.vvvv.ch09;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPclient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Scanner scanner = new Scanner(in);
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                System.out.println("From Server:" + message);
            }
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//telnet 127.0.0.1 9999