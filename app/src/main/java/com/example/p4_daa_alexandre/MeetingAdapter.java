package com.example.p4_daa_alexandre;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private List<Meeting> mMeetings;

    // Constructeur pour initialiser la liste de réunions et l'écouteur de clic
    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    // ViewHolder pour stocker les vues de l'élément de la liste de réunions
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMeetingTitle;
        private TextView mMeetingParticipants;
        private TextView mMeetingHour;
        private TextView mMeetingRoomName;

        public ViewHolder(View itemView) {
            super(itemView);
            mMeetingTitle = itemView.findViewById(R.id.title_item_meeting_list_textview);
            //mMeetingHour = itemView.findViewById(R.id.hour_item_meeting_list_textview);
            //mMeetingParticipants = itemView.findViewById(R.id.participant_name_item_meeting_list_textview);
            mMeetingRoomName = itemView.findViewById(R.id.room_name_item_meeting_list_textview);
        }

        public void bind(Meeting meeting) {
            mMeetingTitle.setText(meeting.getTitle());
            //mMeetingParticipants.setText(meeting.getParticipants());
            //mMeetingHour.setText(meeting.getTime());
            mMeetingRoomName.setText(meeting.getRoomName());

        }
    }

    // onCreateViewHolder pour créer le ViewHolder de l'élément de la liste de réunions
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_list, parent, false);
        return new ViewHolder(itemView);
    }

    // onBindViewHolder pour mettre à jour les vues de l'élément de la liste de réunions
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.bind(meeting);
    }

    // getItemCount pour renvoyer le nombre d'éléments dans la liste de réunions
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}