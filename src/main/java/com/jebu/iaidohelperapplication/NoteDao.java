package com.jebu.iaidohelperapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

//Data access object interface for accessing Room database note data and defining the needed queries.
@Dao
public interface NoteDao {

    @Query("SELECT * FROM note where noteCategory LIKE  :noteCategory AND kataCategory LIKE :kataCategory")
    List<Note> findByCategory(int noteCategory, int kataCategory);

    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Insert
    void insertNote(Note note);

    @Insert
    void insertAll(List<Note> note);

    @Delete
    void deleteNoteByNote(Note note);

    @Query("DELETE FROM note")
    public void nukeTable();

    @Query("DELETE FROM note where noteCategory LIKE  :noteCategory AND kataCategory LIKE :kataCategory AND noterow LIKE :noteRow")
    public void deleteNote(int noteCategory, int kataCategory, int noteRow);
}
