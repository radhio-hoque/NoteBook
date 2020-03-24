package com.radhio.myarchitectureapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.radhio.myarchitectureapp.Entities.Note;

public class SharedViewModel extends ViewModel {
    private MutableLiveData<Note> noteMutableLiveData = new MutableLiveData<>();

    public void addNote(Note note){
        noteMutableLiveData.setValue(note);
    }

    public LiveData<Note> getnote() {
        return noteMutableLiveData;
    }
}
