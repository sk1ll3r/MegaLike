package com.atlast.MegaLike;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class MainGalleryActivity extends FragmentActivity {
	private static final int NUMBER_OF_PAGES = 4;

	private ViewPager mViewPager;
	private MyFragmentPagerAdapter mMyFragmentPagerAdapter;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_gallery);
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mMyFragmentPagerAdapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager());
		mViewPager.setAdapter(mMyFragmentPagerAdapter);
	}

	private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {

		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			switch (index) {
			case 0:
				return PageFragment.newInstance("Tagged");
			case 1:
				return PageFragment.newInstance("Uploaded");
			case 2:
				return PageFragment.newInstance("Starred");
			case 3:
				return PageFragment.newInstance("Statuses");
			default:
				return PageFragment.newInstance("Unkown");
			}
		}

		@Override
		public int getCount() {

			return NUMBER_OF_PAGES;
		}
	}
}
