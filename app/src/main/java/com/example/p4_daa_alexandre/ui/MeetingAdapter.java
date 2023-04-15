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
    private ItemClickListener mListener;

    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MeetingItemBinding binding = MeetingItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new MeetingViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.mBinding.titleMeeting.setText("Réunion JC");
        holder.mBinding.hourMeeting.setText("10h30");
    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public static class MeetingViewHolder extends RecyclerView.ViewHolder {

        private MeetingItemBinding mBinding;

        public MeetingViewHolder(MeetingItemBinding binding, ItemClickListener listener) {
            super(binding.getRoot());
            mBinding = binding;
            mBinding.getRoot().setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }


        public void bind(Meeting meeting) {
            String title = meeting.toString();
            String hour = meeting.toString();
            mBinding.titleMeeting.setText(title);
            mBinding.hourMeeting.setText(hour);
        }

    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }

}
