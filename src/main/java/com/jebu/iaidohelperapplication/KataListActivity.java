package com.jebu.iaidohelperapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

//Activity for getting and displaying the kata list view.
public class KataListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalist);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Katas");
        }

        KataModel kataModel = KataModel.getInstance();
        kataModel.populate();
        ArrayList<Kata> kataArray = kataModel.getKataList();


        KatalistAdapter katalistAdapter = new KatalistAdapter(this, kataArray);

        ListView katalistView = (ListView) findViewById(R.id.kataListView);
        katalistView.setAdapter(katalistAdapter);

        katalistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("info", Integer.toString(i));
                Intent kataView = new Intent(view.getContext(), KataViewActivity.class);
                Log.d("test", Integer.toString(i));
                kataView.putExtra("kataPosition", i);
                view.getContext().startActivity(kataView);
            }
        });


    }


}
