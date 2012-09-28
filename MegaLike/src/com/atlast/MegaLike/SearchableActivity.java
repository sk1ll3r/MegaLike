package com.atlast.MegaLike;

import com.actionbarsherlock.app.SherlockListActivity;
import com.atlast.MegaLike.FacebookLogic.FQLFriend;
import com.atlast.MegaLike.Lib.Extra;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchableActivity extends SherlockListActivity {
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String uid = ((FQLFriend) getListAdapter().getItem(position)).uid;
		Extra.CURRENT_FRIEND_UID = uid;
		finish();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handleIntent(getIntent());
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		handleIntent(intent);
	}

	private void handleIntent(Intent intent) {
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);
			doMySearch(query);
		} else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
			// Handle a suggestions click (because the suggestions all use ACTION_VIEW
			String uid = intent.getDataString();
			Extra.CURRENT_FRIEND_UID = uid;
			finish();
		}
	}

	private void doMySearch(String query) {
		ArrayAdapter<FQLFriend> adapter = new ArrayAdapter<FQLFriend>(this, android.R.layout.simple_list_item_1, Extra.mFacebookData.getMatches(query));
		setListAdapter(adapter);
	}
}
