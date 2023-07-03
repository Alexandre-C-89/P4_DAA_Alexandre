package com.example.p4_daa_alexandre.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.databinding.ActivityMainBinding;
import com.example.p4_daa_alexandre.ui.home.HomeFragment;

import java.time.LocalDate;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

   private ActivityMainBinding binding;
   //private List<Meeting> mMeetings;
   private MeetingViewModel mViewModel;

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

      mViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);

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
            // Action pour filtrer par jour
            handleFilterToday();
            return true;
         case R.id.search_room:
            // Action pour filtrer par salle de réunion
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
      DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
         @Override
         public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // La date a été sélectionnée, vous pouvez effectuer les actions nécessaires ici
            // Par exemple, vous pouvez mettre à jour votre liste de réunions avec la date sélectionnée
            LocalDate selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);
            filterMeetingsByDate(selectedDate);
         }
      };

      // Récupérez la date actuelle pour initialiser le DatePickerDialog
      Calendar calendar = Calendar.getInstance();
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH);
      int day = calendar.get(Calendar.DAY_OF_MONTH);

      // Créez et affichez le DatePickerDialog
      DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
      datePickerDialog.show();
   }

   private void filterMeetingsByDate(LocalDate selectedDate) {
      mViewModel.filterMeetingsByDate(selectedDate);
   }

   private void handlefilterReset() {
      /**
       * Appel la méthode Reset
       */
      mViewModel.resetFilter();
   }

   private void handleFilterRoom() {
      MenuItem menuItem = binding.toolbar.getMenu().findItem(R.id.search_room);
      SearchView searchView = (SearchView) menuItem.getActionView();
      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String roomName) {
            // Action lorsque l'utilisateur soumet la recherche
            mViewModel.filterMeetingsByRoomName(roomName);
            return true;
         }

         @Override
         public boolean onQueryTextChange(String newRoomName) {
            // Action lorsque le texte de recherche change
            // Par exemple, vous pouvez mettre à jour la liste en fonction du texte de recherche en temps réel
            mViewModel.updateListBasedOnSearchText(newRoomName);
            return true;
         }
      });
   }

}
