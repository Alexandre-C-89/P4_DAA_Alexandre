package com.example.p4_daa_alexandre.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;
import com.example.p4_daa_alexandre.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private List<Meeting> mMeetings;
   private MeetingRepository meetingRepository;
   private MeetingViewModel meetingViewModel;

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
      meetingViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);
      setContentView(binding.getRoot());
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.home_menu, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         case R.id.filter_today:
            // Action pour filtrer par aujourd'hui
            handleFilterToday();
            return true;
         case R.id.filter_room:
            // Action pour filtrer par cette semaine
            handleFilterRoom();
            return true;
         /**
          * choix pour reset
          */
         case R.id.filter_reset:
            handlefilterReset();
            return true;
      }
      return super.onOptionsItemSelected(item);
   }

   private void handleFilterToday() {

      /**
       * Date picker pour choisir la date
       */
   }

   private void handlefilterReset() {
      /**
       * Appel la méthode Get
       */
   }

   private void handleFilterRoom() {
      /**
       * SearchView
       */
   }

}
