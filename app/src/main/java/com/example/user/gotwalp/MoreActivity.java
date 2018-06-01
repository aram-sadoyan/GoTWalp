package com.example.user.gotwalp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MoreActivity extends AppCompatActivity {

	public ArrayList<InfoParams> infoParamses = new ArrayList<>();
	private String PACKAGE_NAME = "";
	private int removePosition = -1;
	ListView lvSimple;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		PACKAGE_NAME = getApplicationContext().getPackageName();
		lvSimple = findViewById(R.id.lv);
		ListView listView = findViewById(R.id.item_list);
		infoParamses = getIntent().getParcelableArrayListExtra("moreAppParms");

		for (int i = 0; i < infoParamses.size(); i++) {
			if (PACKAGE_NAME.equals(infoParamses.get(i).getAction())) {
				removePosition = i;
			}
		}
		if (removePosition != -1) {
			infoParamses.remove(removePosition);
		}

		MoreListAdapter moreListAdapter = new MoreListAdapter(this, R.layout.list_item, infoParamses);
		//moreListAdapter.setPackageName();
		listView.setAdapter(moreListAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String a = infoParamses.get(position).getAction();
				goToStore(a);
			}
		});
	}


	private void goToStore(String packageName) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
		}
	}
}
