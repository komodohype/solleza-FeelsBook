package com.example.solleza_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TallyActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Emotion> emotions = new ArrayList<>();

    private TextView tallyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tally);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        loadEmotions();
        tallyTextView = findViewById(R.id.tallyTextView);
        tallyTextView.setText(getTally());
    }

    private void loadEmotions() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(new GsonDeserializeExclusion()).create();

            Type type = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotions = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            emotions = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTally() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Anger", 0);
        map.put("Fear", 0);
        map.put("Joy", 0);
        map.put("Love", 0);
        map.put("Sadness", 0);
        map.put("Surprise", 0);

        for (Emotion em: emotions) {
            map.put(em.getType(), map.get(em.getType()) +1);
        }

        String tally = new String("");
        for (String key: map.keySet()) {
            tally = tally + key + ": " + map.get(key).toString() + "\n";
        }

        return tally;
    }
}
