package com.vvvv.ch07;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ClockDemo implements ActionListener {
    private JFrame frame;
    private JPanel clockPanel;
    private Container contentPane;

    public void actionPerformed(ActionEvent e) {
    }

    public ClockDemo() {
        frame = new JFrame("ClockDemo");
        contentPane = frame.getContentPane();
        frame.setBounds(200, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        contentPane.setLayout(new BorderLayout());
        clockPanel = new ClockPanel();
        contentPane.add(clockPanel);
    }

    public void go() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ClockDemo().go();
    }
}

class ClockPanel extends JPanel implements ActionListener {
    private Timer timer;
    //private java.util.Random rand;
    private int i = -15, j = -15, k = -15;

    public ClockPanel() {
        //rand=new java.util.Random();
        timer = new Timer(20, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        updateUI();        //来自JPanel
    }

    public void paint(Graphics g) {
        int cx = 200, cy = 200;    //圆心位置
        //int x1=rand.nextInt(400);
        //int y1=rand.nextInt(400);
        //int x2=rand.nextInt(400);
        //int y2=rand.nextInt(400);
        i++;
        if (0 == (i + 15) % 60) {
            j++;
            if (0 == (j + 15) % 12) {
                k++;
            }
        }
        double r1 = 150, r2 = 120, r3 = 100;    //半径
        double alpha = Math.PI / 30.0;    //表针一次走动所转的孤度数

        g.clearRect(0, 0, 400, 400);
        //g.drawLine(x1,y1,x2,y2);
        g.drawOval(50, 50, 300, 300);
        for (int n = 0; n < 60; n++) {
            double r0 = r1 - 3.0;
            if (0 == n % 5) {
                r0 = r1 - 8.0;
            }
            g.drawLine((int) (cx + r0 * Math.cos(n * alpha)), (int) (cy + r0 * Math.sin(n * alpha)), (int) (cx + r1 * Math.cos(n * alpha)), (int) (cy + r1 * Math.sin(n * alpha)));
        }
        g.drawLine(cx, cy, (int) (cx + r1 * Math.cos(i * alpha)), (int) (cy + r1 * Math.sin(i * alpha)));
        g.drawLine(cx, cy, (int) (cx + r2 * Math.cos(j * alpha)), (int) (cy + r2 * Math.sin(j * alpha)));
        g.drawLine(cx, cy, (int) (cx + r3 * Math.cos(k * alpha)), (int) (cy + r3 * Math.sin(k * alpha)));

    }
}