package com.example.p4_daa_alexandre;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private List<Meeting> mMeetings;

   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       /**
        * Toolbar
        */
       setSupportActionBar(binding.toolbar);
       HomeFragment homeFragment = new HomeFragment();
       getSupportFragmentManager().beginTransaction()
               .replace(R.id.container_fragment, homeFragment)
               .addToBackStack(null)
               .commit();
       // Initialiser la liste de réunions
       mMeetings = new ArrayList<>();
       setContentView(binding.getRoot());

   }
}
