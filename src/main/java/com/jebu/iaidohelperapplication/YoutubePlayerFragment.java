package com.jebu.iaidohelperapplication;


import android.content.Intent;

import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import com.google.android.youtube.player.YouTubePlayerView;

//Custom fragment for fetching and displaying the youtube technique videos by using networking and a registered YouTube API.
public class YoutubePlayerFragment extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    public static final String DEVELOPER_KEY = "AIzaSyCy4PKKlXpnrh77DXkJVC3hXGkWKk0xG5Q";
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private  String videoId;
    private YouTubePlayer player;


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    "There was an error initializing the YouTubePlayer (%1$s)",
                    errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        this.player = player;
        if (!wasRestored) {
            player.cueVideo(videoId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtubeView);
    }

    public void setVideoId (String videoId) {
        this.videoId = videoId;
    }


    public YouTubePlayer getPlayer() {
        return this.player;
    }
}