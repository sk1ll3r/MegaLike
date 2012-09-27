package com.atlast.MegaLike;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.android.*;
import com.facebook.android.Facebook.*;

public class LoginActivity extends Activity {

    Facebook facebook = new Facebook("367951253282551");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Log.d("TAG","facebook.authorize");
        
        facebook.authorize(this, new DialogListener() {
            public void onComplete(Bundle values) {Log.d("TAG","SUCCESS");}

            public void onFacebookError(FacebookError error) {Log.d("TAG","onFacebookError "+error.getMessage());}

            public void onError(DialogError e) {Log.d("TAG","onError "+e.getMessage());}

            public void onCancel() {Log.d("TAG","onCancel ");}
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG","onActivityResult");
        facebook.authorizeCallback(requestCode, resultCode, data);
    }
}