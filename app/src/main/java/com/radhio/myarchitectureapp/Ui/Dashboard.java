package com.radhio.myarchitectureapp.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.radhio.myarchitectureapp.Adapter.NoteAdapter;
import com.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.R;
import com.radhio.myarchitectureapp.ViewModel.NoteViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Dashboard extends Fragment {
    private View root;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;
    private FloatingActionButton addButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root =  inflater.inflate(R.layout.dashboard_fragment, container, false);

        addButton = root.findViewById(R.id.note_addButton);

           /*
        In our activity we let the system provide us the correct ViewModel instance by calling ViewModelProviders.of,
        where we pass the Activity or Fragment this ViewModel's lifecycle should be scoped to.
         When our Activity/Fragment is then destroyed, the ViewModel will go through it's onCleared method and get removed from the memory.
         */
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        // Since getAllNotes returns Live Data i use here Observe which is LiveData Method
        noteViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                // this will trigger if only my activity is Foreground and if my activity is destroyed this will not hold the reference of this activity anymore.
                //update recyclerview
                noteAdapter.setNotes(notes);
            }
        });

        //set recyclerView
        RecyclerView noteRecyclerview = root.findViewById(R.id.note_recyclerview);
        noteRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        noteRecyclerview.setHasFixedSize(true);
        noteAdapter = new NoteAdapter();
        noteRecyclerview.setAdapter(noteAdapter);

        //navigate one fragment to another
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_dashboard_to_addNote,null);
            }
        });

        return root;
    }

}
