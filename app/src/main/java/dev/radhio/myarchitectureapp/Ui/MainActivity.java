package dev.radhio.myarchitectureapp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.radhio.myarchitectureapp.R;

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
