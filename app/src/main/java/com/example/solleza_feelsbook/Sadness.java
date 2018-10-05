package com.example.solleza_feelsbook;

public class Sadness extends Emotion {
    @Override
    public String toString() {
        return getDateString() + " | Sadness | " + getComment();
    }
}
