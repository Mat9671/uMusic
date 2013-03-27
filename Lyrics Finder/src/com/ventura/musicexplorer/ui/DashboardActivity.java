package com.ventura.musicexplorer.ui;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.ventura.musicexplorer.R;

public class DashboardActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.dashboard);
	}

	public void onMusicPlayerButtonClick(View button) {
		alert(R.string.message_coming_soon);
	}

	public void onSearchArtistButtonClick(View button) {
		this.startActivity(new Intent(this, MusicInfoActivity.class));
	}

	public void onSearchLyricsButtonClick(View button) {
		alert(R.string.message_coming_soon);
	}

	public void onAboutButtonClick(View button) {
		this.startActivity(new Intent(this, AboutActivity_.class));
	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		Window window = getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
}
