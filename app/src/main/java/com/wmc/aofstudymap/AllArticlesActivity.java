package com.wmc.aofstudymap;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AllArticlesActivity extends AppCompatActivity {

    boolean mBound;

    ImageButton mArticleOneButton;
    ImageButton mArticleTwoButton;
    ImageButton mArticleThreeButton;
    ImageButton mArticleFourButton;
    ImageButton mArticleFiveButton;
    ImageButton mArticleSixButton;
    ImageButton mArticleSevenButton;
    ImageButton mArticleEightButton;
    ImageButton mArticleNineButton;
    ImageButton mArticleTenButton;
    ImageButton mArticleElevenButton;
    ImageButton mArticleTwelveButton;
    ImageButton mArticleThirteenButton;
    ImageButton mArticleFourteenButton;
    ImageButton mArticleFifteenButton;
    ImageButton mArticleSixteenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_articles);
        try {
            //#1
            mArticleOneButton = (ImageButton) findViewById(R.id.btnArticleOne);
            mArticleOneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), TriuneGodActivity.class);
                    startActivity(intent);
                }
            });
            //#2
            mArticleTwoButton = (ImageButton) findViewById(R.id.btnArticleTwo);
            mArticleTwoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), JesusChristActivity.class);
                    startActivity(intent);
                }
            });
            //#3
            mArticleThreeButton = (ImageButton) findViewById(R.id.btnArticleThree);
            mArticleThreeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), HolySpiritActivity.class);
                    startActivity(intent);
                }
            });
            //#4
            mArticleFourButton = (ImageButton) findViewById(R.id.btnArticleFour);
            mArticleFourButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), HolyScripturesActivity.class);
                    startActivity(intent);
                }
            });
            //#5
            mArticleFiveButton = (ImageButton) findViewById(R.id.btnArticleFive);
            mArticleFiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), SinArticleActivity.class);
                    startActivity(intent);
                }
            });
            //#6
            mArticleSixButton = (ImageButton) findViewById(R.id.btnArticleSix);
            mArticleSixButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), AtonementArticleActivity.class);
                    startActivity(intent);
                }
            });
            //#7
            mArticleSevenButton = (ImageButton) findViewById(R.id.btnArticleSeven);
            mArticleSevenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), PrevenientGraceArticleActivity.class);
                    startActivity(intent);
                }
            });
            //#8
            mArticleEightButton = (ImageButton) findViewById(R.id.btnArticleEight);
            mArticleEightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), RepentanceArticleActivity.class);
                    startActivity(intent);
                }
            });
            //#9
            mArticleNineButton = (ImageButton) findViewById(R.id.btnArticleNine);
            mArticleNineButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), JustRegenAdoptActivity.class);
                    startActivity(intent);
                }
            });
            //10
            mArticleTenButton = (ImageButton) findViewById(R.id.btnArticleTen);
            mArticleTenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), EntireSanctificationArticleActivity.class);
                    startActivity(intent);
                }
            });
            //11
            mArticleElevenButton = (ImageButton) findViewById(R.id.btnArticleEleven);
            mArticleElevenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), TheChurchArticleActivity.class);
                    startActivity(intent);
                }
            });
            //12
            mArticleTwelveButton = (ImageButton) findViewById(R.id.btnArticleTwelve);
            mArticleTwelveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), BaptismActivity.class);
                    startActivity(intent);
                }
            });
            //13
            mArticleThirteenButton = (ImageButton) findViewById(R.id.btnArticleThirteen);
            mArticleThirteenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), LordsSupperActivity.class);
                    startActivity(intent);
                }
            });
            //14
            mArticleFourteenButton = (ImageButton) findViewById(R.id.btnArticleFourteen);
            mArticleFourteenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), DivineHealingActivity.class);
                    startActivity(intent);
                }
            });
            //15
            mArticleFifteenButton = (ImageButton) findViewById(R.id.btnArticleFifteen);
            mArticleFifteenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), SecondComingActivity.class);
                    startActivity(intent);
                }
            });
            //16
            mArticleSixteenButton = (ImageButton) findViewById(R.id.btnArticleSixteen);
            mArticleSixteenButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Intent intent = new Intent(getApplicationContext(), RessJudgDestArticleActivity.class);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_all_articles, menu);
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
            if(AppUtility.isApplicationSentToBackground(getApplicationContext())) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (!mBound) {
                AppController.getInstance().startService(getApplicationContext());
                mBound = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if(AppUtility.isApplicationSentToBackground(getApplicationContext())) {
                AppController.getInstance().stopService(getApplicationContext());
                mBound = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mArticleOneButton = null;
        mArticleTwoButton = null;
        mArticleThreeButton = null;
        mArticleFourButton = null;
        mArticleFiveButton = null;
        mArticleSixButton = null;
        mArticleSevenButton = null;
        mArticleEightButton = null;
        mArticleNineButton = null;
        mArticleTenButton = null;
    }
}
