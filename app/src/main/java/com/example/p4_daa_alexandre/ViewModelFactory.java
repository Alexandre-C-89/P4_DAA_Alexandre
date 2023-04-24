package com.example.p4_daa_alexandre;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;

import java.time.format.DateTimeFormatter;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        factory = new ViewModelFactory(
                                new MeetingRepository(),
                                DateTimeFormatter.ofPattern("HH:mm")
                        );
                    }
                }
            }
        }

        return factory;
    }

    @NonNull
    private final MeetingRepository meetingRepository;
    @NonNull
    private final DateTimeFormatter hourDateTimeFormatter;

    private ViewModelFactory(
            @NonNull MeetingRepository meetingRepository,
            @NonNull DateTimeFormatter hourDateTimeFormatter
    ) {
        this.meetingRepository = meetingRepository;
        this.hourDateTimeFormatter = hourDateTimeFormatter;
    }



}
