package com.studiow.user.bawalp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * Created by AramSadoyan on 2/26/18.
 */

public class MoreListAdapter extends ArrayAdapter<InfoParams> {


	private Context context = null;


	public MoreListAdapter(@NonNull Context context, @LayoutRes int resource, List moreAppParams) {
		super(context, resource, moreAppParams);
		this.context = context;

	}


	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		InfoParams infoParam = getItem(position);

		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			LayoutInflater vi = LayoutInflater.from(getContext());
			convertView = vi.inflate(R.layout.list_item, parent, false);
			viewHolder.txtName = convertView.findViewById(R.id.textV);
			viewHolder.imgInfo = convertView.findViewById(R.id.img);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.txtName.setText(infoParam.getTitle());

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		infoParam.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
		Glide.with(context)
				.load(stream.toByteArray())
				.asBitmap()
				.into(viewHolder.imgInfo);


//		//if (!packageName.equals(dataModel[0])) {
//		//	viewHolder.txtName.setText(dataModel[1]);
//			Glide.with(context)
//					.load("https://firebasestorage.googleapis.com/v0/b/wallpapers-63650.appspot.com/o/images%2Fgicon.jpg?alt=media&token=5d5dbe1d-1824-4c04-8537-af7656682418")
//					.into(viewHolder.imgInfo);
//		//}

		return convertView;
	}


	private static class ViewHolder {
		TextView txtName;

		ImageView imgInfo;
	}



}
