package com.atlast.MegaLike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.atlast.MegaLike.Lib.Extra;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public final class PageFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	private ImageLoader imageLoader;
	private String[] imageUrls;
	private DisplayImageOptions options;

	public static PageFragment newInstance(String content) {
		PageFragment fragment = new PageFragment();
		fragment.options = new DisplayImageOptions.Builder().showStubImage(R.drawable.stub_image).showImageForEmptyUri(R.drawable.image_for_empty_url).cacheInMemory().cacheOnDisc().build();

		String[] heavyImages = fragment.getActivity().getResources().getStringArray(R.array.heavy_images);
		String[] lightImages = fragment.getActivity().getResources().getStringArray(R.array.light_images);

		fragment.imageUrls = new String[heavyImages.length + lightImages.length];
		List<String> urls = new ArrayList<String>();
		urls.addAll(Arrays.asList(heavyImages));
		urls.addAll(Arrays.asList(lightImages));
		fragment.imageUrls = (String[]) urls.toArray(new String[0]);

		fragment.imageLoader = ImageLoader.getInstance();

		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
			// TODO
		}
	}

	@Override
	public void onStop() {
		imageLoader.stop();
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment, container, false);
		GridView gridView = (GridView) view.findViewById(R.id.gridview);
		gridView.setAdapter(new ImageAdapter());
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startImageGalleryActivity(position);
			}
		});
		return view;
	}

	private void startImageGalleryActivity(int position) {
		Intent intent = new Intent(getActivity(), PhotoActivity.class);
		intent.putExtra(Extra.IMAGES, imageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	public class ImageAdapter extends BaseAdapter {
		public int getCount() {
			return imageUrls.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			final ImageView imageView;
			if (convertView == null) {
				imageView = (ImageView) getActivity().getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls[position], imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingComplete(Bitmap loadedImage) {
					Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
					imageView.setAnimation(anim);
					anim.start();
				}
			});

			return imageView;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// TODO
		// outState.putString(KEY_CONTENT, mContent);
	}
}
