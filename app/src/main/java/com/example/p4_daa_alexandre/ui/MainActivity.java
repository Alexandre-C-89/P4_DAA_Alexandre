package com.example.p4_daa_alexandre.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;
import com.example.p4_daa_alexandre.ui.home.HomeFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private List<Meeting> mMeetings;

   private Toolbar toolbar;

   private MeetingRepository meetingRepository;

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
               /**case R.id.filter_room:
                  // Action pour filtrer par cette semaine
                  handleFilterThisWeek();
                  return true;*/
               /**
                * choix pour reset
                */
               // Ajoute d'autres cas pour les autres choix du menu contextuel si nécessaire
            }
            return false;
         }
      });

      // Afficher le menu contextuel
      popupMenu.show();
   }

   private void handleFilterToday() {
         meetingRepository.filterDay(mMeetings);
   }

   private boolean isSameDay(LocalDate date1, LocalDate date2) {
      return date1.isEqual(date2);
   }

}
