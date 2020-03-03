package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundpool;
    private int babychicks, laughingbirds,screaminghawk,cartooncomputer,tvstatic;
    private int cartooncomputerStreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundpool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        babychicks = soundpool.load(this, R.raw.babychicks, 1);
        cartooncomputer = soundpool.load(this, R.raw.cartooncomputer, 1);
        laughingbirds = soundpool.load(this, R.raw.laughingbirds, 1);
        screaminghawk = soundpool.load(this, R.raw.screaminghawk, 1);
        tvstatic = soundpool.load(this, R.raw.tvstatic, 1);
    }

    public void playSound(View v){
        switch(v.getId()){
            case R.id.babychicksbutton:
                soundpool.play(babychicks, 1,1,0, 0,1);
                soundpool.pause(cartooncomputerStreamId);
                break;
            case R.id.cartooncomputerbutton:
                cartooncomputerStreamId = soundpool.play(cartooncomputer, 1,1,0, -1,1);
                break;
            case R.id.laughingbirdsbutton:
                soundpool.play(laughingbirds, 1,1,0, 0,1);
                break;
            case R.id.screaminghawksbutton:
                soundpool.play(screaminghawk, 1,1,0, 0,1);
                break;
            case R.id.tvstaticbutton:
                soundpool.play(tvstatic, 1,1,0, 0,1);
                break;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        soundpool.release();
        soundpool = null;
    }
}
