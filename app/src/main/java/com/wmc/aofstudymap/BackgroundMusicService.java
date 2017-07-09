package com.wmc.aofstudymap;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by admiralato on 9/19/15.
 */

public class BackgroundMusicService extends Service {

    // TODO Stop service when HOME button is pressed
    public MediaPlayer mPlayer;
    private final IBinder mBinder = new BackgroundMusicServiceBinder();
    private static BackgroundMusicService mInstance = null;

    public class BackgroundMusicServiceBinder extends Binder {
        BackgroundMusicService getBackgroundMusicService() {
            if(mPlayer == null) {
                try {
                    mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bg_music);
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mPlayer.start();
                        }
                    });
//                    mPlayer.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mPlayer.setLooping(true);
                mPlayer.setVolume(0.4f, 0.4f);
                mPlayer.start();
            }
            return BackgroundMusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mPlayer.stop();
        mPlayer.release();
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
