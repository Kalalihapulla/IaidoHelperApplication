package com.jebu.iaidohelperapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;


//Activity class for displaying and handling Iaido practicioner training tools such as on input shared preference training note storing. The stored notes can be fetched if needed.
//Also includes a chronometer stopwatch for timing activities.
public class TrainingToolsActivity extends AppCompatActivity {

    private boolean chronometerRunning;
    private long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainingtools);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Training tools");
        }

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Button geTrainingNotes = (Button) findViewById(R.id.getTrainingNotesButton);
        final EditText trainingNotes = (EditText) findViewById(R.id.trainingNotes);

        Button trainingKatalistButton = (Button) findViewById(R.id.trainingKatalistButton);

        final Button toggleChronometerButton = (Button) findViewById(R.id.toggleChronometer);
        Button resetChronometerButton = (Button) findViewById(R.id.resetChronometer);

        final Chronometer trainingTimer = (Chronometer) findViewById(R.id.trainingTimer);


        trainingNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("trainingNotes", trainingNotes.getText().toString());
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        geTrainingNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trainingNotes.setText(sharedPref.getString("trainingNotes", null));
            }
        });

        trainingKatalistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trainingKatalistIntent = new Intent(TrainingToolsActivity.this, KataListActivity.class);
                startActivity(trainingKatalistIntent);
            }
        });

        toggleChronometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!chronometerRunning) {
                    trainingTimer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    trainingTimer.start();
                    chronometerRunning = true;
                    toggleChronometerButton.setText("Stop");
                    toggleChronometerButton.setBackgroundColor(Color.parseColor("#f44242"));
                }

                else if (chronometerRunning) {
                    timeWhenStopped = trainingTimer.getBase() - SystemClock.elapsedRealtime();
                    trainingTimer.stop();
                    chronometerRunning = false;
                    toggleChronometerButton.setText("Start");
                    toggleChronometerButton.setBackgroundColor(Color.parseColor("#ffffff"));
                }

            }
        });

        resetChronometerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    trainingTimer.stop();
                    trainingTimer.setBase(SystemClock.elapsedRealtime());
                    timeWhenStopped = 0;
                    chronometerRunning = false;
            }
        });


    }


}

