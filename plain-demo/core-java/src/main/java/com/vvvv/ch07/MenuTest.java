package com.vvvv.ch07;

import javax.swing.*;
import java.awt.*;

public class MenuTest {
    private JFrame frame;
    private Container contentPane;
    private JMenuItem m_item_1, m_item_2;
    private JMenu m_1, m_2;
    private JMenuBar mb;

    public MenuTest() {
        frame = new JFrame("Menu Test");
        frame.setBounds(200, 200, 400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = frame.getContentPane();
        initGUI();
    }

    public void initGUI() {
        contentPane.setLayout(new BorderLayout());
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
        contentPane.add(mb, BorderLayout.NORTH);
    }

    public void go() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MenuTest().go();
    }
}