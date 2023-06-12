package com.cob.feedback.enums;

public enum FeedbackFeeling {
    VGood("VGood"),
    Good("Good"),
    Sad("Sad"),
    VSad("VSad");

    public final String label;

    FeedbackFeeling(String label) {
        this.label = label;
    }
    @Override
    public String toString(){
        return label;
    }
}
