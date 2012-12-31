package com.vvvv.ch07;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Caculator implements ActionListener {
    private JFrame frame;
    private Container contentPane;

    private JMenuItem m_item_1, m_item_2;
    private JMenu m_1, m_2;
    private JMenuBar mb;
    private JTextField textField;
    private JButton up_btn_1, up_btn_2, up_btn_3;
    private JButton left_btn_1, left_btn_2, left_btn_3, left_btn_4;

    private JButton[] btn_no = new JButton[20];
    private String[] btn_content = new String[]{"7", "8", "9", "/", "sqrt", "4", "5",
            "6", "*", "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "="};

    public Caculator() {
        frame = new JFrame("Caculator");
        contentPane = frame.getContentPane();
        frame.setBounds(200, 200, 400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
    }

    public void initGUI() {
        //声明子容器
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel p2 = new JPanel(new GridLayout(4, 1, 5, 5));
        JPanel p3 = new JPanel(new GridLayout(4, 5, 5, 5));
        JPanel p4 = new JPanel(new BorderLayout());
        JPanel p5 = new JPanel(new BorderLayout());

        //菜单
        m_1 = new JMenu("Edit");
        m_2 = new JMenu("About");
        m_item_1 = new JMenuItem("Ctrl c");
        m_item_2 = new JMenuItem("Ctrl v");
        m_1.add(m_item_1);
        m_1.add(new JSeparator());
        m_1.add(new JMenuItem("test"));
        m_2.add(m_item_2);
        mb = new JMenuBar();
        mb.add(m_1);
        mb.add(m_2);
        p5.add(mb, BorderLayout.NORTH);

        up_btn_1 = new JButton("Backspace");
        up_btn_2 = new JButton("CE");
        up_btn_3 = new JButton("C");

        p1.add(up_btn_1);
        p1.add(up_btn_2);
        p1.add(up_btn_3);


        left_btn_1 = new JButton("MC");
        left_btn_2 = new JButton("MR");
        left_btn_3 = new JButton("MS");
        left_btn_4 = new JButton("M+");

        p2.add(left_btn_1);
        p2.add(left_btn_2);
        p2.add(left_btn_3);
        p2.add(left_btn_4);

        //添加运算按钮
        for (int i = 0; i < btn_no.length; i++) {
            btn_no[i] = new JButton(btn_content[i]);
        }

        //简便的方法，使用数组存放后边的符号
        for (int i = 0; i < btn_no.length; i++) {
            p3.add(btn_no[i]);
            btn_no[i].addActionListener(this);
        }

        p4.add(p1, BorderLayout.NORTH);
        p4.add(p2, BorderLayout.WEST);
        p4.add(p3, BorderLayout.CENTER);

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        p5.add(textField, BorderLayout.SOUTH);

        contentPane.add(p4, BorderLayout.CENTER);
        contentPane.add(p5, BorderLayout.NORTH);
    }

    public void go() {
        frame.setVisible(true);
    }

    StringBuffer strBuf = new StringBuffer("");

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i = 0; i < btn_no.length; i++) {
            if (source == btn_no[i]) {
                JButton btn = (JButton) source;
                strBuf.append(btn.getLabel());
                textField.setText(strBuf.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Caculator().go();
    }
}