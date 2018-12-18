package com.jebu.iaidohelperapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

//Custom adapter class for populating the video grid for the technique video gallery. Also features an option to delete items from the video directory.
public class VideogridAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList<VideoItem> videoItems;

    public VideogridAdapter(Context context, ArrayList<VideoItem> videoItems) {
        this.mContext = context;
        this.videoItems = videoItems;
    }

    @Override
    public int getCount() {
        return videoItems.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        final VideoItem videoItem = videoItems.get(position);


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.activity_gridcontent, null);
        }

        final ImageView videoImageView = (ImageView) convertView.findViewById(R.id.videoThumbnail);
        final TextView videoNumberTextView = (TextView) convertView.findViewById(R.id.videoNumberText);
        final Button deleteVideoButton = (Button) convertView.findViewById(R.id.deleteVideoButton);

        deleteVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater li = LayoutInflater.from(mContext);
                View promptsView = li.inflate(R.layout.activity_deletepopup, null);


                File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/katavideos/");
                final File[] files = directory.listFiles();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        files[position].delete();
                                        notifyDataSetChanged();
                                        Toast.makeText(KataModel.getAppContext(), "Video deleted!", Toast.LENGTH_SHORT).show();


                                        //  database.noteDao().nukeTable();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();

                alertDialog.show();

            }

        });


        videoImageView.setImageBitmap(videoItem.getVideoThumbnail());
        videoNumberTextView.setText(Integer.toString(position));

        return convertView;

    }

    //Updates the video grid content.
    public void updateContent(ArrayList<VideoItem> videoItems) {
        this.videoItems.clear();
        this.videoItems.addAll(videoItems);
        notifyDataSetChanged();
        Log.d("info", "Video data updated");

    }

}