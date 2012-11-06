package com.atlast.MegaLike;

import java.util.Collections;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atlast.MegaLike.FacebookLogic.Link;
import com.atlast.MegaLike.Lib.Extra;

public final class StatusesTabFragment extends Fragment {
	private Vector<Link> mLinks = new Vector<Link>();
	private Vector<Spanned> mStatuses = new Vector<Spanned>();
	private String currentlyDisplayedUID;

	public static StatusesTabFragment newInstance() {
		StatusesTabFragment fragment = new StatusesTabFragment();
		return fragment;
	}

	@Override
	public void onResume() {
		Log.d("TAG", "StatusesTabFragment - onResume");
		super.onResume();
		parseLinks();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onPause() {
		super.onStop();
	}

	private void parseLinks() {
		if (!Extra.CURRENT_FRIEND_UID.equals(currentlyDisplayedUID)) {
			mLinks.clear();
			mStatuses.clear();
			mLinks = Extra.mFacebookData.getLinks(Extra.CURRENT_FRIEND_UID);
			Log.d("TAG", "StatusesTabFragment - parseLinks() - mLinks.size() = " + mLinks.size());
			Collections.sort(mLinks);
			for (Link link : mLinks) {
				if (link.url != null && link.linkTitle != null) {
					String linkUrl = link.url.replaceAll("gdata.youtube.com/feeds/api/videos/", "www.youtube.com/watch?v=");
					mStatuses.add(Html.fromHtml(link.status + "\n<a href=" + linkUrl + ">" + link.linkTitle + "</a>"));
				} else
					mStatuses.add(Html.fromHtml(link.status));
			}
			currentlyDisplayedUID = Extra.CURRENT_FRIEND_UID;
			Log.d("TAG", "StatusesTabFragment - parseLinks - parsed " + mStatuses.size() + " statuses of " + Extra.CURRENT_FRIEND_UID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("TAG", "StatusesTabFragment - onCreateView");
		parseLinks();
		View view = inflater.inflate(R.layout.statusestabfragment, container, false);
		ListView listView = (ListView) view.findViewById(R.id.statusestabfragment_listview);
		listView.setAdapter(new StatusArrayAdapter(getActivity(), mStatuses.toArray(new Spanned[mStatuses.size()])));
		return view;
	}

	public class StatusArrayAdapter extends ArrayAdapter<Spanned> {

		private Spanned[] values;

		public StatusArrayAdapter(Context context, Spanned[] values) {
			super(context, R.layout.statusestabfragment_rowlayout, values);
			this.values = values;
		}

		// TODO might have an issue like
		// http://stackoverflow.com/a/4409383/1357509 or
		// http://stackoverflow.com/a/10973709/1357509
		// original tutorial here
		// http://jtomlinson.blogspot.sk/2010/03/textview-and-html.html

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if (convertView == null) {
				textView = (TextView) getActivity().getLayoutInflater().inflate(R.layout.statusestabfragment_rowlayout, parent, false);
			} else {
				textView = (TextView) convertView;
			}
			textView.setMovementMethod(LinkMovementMethod.getInstance());
			textView.setText(position < mStatuses.size() ? mStatuses.get(position) : values[position]);
			return textView;
		}
	}
}