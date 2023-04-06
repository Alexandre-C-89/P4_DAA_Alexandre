package com.example.p4_daa_alexandre.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.p4_daa_alexandre.R;

public class AddMeetingFragment extends Fragment {

    public static AddMeetingFragment newInstance() {
        AddMeetingFragment fragment = new AddMeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_add, container, false);

        return view;
    }

    /**
     * Init the list of meeting
     */
    private void initListOfMeeting() {

    }

}
