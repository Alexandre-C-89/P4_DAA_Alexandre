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
    private OnItemClickListener mListener;

    // Interface pour écouter les clics sur les éléments de la liste de réunions
    public interface OnItemClickListener {
        void onItemClick(Meeting meeting);
    }

    // Constructeur pour initialiser la liste de réunions et l'écouteur de clic
    public MeetingAdapter(List<Meeting> meetings, OnItemClickListener listener) {
        mMeetings = meetings;
        mListener = listener;
    }

    // ViewHolder pour stocker les vues de l'élément de la liste de réunions
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMeetingTitle;
        private TextView mMeetingParticipants;
        private TextView mMeetingHour;
        private TextView mMeetingRoomName;

        public ViewHolder(View itemView) {
            super(itemView);
            mMeetingTitle = itemView.findViewById(R.id.title_create_meeting_inputedittext);
            mMeetingHour = itemView.findViewById(R.id.hour_create_meeting_inputedittext);
            mMeetingParticipants = itemView.findViewById(R.id.participant_create_meeting_inputedittext);
            mMeetingRoomName = itemView.findViewById(R.id.room_name_create_meeting_inputedittext);
        }

        public void bind(Meeting meeting, OnItemClickListener listener) {
            mMeetingTitle.setText(meeting.getTitle());
            mMeetingParticipants.setText(meeting.getParticipants());

            // Ajouter un écouteur de clic sur l'élément de la liste de réunions
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(meeting);
                }
            });
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
        holder.bind(meeting, mListener);
    }

    // getItemCount pour renvoyer le nombre d'éléments dans la liste de réunions
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }
}