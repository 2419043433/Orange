package com.orange.os.android.interfaces;

public interface IActivityDelegate
{
	void onCreate();
	void onDestroy();
	void onStart();
	void onStop();
	void onPause();
	void onResume();
}