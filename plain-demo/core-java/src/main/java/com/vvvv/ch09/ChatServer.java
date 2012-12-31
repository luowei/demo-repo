package com.vvvv.ch09;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Scanner;

public class ChatServer {
    private ServerSocket ss;
    private List list;
    //private Queue list;		//解决并发安全：方法一
    private Socket socket;
    private static final int PORT = 9999;
    private static final String WELCOME_MESSAGE = "@Welcom to chat room !";

    public ChatServer() {
        try {
            list = new LinkedList();
            //list=new ConcurrentLinkedQueue();	//方法一：使用ConcurrentLinkedQueue解决并发安全
            ss = new ServerSocket(PORT);
            while (true) {
                socket = ss.accept();
                add(socket);
                new Handler(socket);    //new一个用户连接句柄
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //内部类，代表着一个用户连接
    class Handler implements Runnable {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
            new Thread(this).start();    //new一个线程，并启动
        }

        public void run() {
            InputStream in = null;
            OutputStream out = null;
            try {
                //while(true)
                //{
                in = socket.getInputStream();
                out = socket.getOutputStream();
                Scanner scanner = new Scanner(in);
                PrintWriter pw = new PrintWriter(out);    //初始化输出对象
                pw.println(WELCOME_MESSAGE);    //输出欢迎信息
                pw.flush();
                while (scanner.hasNextLine())    //持续等待用户输入，获得客户的发言信息
                {
                    String message = scanner.nextLine();
                    broadcast(socket.getInetAddress().getHostAddress() + " says:" + message);

                    //方法二：解决并发安全
                    //broadcast_1(socket.getInetAddress().getHostAddress()+" says:"+message);
                    //广播客户的发言
                }
                //pw.close();
                socket.close();
                //}

                //scanner.close();
                //in.close();
                //out.close();
                //socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //广播客户的发言(群发)
    //解决并发安全问题，方法二
    public void broadcast_1(String message) throws Exception {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Socket socket = (Socket) list.get(i);
            if (socket.isClosed())    //判断socket是否被关闭
            {
                System.out.println("Socket is closed,the list size is:" + list.size());
                remove(socket);        //如果socket关闭了，就从list中删除
                System.out.println("after remove,the list size is:" + list.size());
                break;
            } else {
                //System.out.println("not close,list size is:"+list.size());
                OutputStream out = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(out);
                pw.println(message);    //把消息写到输出流
                pw.flush();
            }
        }
    }


    //广播客户的发言(群发)(使用迭代器遍历)
    public void broadcast(String message) throws Exception {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Socket socket = (Socket) it.next();
            if (socket.isClosed())    //监听
            {
                System.out.println("Socket is closed,the list size is:" + list.size());
                remove(socket);        //如果socket关闭了，就从list中删除
                System.out.println("after remove,the list size is:" + list.size());
                break;        //解决并发安全，方法三
            } else {
                OutputStream out = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(out);
                pw.println(message);    //把消息写到输出流
                pw.flush();
                //pw.close();
                //out.close();
            }
        }
    }

    //添加用户 上线
    public void add(Object obj) {
        list.add(obj);
    }

    //删除用户  下线
    public void remove(Object obj) {
        list.remove(obj);
    }

    public static void main(String[] args) throws Exception {
        new ChatServer();
    }
}
//服务端启动后，客户端只需在cmd中用telnet连接到服务端，即可发送聊天信息
//telnet 127.0.0.1 9999