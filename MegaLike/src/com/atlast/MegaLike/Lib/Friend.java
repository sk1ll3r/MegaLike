package com.atlast.MegaLike.Lib;

public class Friend {
	private int userID;
	private String name;
	
	public Friend() {
		
	}

	public Friend(int userID, String name) {
		this.userID = userID;
		this.name = name;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
