package com.example.nerapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NewActivePage extends AsyncTask<Void,Void,String> {
    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL("192.168.1.6/name");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    httpURLConnection.getInputStream()
            ));
            stringBuilder = new StringBuilder();

            while ((json = bufferedReader.readLine()) != null){
                stringBuilder.append(json);
            }
            System.out.println("My name is : "+stringBuilder);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    HttpURLConnection httpURLConnection = null;
    BufferedReader bufferedReader = null;
    String json;
    StringBuilder stringBuilder;
}
