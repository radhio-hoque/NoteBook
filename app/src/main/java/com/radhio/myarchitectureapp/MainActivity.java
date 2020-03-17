package com.radhio.myarchitectureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.radhio.myarchitectureapp.ViewModel.NoteViewModel;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
