package com.jebu.iaidohelperapplication;

import android.graphics.Bitmap;

//Model class for the video grid items consisting of video Uris and thumbnails.
public class VideoItem {

    private String videoUri;
    private Bitmap videoThumbnail;

    public VideoItem(String videoUri, Bitmap videoThumbnail) {
        this.videoUri = videoUri;
        this.videoThumbnail = videoThumbnail;
    }

    public String getVideoUri() {
        return this.videoUri;
    }

    public  Bitmap getVideoThumbnail() {
        return this.videoThumbnail;
    }
}
