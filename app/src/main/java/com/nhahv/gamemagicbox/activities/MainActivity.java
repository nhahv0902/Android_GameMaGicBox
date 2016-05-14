package com.nhahv.gamemagicbox.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nhahv.gamemagicbox.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button[] mListButton = new Button[mNumber];
    private TextView mTxtTime, mTxtTitle;
    private Random mRandom;
    private int mSum;
    private int mSumClick;
    private int mColor = Color.parseColor("#8BC34A");
    private int time = 5;
    private Handler mHandler;
    private static final int mNumber = 9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRandom = new Random();
        initViews();
        addEvents();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mTxtTime.setText(getString(R.string.time_, time));

                if (time <= 0) {
                    getShowToast("YOU LOSE");
                    setEnableFails();
                }
            }
        };
    }

    private void initViews() {

        for (int i = 0; i < mNumber; i++) {
            mListButton[i] = (Button) findViewById(R.id.btn_1 + i);
            mListButton[i].setOnClickListener(new Events());
        }

        mTxtTime = (TextView) findViewById(R.id.txt_time);
        mTxtTitle = (TextView) findViewById(R.id.txt_title);
        mTxtTime.setText(getString(R.string.time_, time));
    }

    private void addEvents() {
        mTxtTitle.setOnClickListener(new Events());
    }

    private class Events implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_1:
                    onClickButton(mListButton[0]);
                    break;
                case R.id.btn_2:
                    onClickButton(mListButton[1]);
                    break;
                case R.id.btn_3:
                    onClickButton(mListButton[2]);
                    break;
                case R.id.btn_4:
                    onClickButton(mListButton[3]);
                    break;
                case R.id.btn_5:
                    onClickButton(mListButton[4]);
                    break;
                case R.id.btn_6:
                    onClickButton(mListButton[5]);
                    break;
                case R.id.btn_7:
                    onClickButton(mListButton[6]);
                    break;
                case R.id.btn_8:
                    onClickButton(mListButton[7]);
                    break;
                case R.id.btn_9:
                    onClickButton(mListButton[8]);
                    break;
                case R.id.txt_title:
                    onClickTryAgain();
                    break;
            }
        }

        private void onClickTryAgain() {
            time = 5;
            mTxtTime.setText(getString(R.string.time_, time));
            mSumClick = 0;
            mSum = 0;
            setEnable();
        }
    }

    private void onClickButton(Button button) {
        int x = mRandom.nextInt(10);

        mSum += x;
        mSumClick++;
        if (mSumClick == 1) {
            setTime();
        }
        String xString = x + "";
        button.setText(xString);
        button.setBackgroundColor(mColor);
        button.setEnabled(false);
        if (mSum > 50) {
            getShowToast("You Wind");
            setEnableFails();
            return;
        }
        if (mSumClick == 9) {
            getShowToast("You Lose");
            setEnableFails();
        }

    }

    private void setTime() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (time > 0) {
                    SystemClock.sleep(1000);
                    time--;
                    Message message = mHandler.obtainMessage();
                    message.arg1 = time;
                    mHandler.sendMessage(message);
                }
            }
        }).start();
    }


    private void setEnableFails() {
        for (int i = 0; i < mNumber; i++) {
            mListButton[i].setEnabled(false);
        }
    }

    private void setEnable() {
        for (int i = 0; i < mNumber; i++) {
            mListButton[i].setEnabled(true);
            mListButton[i].setText("");
            mListButton[i].setBackgroundColor(getResources().getColor(R.color.colorButton));
        }
    }

    private void getShowToast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
