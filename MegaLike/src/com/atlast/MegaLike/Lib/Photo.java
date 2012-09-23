package com.atlast.MegaLike.Lib;

public class Photo {
	
	private String small;
	private String large;
	private int likes;
	
	public Photo() {

	}
	
	public Photo(String small, String large, int likes) {
		this.small = small;
		this.large = large;
		this.likes = likes;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
}
