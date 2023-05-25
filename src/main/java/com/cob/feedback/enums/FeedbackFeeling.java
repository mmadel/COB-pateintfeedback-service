package com.cob.feedback.enums;

public enum FeedbackFeeling {
    VGood("vgood"),
    Good("good"),
    Sad("sad"),
    VSad("vsad");

    public final String label;

    FeedbackFeeling(String label) {
        this.label = label;
    }
    @Override
    public String toString(){
        return label;
    }
}
