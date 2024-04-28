package com.example.chuchnorris;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLAsyncTask extends AsyncTask<String, Void, String> {

    private MainActivity activity;

    public URLAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(Constants.NORRIS_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jokeBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jokeBuilder.append(line).append("\n");
            }
            return jokeBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error fetching joke";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (activity != null) {
            activity.updateJokeText(result);
        }
    }
}
