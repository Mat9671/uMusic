package com.ventura.umusic.business;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpException;

import android.content.Context;

import com.ventura.androidutils.exception.LazyInternetConnectionException;
import com.ventura.androidutils.exception.NoInternetConnectionException;
import com.ventura.umusic.entity.music.Lyrics;

public class LyricsService extends BaseService {

	protected static final String URL_GET_LYRICS = "/lyrics/?artist=%1$s&song=%2$s";

	public LyricsService(Context context) {
		super(context);
	}

	public Lyrics getLyrics(String artist, String song)
			throws NoInternetConnectionException,
			LazyInternetConnectionException, HttpException {
		String url = null;
		try {
			url = String.format(ArtistService.URL_BASE_API
					+ LyricsService.URL_GET_LYRICS,
					URLEncoder.encode(artist, "UTF-8"),
					URLEncoder.encode(song, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String jsonResponse = this.doGet(url);
		Lyrics lyrics = null;
		try {
			lyrics = this.deserialize(jsonResponse, Lyrics.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lyrics;
	}

	public Lyrics getLyrics(Lyrics lyric) throws NoInternetConnectionException,
			LazyInternetConnectionException, HttpException {
		if (lyric != null)
			return getLyrics(lyric.getArtistName(), lyric.getMusicName());
		return null;
	}
}
