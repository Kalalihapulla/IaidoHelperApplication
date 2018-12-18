package com.jebu.iaidohelperapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jebu on 6.3.2018.
 */

//Custom adapter for populating the kata/technique list view.
public class KatalistAdapter extends ArrayAdapter<Kata> {
    public KatalistAdapter(Context context, ArrayList<Kata> kataArrayList) {
        super(context, 0, kataArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Kata kata = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_katalistcontent, parent, false);
        }

        TextView kataName = (TextView) convertView.findViewById(R.id.kataName);

        TextView kataDesc = (TextView) convertView.findViewById(R.id.kataDescription);

        kataName.setText(kata.getName());

        kataDesc.setText(kata.getDescription());

        kataName.setTag(position);


        return convertView;
        }
    }


