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

	private Context context;

	MoreListAdapter(@NonNull Context context, @LayoutRes int resource, List moreAppParams) {
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

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		if(infoParam == null){
			return convertView;
		}
		infoParam.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
		Glide.with(context)
				.load(stream.toByteArray())
				.asBitmap()
				.into(viewHolder.imgInfo);
		viewHolder.txtName.setText(infoParam.getTitle());
		return convertView;
	}

	private static class ViewHolder {
		TextView txtName;
		ImageView imgInfo;
	}
}
