package com.jebu.iaidohelperapplication;

import android.Manifest;

import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubePlayerFragment;

//Activity class for displaying and handling a youtube view and a video view for comparing the kata/technique video taken by the practitioner.
public class VideoCaptureActivity extends AppCompatActivity {

    private static final String apiKey = "AIzaSyCy4PKKlXpnrh77DXkJVC3hXGkWKk0xG5Q";

    //Features a dialog for asking user for accessing the external storage files.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videolayout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Video comparison");
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1234);
        } else {

            playbackRecordedVideo(getVideoDetails());
            initializeYoutubeFragment();
        }
    }

    //Handles the asked permission result
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1234: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    playbackRecordedVideo(getVideoDetails());
                    initializeYoutubeFragment();

                } else {
                    Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();

                }
                return;
            }

        }
    }

    //Displays and plays the recorded/fetched video on a video view for video comparison.
    public void playbackRecordedVideo(Uri videoUri) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final VideoView mVideoView = (VideoView) findViewById(R.id.kataVideoView);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });

    }

    //Initializes the youtube view fragment for video comparison.
    private void initializeYoutubeFragment() {

        Bundle videoInfo = getIntent().getExtras();
        int kataNumber = videoInfo.getInt("kataNumber");

        YoutubePlayerFragment yTfragment = new YoutubePlayerFragment();
        yTfragment.setVideoId(KataModel.getInstance().getKataList().get(kataNumber).getVideoId());
        YouTubePlayerFragment myYouTubePlayerFragment;
        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeView);
        myYouTubePlayerFragment.initialize(apiKey, yTfragment);
    }

    //returns the current video URI.
    private Uri getVideoDetails() {
        Bundle videoInfo = getIntent().getExtras();
        String videoUriString = videoInfo.getString("videouri");
        Log.d("info", videoUriString);
        Uri videoUri = Uri.parse(videoUriString);
        return videoUri;
    }

}

