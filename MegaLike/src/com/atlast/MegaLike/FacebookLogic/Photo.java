package com.atlast.MegaLike.FacebookLogic;

public class Photo implements Comparable<Photo>{

	public String pid;
	
	public String thumbSrc;
	
	public String bigSrc;

	public int likes;
	
	public String link;
	
	public Photo(String pid, String thumbSrc, String bigSrc, int likes, String link) {
		this.pid = pid;
		this.thumbSrc = thumbSrc;
		this.bigSrc = bigSrc;
		this.likes = likes;
		this.link = link;
	}

	public int compareTo(Photo other) {
		if (likes == other.likes) return pid.compareTo(other.pid);
		if (likes > other.likes) return -1;
		else return 1;
	}
	
	@Override
	public boolean equals(Object other){
		return (other instanceof Photo) && pid.equals(((Photo)other).pid);
	}
	
	@Override
	public int hashCode() {
		return pid.hashCode();
	}
}
