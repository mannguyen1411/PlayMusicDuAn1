package com.example.duan1.playmusicduan1;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.InterruptedIOException;
import java.util.ArrayList;

public class Controller extends AppCompatActivity implements View.OnClickListener {
    static MediaPlayer mp;
    ImageButton btnBackward, btnBack, btnPlay, btnforward, btnNext;
    ImageView imageView;
    SeekBar sb;
    ArrayList<File> mySongs;
    int position;
    Uri u;
    Thread updateSeeBar;
    Animation animation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        addControls();
        Clockwise();
        addEvents();

        // Update SeeBar
        updateSeeBar = new Thread() {
            @Override
            public void run() {
                int totalDuration = mp.getDuration();
                int currentPosition = 0;
                sb.setMax(totalDuration);
                while (currentPosition < totalDuration) {
                    try {
                        sleep(500);
                        currentPosition = mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // super.run();
            }
        };

        // Nếu có nhạc đang phát thì ngừng và phát bài tiếp theo
        if (mp != null) {
            mp.stop();
            mp.release();
        }

        Intent i = getIntent();
        Bundle b = i.getExtras();
        mySongs = (ArrayList) b.getParcelableArrayList("songlist");
        position = b.getInt("pos", 0);

        u = Uri.parse(mySongs.get(position).toString());
        mp = MediaPlayer.create(getApplicationContext(), u);
        mp.start();
        updateSeeBar.start();
        sb.setMax(mp.getDuration());
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(seekBar.getProgress());


            }
        });


    }

    private void addControls() {
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        btnBackward = (ImageButton) findViewById(R.id.btnbw);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnforward = (ImageButton) findViewById(R.id.btnforward);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        sb = (SeekBar) findViewById(R.id.seekBar);
        imageView = (ImageView)findViewById(R.id.imageCd);
    }

    private void addEvents() {
        btnBack.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnforward.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }


    public void Clockwise(){
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        imageView.startAnimation(animation);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {

            case R.id.btnPlay:

                if (mp.isPlaying()) {

                    btnPlay.setImageResource(R.drawable.ic_menu_manage);
                    mp.pause();
                    imageView.clearAnimation();
                } else {
                    btnPlay.setImageResource(R.drawable.ic_menu_send);
                    mp.start();
                    Clockwise();

                }
                break;

            case R.id.btnforward:
                mp.seekTo(mp.getCurrentPosition() + 5000);
                break;

            case R.id.btnbw:
                mp.seekTo(mp.getCurrentPosition() - 5000);
                break;

            case R.id.btnNext:
                mp.stop();
                mp.release();
                position = (position + 1) % mySongs.size();
                u = Uri.parse(mySongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);
                mp.start();
                sb.setMax(mp.getDuration());
                break;

            case R.id.btnBack:
                mp.stop();
                mp.release();
                position = (position - 1 < 0) ? mySongs.size() - 1 : position - 1;

//                if (position - 1 < 0) {
//                    position = mySongs.size() - 1;
//                } else {
//                    position = position - 1;
//                }

                u = Uri.parse(mySongs.get(position).toString());
                mp = MediaPlayer.create(getApplicationContext(), u);
                mp.start();
                sb.setMax(mp.getDuration());
                break;

        }
    }

}
