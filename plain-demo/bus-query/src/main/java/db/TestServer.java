package db;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer {
    public static void main(String agre[]) throws Exception {
        //开放端口
        ServerSocket server = new ServerSocket(80);
        System.out.println("--------开放端口等待客人----------");
        //等待客人
        Socket socket = server.accept();
        System.out.println("--------客人来了----------");
        //读取客人的信息
        InputStream input = socket.getInputStream();
        byte bs[] = new byte[1024];
        int count = input.read(bs);
        System.out.println(new String(bs, 0, count));
        socket.getOutputStream().write("<h1>你好!</h1>".getBytes());
        socket.getOutputStream().close();
    }
}

