package com.andreea.jokeactivitylibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";
    private static final String TAG = JokeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Intent intent = getIntent();
        if (intent == null || intent.hasExtra(JokeActivity.JOKE_KEY)) {
            Log.e(TAG, "Missing joke extra string" + JOKE_KEY);
            return;
        }

        String jokeText = intent.getStringExtra(JokeActivity.JOKE_KEY);
        TextView jokeTextView = findViewById(R.id.joke_text_view);
        if (TextUtils.isEmpty(jokeText)) {
            jokeTextView.setText(jokeText);
        }
    }
}
