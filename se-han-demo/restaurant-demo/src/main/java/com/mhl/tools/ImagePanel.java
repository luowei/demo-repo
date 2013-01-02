/*
 * 这是一个可以动态加载一个图片做背景的JPanel
 */

package com.mhl.tools;
import javax.swing.*;
import javax.imageio.*;
import java.awt.*;

public class ImagePanel extends JPanel{
	Image im;
	//构造函数去指定该Panel大小
	public ImagePanel(Image im)
	{
		this.im=im;
		//希望它大小是自适应
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	//画出背景
	public void paintComponent(Graphics g)
	{
		//清屏
		super.paintComponents(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(),this);
	}
}
