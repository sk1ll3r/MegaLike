package com.atlast.MegaLike;

import java.util.Vector;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.atlast.MegaLike.Lib.Extra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class PhotoActivity extends SherlockFragmentActivity {

	private static int NUM_ITEMS;
	public Vector<String> bigImageUrls;
	private int pagerPosition;

	MyAdapter mAdapter;
	ViewPager mPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(Extra.THEME); // Used for theme switching in samples
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photoactivity);

		Bundle bundle = getIntent().getExtras();
		bigImageUrls = (Vector<String>) bundle.getParcelable(Extra.IMAGES);
		pagerPosition = bundle.getInt(Extra.IMAGE_POSITION, 0);
		NUM_ITEMS = bigImageUrls.size();

		mAdapter = new MyAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.photoactivity_pager);
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(pagerPosition);
	}

	public static class MyAdapter extends FragmentPagerAdapter {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return NUM_ITEMS;
		}

		@Override
		public Fragment getItem(int position) {
			return ImageFragment.newInstance(position);
		}
	}
}
