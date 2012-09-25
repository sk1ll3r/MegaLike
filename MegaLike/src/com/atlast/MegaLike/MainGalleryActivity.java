package com.atlast.MegaLike;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

public class MainGalleryActivity extends SherlockFragmentActivity {
	private static final String[] CONTENT = new String[] { "All", "Tagged", "Uploaded", "Starred", "Statuses" };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Search").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add("About").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add("Logout").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(MainGalleryActivity.this, "Got click: " + item, Toast.LENGTH_SHORT).show();
		return true;
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_gallery);

		FragmentPagerAdapter adapter = new MegalikeAdapter(getSupportFragmentManager());

		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);
	}

	private static class MegalikeAdapter extends FragmentPagerAdapter {
		public MegalikeAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return PageFragment.newInstance(CONTENT[position % CONTENT.length]);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}
}
