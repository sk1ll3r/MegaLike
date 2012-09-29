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
	private Vector<Link> mLinks;
	private Vector<Spanned> mStatuses = new Vector<Spanned>();

	public static StatusesTabFragment newInstance() {
		StatusesTabFragment fragment = new StatusesTabFragment();
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mLinks = Extra.mFacebookData.getLinks(Extra.CURRENT_FRIEND_UID);
		Collections.sort(mLinks);
		for (Link link : mLinks) {
			if (link.url != null && link.linkTitle != null) {
				String linkUrl = link.url.replaceAll("gdata.youtube.com/feeds/api/videos/", "www.youtube.com/watch?v=");
				mStatuses.add(Html.fromHtml(link.status + "\n<a href=" + linkUrl + ">" + link.linkTitle + "</a>"));
			} else
				mStatuses.add(Html.fromHtml(link.status));
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.statusestabfragment, container, false);
		ListView listView = (ListView) view.findViewById(R.id.statusestabfragment_listview);

		StatusArrayAdapter adapter = new StatusArrayAdapter(getActivity(), mStatuses.toArray(new Spanned[mStatuses.size()]));
		listView.setAdapter(adapter);
		return view;
	}

	public class StatusArrayAdapter extends ArrayAdapter<Spanned> {
		// TODO might have an issue like http://stackoverflow.com/a/4409383/1357509
		private final Context context;
		private final Spanned[] values;

		public StatusArrayAdapter(Context context, Spanned[] values) {
			super(context, R.layout.statusestabfragment_rowlayout, values);
			this.context = context;
			this.values = values;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView textView;
			if (convertView == null) {
				textView = (TextView) getActivity().getLayoutInflater().inflate(R.layout.statusestabfragment_rowlayout, parent, false);
			} else {
				textView = (TextView) convertView;
			}
			textView.setMovementMethod(LinkMovementMethod.getInstance());
			textView.setText(values[position]);
			return textView;
		}
	}
}