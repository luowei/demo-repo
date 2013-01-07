/**
 * 在model层中的 登录验证
 */

package com.client.model;

import com.client.common.User;

import java.net.*;
import java.io.*;


public class QqClientUser {
	public boolean checkUser(User u)
	{
		return new QqClientConServer().sendInfoToServer(u);
	}
}
