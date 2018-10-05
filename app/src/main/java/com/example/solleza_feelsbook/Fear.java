package com.example.solleza_feelsbook;

public class Fear extends Emotion {
    @Override
    public String toString() {
        return getDateString() + " | Fear | " + getComment();
    }
}
