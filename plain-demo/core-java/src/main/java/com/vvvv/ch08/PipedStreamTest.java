package com.vvvv.ch08;

import java.io.*;

public class PipedStreamTest {

    public static void main(String[] args) throws Exception {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();
        pis.connect(pos);
        new Sender(pos);
        new Reciever(pis);
    }
}

class Sender implements Runnable {
    private PipedOutputStream pos;

    public Sender(PipedOutputStream pos) {
        this.pos = pos;
        new Thread(this).start();
    }

    public void run() {
        BufferedOutputStream bos = new BufferedOutputStream(pos);
        DataOutputStream dos = new DataOutputStream(bos);
        for (int i = 0; i < 100; i++) {
            try {
                dos.writeInt(i);    //向Reciever线程发送
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Send:" + i);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            dos.close();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Reciever implements Runnable {
    private PipedInputStream pis;

    public Reciever(PipedInputStream pis) {
        this.pis = pis;
        new Thread(this).start();
    }

    public void run() {
        BufferedInputStream bis = new BufferedInputStream(pis);
        DataInputStream dis = new DataInputStream(bis);
        for (int i = 0; i < 100; i++) {
            try {
                int result = dis.readInt();    //读取
                System.out.println("Recived:" + result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            dis.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}