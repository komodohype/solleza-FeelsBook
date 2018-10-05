package com.example.solleza_feelsbook;

public class Love extends Emotion {
    @Override
    public String toString() {
        return getDateString() + " | Love | " + getComment();
    }
}
