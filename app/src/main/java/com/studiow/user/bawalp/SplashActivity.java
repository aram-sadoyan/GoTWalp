package com.studiow.user.bawalp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;

public class SplashActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this, getString(R.string.admob_app_id));
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
