package com.example.p4_daa_alexandre.data.sorting.model;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.Comparator;

public class ChronologicalSortingType {

    OLDEST_FIRST(
        new int[]{-R.attr.state_not_sorted, R.attr.state_sorted, -R.attr.state_invert_sorted},
    R.string.sorting_chronological_sorted,
            (o1, o2) -> {
        return o1.getTime().compareTo(o2.getTime());
    }
    ),
    NEWEST_FIRST(
        new int[]{-R.attr.state_not_sorted, -R.attr.state_sorted, R.attr.state_invert_sorted},
    R.string.sorting_chronological_inverted_sorted,
            (o1, o2) -> {
        return o2.getTime().compareTo(o1.getTime());
    }
    ),
    NONE(
        new int[]{R.attr.state_not_sorted, -R.attr.state_sorted, -R.attr.state_invert_sorted},
    R.string.sorting_chronological_none,
            null
            );

    private final int[] state;

    @StringRes
    private final int messageStringRes;

    @Nullable
    private final Comparator<Meeting> comparator;

    ChronologicalSortingType(int[] state, @StringRes int messageStringRes, @Nullable Comparator<Meeting> comparator) {
        this.state = state;
        this.messageStringRes = messageStringRes;
        this.comparator = comparator;
    }

    public int[] getState() {
        return state;
    }

    @StringRes
    public int getMessageStringRes() {
        return messageStringRes;
    }

    @Nullable
    public Comparator<Meeting> getComparator() {
        return comparator;
    }

}
