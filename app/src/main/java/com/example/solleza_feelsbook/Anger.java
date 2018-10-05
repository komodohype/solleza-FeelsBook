package com.example.solleza_feelsbook;

import java.util.Date;

public class Anger extends Emotion {
    @Override
    public String toString() {
        return getDateString() + " | Anger | " + getComment();
    }
}
