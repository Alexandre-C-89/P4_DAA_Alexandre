package com.example.p4_daa_alexandre.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
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
      if (item.getItemId() == R.id.filter_menu_item) {
         // Handle filter action here
         showFilterMenu();
         return true;
      }
      return super.onOptionsItemSelected(item);
   }

   private void showFilterMenu() {
      View view = findViewById(R.id.filter_menu_item); // L'élément de menu qui a été cliqué

      PopupMenu popupMenu = new PopupMenu(this, view);
      popupMenu.inflate(R.menu.home_menu); // Le menu contextuel avec les différents choix

      // Gérer les clics sur les éléments du menu contextuel
      popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
         @Override
         public boolean onMenuItemClick(MenuItem item) {
            // Gérer les actions des éléments du menu contextuel
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
            return false;
         }
      });

      // Afficher le menu contextuel
      popupMenu.show();
   }

   private void handleFilterToday() {
      meetingViewModel.getFilteredMeetingsLiveData().observe(this, new Observer<List<Meeting>>() {
         @Override
         public void onChanged(List<Meeting> filteredMeetings) {
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
            homeFragment.updateFilterList(filteredMeetings);
         }
      });
   }

   private void handlefilterReset() {
      meetingViewModel.getResetFilter().observe(this, new Observer<List<Meeting>>() {
         @Override
         public void onChanged(List<Meeting> meetings) {
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
            homeFragment.updateFilterList(meetings);
         }
      });
   }

   private void handleFilterRoom() {
      meetingViewModel.getFilteredMeetingsByRoomLiveData().observe(this, new Observer<List<Meeting>>() {
         @Override
         public  void onChanged(List<Meeting> filteredMeetingsByRoom) {
            HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
            homeFragment.updateFilterList(filteredMeetingsByRoom);
         }
      });
   }

}
