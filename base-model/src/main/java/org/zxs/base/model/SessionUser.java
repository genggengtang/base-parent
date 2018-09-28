package org.zxs.base.model;

public class SessionUser {
	
	private Integer id;
	
	private String nickname;
	
	private Integer role;
	
	private String group;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Integer getRole() {
		return role;
	}
	
	public void setRole(Integer role) {
		this.role = role;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "SessionUser [userId=" + id + ", nickname=" + nickname + ", role=" + role + ", group=" + group + "]";
	}
	
}