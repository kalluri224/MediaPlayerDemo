package com.example.user.mediaplayerdemo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mPlayer;
    SeekBar sBar;
    Handler myhandler=new Handler() {
        @Override
        public void close() {

        }

        @Override
        public void flush() {

        }

        @Override
        public void publish(LogRecord record) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer=MediaPlayer.create(getApplicationContext(), R.raw.test);


        sBar = (SeekBar) findViewById(R.id.Sbar);

        sBar.setMax(mPlayer.getDuration());
        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                mPlayer.seekTo(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        updateSeekBar();
    }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        };

    public void updateSeekBar(){

        sBar.setProgress(mPlayer.getCurrentPosition());
        sBar.postDelayed(r, 1000);

    }
    public void test(View v){
     switch (v.getId()){
         case R.id.back_imageview:

             mPlayer.seekTo(mPlayer.getCurrentPosition()-mPlayer.getDuration()/10);
         break;

         case R.id.Forword_imageview:

             mPlayer.seekTo(mPlayer.getCurrentPosition()+mPlayer.getDuration()/10);
             break;
         case R.id.play_imageview:
             mPlayer.start();
             break;
         case R.id.stop_imageview:
             mPlayer.stop();
             mPlayer=MediaPlayer.create(getApplicationContext(), R.raw.test);
             break;
         case R.id.pause_imageview:
             mPlayer.pause();
             break;
         case R.id.list_item:
             Intent i=new Intent();
             i.setAction(Intent.ACTION_GET_CONTENT);
             i.setType("audio/*");
             startActivityForResult(i, 0);
             break;





     }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(getApplicationContext(), data.getData().getPath(), Toast.LENGTH_LONG).show();

        try{
            mPlayer=new MediaPlayer();
            mPlayer.setDataSource(data.getData().getPath());
            mPlayer.prepare();
            mPlayer.start();
        }catch (Exception e) {
            // TODO: handle exception
        }

    }


}







