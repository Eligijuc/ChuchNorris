package com.example.chuchnorris;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button fetchJokeButton;
    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchJokeButton = findViewById(R.id.fetchJokeButton);
        jokeTextView = findViewById(R.id.jokeTextView);

        fetchJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new URLAsyncTask(MainActivity.this).execute();
            }
        });
    }

    public void updateJokeText(String jokeString) {
        try {
            JSONObject jokeObject = new JSONObject(jokeString);
            String joke = jokeObject.getString("value");
            jokeTextView.setText(joke);
        } catch (JSONException e) {
            e.printStackTrace();
            jokeTextView.setText("Error parsing joke");
        }
    }
}
