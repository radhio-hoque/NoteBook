package com.radhio.myarchitectureapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

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

    /*we have to execute these 4 method on background thread that's why use asynctask class
    because room doesn't allow bd operation in main thread
     */
    public void insert(Note note){
        new InsertAsynctask(noteDao).execute(note);
    }
    public void update(Note note){
        new UpdateAsynctask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteAsynctask(noteDao).execute(note);
    }
    public void deleteAllNotes(){
        new DeleteAllNotesAsynctask(noteDao).execute();
    }

    // room will automatically execute the db operation and return the the LiveData on Background thread
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    /*
    Four Different AsyncTask for to clear understand but we can make it more complex by making one method
     */

    private static class InsertAsynctask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao; // need noteDao to make db operation

        // as this an static class we can access NoteDao directly here we have to make construction
        private InsertAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateAsynctask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao; // need noteDao to make db operation

        // as this an static class we can access NoteDao directly here we have to make construction
        private UpdateAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteAsynctask extends AsyncTask<Note, Void, Void>{
        private NoteDao noteDao; // need noteDao to make db operation

        // as this an static class we can access NoteDao directly here we have to make construction
        private DeleteAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsynctask extends AsyncTask<Void, Void, Void>{
        private NoteDao noteDao; // need noteDao to make db operation

        // as this an static class we can access NoteDao directly here we have to make construction
        private DeleteAllNotesAsynctask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNote();
            return null;
        }
    }
}
