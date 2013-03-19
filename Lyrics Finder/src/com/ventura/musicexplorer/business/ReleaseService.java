package com.ventura.musicexplorer.business;

import android.content.Context;

import com.ventura.androidutils.exception.LazyInternetConnectionException;
import com.ventura.androidutils.exception.NoInternetConnectionException;
import com.ventura.musicexplorer.R;
import com.ventura.musicexplorer.entity.release.Master;
import com.ventura.musicexplorer.entity.release.Release;

public class ReleaseService extends BaseService {

	public ReleaseService(Context context) {
		super(context);
	}

	public Release getRelease(int releaseId)
			throws NoInternetConnectionException,
			LazyInternetConnectionException {

		String requestUrl = String.format(
				this.getContext().getString(R.string.get_release_by_id_url),
				String.valueOf(releaseId));

		String jsonResponse = this.doGet(requestUrl);

		Release release = null;

		try {
			release = this.deserialize(jsonResponse, Release.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return release;
	}

	public Master getMaster(int masterId) throws NoInternetConnectionException,
			LazyInternetConnectionException {

		String requestUrl = String.format(
				this.getContext().getString(R.string.get_master_by_id_url),
				String.valueOf(masterId));

		String jsonResponse = this.doGet(requestUrl);

		Master master = null;

		try {
			master = this.deserialize(jsonResponse, Master.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return master;
	}
}
