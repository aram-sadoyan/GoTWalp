package com.example.user.gotwalp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MoreActivity extends AppCompatActivity {


	public ArrayList<InfoParams> infoParamses = new ArrayList<>();


	private List moreAppParams = new ArrayList<>();
	private List bitmaps = new ArrayList<>();

	private String PACKAGE_NAME = "";
    private ListView listView;
	private int ACTION = 0;
	private int TITLE = 1;
	private int IMAGE_URL = 2;
	private int removePosition = -1;

	ListView lvSimple;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		PACKAGE_NAME = getApplicationContext().getPackageName();
		lvSimple = findViewById(R.id.lv);
		listView = findViewById(R.id.item_list);
	//	moreAppParams = getIntent().getParcelableArrayListExtra("moreAppParms");
		infoParamses = getIntent().getParcelableArrayListExtra("moreAppParms");

		for (int i = 0; i < infoParamses.size(); i++) {
			if (PACKAGE_NAME.equals(infoParamses.get(i).getAction())) {
				removePosition = i;
			}
		}
//
		if(removePosition != -1){
			infoParamses.remove(removePosition);
		}

		MoreListAdapter moreListAdapter = new MoreListAdapter(this,R.layout.list_item, infoParamses);
		moreListAdapter.setPackageName(PACKAGE_NAME);
		listView.setAdapter(moreListAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String a =  infoParamses.get(position).getAction();
				goToStore(a);
			}
		});





//

//		//TODO set imageView with url
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				IMAGE_URL url = null;
//				try {
//					url = new IMAGE_URL(a[2]);
//					bmp = null;
//					bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//		imageView.setImageBitmap(bmp);

//		String[] texts = { "sometext 1", "sometext 2", "sometext 3",
//				"sometext 4", "sometext 5" };
//		boolean[] checked = { true, false, false, true, false };
//		int img = R.drawable.growth_ripple;

//		ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(moreAppParams.size());
//		Map<String, Object> m;

//		for (Object mP : moreAppParams) {
//			m = new HashMap<String, Object>();
//			m.put(ATTRIBUTE_NAME_TEXT, text);
//			m.put(ATTRIBUTE_NAME_IMAGE, img);
//			data.add(m);
//		}



//		String[] from = {ATTRIBUTE_NAME_TEXT,
//				ATTRIBUTE_NAME_IMAGE};
//		int[] to = {R.id.tvText, R.id.ivImg};

	//	SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

		//lvSimple = findViewById(R.id.lv);
		//lvSimple.setAdapter(sAdapter);

//		lvSimple.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				if (parent.getAdapter() == null || parent.getAdapter().getItem(position) == null) {
//					return;
//				}
//				final Map<String, String> m = (Map<String, String>) parent.getAdapter().getItem(position);
//				//goToStore(m.get(ATTRIBUTE_ACTION));
//
////				Thread thread = new Thread(new Runnable() {
////					URL url = null;
////					@Override
////					public void run() {
////						try  {
////							url = new URL(m.get(ATTRIBUTE_NAME_IMAGE));
////							bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
////						} catch (IOException e) {
////							e.printStackTrace();
////						}
////					}
////				});
////				thread.start();
////				u.setImageBitmap(bmp);
//			}
//		});





	}


	private void goToStore(String packageName) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
		} catch (android.content.ActivityNotFoundException anfe) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
		}

	}



//	private void initLists(){
//		LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
//		View v = inflater.inflate(R.layout.list_item, subsButtonContainer, false);
//	}
}
