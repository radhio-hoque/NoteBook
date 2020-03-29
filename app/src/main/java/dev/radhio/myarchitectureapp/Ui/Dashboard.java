package dev.radhio.myarchitectureapp.Ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import dev.radhio.myarchitectureapp.Adapter.NoteAdapter;
import dev.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.R;
import dev.radhio.myarchitectureapp.ViewModel.NoteViewModel;
import dev.radhio.myarchitectureapp.ViewModel.SharedViewModel;

import java.util.List;

public class Dashboard extends Fragment {

    private View root;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;
    private FloatingActionButton addButton;
    private SharedViewModel viewModel;
    private RecyclerView noteRecyclerview;
    private EditNote editNote = new EditNote();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.dashboard_fragment, container, false);

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
                if (notes.size() > 0) {
                    noteAdapter.setNotes(notes);
                }
            }
        });


        //set recyclerView
        noteRecyclerview = root.findViewById(R.id.note_recyclerview);
        noteRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        noteRecyclerview.setHasFixedSize(true);
        noteAdapter = new NoteAdapter(getContext());
        noteRecyclerview.setAdapter(noteAdapter);


        //navigate one fragment to another
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
//                NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.addNote,true).build();
                navController.navigate(R.id.action_dashboard_to_addNote, null);
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {


        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel.getnote().observe(getViewLifecycleOwner(), new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                String checkFragment = DashboardArgs.fromBundle(getArguments()).getTitle();
                if (checkFragment == editNote.toString()) {
                    int id= NoteAdapter.id;
                    note.setId(id);
                    noteViewModel.update(note);
                }
                else {
                    noteViewModel.insert(note);
                }

            }
        });

           /*
            ItemTouchHelper together with a SimpleCallback and handle swipe events in the onSwiped method.
             We get the position of the swiped ViewHolder with getAdapterPosition and then use it to retrieve the correct item from the ArrayList in our adapter, so we can delete it over our ViewModel.
             We ignore onMove and the int direction in this example and add this ItemTouchHelper to our RecyclerView with the attachToRecyclerView method.
             */

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(noteRecyclerview);
    }

}
