package com.example.nerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Connection;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;


public class FormActivity extends AppCompatActivity {
    HttpURLConnection httpURLConnection = null;
    BufferedReader bufferedReader = null;
    String json;
    StringBuilder stringBuilder;
    TextView t6,t7,t8,t9,t10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        t6 = (TextView) findViewById(R.id.t6);
        t7 = (TextView) findViewById(R.id.t7);
        t8 = (TextView) findViewById(R.id.t8);
        t9 = (TextView) findViewById(R.id.t9);
        t10 = (TextView) findViewById(R.id.t10);

        NewActivePage newActivePage = new NewActivePage();
        newActivePage.execute();
    }
    private class NewActivePage extends AsyncTask<Void, Void, String> implements com.example.nerapp.NewActivePage {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://192.168.43.19:4000/name");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(
                        httpURLConnection.getInputStream()
                ));
                stringBuilder = new StringBuilder();
                System.out.println(bufferedReader);
                while ((json = bufferedReader.readLine()) != null){
                    stringBuilder.append(json);
                }
                JSONObject object = new JSONObject(stringBuilder.toString());
                JSONArray name = object.getJSONArray("name");
                JSONObject phone = object.getJSONObject("phone");
                JSONArray skills = object.getJSONArray("skills");
                JSONObject email = object.getJSONObject("email");
                JSONArray place = object.getJSONArray("place");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected String onPostExecute(){
            t6.setText("");
            t7.setText("");
            t8.setText("");
            t9.setText("");
            t10.setText("");
            return null;
        }
    }

}
