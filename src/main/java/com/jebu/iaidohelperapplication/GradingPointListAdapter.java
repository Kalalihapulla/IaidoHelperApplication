package com.jebu.iaidohelperapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;


//Custom adapter for populating the kata/technique grading points ExpandableList with grading point content.
public class GradingPointListAdapter extends BaseExpandableListAdapter {

    customButtonListener customListener;

    //interface for listening onClicks from the activity class.
    public interface customButtonListener {
        public void onButtonClickListener(int position, int kataNumber, int noteNumber);
    }

    public void setCustomButtonListener(customButtonListener listener) {
        this.customListener = listener;
    }

    private Context context;
    private ArrayList<String> gradingPointsList;
    private HashMap<String, ArrayList<String>> gradingPointsNotes;
    //   final KataModel kataModel = KataModel.getInstance();
    private int kataNumber;


    public GradingPointListAdapter(Context context, ArrayList<String> gradingPointsList, HashMap<String, ArrayList<String>> gradingPointsNotes, int kataNumber) {
        this.context = context;
        this.gradingPointsList = gradingPointsList;
        this.gradingPointsNotes = gradingPointsNotes;
        this.kataNumber = kataNumber;

    }

    //Populating the expandable list groups/titles.
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
        String gradingPoint = (String) getGroup(groupPosition);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_gradingpointtitles, null);

            viewHolder = new ViewHolder();
            viewHolder.button = (Button) convertView
                    .findViewById(R.id.videoTimestampButton);
            convertView.setTag(viewHolder);


            viewHolder.button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (customListener != null) {
                        customListener.onButtonClickListener(groupPosition, kataNumber, getChildrenCount(groupPosition));
                    }
                }
            });

            Button addNoteButton = (Button) convertView.findViewById(R.id.addNoteButton);
            addNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    addNoteToDb(groupPosition, kataNumber, getChildrenCount(groupPosition));
                }
            });
        }

        TextView gradingPointTitle = (TextView) convertView.findViewById(R.id.gradingPointTitle);
        gradingPointTitle.setText(gradingPoint);
        return convertView;
    }

    //Populating the expandable list group children with note content.
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childTitle = (String) getChild(groupPosition, childPosition);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_gradingpointdetails, null);


            Button deleteNoteButton = (Button) convertView.findViewById(R.id.deleteNoteButton);

            deleteNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    deleteNote(groupPosition, kataNumber, childPosition);

                }
            });
        }
        TextView gradingPointDetail = (TextView) convertView.findViewById(R.id.gradingPointDetail);
        gradingPointDetail.setText(childTitle);
        return convertView;

    }
    //Expandable list class methods...
    @Override
    public int getGroupCount() {
        return gradingPointsList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return gradingPointsNotes.get(gradingPointsList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return gradingPointsList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return gradingPointsNotes.get(gradingPointsList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    //Add note to Room database by defining 3 values. Displays a dialog to input the note.
    private void addNoteToDb(final int notePos, final int kataPos, final int childPosition) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_noteinputprompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Note gradingPointNote = new Note(notePos, kataPos, userInput.getText().toString(), childPosition);
                                        NoteDatabase database = NoteDatabase.getNoteDatabase(KataModel.getAppContext());

                                        database.noteDao().insertNote(gradingPointNote);

                                        Log.d("info", "new note added, to " + notePos + " " + kataPos
                                                + " " + childPosition);
                                        notifyDataSetChanged();
                                        Toast.makeText(KataModel.getAppContext(), "New note added!", Toast.LENGTH_SHORT).show();


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


    }

    //Delete note from the Room database by finding it with 3 given values. Prompts the user with confirmation dialog.
    private void deleteNote(final int notePos, final int kataPos, final int childNotePosition) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_deletepopup, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);


                alertDialogBuilder.setView(promptsView);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        NoteDatabase database = NoteDatabase.getNoteDatabase(KataModel.getAppContext());

                                        database.noteDao().deleteNote(notePos, kataPos, childNotePosition);

                                        Log.d("info", "Note deleted: " + notePos + " " + kataPos + " " + childNotePosition);
                                        notifyDataSetChanged();
                                        Toast.makeText(KataModel.getAppContext(), "Note deleted!", Toast.LENGTH_SHORT).show();

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
    }


    //Updates the Expandable list content.
    public void updateContent(ArrayList<String> gradingPointsList, HashMap<String, ArrayList<String>> gradingPointsNotes) {
        this.gradingPointsList.clear();
        this.gradingPointsList.addAll(gradingPointsList);
        this.gradingPointsNotes.clear();
        this.gradingPointsNotes.putAll(gradingPointsNotes);
        notifyDataSetChanged();
        Log.d("info", "data updated");

    }


    public class ViewHolder {
        Button button;
    }


}

