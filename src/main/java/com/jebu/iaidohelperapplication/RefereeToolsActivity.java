package com.jebu.iaidohelperapplication;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

//Activity class for displaying and handling tools for Iaido competition referees or for competition training. Features on input shared preference storing for
// notes that referees can type for current competitors, he stored notes can be fetched if needed. Also has two different countdown timer modes for different tournament phases.
public class RefereeToolsActivity extends AppCompatActivity {

    private int timerValue;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refereetools);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Referee tools");
        }

        Button poolTimerButton = (Button) findViewById(R.id.poolTimerButton);
        Button semifinalsTimerButton = (Button) findViewById(R.id.semifinalsTimerButton);
        Button startTimerButton = (Button) findViewById(R.id.startTimerButton);
        Button refereeKatalistButton = (Button) findViewById(R.id.refereeKatasButton);
        Button getRedNotesButton = (Button) findViewById(R.id.getRedNotesButton);
        final EditText redContenderNotes = (EditText)findViewById(R.id.redNotes);
        Button getWhiteNotesButton = (Button) findViewById(R.id.getWhiteNotesButton);
        final EditText whiteContenderNotes = (EditText)findViewById(R.id.whiteNotes);
        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);


        TextView timerText = (TextView) findViewById(R.id.timerText);
        timerText.setText("" + String.format("%d : %d",
                TimeUnit.MILLISECONDS.toMinutes(0),
                TimeUnit.MILLISECONDS.toSeconds(0) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(0))));


        redContenderNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("redCompetitorNotes", redContenderNotes.getText().toString());
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getRedNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redContenderNotes.setText(sharedPref.getString("redCompetitorNotes",null));
            }
        });

        whiteContenderNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("whiteCompetitorNotes", whiteContenderNotes.getText().toString());
                editor.commit();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        getWhiteNotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                whiteContenderNotes.setText(sharedPref.getString("whiteCompetitorNotes",null));
            }
        });


        poolTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerValue = 240000;
                TextView timerText = (TextView) findViewById(R.id.timerText);
                timerText.setText("" + String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(timerValue),
                        TimeUnit.MILLISECONDS.toSeconds(timerValue) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timerValue))));
                Toast.makeText(KataModel.getAppContext(), "Timer Set!", Toast.LENGTH_SHORT).show();
            }
        });

        semifinalsTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerValue = 360000;
                TextView timerText = (TextView) findViewById(R.id.timerText);
                timerText.setText("" + String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(timerValue),
                        TimeUnit.MILLISECONDS.toSeconds(timerValue) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timerValue))));
                Toast.makeText(KataModel.getAppContext(), "Timer Set!", Toast.LENGTH_SHORT).show();
            }
        });

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timerRunning && timerValue != 0) {
                    startTimer(timerValue);
                    Toast.makeText(KataModel.getAppContext(), "Timer Started! Hajime!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(KataModel.getAppContext(), "Set the timer duration first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        refereeKatalistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent refereeKatalistIntent = new Intent(RefereeToolsActivity.this, KataListActivity.class);
                startActivity(refereeKatalistIntent);
            }
        });


    }

    private void startTimer(int millis) {


        final CountDownTimer timer = new CountDownTimer(millis, 1000) {


            TextView timerText = (TextView) findViewById(R.id.timerText);


            public void onTick(long millisUntilFinished) {
                timerText.setText("" + String.format("" + String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))));
            }

            public void onFinish() {
                timerText.setText("GOGI!");
                Button startTimerButton = (Button) findViewById(R.id.startTimerButton);
                ConstraintLayout background = (ConstraintLayout) findViewById(R.id.refereeConstraintLayout);
                background.setBackgroundColor(Color.parseColor("#ffe347"));
                startTimerButton.setText("Start");
                startTimerButton.setBackgroundColor(Color.parseColor("#baaeae"));
                timerRunning = false;
            }
        }.start();


        final Button resetTimerButton = (Button) findViewById(R.id.resetTimerButton);

        resetTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView timerText = (TextView) findViewById(R.id.timerText);
                timer.cancel();
                timerText.setText("" + String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(0),
                        TimeUnit.MILLISECONDS.toSeconds(0) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(0))));
                timerRunning = false;
                Toast.makeText(KataModel.getAppContext(), "Timer Resetted!", Toast.LENGTH_SHORT).show();

            }
        });

        timerRunning = true;


    }

}

