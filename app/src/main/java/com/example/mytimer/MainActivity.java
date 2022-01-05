package com.example.mytimer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import android.os.SystemClock;
import android.app.Activity;
import android.view.View.OnClickListener;

import java.util.HashMap;

public class MainActivity extends Activity implements OnClickListener {

    // private TextView viewDisplay;
    private Button start;

    private Button stop;

    private Button pause;
    private HashMap<Integer, Integer> soundID = new HashMap<Integer, Integer>();
    private SoundPool soundPool;
    private CountDownTimer countDownTimer;
    private final int limit = 60;
    private String tmp = "";
    private String lastNum = "";
    private CountDownTimer relaxTimer;
    private int streamID;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getActionBar().hide();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        initial();


    }

    private void resetFlag() {
        flag = false;
    }

    private void turnOffTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void CountingStart(int _num, boolean _flag) {
        if (_flag) {
            _num = Integer.parseInt(tmp);
            resetFlag();
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        countDownTimer = new CountDownTimer(_num * 1000, 1000) {
            @Override
            public void onTick(long index) {
                tmp = index / 1000 + "";
                start.setText(tmp);
                if (tmp != lastNum) {
                    voice(tmp);
                    lastNum = tmp;
                }
            }

            @Override
            public void onFinish() {


                relaxTimer.start();

            }
        };

        relaxTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                countDownTimer.start();
            }
        };

        countDownTimer.start();
    }

    private void initial() {
        //viewDisplay = (TextView) findViewById(R.id.timer);

        start = (Button) findViewById(R.id.start);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener((View.OnClickListener) this);
        pause.setOnClickListener((View.OnClickListener) this);

        stop.setOnClickListener((View.OnClickListener) this);

        soundPool = new SoundPool.Builder().build();
        soundID.put(1, soundPool.load(this, R.raw.one, 1));
        soundID.put(2, soundPool.load(this, R.raw.two, 1));
        soundID.put(3, soundPool.load(this, R.raw.three, 1));
        soundID.put(4, soundPool.load(this, R.raw.four, 1));
        soundID.put(5, soundPool.load(this, R.raw.five, 1));
        soundID.put(6, soundPool.load(this, R.raw.six, 1));
        soundID.put(7, soundPool.load(this, R.raw.seven, 1));
        soundID.put(8, soundPool.load(this, R.raw.eight, 1));
        soundID.put(9, soundPool.load(this, R.raw.night, 1));
        soundID.put(10, soundPool.load(this, R.raw.ten, 1));
        soundID.put(0, soundPool.load(this, R.raw.next, 1));
        soundID.put(11, soundPool.load(this, R.raw.aways, 1));
        soundID.put(30, soundPool.load(this, R.raw.thirty, 2));

    }

    public void voice(String sec) {

        switch (sec) {
            case "0":
                streamID = soundPool.play(soundID.get(0), 3, 3, 1, 0, 1);
                break;
            case "1":
                streamID = soundPool.play(soundID.get(1), 3, 3, 1, 0, 1);
                break;
            case "2":
                streamID = soundPool.play(soundID.get(2), 3, 3, 1, 0, 1);
                break;
            case "3":
                streamID = soundPool.play(soundID.get(3), 3, 3, 1, 0, 1);
                break;
            case "4":
                streamID = soundPool.play(soundID.get(4), 3, 3, 1, 0, 1);
                break;
            case "5":
                streamID = soundPool.play(soundID.get(5), 3, 3, 1, 0, 1);
                break;
            case "6":
                streamID = soundPool.play(soundID.get(6), 3, 3, 1, 0, 1);
                break;
            case "7":
                streamID = soundPool.play(soundID.get(7), 3, 3, 1, 0, 1);
                break;
            case "8":
                streamID = soundPool.play(soundID.get(8), 3, 3, 1, 0, 1);
                break;
            case "9":
                streamID = soundPool.play(soundID.get(9), 3, 3, 1, 0, 1);
                break;
            case "10":
                streamID = soundPool.play(soundID.get(10), 3, 3, 1, 0, 1);
                break;
            case "30":
                streamID = soundPool.play(soundID.get(30), 3, 3, 1, 0, 1);
                break;
            default:
                streamID = soundPool.play(soundID.get(11), 1, 1, 0, 0, 1);
                break;

        }

    }

    @SuppressLint("ResourceAsColor")
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.start:
                CountingStart(limit, flag);
                start.setTextSize(250F);
                stop.setText("S\nT\nO\nP");
                pause.setText("Pause");
                break;

            case R.id.stop:
                turnOffTimer();
                start.setTextSize(130);
                start.setText("Start");

                stop.setText("");
                pause.setText("");
                resetFlag();
                soundPool.stop(streamID);
                break;

            case R.id.pause:
                turnOffTimer();
                soundPool.stop(streamID);
                flag = true;
                break;
            default:
                break;
        }
    }


}