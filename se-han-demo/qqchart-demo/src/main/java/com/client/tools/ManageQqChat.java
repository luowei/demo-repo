/**
 * 这是一个管理用户聊天界面的类
 */

package com.client.tools;
import java.util.*;

import com.client.view.QqChat;
public class ManageQqChat {
	
	private static HashMap hm=new HashMap<String,QqChat>();
	
	//加入HashMap
	public static void addQqChat(String loginIdAndFriendId,QqChat qqChat)
	{
		hm.put(loginIdAndFriendId, qqChat);
	}
	
	//从HashMap取出
	public static QqChat getQqChat(String loginIdAndFriendId)
	{
		return (QqChat)hm.get(loginIdAndFriendId);
	}
}
