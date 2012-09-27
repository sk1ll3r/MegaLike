package com.atlast.MegaLike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class LoginActivity extends Activity {

	Facebook facebook = new Facebook("367951253282551");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	public void startLogin(View view) {
		view.setBackgroundResource(R.drawable.image_fblogin_hover);
		facebook.authorize(this, new DialogListener() {
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