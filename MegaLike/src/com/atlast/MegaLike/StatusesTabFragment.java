package com.atlast.MegaLike;

import java.util.Collections;
import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.atlast.MegaLike.FacebookLogic.Link;
import com.atlast.MegaLike.Lib.Extra;

public final class StatusesTabFragment extends Fragment {
	private Vector<Link> mLinks;
	private Vector<String> mStatuses = new Vector<String>();
	
	public static StatusesTabFragment newInstance() {
		StatusesTabFragment fragment = new StatusesTabFragment();
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mLinks = Extra.mFacebookData.getLinks(Extra.CURRENT_FRIEND_UID);
		Collections.sort(mLinks);
		for(Link link : mLinks)
			mStatuses.add(link.status);
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
		
			
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, mStatuses.toArray(new String[mStatuses.size()]));
		listView.setAdapter(adapter);
		return view;
	}
}
