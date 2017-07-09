package com.wmc.aofstudymap;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class FirstThreeArticleActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private final String TAG = FirstThreeArticleActivity.class.getSimpleName();
    private GestureDetectorCompat mDetector;

    ImageButton mNextButton;
    ImageButton mPlayPauseButton;
    ImageButton mReplayButton;
    ImageButton mHomeButton;

    ImageView mFirstThreeArticlesImageView;

    TextView mCaptionTextView;
    MediaPlayer mPlayer;

    boolean isPaused;
    boolean mBound;

    final Handler menu_handler = new Handler();
    boolean buttons_visible = false;

    private void hideButtons() {
        buttons_visible = false;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hide_buttons);
        animation.reset();
        mHomeButton.startAnimation(animation);
        mHomeButton.setVisibility(View.INVISIBLE);

        mPlayPauseButton.startAnimation(animation);
        mPlayPauseButton.setVisibility(View.INVISIBLE);

        mReplayButton.startAnimation(animation);
        mReplayButton.setVisibility(View.INVISIBLE);

        mNextButton.startAnimation(animation);
        mNextButton.setVisibility(View.INVISIBLE);
    }

    private void displayButtons() {
        buttons_visible = true;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.show_buttons);
        animation.reset();

        mHomeButton.clearAnimation();
        mHomeButton.startAnimation(animation);
        mHomeButton.setVisibility(View.VISIBLE);

        mPlayPauseButton.clearAnimation();
        mPlayPauseButton.startAnimation(animation);
        mPlayPauseButton.setVisibility(View.VISIBLE);

        mReplayButton.clearAnimation();
        mReplayButton.startAnimation(animation);
        mReplayButton.setVisibility(View.VISIBLE);

        mNextButton.clearAnimation();
        mNextButton.startAnimation(animation);
        mNextButton.setVisibility(View.VISIBLE);
    }

    Runnable button_hide_thread = new Runnable() {
        public void run() {
            hideButtons();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_three_article);
        isPaused = false;

        try {
            mFirstThreeArticlesImageView = (ImageView) findViewById(R.id.firstThreeArticlesImageView);

            mDetector = new GestureDetectorCompat(this, this);
            mDetector.setOnDoubleTapListener(this);

            // Home Button
            mHomeButton = (ImageButton) findViewById(R.id.btnHome);
            mHomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            mHomeButton.setVisibility(View.INVISIBLE);

            // Play Button
            mPlayPauseButton = (ImageButton) findViewById(R.id.btnPlay);
            mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (mPlayer.isPlaying() && !isPaused) {
                            mPlayer.pause();
                            isPaused = true;
                            mPlayPauseButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                        } else {
                            mPlayer.start();
                            isPaused = false;
                            mPlayPauseButton.setImageResource(R.drawable.ic_pause_black_36dp);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mPlayPauseButton.setVisibility(View.INVISIBLE);

            // Replay Button
            mReplayButton = (ImageButton) findViewById(R.id.btnReplay);
            mReplayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Log.v(TAG, "Player is currently playing: " + mPlayer.isPlaying());
                        mPlayPauseButton.setImageResource(R.drawable.ic_pause_black_36dp);

                        mPlayer.stop();
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.one_to_three);
                        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mPlayer.stop();
                                mPlayPauseButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                            }
                        });
                        mPlayer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mReplayButton.setVisibility(View.INVISIBLE);

            mCaptionTextView = (TextView) findViewById(R.id.txtViewArticleOntToThreeContent);
            mCaptionTextView.setMovementMethod(new ScrollingMovementMethod());

            mNextButton = (ImageButton) findViewById(R.id.btnRightArrow);
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), TriuneGodActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
            mNextButton.setVisibility(View.INVISIBLE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
            mFirstThreeArticlesImageView.startAnimation(fade);

            mPlayer = MediaPlayer.create(this, R.raw.one_to_three);
            mPlayer.setVolume(1.0f, 1.0f);
            mPlayer.start();
            isPaused = false;
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    try {
                        mPlayPauseButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            mPlayPauseButton.setImageResource(R.drawable.ic_pause_black_36dp);

            if (!mBound) {
                AppController.getInstance().startService(getApplicationContext());
                mBound = true;
            }
            Log.v(TAG, "onResume: Service running? " + AppUtility.isMyServiceRunning(BackgroundMusicService.class,
                    getApplicationContext()) + " Bound: " + mBound);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mPlayer.stop();
            mPlayer.reset();
            mPlayer.release();
            mPlayer = null;

            Log.v(TAG, "onPause: Service running? " +
                    AppUtility.isMyServiceRunning(BackgroundMusicService.class, getApplicationContext()) +
                    " | Finishing? " + isFinishing() +
                    " is BOUND? " + mBound);

            if (this.isFinishing() || isFinishing()) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }

            if (AppUtility.isApplicationSentToBackground(getApplicationContext())) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }

            Log.v(TAG, "onPause: Service running? " +
                    AppUtility.isMyServiceRunning(BackgroundMusicService.class, getApplicationContext()) +
                    " | Finishing? " + isFinishing() +
                    " is BOUND? " + mBound);

            Log.v(TAG, "Sent to background: " + AppUtility.isApplicationSentToBackground(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (this.isFinishing() || isFinishing()) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }

            Log.v(TAG, "onDestroy: Service running? " + AppUtility.isMyServiceRunning(BackgroundMusicService.class,
                    getApplicationContext()) + " Bound: " + mBound);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_three_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (this.isFinishing() || isFinishing()) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }
            if (AppUtility.isApplicationSentToBackground(getApplicationContext())) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }
            Log.v(TAG, "onStop: Service running? " + AppUtility.isMyServiceRunning(BackgroundMusicService.class,
                    getApplicationContext()) + " Bound: " + mBound + " isFinishing: " + isFinishing() +
                    " this.isFinishing: " + this.isFinishing());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Intent intent = new Intent(getApplicationContext(), AllArticlesActivity.class);
        startActivity(intent);
        Log.d(TAG, "onDoubleTap: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(TAG, "onDoubleTapEvent: " + e.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            if (!buttons_visible)
                displayButtons();
            menu_handler.postDelayed(button_hide_thread, 5000);
        } else {
            menu_handler.removeCallbacks(button_hide_thread);
            menu_handler.postDelayed(button_hide_thread, 5000);
        }
        Log.d(TAG, "onDown: " + e.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(TAG, "onShowPress: " + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(TAG, "onSingleTapUp: " + e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(TAG, "onScroll: " + e1.toString() + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(TAG, "onLongPress: " + e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(TAG, "onFling: " + e1.toString() + e2.toString());
        return true;
    }
}
