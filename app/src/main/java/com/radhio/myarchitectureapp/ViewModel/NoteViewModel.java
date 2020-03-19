package com.radhio.myarchitectureapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.Repository.NoteRepository;

import java.util.List;

/*
 ViewModel stores and processes data for the activity/fragment and it doesn't get destroyed on configuration changes,
 so it doesn't lose it's variable state for example when the device is rotated.
 By extending AndroidViewModel, we get a handle to the application context, which we then use to instantiate our RoomDatabase.
*/

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    /* our activity should not have reference of repository instead of viewModel
    so i have made a wrapper method for our database operation method from my repository
     */

    public void insert(Note note){ noteRepository.insert(note); }
    public void update(Note note){
        noteRepository.update(note);
    }
    public void delete(Note note){
        noteRepository.delete(note);
    }
    public void deleteAllNotes(Note note){
        noteRepository.deleteAllNotes();
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
