package com.example.user.gotwalp;

import android.app.Application;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by AramSadoyan on 2/1/18.
 */

public class AdmobApplication extends Application {

	public InterstitialAd interstitialAd;

	public void createWallAd() {
		interstitialAd = new InterstitialAd(this);
		//interstitialAd.setAdUnitId(getResources().getString(R.string.banner_ad_unit_id));
	}

	public void requestNewInterstitial() {
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice("98020A4CA653CBDED5EB3A03818ED4B1")
				.build();
		interstitialAd.loadAd(adRequest);
	}

	public boolean isAdLoaded() {
		return interstitialAd.isLoaded();
	}

	public void displayLoadedAd() {
		interstitialAd.show();
	}
}
