package com.atlast.MegaLike.FacebookLogic;

public class Link implements Comparable<Link> {

	public String linkId;
	
	public String ownerId;
	
	public String status;

	public String linkTitle;
	
	public String url;
	
	//public String imageUrl;
	
	public String createdTime;
	
	public int likes;
	
	public Link(String linkId, String ownerId, String status, String linkTitle, String url, /*String imageUrl,*/ String createdTime) {
		this.linkId = linkId;
		this.ownerId = ownerId;
		this.status = status;
		this.linkTitle = linkTitle;
		this.url = url;
		//this.imageUrl = imageUrl;
		this.createdTime = createdTime;
		likes = 0;
	}
	
	public int compareTo(Link other) {
		if (likes == other.likes) return linkId.compareTo(other.linkId);
		if (likes > other.likes) return -1;
		else return 1;
	}
	
	@Override
	public boolean equals(Object other){
		return (other instanceof Link) && linkId.equals(((Link)other).linkId);
	}
	
	@Override
	public int hashCode() {
		return linkId.hashCode();
	}
	
	public String toString(){
		return status;
	}
}
