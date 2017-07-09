package com.wmc.aofstudymap;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DivineHealingActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private final String TAG = DivineHealingActivity.class.getSimpleName();
    private GestureDetectorCompat mDetector;

    ImageButton mPreviousButton;
    ImageButton mNextButton;
    ImageButton mReplayButton;
    ImageButton mPlayPauseButton;
    ImageButton mHomeButton;
    ImageButton mMoreInfoButton;

    ImageView mDivineHealingImageView;

    boolean isPaused;
    boolean mBound;

    MediaPlayer mPlayer;
    TextView mCaptionTextView;

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

        mPreviousButton.startAnimation(animation);
        mPreviousButton.setVisibility(View.INVISIBLE);

        mMoreInfoButton.startAnimation(animation);
        mMoreInfoButton.setVisibility(View.INVISIBLE);
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

        mPreviousButton.clearAnimation();
        mPreviousButton.startAnimation(animation);
        mPreviousButton.setVisibility(View.VISIBLE);

        mMoreInfoButton.clearAnimation();
        mMoreInfoButton.startAnimation(animation);
        mMoreInfoButton.setVisibility(View.VISIBLE);
    }

    Runnable button_hide_thread = new Runnable() {
        public void run() {
            hideButtons();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divine_healing);

        try {
            mDivineHealingImageView = (ImageView) findViewById(R.id.divineHealingImageView);

            mDetector = new GestureDetectorCompat(this, this);
            mDetector.setOnDoubleTapListener(this);

            // More Info Button
            mMoreInfoButton = (ImageButton) findViewById(R.id.btnMoreInfo);
            mMoreInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // TODO Transfer this to an XML file
                        AlertDialog.Builder builder = new AlertDialog.Builder(DivineHealingActivity.this);
                        TextView title = new TextView(DivineHealingActivity.this);
                        title.setText("XIV. Divine Healing");
                        title.setBackgroundColor(Color.DKGRAY);
                        title.setPadding(10, 10, 10, 10);
                        title.setGravity(Gravity.CENTER);
                        title.setTextColor(Color.WHITE);
                        title.setTextSize(20);
                        builder.setCustomTitle(title);

                        TextView message = new TextView(DivineHealingActivity.this);
                        message.setText(R.string.divineHealingInfo);
                        message.setVerticalScrollBarEnabled(true);
                        message.setMovementMethod(ScrollingMovementMethod.getInstance());
                        message.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
                        message.setPadding(5, 15, 5, 15);
                        message.setGravity(Gravity.CENTER_HORIZONTAL);

                        builder.setView(message);
                        builder.setCancelable(true);

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        if (mPlayer.isPlaying() && !isPaused) {
                            mPlayer.pause();
                            isPaused = true;
                            mPlayPauseButton.setImageResource(R.drawable.ic_play_arrow_black_36dp);
                        }

                        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                try
                                {
                                    mPlayer.start();
                                    isPaused = false;
                                    mPlayPauseButton.setImageResource(R.drawable.ic_pause_black_36dp);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            mMoreInfoButton.setVisibility(View.INVISIBLE);

            // Home Button
            mHomeButton = (ImageButton) findViewById(R.id.btnHome);
            mHomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DivineHealingActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
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
                        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.divine_healing);
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

            mCaptionTextView = (TextView) findViewById(R.id.txtViewDivineHealingArticleContent);
            mCaptionTextView.setMovementMethod(new ScrollingMovementMethod());

            mPreviousButton = (ImageButton) findViewById(R.id.btnLeftArrow);
            mPreviousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), LordsSupperActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
            mPreviousButton.setVisibility(View.INVISIBLE);

            mNextButton = (ImageButton) findViewById(R.id.btnRightArrow);
            mNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), TheEndTimesActivity.class);
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
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_divine_healing, menu);
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
    protected void onPause() {
        super.onPause();
        try {
            mPlayer.stop();
            mPlayer.release();

            if(AppUtility.isApplicationSentToBackground(getApplicationContext())) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }

            Log.v(TAG, "onPause: Service running? " + AppUtility.isMyServiceRunning(BackgroundMusicService.class,
                    getApplicationContext()) + " Bound: " + mBound + " isFinishing: " + isFinishing() +
                    " this.isFinishing: " + this.isFinishing() + " Sent to background: " +
                    AppUtility.isApplicationSentToBackground(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
            mDivineHealingImageView.startAnimation(fade);

            mPlayer = MediaPlayer.create(this, R.raw.divine_healing);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try
        {
            if (this.isFinishing() || isFinishing()) {
                if(mPlayer != null) mPlayer.release();
                mBound = false;
            }

            Log.v(TAG, "onDestroy: Service running? " + AppUtility.isMyServiceRunning(BackgroundMusicService.class,
                    getApplicationContext()) + " Bound: " + mBound + " isFinishing: " + isFinishing() +
                    " this.isFinishing: " + this.isFinishing() + " Sent to background: " +
                    AppUtility.isApplicationSentToBackground(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if(this.isFinishing() || isFinishing()) {
                mBound = false;
                if(mPlayer != null) mPlayer.release();
            }

            if(AppUtility.isApplicationSentToBackground(getApplicationContext())) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }
            Log.v(TAG, "onStop: Service running? " + AppUtility.isMyServiceRunning(BackgroundMusicService.class,
                    getApplicationContext()) + " Bound: " + mBound + " isFinishing: " + isFinishing() +
                    " this.isFinishing: " + this.isFinishing() + " Sent to background: " +
                    AppUtility.isApplicationSentToBackground(getApplicationContext()));
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
