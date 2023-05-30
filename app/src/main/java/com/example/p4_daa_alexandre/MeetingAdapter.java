package com.example.p4_daa_alexandre;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder> {

    private List<Meeting> mMeetings;
    private OnItemClickListener mListener;

    // Constructeur pour initialiser la liste de réunions et l'écouteur de clic
    public MeetingAdapter(List<Meeting> meetings) {
        mMeetings = meetings;
    }

    // Interface pour écouter les clics sur les éléments de la liste
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Méthode pour définir l'écouteur de clic
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // ViewHolder pour stocker les vues de l'élément de la liste de réunions
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMeetingTitle;
        private TextView mMeetingDate;
        private TextView mMeetingHour;
        private TextView mMeetingParticipants;
        private TextView mMeetingRoomName;
        private ImageButton mDeleteButton;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            mMeetingTitle = itemView.findViewById(R.id.title_item_meeting_list_textview);
            mMeetingHour = itemView.findViewById(R.id.hour_item_meeting_list_textview);
            mMeetingDate = itemView.findViewById(R.id.date_item_meeting_list_textview);
            mMeetingParticipants = itemView.findViewById(R.id.participant_name_item_meeting_list_textview);
            mMeetingRoomName = itemView.findViewById(R.id.room_name_item_meeting_list_textview);

            mDeleteButton = itemView.findViewById(R.id.delete_button);
            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }

        public void bind(Meeting meeting) {
            mMeetingTitle.setText(meeting.getTitle());
            mMeetingParticipants.setText(TextUtils.join(", ", meeting.getParticipants()));
            mMeetingHour.setText(meeting.getTime().toString()); // Convert LocalTime to String
            mMeetingDate.setText(meeting.getDate().toString()); // Convert LocalDateTime to String
            mMeetingRoomName.setText(meeting.getRoomName());
        }

    }

    // onCreateViewHolder pour créer le ViewHolder de l'élément de la liste de réunions
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meeting_list, parent, false);
        return new ViewHolder(itemView, mListener);
    }

    // onBindViewHolder pour mettre à jour les vues de l'élément de la liste de réunions
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);
        holder.bind(meeting);
    }

    // getItemCount pour renvoyer le nombre d'éléments dans la liste de réunions
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public void removeMeeting(int position) {
        if (position >= 0 && position < mMeetings.size()) {
            mMeetings.remove(position);
            notifyItemRemoved(position);
        }
    }
}