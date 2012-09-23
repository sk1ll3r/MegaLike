package com.atlast.MegaLike.Lib;

import java.util.Vector;

public class FacebookData {

	public FacebookData() {
	}

	public Vector<Photo> getPhotosAll(int userID) {
		TestData mData = new TestData();
		return mData.getPhotosAll(userID);
	}
	
	public Vector<Photo> getPhotosTagged(int userID) {
		TestData mData = new TestData();
		return mData.getPhotosTagged(userID);
	}
	
	public Vector<Photo> getPhotosUploaded(int userID) {
		TestData mData = new TestData();
		return mData.getPhotosUploaded(userID);
	}
	
	public Vector<Photo> getPhotosStarred(int userID) {
		TestData mData = new TestData();
		return mData.getPhotosStarred(userID);
	}

	public Vector<Link> getLinks(int userID) {
		TestData mData = new TestData();
		return mData.getLinks(userID);
	}

	public Vector<Friend> getFriends(int userID) {
		TestData mData = new TestData();
		return mData.getFriends(userID);
	}

}