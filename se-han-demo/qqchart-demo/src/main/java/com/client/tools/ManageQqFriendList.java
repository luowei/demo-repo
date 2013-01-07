/**
 * 管理好友、陌生人、黑名单...界面类
 */

package com.client.tools;
import java.util.*;
import java.io.*;

import com.client.view.QqFriendList;

public class ManageQqFriendList {

	private static HashMap hm=new HashMap<String,QqFriendList>();
	
	public static void addQqFrendList(String qqid,QqFriendList qqFriendList)
	{
		hm.put(qqid, qqFriendList);
	}
	public static QqFriendList getQqFriendList(String qqId)
	{
		return (QqFriendList)hm.get(qqId);
	}
}
