package com.atlast.MegaLike.Lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import android.content.Context;

import com.atlast.MegaLike.R;
import com.atlast.MegaLike.FacebookLogic.DataManager;
import com.atlast.MegaLike.FacebookLogic.FQLFriend;
import com.atlast.MegaLike.FacebookLogic.Photo;

public class FacebookData {
	private static DataManager mDataManager;
	String[] tuanAll, tuanTagged, tuanUploaded, tuanStarred, rastoAll, rastoTagged, rastoUploaded, rastoStarred, matoAll, matoTagged, matoUploaded, matoStarred, shaanAll, shaanTagged, shaanUploaded, shaanStarred;
	private final Map<String, List<FQLFriend>> mSearchSuggestionsDict = new ConcurrentHashMap<String, List<FQLFriend>>();
	private List<FQLFriend> mFriends;

	// TODO remove in real version
	private static final FacebookData sInstance = new FacebookData(UILApplication.getAppContext());

	public static FacebookData getInstance() {
		mDataManager = new DataManager(Extra.mFacebook.getAccessToken());
		return sInstance;
	}

	private FacebookData(Context context) {
		String[] lightImages = context.getResources().getStringArray(R.array.light_images);

		tuanAll = concat(context.getResources().getStringArray(R.array.fbdata_tuan_all), lightImages);
		tuanTagged = concat(context.getResources().getStringArray(R.array.fbdata_tuan_tagged), lightImages);
		tuanUploaded = concat(context.getResources().getStringArray(R.array.fbdata_tuan_uploaded), lightImages);
		tuanStarred = concat(context.getResources().getStringArray(R.array.fbdata_tuan_starred), lightImages);

		rastoAll = concat(context.getResources().getStringArray(R.array.fbdata_rasto_all), lightImages);
		rastoTagged = concat(context.getResources().getStringArray(R.array.fbdata_rasto_tagged), lightImages);
		rastoUploaded = concat(context.getResources().getStringArray(R.array.fbdata_rasto_uploaded), lightImages);
		rastoStarred = concat(context.getResources().getStringArray(R.array.fbdata_rasto_starred), lightImages);

		matoAll = concat(context.getResources().getStringArray(R.array.fbdata_mato_all), lightImages);
		matoTagged = concat(context.getResources().getStringArray(R.array.fbdata_mato_tagged), lightImages);
		matoUploaded = concat(context.getResources().getStringArray(R.array.fbdata_mato_uploaded), lightImages);
		matoStarred = concat(context.getResources().getStringArray(R.array.fbdata_mato_starred), lightImages);

		shaanAll = concat(context.getResources().getStringArray(R.array.fbdata_shaan_all), lightImages);
		shaanTagged = concat(context.getResources().getStringArray(R.array.fbdata_shaan_tagged), lightImages);
		shaanUploaded = concat(context.getResources().getStringArray(R.array.fbdata_shaan_uploaded), lightImages);
		shaanStarred = concat(context.getResources().getStringArray(R.array.fbdata_shaan_starred), lightImages);

		if(mFriends != null) loadFriends();
	}

	private void loadFriends() {
		String[] values = new String[] { "Tuan", "Rasto", "Mato", "Shaan", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };
		mFriends = mDataManager.getFriends();
		for (FQLFriend friend : mFriends)
			addFriend(friend);
	}

	private void addFriend(FQLFriend friend) {
		String name = friend.name.toLowerCase();
		int len = name.length();
		for (int i = 0; i < len; i++) {
			String prefix = name.substring(0, len - i);
			addMatch(prefix, friend);
		}
	}

	private void addMatch(String query, FQLFriend friend) {
		List<FQLFriend> matches = mSearchSuggestionsDict.get(query);
		if (matches == null) {
			matches = new ArrayList<FQLFriend>();
			mSearchSuggestionsDict.put(query, matches);
		}
		matches.add(friend);
	}
	
	public List<FQLFriend> getMatches(String query) {
        List<FQLFriend> list = mSearchSuggestionsDict.get(query);
        return list == null ? Collections.EMPTY_LIST : list;
    }

	private static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	public Vector<Photo> getPhotosAll(String friendUID) {
		return mDataManager.getAllUserCombinedPhotos(friendUID);
	}

	public Vector<Photo> getPhotosTagged(String friendUID) {
		return mDataManager.getAllUserTaggedPhotos(friendUID);
	}

	public Vector<Photo> getPhotosUploaded(String friendUID) {
		return mDataManager.getAllUserPhotos(friendUID);
	}

	public Vector<Photo> getPhotosStarred() {
		return mDataManager.getStarPhotos();
	}

	public String[] getLinks(int userID) {
		return null;
	}
	
	public Vector<Photo> getPhotos(int index, String friendUID) {
		switch (index) {
		case 0:
			return getPhotosAll(friendUID);
		case 1:
			return getPhotosTagged(friendUID);
		case 2:
			return getPhotosUploaded(friendUID);
		case 3:
			return getPhotosStarred();
		default:
			return getPhotosAll(friendUID);
		}
	}
}