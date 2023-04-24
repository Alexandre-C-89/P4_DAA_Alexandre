package com.example.p4_daa_alexandre.ui.meetings;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_daa_alexandre.BuildConfig;
import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.ui.create.CreateMeetingActivity;
import com.example.p4_daa_alexandre.ui.details.MeetingDetailActivity;
import com.example.p4_daa_alexandre.ui.meetings.list.MeetingAdapter;
import com.example.p4_daa_alexandre.ui.meetings.list.OnMeetingClickedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MeetingActivity extends AppCompatActivity implements OnMeetingClickedListener{

        private CoordinatorLayout rootView;
        private MeetingViewModel viewModel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.meeting_activity);

            rootView = findViewById(R.id.meeting_cl_root);

            //viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MeetingViewModel.class);

            initToolbar();
            initRecyclerViews();
            //initSortingDialog();
            initFab();
        }

        private void initToolbar() {
            Toolbar toolbar = findViewById(R.id.meeting_toolbar);
            setSupportActionBar(toolbar);
        }

        private void initRecyclerViews() {
            final MeetingAdapter adapter = new MeetingAdapter(this);
            RecyclerView recyclerView = findViewById(R.id.meeting_rv);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }

        /**private void initSortingDialog() {
            viewModel.getViewActionSingleLiveEvent().observe(this, viewAction -> {
                if (viewAction == MeetingViewAction.DISPLAY_SORTING_DIALOG) {
                    SortDialogFragment.newInstance().show(getSupportFragmentManager(), null);
                }
            });
        }*/

        private void initFab() {
            FloatingActionButton floatingActionButton = findViewById(R.id.meeting_fab);
            floatingActionButton.setOnClickListener(view -> startActivity(CreateMeetingActivity.navigate(MeetingActivity.this)));

            // This is false in release, so it won't be enabled in prod !
            if (BuildConfig.DEBUG) {
                floatingActionButton.setOnLongClickListener(view -> {

                    viewModel.onAddDebugMeetingClicked();

                    return true;
                });
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();

            inflater.inflate(R.menu.meeting_menu, menu);

            return true;
        }

        /**@Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            int itemId = item.getItemId();
            if (itemId == R.id.meeting_menu_sort) {
                viewModel.onDisplaySortingButtonClicked();
                return true;
            }

            return super.onOptionsItemSelected(item);
        }*/

        @Override
        public void onMeetingClicked(@NonNull View imageView, @NonNull View textView, int meetingId) {
            Intent intent = MeetingDetailActivity.navigate(this, meetingId);

            Pair<View, String> imageViewPair = new Pair<>(imageView, imageView.getTransitionName());
            Pair<View, String> textViewPair = new Pair<>(textView, textView.getTransitionName());

            @SuppressWarnings("unchecked")
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    imageViewPair,
                    textViewPair
            );

            startActivity(intent, options.toBundle());
        }

        @Override
        public void onDeleteMeetingClicked(int meetingId) {
            viewModel.onDeleteMeetingClicked(meetingId);
        }

        /**@Override
        public void onHourSelected(@NonNull LocalTime hour) {
            viewModel.onHourSelected(hour);
        }*/
}
