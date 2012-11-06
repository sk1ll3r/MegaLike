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

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

public class MainGalleryActivity extends SherlockFragmentActivity {
	private FragmentStatePagerAdapter mFragmentAdapter;

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
				finish();
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
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maingallery);

		mFragmentAdapter = new MegalikeAdapter(getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.maingallery_pager);
		pager.setAdapter(mFragmentAdapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.maingallery_indicator);
		indicator.setViewPager(pager);

		configureSearch();
	}

	private void configureSearch() {
		((SearchManager) getSystemService(Context.SEARCH_SERVICE)).setOnDismissListener(new SearchManager.OnDismissListener() {

			public void onDismiss() {
				Log.d("TAG", "MainGalleryActivity - onDismiss()");
				redrawUI();
			}

		});
	}

	private void redrawUI() {
		Log.d("TAG", "MainGalleryActivity - redrawUI()");
		mFragmentAdapter.notifyDataSetChanged();
	}

	private static class MegalikeAdapter extends FragmentStatePagerAdapter {
		public MegalikeAdapter(FragmentManager fm) {
			super(fm);
		}

		public int getItemPosition(Object object) {
			Log.d("TAG", "MainGalleryActivity - updating fragments");
			return POSITION_NONE;
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0)
				return PhotosTabFragment.newInstance(position % Extra.TAB_CONTENT.length);
			else
				return StatusesTabFragment.newInstance();
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