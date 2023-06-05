package com.example.p4_daa_alexandre;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   private List<Meeting> mMeetings;

   private Toolbar toolbar;

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
               /**case R.id.filter_this_week:
                  // Action pour filtrer par cette semaine
                  handleFilterThisWeek();
                  return true;*/
               // Ajoute d'autres cas pour les autres choix du menu contextuel si nécessaire
            }
            return false;
         }
      });

      // Afficher le menu contextuel
      popupMenu.show();
   }

   private void handleFilterToday() {
      // Obtenir la date d'aujourd'hui en tant que LocalDate
      LocalDate today = LocalDate.now();

      // Filtrer les réunions par la date d'aujourd'hui
      List<Meeting> filteredMeetings = new ArrayList<>();
      for (Meeting meeting : mMeetings) {
         LocalDate meetingDate = meeting.getDate();
         Log.d("Date", "Meeting date: " + meetingDate);
         Log.d("Date", "Today's date: " + today);

         if (isSameDay(meetingDate, today)) {
            filteredMeetings.add(meeting);
         }
      }

      // Mettre à jour la liste des réunions dans le fragment HomeFragment
      HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
      homeFragment.updateFilterList(filteredMeetings);
   }

   private boolean isSameDay(LocalDate date1, LocalDate date2) {
      return date1.isEqual(date2);
   }

}
