package com.jebu.iaidohelperapplication;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;


import java.io.File;
import java.util.ArrayList;


//Activity class for displaying and handling the video grid for displaying the recorded technique video gallery.
public class VideoGridActivity extends AppCompatActivity {

    private ArrayList<VideoItem> videoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videogrid);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Video gallery");
        }

        GridView gridView = (GridView)findViewById(R.id.videoGrid);
        final ArrayList<VideoItem> videoItems = populateVideoitems();
        this.videoItems = videoItems;
        final VideogridAdapter videoAdapter = new VideogridAdapter(this, videoItems);
        gridView.setAdapter(videoAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri videoReviewUri =  Uri.parse(videoItems.get(position).getVideoUri());
                Intent intent = new Intent(Intent.ACTION_VIEW, videoReviewUri);
                intent.setDataAndType(videoReviewUri, "video/mp4");
                startActivity(intent);

            }
        });

        Button refreshVideosButton = (Button)findViewById(R.id.refreshGridButton);

        refreshVideosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoAdapter.updateContent(populateVideoitems());
            }
        });
    }

    //Fetches and populates the video and thumbnail items for the custom video grid adapter.
    private ArrayList<VideoItem> populateVideoitems() {


        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/katavideos/");
        File[] files = directory.listFiles();

        ArrayList<VideoItem> videoItems = new ArrayList<>();

        for (File file : files) {
            Log.d("info", "new file");
            String path = file.getPath();
            Log.d("info", path);
            VideoItem videoItem = new VideoItem(path, ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND));
            videoItems.add(videoItem);


            Log.d("info", path);

        }
        Log.d("info", Integer.toString(files.length));

        return videoItems;
    }




}

