package com.radhio.myarchitectureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.radhio.myarchitectureapp.Adapter.NoteAdapter;
import com.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.ViewModel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        In our activity we let the system provide us the correct ViewModel instance by calling ViewModelProviders.of,
        where we pass the Activity or Fragment this ViewModel's lifecycle should be scoped to.
         When our Activity/Fragment is then destroyed, the ViewModel will go through it's onCleared method and get removed from the memory.
         */
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        // Since getAllNotes returns Live Data i use here Observe which is LiveData Method
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // this will trigger if only my activity is Foreground and if my activity is destroyed this will not hold the reference of this activity anymore.
                //update recyclerview
                noteAdapter.setNotes(notes);
            }
        });

        //set recyclerView
        RecyclerView noteRecyclerview = findViewById(R.id.note_recyclerview);
        noteRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        noteRecyclerview.setHasFixedSize(true);
        noteAdapter = new NoteAdapter();
        noteRecyclerview.setAdapter(noteAdapter);
    }
}
