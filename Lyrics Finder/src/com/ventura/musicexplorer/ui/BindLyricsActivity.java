package com.ventura.musicexplorer.ui;

import java.io.IOException;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagConstant;
import org.farng.mp3.TagException;
import org.jmusixmatch.entity.lyrics.Lyrics;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ventura.androidutils.utils.ConnectionManager;
import com.ventura.musicexplorer.R;
import com.ventura.musicexplorer.constants.GlobalConstants;
import com.ventura.musicexplorer.constants.RequestCodes;
import com.ventura.musicexplorer.lyrdb.ui.ListLyricsActivity;
import com.ventura.musicexplorer.lyrics.Lyric;

public class BindLyricsActivity extends BaseActivity {

	final String TAG = getClass().getName();

	private Button mAcceptLyricsButton;
	private Button mEditLyricsButton;
	private TextView mLyricsTextView;
	private EditText mLyricsTextField;
	private MP3File mCurrentMP3File;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_bind_lyrics);
		mAcceptLyricsButton = (Button) this
				.findViewById(R.id.btn_accept_lyrics);
		mEditLyricsButton = (Button) this.findViewById(R.id.btn_edit_lyrics);
		mLyricsTextView = (TextView) this.findViewById(R.id.lyrics_text_view);
		mLyricsTextField = (EditText) this.findViewById(R.id.lyrics_text_field);

		Intent intent = getIntent();
		String action = intent.getAction();

		if (!action.equals(Intent.ACTION_SEND)) {
			finish();
		}

		if (!ConnectionManager.isConnected(this)) {
			Toast.makeText(getBaseContext(),
					getString(R.string.message_no_internet_connection),
					Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		Uri sharedPath = intent.getData();
		if (sharedPath == null && intent.getExtras() != null) {
			sharedPath = (Uri) intent.getExtras().get(Intent.EXTRA_STREAM);
		}

		try {
			mCurrentMP3File = new MP3File(this.getSharedFile(sharedPath));
		} catch (IOException e) {
			Log.i(TAG, "IOException - Error instantiating MP3File from path "
					+ sharedPath + ". Error message: " + e.getMessage());
		} catch (TagException e) {
			Log.i(TAG, "TagException - Error instantiating MP3File from path "
					+ sharedPath + ". Error message: " + e.getMessage());
		}

		if (!mCurrentMP3File.hasID3v2Tag()) {
			Toast.makeText(getBaseContext(),
					getString(R.string.message_file_not_supported),
					Toast.LENGTH_SHORT).show();
			finish();
		} else {
			if (mCurrentMP3File.getID3v2Tag().getSongLyric().length() > 0) {
				Toast.makeText(getBaseContext(),
						"This song already have lyrics.", Toast.LENGTH_SHORT)
						.show();
			}
			Intent lyricsSearchIntent = new Intent(this,
					ListLyricsActivity.class);
			lyricsSearchIntent.putExtra(GlobalConstants.EXTRA_ARTIST_NAME,
					this.mCurrentMP3File.getID3v2Tag().getLeadArtist());
			lyricsSearchIntent.putExtra(GlobalConstants.EXTRA_TRACK_NAME,
					this.mCurrentMP3File.getID3v2Tag().getSongTitle());
			this.startActivityForResult(lyricsSearchIntent,
					RequestCodes.GET_LYRICS);
		}

		mAcceptLyricsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String lyrics = null;
				if (mLyricsTextView.getVisibility() == View.VISIBLE) {
					lyrics = mLyricsTextView.getText().toString();
				} else {
					lyrics = mLyricsTextField.getText().toString();
				}

				saveLyrics(mCurrentMP3File, new Lyric(null, null, lyrics));
			}
		});

		mEditLyricsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mEditLyricsButton.setVisibility(View.GONE);
				mLyricsTextView.setVisibility(View.GONE);
				mLyricsTextField.setVisibility(View.VISIBLE);

				mLyricsTextField.setText(mLyricsTextView.getText());
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == RequestCodes.GET_LYRICS) {
			if (resultCode == RESULT_OK) {
				Lyrics lyr = new Lyrics();
				lyr.setLyricsBody(data
						.getStringExtra(GlobalConstants.EXTRA_TRACK_LYRICS));
				this.setLyrics(lyr);
			} else if (resultCode == RESULT_CANCELED) {
				finish();
			}
		}
	}

	private void setLyrics(Lyrics lyrics) {
		Log.i(TAG, "Found Lyrics: \r\n" + lyrics.getLyricsBody());
		mLyricsTextView.setText(lyrics.getLyricsBody());
		mAcceptLyricsButton.setVisibility(View.VISIBLE);
		mEditLyricsButton.setVisibility(View.VISIBLE);
	}

	private void saveLyrics(MP3File file, Lyric lyrics) {
		file.getID3v2Tag().setSongLyric(lyrics.getLyric());

		try {
			file.save(TagConstant.MP3_FILE_SAVE_OVERWRITE);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		}

		Toast.makeText(getBaseContext(),
				getString(R.string.message_lyrics_saved), Toast.LENGTH_SHORT)
				.show();
		finish();
	}
}
