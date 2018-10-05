package com.example.solleza_feelsbook;

public class Surprise extends Emotion {
    @Override
    public String toString() {
        return getDateString() + " | Surprise | " + getComment();
    }
}
