package com.example.lastdino;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {

	private final static String TAG ="GridViewAdapter";
	Context context = null;
	Uri[] imageIDs = null;
	public GridViewAdapter(Context context, Uri[] imageIDs)
	{
		this.context = context;
		this.imageIDs = imageIDs;
		
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return (null !=imageIDs) ?imageIDs.length:0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (null != imageIDs)? imageIDs[position] :0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = null;
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.grid_item, null);
		
		if(null !=convertView)
		{
			v=convertView;
		}
		else
		{
			
			imageView = (ImageView)v.findViewById(R.id.grid_item_img);
			imageView.setImageURI(imageIDs[position]);
//			imageView = new ImageView(context);
//			imageView.setMinimumHeight(100);
//			imageView.setMinimumWidth(120);
//			imageView.setMaxHeight(100);
//			imageView.setMaxWidth(120);
//			imageView.setImageURI(imageIDs[position]);
//			
//			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			//ImageClickListener imageViewClickListener
            //= new ImageClickListener(context, imageIDs[position], position);
		
        //imageView.setOnClickListener(imageViewClickListener);
        
        	
		}
		
		return v;
	}

}
