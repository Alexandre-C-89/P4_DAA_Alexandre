package com.example.p4_daa_alexandre.ui.meetings.list;

import android.view.View;
import androidx.annotation.NonNull;

public interface OnMeetingClickedListener {

    void onMeetingClicked(@NonNull View imageView, @NonNull View textView, int meetingId);

    void onDeleteMeetingClicked(int meetingId);

}
