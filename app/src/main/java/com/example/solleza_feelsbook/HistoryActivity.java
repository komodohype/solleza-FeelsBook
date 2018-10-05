package com.example.solleza_feelsbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class HistoryActivity extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ArrayList<Emotion> emotions = new ArrayList<>();
    private ArrayAdapter<Emotion> adapter;
    private ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        historyListView = findViewById(R.id.historyListView);

        loadEmotions();
        adapter = new ArrayAdapter<>(this, R.layout.activity_history_list_view, R.id.textView, emotions);
        historyListView.setAdapter(adapter);
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
}
