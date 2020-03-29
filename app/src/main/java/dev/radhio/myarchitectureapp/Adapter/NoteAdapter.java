package dev.radhio.myarchitectureapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import dev.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.R;

import dev.radhio.myarchitectureapp.Ui.DashboardDirections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/*
RecyclerView.Adapter, where I add a NoteViewHolder as an inner class and implement onCreateViewHolder, onBindViewHolder and getItemCount to inflate it and populate it with data.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public static int id ;
    private List<Note> notes = new ArrayList<>();
    private Context context;
    private String colors[] = {"#FF7A7B", "#FEA63C", "#4073DC", "#4E9448"};

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NoteViewHolder holder, int position) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        holder.noteView.setBackgroundColor(Color.parseColor(colors[randomNum]));
        holder.title.setText(notes.get(position).getTitle());
        holder.description.setText(notes.get(position).getDescription());
        // as i can not pass int into TextView
        holder.priority.setText(String.valueOf(notes.get(position).getPriority()));
        holder.noteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != RecyclerView.NO_POSITION) {
                    Note note = notes.get(position);
                    String title = note.getTitle();
                    String description = note.getDescription();
                    int priority = note.getPriority();
                    id = note.getId();
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(DashboardDirections.actionDashboardToEditNote(title,description,priority,id));
                }
            }
        });
    }

    // how many item i want to show in recyclerview
    @Override
    public int getItemCount() {
        return notes.size();
    }

    // i want to call it from Dashboard and return type is note(this way i can get note from this adapter to the outside)
    public Note getNoteAt(int position){
        return notes.get(position);
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    //NoteViewHolder Class holds a views in my sing;e recyclerview items
    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title,description,priority;
        private CardView noteCard;
        private View noteView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_description);
            priority = itemView.findViewById(R.id.note_priority);
            noteCard = itemView.findViewById(R.id.note_card);
            noteView = itemView.findViewById(R.id.note_view);
        }
    }
}
