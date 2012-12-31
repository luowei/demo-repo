package com.vvvv.ch09;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramPacket packet = null;
        DatagramSocket socket = null;
        socket = new DatagramSocket(10002);    //使用10000号端口
        SocketAddress address = new InetSocketAddress("127.0.0.1", 10000);        //构建要发送的包的地
        for (int i = 0; i < 100; i++) {
            String string = "" + i;
            packet = new DatagramPacket(string.getBytes(), string.length(), address);
            socket.send(packet);
            System.out.println("Send:" + string + " NO." + i);
        }
        socket.close();
    }
}

//telnet 127.0.0.1 9999