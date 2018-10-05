package com.example.solleza_feelsbook;

public class Joy extends Emotion {
    @Override
    public String toString() {
        return getDateString() + " | Joy | " + getComment();
    }
}
