package com.jebu.iaidohelperapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.ExpandableListView;

import android.widget.Toast;


import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Activity class for displaying the kata/technique videos, grading points, notes etc.
public class KataViewActivity extends AppCompatActivity implements GradingPointListAdapter.customButtonListener {

    private static final String apiKey = "AIzaSyCy4PKKlXpnrh77DXkJVC3hXGkWKk0xG5Q";
    ArrayList<String> gradingPoints;
    HashMap<String, ArrayList<String>> gradingPointNotes;
    final KataModel kataModel = KataModel.getInstance();
    private YouTubePlayer player;
    private GradingPointListAdapter gradingPointListAdapter;
    private static final int VIDEO_CAPTURE = 101;
    private Uri videoUri;
    private int kataNumber;
    private YoutubePlayerFragment youtubePlayerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kataview);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Kata tools");
        }

        Bundle kataPosition = getIntent().getExtras();
        this.kataNumber = kataPosition.getInt("kataPosition");

        initializeYoutubeFragment();

        final ExpandableListView gradingPointList = (ExpandableListView) findViewById(R.id.gradingPointsList);
        populateGradingPoints();

        gradingPointListAdapter = new GradingPointListAdapter(this, gradingPoints, gradingPointNotes, kataNumber);
        gradingPointListAdapter.setCustomButtonListener(this);
        gradingPointList.setAdapter(gradingPointListAdapter);

        gradingPointList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d("onGroupClick:", "worked");

                parent.smoothScrollToPosition(groupPosition);

                populateGradingPoints();
                gradingPointListAdapter.updateContent(gradingPoints, gradingPointNotes);

                return false;
            }
        });


        Button refreshButton = (Button) findViewById(R.id.refreshButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                populateGradingPoints();
                gradingPointListAdapter.updateContent(gradingPoints, gradingPointNotes);
            }
        });

        Button captureVideoButton = (Button) findViewById(R.id.captureVideoButton);
        captureVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecordingVideo();
            }
        });

    }


    //Fetches the grading point and their specific data from the global model and the database and uses it to populate and update the GradingPointListAdapter.
    private void populateGradingPoints() {

        Bundle kataPosition = getIntent().getExtras();
        final int kataNumber = kataPosition.getInt("kataPosition");

        this.gradingPoints = new ArrayList<>();
        this.gradingPointNotes = new HashMap<>();

        for (GradingPoint gradingPoint : kataModel.getGradingPoints(kataNumber)) {
            this.gradingPoints.add(gradingPoint.getDescription());
        }

        List<Note> gradingPointsNotesFromDb = NoteDatabase.getNoteDatabase(KataModel.getAppContext()).noteDao().getAll();

        ArrayList<String> notesForGradingpoints = new ArrayList<>();

        for (Note note : gradingPointsNotesFromDb) {
            notesForGradingpoints.add(note.getNoteData());
        }

        int gpCount =  KataModel.getInstance().getKataList().get(kataNumber).getGradingPoints().size();

            for (int i = 0; i < gpCount; i++) {
                List<Note> gp = NoteDatabase.getNoteDatabase(KataModel.getAppContext()).noteDao().findByCategory(i, kataNumber);

                ArrayList<String> notesGp = new ArrayList<>();
                for (Note note : gp) {

                    notesGp.add(note.getNoteData());

                }
                this.gradingPointNotes.put(gradingPoints.get(i), notesGp);
            }
        }


    //Starts an intent to record video. Creates a external storage directory for storing technique video files. Asks the user for usage permissions.
    private void startRecordingVideo() {

        askWritePermission();

        if (Build.VERSION.SDK_INT >= 24) {
            try {
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

            File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/katavideos/");
            File[] files = directory.listFiles();
            int directoryPosition = files.length + 1;
            File mediaFile = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/katavideos/katavideo" + directoryPosition + ".mp4");
            videoUri = Uri.fromFile(mediaFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
            startActivityForResult(intent, VIDEO_CAPTURE);
        } else {
            Toast.makeText(this, "No camera detected on your device!", Toast.LENGTH_LONG).show();
        }
    }

    //Handles the recorded video and stores it to the specific directory if chosen to.
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video has been saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
                Intent videoCaptureIntent = new Intent(KataViewActivity.this, VideoCaptureActivity.class);
                String videoUriToSend = videoUri.toString();
                videoCaptureIntent.putExtra("videouri", videoUriToSend);
                videoCaptureIntent.putExtra("kataNumber", kataNumber);
                startActivity(videoCaptureIntent);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video", Toast.LENGTH_LONG).show();
            }
        }
    }
    //Find the youtube video view and initializes the YouTubePlayerFragment.
    private void initializeYoutubeFragment() {

        YoutubePlayerFragment yTfragment = new YoutubePlayerFragment();
        yTfragment.setVideoId(kataModel.getKataList().get(kataNumber).getVideoId());
        YouTubePlayerFragment myYouTubePlayerFragment;
        myYouTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeView);
        myYouTubePlayerFragment.initialize(apiKey, yTfragment);
        this.youtubePlayerFragment = yTfragment;
    }

    //Methods for displaying and handling external storage permissions.
    private void askWritePermission() {
        if (ContextCompat.checkSelfPermission(this, // request permission when it is not granted.
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("myAppName", "permission:WRITE_EXTERNAL_STORAGE: NOT granted!");
            // Should we show an explanation?

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    4321);

        } else {
            createDirectory();
        }
    }


    //Handles the permission request result.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 4321: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createDirectory();
                }
                return;
            }
        }
    }

    //Creates a file storage directory if it does not exist already.
    public void createDirectory() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/katavideos");
        if (!folder.exists()) {
            if (folder.mkdirs())
                Toast.makeText(this, "New Folder Created", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onButtonClickListener(int position, int kataNumber, int notePosition) {
        youtubePlayerFragment.getPlayer().seekToMillis(Integer.parseInt(KataModel.getInstance().getKataList().get(kataNumber).getGradingPoints().get(position).getTimeStamp()));
        populateGradingPoints();
        gradingPointListAdapter.updateContent(gradingPoints, gradingPointNotes);

    }
}

