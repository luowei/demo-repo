/**
 * 这是用户信息类
 */

package com.server.server.common;

public class User implements java.io.Serializable{
	//将这个类序列化，就使其对应的对象可以在网络上或者是在文件中传输
	
	private String userId;
	private String passWd;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
}
