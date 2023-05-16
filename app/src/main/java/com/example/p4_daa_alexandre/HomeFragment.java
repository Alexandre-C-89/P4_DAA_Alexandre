package com.example.p4_daa_alexandre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_daa_alexandre.DI.Di;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private List<Meeting> meetingList = new ArrayList();
    private HomeFragmentViewModel mViewModel;
    private MeetingRepository mMeetingRepository;
    private MeetingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        mMeetingRepository = Di.getMeetingRepository();

        // Initialize the ViewModel with the MeetingRepository
        mViewModel = new ViewModelProvider(this, new HomeFragmentViewModelFactory(mMeetingRepository)).get(HomeFragmentViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Observe la liste de meeting
        mViewModel.getMeetings().observe(getViewLifecycleOwner(), new Observer<List<Meeting>>() {
            @Override
            public void onChanged(List<Meeting> meetings) {
                // Mettre à jour la liste de meetings
                updateList(meetings);
            }
        });
        initRecyclerViews();

        // Récupérer la nouvelle réunion passée en tant qu'argument
        Bundle args = getArguments();
        if (args != null && args.containsKey("newMeeting")) {
            Meeting newMeeting = args.getParcelable("newMeeting");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            // Récupérer la nouvelle réunion passée en tant qu'argument
            Meeting newMeeting = data.getParcelableExtra("newMeeting");
            // Ajouter la nouvelle réunion à la liste des réunions
            mViewModel.addMeeting(newMeeting);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateList(List<Meeting> listMeeting){
        meetingList.clear();
        meetingList.addAll(listMeeting);
        adapter.notifyDataSetChanged();
    }

    private void initRecyclerViews() {
        adapter = new MeetingAdapter(meetingList);
        RecyclerView recyclerView = binding.listMeetingRecyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
