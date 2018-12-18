package com.jebu.iaidohelperapplication;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

//The singleton class for instantiating the Room database for storing the kata/technique notes.
@Database(entities = {Note.class}, version = 2)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static NoteDatabase getNoteDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note").allowMainThreadQueries().fallbackToDestructiveMigration().build();
                    //Currently allows main thread queries. Needs to be switched to separate worker thread in the future.
            Log.d("info", "DATABASE CREATED!");
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}