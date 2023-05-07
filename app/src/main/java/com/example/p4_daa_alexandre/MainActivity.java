package com.example.p4_daa_alexandre;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private List<Meeting> mMeetings;
   private MeetingAdapter mAdapter;

   @Override
    protected void onCreate(Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(R.layout.activity_main);

       setSupportActionBar(binding.toolbar);

       // Je suis rediriger sur la page d'ajout d'une réunion
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

       // Initialiser la liste de réunions
       mMeetings = new ArrayList<>();
       mMeetings.add(new Meeting("Réunion A", "John, Alice"));
       mMeetings.add(new Meeting("Réunion B", "Bob, Charlie"));
       mMeetings.add(new Meeting("Réunion

      initRecyclerViews();

   }

    private void initRecyclerViews() {
        final MeetingAdapter adapter = new MeetingAdapter(this, MeetingRepository.getInstance().getMeetings());
        RecyclerView recyclerView = findViewById(R.id.list_meeting_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MeetingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meeting meeting) {
                // Ouvrir le fragment DetailMeetingFragment
                DetailMeetingFragment detailMeetingFragment = DetailMeetingFragment.newInstance(meeting);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_fragment, detailMeetingFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
