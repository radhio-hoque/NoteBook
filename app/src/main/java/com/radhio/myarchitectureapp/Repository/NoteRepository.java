package com.radhio.myarchitectureapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.radhio.myarchitectureapp.DAO.NoteDao;
import com.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.RoomDatabase.NoteDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    // use application as an context to create database instance
    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getALlNotes();
    }

    public void insert(Note note){

    }
    public void update(Note note){

    }
    public void delete(Note note){

    }
    public void deleteAllNotes(){

    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
