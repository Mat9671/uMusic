package com.ventura.musicexplorer.lyrics.provider;

import java.util.Locale;

import com.ventura.musicexplorer.lyrics.Lyric;
import com.ventura.musicexplorer.lyrics.LyricNotFoundException;

public class TerraLetras extends LyricProvider {
	@Override
	public String decodeLyricHtml(String html, Lyric actualLyric)
			throws LyricNotFoundException {
		String lyric;
		String startingElement = "<p>";
		String endingElement = "</p>";

		String header = html.substring(html.indexOf("<h3>") + 4,
				html.indexOf("</h3>"));

		if (header.equalsIgnoreCase("M�sica n�o encontrada")) {
			throw new LyricNotFoundException();
		} else if (header.equalsIgnoreCase("Prov�vel m�sica")) {
			html = html.substring(html.indexOf(endingElement, 0)
					+ endingElement.length());
		}

		int indexOfStartingElement = html.indexOf(startingElement);
		int[] lyricsRange = new int[] {
				indexOfStartingElement + startingElement.length(),
				html.indexOf(endingElement, indexOfStartingElement) };
		html = html.trim();
		lyric = html.substring(lyricsRange[0], lyricsRange[1]).trim();
		return lyric;
	}

	@Override
	public String encodeToUrl(String target) {
		return target.trim().replace(' ', '+').replace("%26", "&")
				.toLowerCase(Locale.ENGLISH);
	}
}
