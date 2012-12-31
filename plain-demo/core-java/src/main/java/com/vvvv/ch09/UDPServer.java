package com.vvvv.ch09;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramPacket packet = null;
        DatagramSocket socket = null;
        socket = new DatagramSocket(10000);    //使用10000号端口
        byte[] buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);        //定义一个packet用于装将接收的数据
        int pacCount = 0;
        while (true) {
            socket.receive(packet);        //接收客户端发过来的数据包
            pacCount++;
            String string = new String(packet.getData(), 0, packet.getLength());
            System.out.println("From Client:" + string + " Count:" + pacCount);
        }
    }
}

//telnet 127.0.0.1 9999