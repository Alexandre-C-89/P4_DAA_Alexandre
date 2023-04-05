package com.example.p4_daa_alexandre.Data;

import java.util.Collections;
import java.util.List;

public class reunionModel {

    public Integer getMhour() {
        return mhour;
    }

    public void setMhour(Integer mhour) {
        this.mhour = mhour;
    }

    public String getMplace() {
        return mplace;
    }

    public void setMplace(String mplace) {
        this.mplace = mplace;
    }

    public String getMabout() {
        return mabout;
    }

    public void setMabout(String mabout) {
        this.mabout = mabout;
    }

    public List<String> getMparticipant() {
        return mparticipant;
    }

    public void setMparticipant(String mparticipant) {
        this.mparticipant = Collections.singletonList(mparticipant);
    }

    private Integer mhour;
    private String mplace;
    private String mabout;
    private List<String> mparticipant;

}
