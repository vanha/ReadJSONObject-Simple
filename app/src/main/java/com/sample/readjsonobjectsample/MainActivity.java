package com.sample.readjsonobjectsample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadJSONObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
    }

    private class ReadJSONObject extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            InputStreamReader inputStreamReader;
            URL url;
            BufferedReader bufferedReader;
            String line = "";
            StringBuilder content = new StringBuilder();
            try {
                url = new URL(strings[0]);
                inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String monhoc = "nothing";

            try {
                JSONObject jsonObject = new JSONObject(s);
                monhoc = jsonObject.getString("monhoc");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Toast.makeText(MainActivity.this, monhoc, Toast.LENGTH_SHORT).show();

        }
    }
}
