package com.ventura.lyricsfinder.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

public abstract class ConnectionManager {

	public static boolean isConnected(Context context) {
		String TAG = ConnectionManager.class.getName();
		
		try {
			ConnectivityManager cm = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
					.isConnected()) { // Status de conex�o 3G
				Log.i(TAG,
						"Status de conex�o 3G: "
								+ cm.getNetworkInfo(
										ConnectivityManager.TYPE_MOBILE)
										.isConnected());
				return true;
			} else if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					.isConnected()) { // Status de conex�o 3G
				Log.i(TAG,
						"Status de conex�o Wifi: "
								+ cm.getNetworkInfo(
										ConnectivityManager.TYPE_WIFI)
										.isConnected());
				return true;
			} else { // N�o possui conex�o com a internet
				Log.i(TAG,
						"Status de conex�o Wifi: "
								+ cm.getNetworkInfo(
										ConnectivityManager.TYPE_WIFI)
										.isConnected());
				Log.i(TAG,
						"Status de conex�o 3G: "
								+ cm.getNetworkInfo(
										ConnectivityManager.TYPE_MOBILE)
										.isConnected());
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}
