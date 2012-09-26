package com.atlast.MegaLike.Lib;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class UILApplication extends Application {
	private static Context context; // only temporary

	@Override
	public void onCreate() {
		super.onCreate();

		//@formatter:off
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
			.threadPoolSize(3)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.memoryCacheSize(1500000)
			.denyCacheImageMultipleSizesInMemory()
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.enableLogging()
			.build();
		//@formatter:on
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
		UILApplication.context = getApplicationContext();
	}

	public static Context getAppContext() {
		return UILApplication.context;
	}
}