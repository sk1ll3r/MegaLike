package com.atlast.MegaLike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ImageFragment extends Fragment {
	private int position;
	private DisplayImageOptions options;
	private String[] imageUrls;
	private ImageLoader imageLoader;

	public static ImageFragment newInstance(int position) {
		ImageFragment fragment = new ImageFragment();
		fragment.position = position;
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.stub_image).showImageForEmptyUri(R.drawable.image_for_empty_url).cacheInMemory().cacheOnDisc().build();

		String[] heavyImages = getActivity().getResources().getStringArray(R.array.heavy_images);
		String[] lightImages = getActivity().getResources().getStringArray(R.array.light_images);

		imageUrls = new String[heavyImages.length + lightImages.length];
		List<String> urls = new ArrayList<String>();
		urls.addAll(Arrays.asList(heavyImages));
		urls.addAll(Arrays.asList(lightImages));
		imageUrls = (String[]) urls.toArray(new String[0]);

		imageLoader = ImageLoader.getInstance();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity().getApplicationContext()).threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2).memoryCacheSize(1500000) // 1.5
																																																			// Mb
				.denyCacheImageMultipleSizesInMemory().discCacheFileNameGenerator(new Md5FileNameGenerator()).enableLogging() // Not
																																// necessary
																																// in
																																// common
				.build();
		imageLoader.init(config);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.imagefragment, container, false);
		final ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_image);
		final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.imagefragment_loading);

		imageLoader.displayImage(imageUrls[position], imageView, options, new ImageLoadingListener() {
			public void onLoadingStarted() {
				spinner.setVisibility(View.VISIBLE);
			}

			public void onLoadingFailed(FailReason failReason) {
				String message = null;
				switch (failReason) {
				case IO_ERROR:
					message = "Input/Output error";
					break;
				case OUT_OF_MEMORY:
					message = "Out Of Memory error";
					break;
				case UNKNOWN:
					message = "Unknown error";
					break;
				}
				Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

				spinner.setVisibility(View.GONE);
				imageView.setImageResource(android.R.drawable.ic_delete);
			}

			public void onLoadingComplete(Bitmap loadedImage) {
				if (getActivity() != null) {
					spinner.setVisibility(View.GONE);
					Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
					imageView.setAnimation(anim);
					anim.start();
				}
			}

			public void onLoadingCancelled() {
				// Do nothing
			}
		});
		return view;
	}

	@Override
	public void onStop() {
		super.onStop();
		imageLoader.stop();
		Log.d("DEBUG", "ImageFragment.onStop()");
	}
}