package com.inuappcenter.shareu.my_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class subjectName {
    @SerializedName("subjectName")
    @Expose
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public subjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}