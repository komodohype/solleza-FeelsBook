package com.example.solleza_feelsbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends Activity {
    public static final String EXTRA_MESSAGE = "com.example.feelsbook.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
     * Called when the user presses a button
     * @TODO: refactor using map and reflection to remove switch block
     */
    public void recordEmotion(View view) {
        EditText commentText = (EditText) findViewById(R.id.commentText);
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
            default:
                emotion = new Emotion();
        }

        try {
            emotion.setComment(comment);
        }
        catch (ExceptionCommentTooLong e) {
        }

        emotion.setDate(new Date());

        TextView displayText = findViewById(R.id.displayText);
        displayText.setText(emotion.getDateString() + new String(": ") + emotion.getComment());
    }

//    /** Called when the user presses a button */
//    public void recordEmotion(View view) {
//        final Intent intent = new Intent(this, DisplayActivity.class);
//
//        Button loveButton = (Button) findViewById(R.id.loveButton);
//        loveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText commentText = (EditText) findViewById(R.id.commentText);
//                String comment = new String("Love: ").concat(commentText.getText().toString());
//
//                TextView displayText = findViewById(R.id.displayText);
//                displayText.setText(comment);
//
////                intent.putExtra(EXTRA_MESSAGE, comment);
////                startActivity(intent);
//            }
//        });
//    }

//    public void sendMessage(View view) {
//        // Do something in response to button
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//
//        // Get the Intent that started this activity and extract the string
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(message);
//    }
}
