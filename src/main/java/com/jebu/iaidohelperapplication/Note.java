package com.jebu.iaidohelperapplication;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


//Model class for kata/technique grading point notes. Contains the Entity data and methods for implementing a Room database table.
@Entity(tableName = "note")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int noteId;

    @ColumnInfo(name = "kataCategory")
    private int kataCategory;

    @ColumnInfo(name = "noteCategory")
    private int noteCategory;

    @ColumnInfo(name = "noteData")
    private String noteData;

    @ColumnInfo(name = "noteRow")
    private int noteRow;



    public Note(int noteCategory, int kataCategory, String noteData, int noteRow) {
        this.noteCategory = noteCategory;
        this.noteData = noteData;
        this.kataCategory = kataCategory;
        this.noteRow = noteRow;
    }

    public int getKataCategory() {
        return kataCategory;
    }

    public void setKataCategory(int kataCategory) {
        this.kataCategory = kataCategory;
    }

    public int getNoteId() {
        return noteId;
    }

    public int getNoteCategory() {
        return noteCategory;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteCategory(int noteCategory) {
        this.noteCategory = noteCategory;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getNoteRow() {
        return noteRow;
    }

    public void setNoteRow(int noteRow) {
        this.noteRow = noteRow;
    }
}
