/**
 * 对Message规定一些规则
 * mesType1->表示是登陆成功
 * mesType2->表示是登陆失败
 * mesType3->表示是普通的消息包
 */

package com.server.server.common;

public class Message implements java.io.Serializable{
	private String mesType;
	private String sender;
	private String getter;
	private String con;
	private String sendTime;
	
	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
	
}
