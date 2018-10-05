package com.example.solleza_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.example.feelsbook.MESSAGE";

    private static final String FILENAME = "file.sav";
    private ArrayList<Emotion> emotions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadEmotions();
    }

    /*
     * Called when the user presses an emotion button
     * @TODO: refactor using map and reflection to remove switch block
     */
    public void recordEmotion(View view) {
        EditText commentText = findViewById(R.id.commentText);
        String comment = commentText.getText().toString();

        Emotion emotion;

        switch(view.getId()) {
            case R.id.loveButton :
                emotion = new Love();
                break;
            case R.id.joyButton :
                emotion = new Joy();
                break;
            case R.id.surpriseButton :
                emotion = new Surprise();
                break;
            case R.id.angerButton :
                emotion = new Anger();
                break;
            case R.id.sadnessButton :
                emotion = new Sadness();
                break;
            case R.id.fearButton :
                emotion = new Fear();
                break;
            default :
                emotion = new Emotion();
        }

        try {
            emotion.setComment(comment);
            emotion.setDate(new Date());

            emotions.add(emotion);
            saveEmotions();

            TextView displayText = findViewById(R.id.displayText);
            displayText.setText(emotion.getDateString() + new String(": ") + emotion.getComment());

            commentText.setText(new String(""));
        }
        catch (ExceptionCommentTooLong e) {
            e.printStackTrace();
        }
    }

    public void saveEmotions() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();

            gson.toJson(emotions, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void viewStatistics(View view) {}

    public void viewHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        String message = FILENAME;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}