package com.atlast.MegaLike.Lib;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;

public class Extra {
	public static final String[] TAB_CONTENT = new String[] { "All", "Tagged", "Uploaded", "Starred", "Statuses" };
	public static final String IMAGES = "com.atlast.MegaLike.Lib.IMAGES";
	public static final String IMAGE_POSITION = "com.atlast.MegaLike.Lib.IMAGE_POSITION";
	public static int THEME = com.actionbarsherlock.R.style.Theme_Sherlock_ForceOverflow;
	public static String CURRENT_USER_UID = null;
	public static String CURRENT_FRIEND_UID = null;
	public static String[] PERMISSIONS = new String[] { "user_photos", "friends_photos", "publish_stream", "read_stream", "friends_status", "user_status" };
	public static String APP_ID= new String("367951253282551");

	public static Facebook mFacebook;
    public static AsyncFacebookRunner mAsyncRunner;
    public static FacebookData mFacebookData;
}