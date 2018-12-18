package com.jebu.iaidohelperapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

//Activity class for displaying the main menu of the application.
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Enables the android action bar changes.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Menu");
        }

        Button katasButton = (Button) findViewById(R.id.katalistButton);
        Button videosButton = (Button) findViewById(R.id.videosButton);
        Button refereeToolsButton = (Button) findViewById(R.id.refereeToolsButton);
        Button trainingToolsButton = (Button) findViewById(R.id.trainingToolsButton);
        Button extrasButton = (Button) findViewById(R.id.extrasButton);


        katasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kataListIntent = new Intent(MenuActivity.this, KataListActivity.class);
                startActivity(kataListIntent);
            }
        });

        videosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent videoGridIntent = new Intent(MenuActivity.this, VideoGridActivity.class);
                startActivity(videoGridIntent);
            }
        });

        refereeToolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent refereeToolsIntent = new Intent(MenuActivity.this, RefereeToolsActivity.class);
                startActivity(refereeToolsIntent);
            }
        });

        trainingToolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trainingToolsIntent = new Intent(MenuActivity.this, TrainingToolsActivity.class);
                startActivity(trainingToolsIntent);
            }
        });

        extrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent extrasIntent = new Intent(MenuActivity.this, ExtrasActivity.class);
                startActivity(extrasIntent);
            }
        });


    }


}

