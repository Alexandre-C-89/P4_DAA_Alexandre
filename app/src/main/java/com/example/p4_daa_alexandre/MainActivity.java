package com.example.p4_daa_alexandre;

import android.os.Bundle;
import android.view.View;

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
       setContentView(R.layout.activity_main);

       setSupportActionBar(binding.toolbar);
       HomeFragment homeFragment = new HomeFragment();
       getSupportFragmentManager().beginTransaction()
               .replace(R.id.container_fragment, homeFragment)
               .addToBackStack(null)
               .commit();

       // Initialiser la liste de réunions
       mMeetings = new ArrayList<>();
       /**mMeetings.add(new Meeting("Réunion A", "John, Alice"));
       mMeetings.add(new Meeting("Réunion B", "Bob, Charlie"));*/

       initAddButton();

   }

    private void initAddButton() {
        binding.addButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              // Créer une instance de CreateMeetingFragment
              CreateMeetingFragment createMeetingFragment = new CreateMeetingFragment();

              // Ajouter le fragment au conteneur de fragment
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.container_fragment, createMeetingFragment)
                      .addToBackStack(null)
                      .commit();
           }
        });
    }

}
