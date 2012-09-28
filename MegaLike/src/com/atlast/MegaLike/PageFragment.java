package com.atlast.MegaLike;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import com.atlast.MegaLike.FacebookLogic.Photo;
import com.atlast.MegaLike.Lib.Extra;
import com.atlast.MegaLike.Lib.FacebookData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

import android.app.Activity;
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
	private int TAB_INDEX;
	private ImageLoader imageLoader;
	private Vector<String> bigImageUrls;
	private Vector<String> thumbImageUrls;
	private DisplayImageOptions options;
	private FacebookData data = FacebookData.getInstance();

	public static PageFragment newInstance(int tabIndex) {
		PageFragment fragment = new PageFragment();
		fragment.TAB_INDEX = tabIndex;
		return fragment;
	}

	@Override
	public void onResume() {
		super.onResume();
		parseImageUrls();
	}

	private void parseImageUrls() {
		Vector<Photo> photos = data.getPhotos(TAB_INDEX, Extra.CURRENT_FRIEND_UID);
		Collections.sort(photos);
		for (Photo photo : photos) {
			bigImageUrls.add(photo.bigSrc);
			thumbImageUrls.add(photo.thumbSrc);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		options = new DisplayImageOptions.Builder().showStubImage(R.drawable.stub_image).showImageForEmptyUri(R.drawable.image_for_empty_url).cacheInMemory().cacheOnDisc().build();
		parseImageUrls();
		imageLoader = ImageLoader.getInstance();
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
		View view = inflater.inflate(R.layout.pagefragment, container, false);
		GridView gridView = (GridView) view.findViewById(R.id.pagefragment_gridview);
		gridView.setAdapter(new ImageAdapter());
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startPhotoActivity(position);
			}
		});
		return view;
	}

	private void startPhotoActivity(int position) {
		Intent intent = new Intent(getActivity(), PhotoActivity.class);
		intent.putExtra(Extra.IMAGES, bigImageUrls);
		intent.putExtra(Extra.IMAGE_POSITION, position);
		startActivity(intent);
	}

	public class ImageAdapter extends BaseAdapter {
		public int getCount() {
			return bigImageUrls.size();
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
				imageView = (ImageView) getActivity().getLayoutInflater().inflate(R.layout.pagefragment_gridimage, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(bigImageUrls.get(position), imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingComplete(Bitmap loadedImage) {
					if (getActivity() != null) {
						Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
						imageView.setAnimation(anim);
						anim.start();
					}
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
