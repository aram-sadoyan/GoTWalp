package com.example.user.gotwalp;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

	//1 Make a note of your new app ID. You'll need to add it to your app's source code to run AdMob.
	//ca-app-pub-6630049462645854~6334922802
	//Create an ad unit to display ads in your app.
	//If your app is published to Google Play or the App Store, remember to come back to link your app.


//	Banner	ca-app-pub-3940256099942544/6300978111
//	Interstitial	ca-app-pub-3940256099942544/1033173712
//	Rewarded Video	ca-app-pub-3940256099942544/5224354917
//	Native Advanced	ca-app-pub-3940256099942544/2247696110
//	Native Express	(Small template): ca-app-pub-3940256099942544/2793859312
//			(Large template): ca-app-pub-3940256099942544/2177258514


	//for test app id ca-app-pub-3940256099942544~3347511713;

	private AdView adView = null;

	private String testBannerAd = "ca-app-pub-3940256099942544/6300978111";

	private LinearLayout imageContainer = null;
	private TextView textView = null;
	private List<Bitmap> bitmaps = new ArrayList<>();
	private int currentPos = 0;
	private int bitMapSize = 0;
	private Locale locale;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		locale = new Locale(Locale.ENGLISH.getLanguage());
		RelativeLayout rootLayout = findViewById(R.id.root_layout);
		imageContainer = findViewById(R.id.image_container);
		MobileAds.initialize(this, getString(R.string.admob_app_id));
		AssetManager assetManager = getAssets();
		adView = findViewById(R.id.adView);
		Button pre = findViewById(R.id.left);
		Button ok = findViewById(R.id.ok);
		Button next = findViewById(R.id.right);
		textView = findViewById(R.id.textView1);

		String[] imgPath = new String[0];
		try {
			imgPath = assetManager.list("bImage");
			for (int i = 0; i < imgPath.length; i++) {
				InputStream is = assetManager.open("bImage/" + imgPath[i]);
				Bitmap bitmap = BitmapFactory.decodeStream(is);
				bitmaps.add(bitmap);
				bitMapSize = bitmaps.size();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (currentPos == bitMapSize - 1) {
					currentPos = 0;
					initViewByPos(currentPos);
				} else {
					initViewByPos(currentPos + 1);
				}
			}
		});

		pre.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				initViewByPos(currentPos - (currentPos == 0 ? (-bitMapSize + 1) : 1));
			}
		});


		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

		initViewByPos(0);

		final AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				//TODO REMOVE FOR RELEASE
				.addTestDevice("98020A4CA653CBDED5EB3A03818ED4B1")
				.build();

		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				super.onAdLoaded();

				Log.d("dwd", "onAdLoaded");
			}

			@Override
			public void onAdClosed() {
				super.onAdClosed();
				Log.d("dwd", "onAdClosed");

			}

			@Override
			public void onAdFailedToLoad(int i) {
				super.onAdFailedToLoad(i);
				Log.d("dwd", "onAdFailedToLoad");

			}

			@Override
			public void onAdLeftApplication() {
				super.onAdLeftApplication();
			}

			@Override
			public void onAdOpened() {
				super.onAdOpened();
				Log.d("dwd", "onAdOpened");

			}
		});

		adView.loadAd(adRequest);


	}

	private void initViewByPos(int i) {
		ImageView imageView = new ImageView(getApplicationContext());
		imageView.setImageBitmap(bitmaps.get(i));
		ViewGroup.LayoutParams params = new LinearLayout
				.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		imageView.setLayoutParams(params);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageContainer.removeAllViews();
		imageContainer.addView(imageView);

		currentPos = i;
		textView.setText(String.valueOf(i));

	}


	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}

}