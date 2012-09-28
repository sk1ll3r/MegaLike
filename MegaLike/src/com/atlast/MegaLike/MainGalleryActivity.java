package com.atlast.MegaLike;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.atlast.MegaLike.Lib.Extra;
import com.atlast.MegaLike.Lib.SessionManager;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;
import com.viewpagerindicator.TabPageIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainGalleryActivity extends SherlockFragmentActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Search").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add("About").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add("Logout").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().equals("Search"))
			onSearchRequested();
		if (item.getTitle().equals("Logout"))
			logout();
		return true;
	}

	private void logout() {
		Extra.mAsyncRunner = new AsyncFacebookRunner(Extra.mFacebook);
		Extra.mAsyncRunner.logout(getApplicationContext(), new RequestListener() {
			public void onComplete(String response, Object state) {
				SessionManager.clear(MainGalleryActivity.this);
				startLoginActivity();
			}

			public void onIOException(IOException e, Object state) {
			}

			public void onFileNotFoundException(FileNotFoundException e, Object state) {
			}

			public void onMalformedURLException(MalformedURLException e, Object state) {
			}

			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}

	private void startLoginActivity() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maingallery);

		FragmentPagerAdapter adapter = new MegalikeAdapter(getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.maingallery_pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.maingallery_indicator);
		indicator.setViewPager(pager);
	}

	private static class MegalikeAdapter extends FragmentPagerAdapter {
		public MegalikeAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return PageFragment.newInstance(position % Extra.TAB_CONTENT.length);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return Extra.TAB_CONTENT[position % Extra.TAB_CONTENT.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return Extra.TAB_CONTENT.length;
		}
	}
}
