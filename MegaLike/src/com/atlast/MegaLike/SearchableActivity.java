package com.atlast.MegaLike;

import java.util.Arrays;
import java.util.Collections;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchableActivity extends SherlockActivity {
	private ListView mList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchable);

		mList = (ListView) findViewById(R.id.searchable_list);

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
		}
	}

	private void doMySearch(String query) {
		String[] values = new String[] { "Tuan", "Rasto", "Mato", "Shaan", "WebOS", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2" };
		Collections.shuffle(Arrays.asList(values));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		mList.setAdapter(adapter);
		// Implement this later mList.setOnItemClickListener(adapter);
	}
}
