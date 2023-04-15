package com.example.p4_daa_alexandre.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;
    private List<Meeting> mMeetings =new ArrayList<>();
    private MeetingRepository mApiService;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mRecyclerView = binding.recyclerviewMeetingList;
        mAdapter = new MeetingAdapter(mMeetings);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        // mApiService = DI.getMeetingApiService();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        initListOfMeeting();
    }

    /**
     * Init the list of meeting
     */
    private void initListOfMeeting() {
        mMeetings.clear();
        mMeetings.addAll(mApiService.getMeetingsLiveData().getValue());
        mAdapter.notifyDataSetChanged();
    }

}
