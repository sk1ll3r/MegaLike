package com.atlast.MegaLike;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class LoginActivity extends Activity {

	private Facebook facebook = new Facebook("367951253282551");
	private String[] PERMISSIONS = new String[] { "user_photos", "friends_photos", "publish_stream", "read_stream", "friends_status", "user_status" };
	private SharedPreferences mPrefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	public void startLogin(View view) {
		view.setBackgroundResource(R.drawable.image_fblogin_hover);

		/*
		 * Get existing access_token if any
		 */
		mPrefs = getPreferences(MODE_PRIVATE);
		String access_token = mPrefs.getString("access_token", null);
		long expires = mPrefs.getLong("access_expires", 0);
		if (access_token != null) {
			facebook.setAccessToken(access_token);
		}
		if (expires != 0) {
			facebook.setAccessExpires(expires);
		}

		/*
		 * Only call authorize if the access_token has expired.
		 */
		if (!facebook.isSessionValid()) {
			facebook.authorize(this, PERMISSIONS, new DialogListener() {
				public void onComplete(Bundle values) {
					startMainGalleryActivity();
				}

				public void onFacebookError(FacebookError error) {
				}

				public void onError(DialogError e) {
				}

				public void onCancel() {
				}
			});
		}

		/*
		 * TODO However, note that this doesn't account for the situation where
		 * the user may have revoked access to your app or if the user has
		 * changed their password. You will need to always look out for the
		 * invalid access_token and redirect the user to re-authorize your app.
		 * For invalid access token, the following error is returned in the
		 * 'response' parameter of the onComplete() method:
		 * 
		 * User revoked access to your app:
		 * {"error":{"type":"OAuthException","message":
		 * "Error validating access token: User 1053947411 has not authorized application 157111564357680."
		 * }}
		 * 
		 * OR when password changed:
		 * {"error":{"type":"OAuthException","message":
		 * "Error validating access token: The session is invalid because the user logged out."
		 * }}
		 * 
		 * https://developers.facebook.com/docs/mobile/android/build/#register
		 */
	}

	private void startMainGalleryActivity() {
		Intent intent = new Intent(this, MainGalleryActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("TAG", "onActivityResult");
		facebook.authorizeCallback(requestCode, resultCode, data);
	}
}