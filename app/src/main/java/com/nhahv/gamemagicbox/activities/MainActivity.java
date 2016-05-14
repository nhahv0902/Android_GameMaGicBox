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

    private Button mBtn1, mBtn2, mBtn3, mBtn4, mBtn5, mBtn6, mBtn7, mBtn8, mBtn9;
    private TextView mTxtTime;
    private Random mRandom;
    private int mSum;
    private int mSumClick;
    private int mColor = Color.parseColor("#8BC34A");
    private int time = 5;
    private Thread mThread;
    private Handler mHandler;

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
                    setEnable();
                }
            }
        };
    }

    private void initViews() {

        mBtn1 = (Button) findViewById(R.id.btn_1);
        mBtn2 = (Button) findViewById(R.id.btn_2);
        mBtn3 = (Button) findViewById(R.id.btn_3);
        mBtn4 = (Button) findViewById(R.id.btn_4);
        mBtn5 = (Button) findViewById(R.id.btn_5);
        mBtn6 = (Button) findViewById(R.id.btn_6);
        mBtn7 = (Button) findViewById(R.id.btn_7);
        mBtn8 = (Button) findViewById(R.id.btn_8);
        mBtn9 = (Button) findViewById(R.id.btn_9);
        mTxtTime = (TextView) findViewById(R.id.txt_time);
        mTxtTime.setText(getString(R.string.time_, time));
    }

    private void addEvents() {
        mBtn1.setOnClickListener(new Events());
        mBtn2.setOnClickListener(new Events());
        mBtn3.setOnClickListener(new Events());
        mBtn4.setOnClickListener(new Events());
        mBtn5.setOnClickListener(new Events());
        mBtn6.setOnClickListener(new Events());
        mBtn7.setOnClickListener(new Events());
        mBtn8.setOnClickListener(new Events());
        mBtn9.setOnClickListener(new Events());
    }

    private class Events implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_1:
                    onClickButton(mBtn1);
                    break;
                case R.id.btn_2:
                    onClickButton(mBtn2);
                    break;
                case R.id.btn_3:
                    onClickButton(mBtn3);
                    break;
                case R.id.btn_4:
                    onClickButton(mBtn4);
                    break;
                case R.id.btn_5:
                    onClickButton(mBtn5);
                    break;
                case R.id.btn_6:
                    onClickButton(mBtn6);
                    break;
                case R.id.btn_7:
                    onClickButton(mBtn7);
                    break;
                case R.id.btn_8:
                    onClickButton(mBtn8);
                    break;
                case R.id.btn_9:
                    onClickButton(mBtn9);
                    break;
            }
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
            setEnable();
            return;
        }
        if (mSumClick == 9) {
            getShowToast("You Lose");
            setEnable();
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


    private void setEnable() {
        mBtn1.setEnabled(false);
        mBtn2.setEnabled(false);
        mBtn3.setEnabled(false);
        mBtn4.setEnabled(false);
        mBtn5.setEnabled(false);
        mBtn6.setEnabled(false);
        mBtn7.setEnabled(false);
        mBtn8.setEnabled(false);
        mBtn9.setEnabled(false);
    }

    private void getShowToast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
