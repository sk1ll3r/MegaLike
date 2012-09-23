package com.atlast.MegaLike.Lib;

public class Link {
	
	private String status;
	private int likes;
	
	public Link() {

	}

	public Link(String status, int likes) {
		this.status = status;
		this.likes = likes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
}
