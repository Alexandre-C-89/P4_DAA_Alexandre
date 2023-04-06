package com.example.p4_daa_alexandre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;
import com.example.p4_daa_alexandre.ui.AddMeetingFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.toolbar);
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("main activity", "click sur le bouton d'ajout !");
                Intent intent = new Intent(MainActivity.this, AddMeetingFragment.class);
                startActivity(intent);
            }
        });

    }
}