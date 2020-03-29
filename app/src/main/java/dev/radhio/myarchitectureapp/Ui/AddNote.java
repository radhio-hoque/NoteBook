package dev.radhio.myarchitectureapp.Ui;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import dev.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.R;
import dev.radhio.myarchitectureapp.ViewModel.SharedViewModel;


public class AddNote extends Fragment {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;
    private FloatingActionButton saveButton;
    private View root;
    private String title,description;
    private int priority;
    private SharedViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.add_note_fragment, container, false);
        editTextTitle = root.findViewById(R.id.take_title);
        editTextDescription = root.findViewById(R.id.take_description);
        numberPickerPriority = root.findViewById(R.id.numberPicker);
        saveButton = root.findViewById(R.id.note_saveButton);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        return root;
    }

    private void saveNote(){
        title = editTextTitle.getText().toString();
        description = editTextDescription.getText().toString();
        priority = numberPickerPriority.getValue();

        if (!errorEditText()){
            Toast.makeText(getActivity(), "Note Submission failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Note note = new Note(title,description,priority);
            viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
            viewModel.addNote(note);
            NavController navController = Navigation.findNavController(root);
            navController.navigate(AddNoteDirections.actionAddNoteToDashboard(toString()));
            Toast.makeText(getActivity(), "Note Successfully Created", Toast.LENGTH_SHORT).show();
    }
    }

    private boolean errorEditText(){
        boolean valid = true;
        if (title.trim().isEmpty()){
            editTextTitle.setError("Enter a valid Title");
            editTextTitle.requestFocus();
            valid = false;
        }
        else if (description.trim().isEmpty()){
            editTextDescription.setError("Enter a valid Description");
            editTextDescription.requestFocus();
            valid = false;
        }
        return valid;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //hide keyboard when any fragment of this class has been detached
        showSoftwareKeyboard(false);
    }

    protected void showSoftwareKeyboard(boolean showKeyboard) {
        final Activity activity = getActivity();
        final InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), showKeyboard ? InputMethodManager.SHOW_FORCED : InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "AddNote";
    }
}
