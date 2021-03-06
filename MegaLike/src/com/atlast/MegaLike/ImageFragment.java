package com.atlast.MegaLike;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
	private String[] bigImageUrls;
	private ImageLoader imageLoader;

	public static ImageFragment newInstance(int position) {
		ImageFragment fragment = new ImageFragment();
		fragment.position = position;
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		bigImageUrls = ((PhotoActivity) getActivity()).bigImageUrls;
		imageLoader = ImageLoader.getInstance();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.imagefragment, container, false);
		final ImageView imageView = (ImageView) view.findViewById(R.id.imagefragment_image);
		final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.imagefragment_loading);

		imageLoader.displayImage(bigImageUrls[position], imageView, new ImageLoadingListener() {
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
	}
}