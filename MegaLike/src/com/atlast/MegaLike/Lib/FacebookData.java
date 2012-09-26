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

public class FacebookData {
	String[] tuanAll, tuanTagged, tuanUploaded, tuanStarred, rastoAll, rastoTagged, rastoUploaded, rastoStarred, matoAll, matoTagged, matoUploaded, matoStarred, shaanAll, shaanTagged, shaanUploaded, shaanStarred;

	private final Map<String, List<String>> mSearchSuggestionsDict = new ConcurrentHashMap<String, List<String>>();

	// TODO remove in real version
	private static final FacebookData sInstance = new FacebookData(UILApplication.getAppContext());

	public static FacebookData getInstance() {
		return sInstance;
	}

	public FacebookData(Context context) {
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

		loadFriends();
	}

	private void loadFriends() {
		String[] values = new String[] { "Tuan", "Rasto", "Mato", "Shaan", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };
		for (String friend : values)
			addFriend(friend.toLowerCase());
	}

	private void addFriend(String friend) {
		int len = friend.length();
		for (int i = 0; i < len; i++) {
			String prefix = friend.substring(0, len - i);
			addMatch(prefix, friend);
		}
	}

	private void addMatch(String query, String friend) {
		List<String> matches = mSearchSuggestionsDict.get(query);
		if (matches == null) {
			matches = new ArrayList<String>();
			mSearchSuggestionsDict.put(query, matches);
		}
		matches.add(friend);
	}
	
	public List<String> getMatches(String query) {
        List<String> list = mSearchSuggestionsDict.get(query);
        return list == null ? Collections.EMPTY_LIST : list;
    }

	private static <T> T[] concat(T[] first, T[] second) {
		T[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	public String[] getPhotosAll(int userID) {
		switch (userID) {
		case 1:
			return tuanAll;
		case 2:
			return rastoAll;
		case 3:
			return matoAll;
		case 4:
			return shaanAll;
		default:
			return null;
		}
	}

	public String[] getPhotosTagged(int userID) {
		switch (userID) {
		case 1:
			return tuanTagged;
		case 2:
			return rastoTagged;
		case 3:
			return matoTagged;
		case 4:
			return shaanTagged;
		default:
			return null;
		}
	}

	public String[] getPhotosUploaded(int userID) {
		switch (userID) {
		case 1:
			return tuanUploaded;
		case 2:
			return rastoUploaded;
		case 3:
			return matoUploaded;
		case 4:
			return shaanUploaded;
		default:
			return null;
		}
	}

	public String[] getPhotosStarred(int userID) {
		switch (userID) {
		case 1:
			return tuanStarred;
		case 2:
			return rastoStarred;
		case 3:
			return matoStarred;
		case 4:
			return shaanStarred;
		default:
			return null;
		}
	}

	public String[] getLinks(int userID) {
		return null;
	}

	public Vector<Friend> getFriends(int userID) {
		TestData mData = new TestData();
		return mData.getFriends(userID);
	}

	public String[] getPhotos(int index, int userID) {
		switch (index) {
		case 0:
			return getPhotosAll(userID);
		case 1:
			return getPhotosTagged(userID);
		case 2:
			return getPhotosUploaded(userID);
		case 3:
			return getPhotosStarred(userID);
		default:
			return getPhotosAll(userID);
		}
	}
}