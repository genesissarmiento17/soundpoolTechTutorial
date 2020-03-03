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
    private int babyChicks, laughingBirds, screamingHawk, cartoonComputer, tvStatic;
    private int cartoonComputerStreamId;

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
                    .setMaxStreams(3)
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else {
            soundpool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        babyChicks = soundpool.load(this, R.raw.babychicks, 1);
        cartoonComputer = soundpool.load(this, R.raw.cartooncomputer, 1);
        laughingBirds = soundpool.load(this, R.raw.laughingbirds, 1);
        screamingHawk = soundpool.load(this, R.raw.screaminghawk, 1);
        tvStatic = soundpool.load(this, R.raw.tvstatic, 1);
    }

    public void playSound(View v){
        switch(v.getId()){
            case R.id.babyChicksButton:
                soundpool.play(babyChicks, 1,1,0, 2,1);
                soundpool.pause(cartoonComputerStreamId);
                break;
            case R.id.cartoonComputerButton:
                cartoonComputerStreamId = soundpool.play(cartoonComputer, 1,1,0, 0,1);
                break;
            case R.id.laughingBirdsButton:
                soundpool.play(laughingBirds, 1,1,0, 0,1);
                break;
            case R.id.screamingHawksButton:
                soundpool.play(screamingHawk, 1,1,1, 0,1);
                break;
            case R.id.tvStaticButton:
                soundpool.play(tvStatic, 1,1,0, 0,1);
                break;

            case R.id.fastComputerButton:
                soundpool.play(cartoonComputer, 1,1,0,0,2);
                break;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        soundpool.release();
        soundpool = null;
    }
}
