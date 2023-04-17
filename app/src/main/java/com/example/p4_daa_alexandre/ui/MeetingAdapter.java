package com.example.p4_daa_alexandre.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.p4_daa_alexandre.data.Meeting;
import com.example.p4_daa_alexandre.databinding.MeetingItemBinding;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetings;

    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MeetingItemBinding binding = MeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MeetingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.bind(meeting);
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        private MeetingItemBinding mBinding;

        public MeetingViewHolder(MeetingItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }


         public void bind(Meeting meeting) {
             String title = meeting.getTitle();
             mBinding.titleMeeting.setText(title);
         }
    }
}
