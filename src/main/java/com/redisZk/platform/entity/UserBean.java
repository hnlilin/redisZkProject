package com.redisZk.platform.entity;

import java.io.Serializable;

/*
 * �û�ʵ����
 */

public class UserBean  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String userCode;// �û�����
	
	private String userName;
	
	private String password;
	
	private String userSex;//�Ա�0:�У�1��Ů

	private String telPhone;

	private String email;

	private String status;

	private String createTime;

	private String creater;// ������

	private String openid;
	
	private String ipAddress;//IP
	
	private String lastLoginTime;//����¼ʱ��

	public UserBean() {
		super();
	}

	public UserBean(String userCode, String userName, String password) {
		super();
		this.userCode = userCode;
		this.userName = userName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserSex() {
		
		if("0".equals(userSex)){
			return "��";
		}
		
		if("1".equals(userSex)){
			return "Ů";
		}
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}