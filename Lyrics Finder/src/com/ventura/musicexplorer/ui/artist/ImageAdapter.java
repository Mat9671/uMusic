package com.ventura.musicexplorer.ui.artist;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.ventura.musicexplorer.R;
import com.ventura.musicexplorer.entity.Image;

public class ImageAdapter extends BaseAdapter {

	// use the default gallery background image
	int defaultItemBackground;
	// gallery context
	private Context galleryContext;
	// array to store bitmaps to display
	private List<Image> images;
	// placeholder bitmap for empty spaces in gallery
	Bitmap placeholder;

	public ImageAdapter(Context context, List<Image> images) {
		// instantiate context
		galleryContext = context;
		this.images = images;
		// decode the placeholder image
		placeholder = BitmapFactory.decodeResource(
				galleryContext.getResources(), R.drawable.no_image);
		// set placeholder as all thumbnail images in the gallery initially
		/*for (int i = 0; i < images.size(); i++)
			this.images.get(i).setBitmap(placeholder);*/
	}

	@Override
	public int getCount() {
		return this.images.size();
	}

	@Override
	public Object getItem(int position) {
		return this.images.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// get view specifies layout and display options for each thumbnail in the
	// gallery
	public View getView(int position, View convertView, ViewGroup parent) {
		// create the view
		ImageView imageView = new ImageView(galleryContext);

		// set layout options
		imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
		// scale type within view area
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		// set default gallery item background
		imageView.setBackgroundResource(defaultItemBackground);

		final int pos = position;
		UrlImageViewHelper.setUrlDrawable(imageView, images.get(position)
				.getUrl().toString(), R.drawable.no_image, 2 * 60 * 100,
				new UrlImageViewCallback() {
					@Override
					public void onLoaded(ImageView imgView, Bitmap bitmap,
							String url, boolean loadedFromCache) {
						/*images.get(pos).setBitmap(bitmap);*/
					}
				});
		// return the view
		return imageView;
	}

}
