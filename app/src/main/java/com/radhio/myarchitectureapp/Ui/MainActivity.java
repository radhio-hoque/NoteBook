package com.radhio.myarchitectureapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.radhio.myarchitectureapp.Adapter.NoteAdapter;
import com.radhio.myarchitectureapp.Entities.Note;
import com.radhio.myarchitectureapp.R;
import com.radhio.myarchitectureapp.ViewModel.NoteViewModel;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    Dashboard dashboard = new Dashboard();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }
}
