package com.studiow.user.bawalp;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AramSadoyan on 2/27/18.
 */

public class InfoParams implements Parcelable {

	private String title;
	private String action;
	private Bitmap imageBitmap;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Bitmap getImageBitmap() {
		return imageBitmap;
	}

	public void setImageBitmap(Bitmap imageBitmap) {
		this.imageBitmap = imageBitmap;
	}


	public InfoParams(){

	}

	protected InfoParams(Parcel in){
		title = in.readString();
     	action = in.readString();
		imageBitmap = in.readParcelable(getClass().getClassLoader());
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(action);
		dest.writeParcelable(imageBitmap, flags);
	}

	public static Creator<InfoParams  > CREATOR = new Creator<InfoParams  >() {

		@Override
		public InfoParams  createFromParcel(Parcel source) {
			return new InfoParams  (source);
		}

		@Override
		public InfoParams  [] newArray(int size) {
			return new InfoParams  [size];
		}

	};
}
