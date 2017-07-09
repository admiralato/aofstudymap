package com.wmc.aofstudymap;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

/**
 * Created by admiralato on 9/26/15.
 */
public class AppController extends Application {

    private static final String LOG_TAG = AppController.class.getSimpleName();

    private static AppController mInstance;
    Intent intentBgMusic;

    BackgroundMusicService mBackgroundMusicService;
    private boolean mBound = false;

    private AppController() {
        mBound = false;
    }

    public static synchronized AppController getInstance() {
        if(mInstance == null) {
            mInstance = new AppController();
        }

        return mInstance;
    }

    public void stopService(Context context) {
        try {
            if(mBound) context.unbindService(mServiceConnection);
            mBound = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startService(Context context) {
        try {
            if (!mBound) {
                intentBgMusic = new Intent(context, BackgroundMusicService.class);
                context.bindService(intentBgMusic, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
            Log.v(LOG_TAG, "Service started.");
        } catch (Exception e) {
            Log.v(LOG_TAG, e.toString());
            e.printStackTrace();
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BackgroundMusicService.BackgroundMusicServiceBinder binder = (BackgroundMusicService.BackgroundMusicServiceBinder) service;
            mBackgroundMusicService = binder.getBackgroundMusicService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    @Override
    public void onTerminate() {
        unbindService(mServiceConnection);
        super.onTerminate();
    }
}
