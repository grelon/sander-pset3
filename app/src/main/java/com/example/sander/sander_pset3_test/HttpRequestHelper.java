package com.example.sander.sander_pset3_test;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by sander on 20-4-17.
 */

public class HttpRequestHelper {

    protected static synchronized String downloadFromServer(String... params) {
        String result = "";
        String chosenTag = params[0];

        if (chosenTag != null) {
            try {
                URL url = new URL("https://www.omdbapi.com/?s=" + chosenTag + "&type=movie");
                HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
                connect.setRequestMethod("GET");


                Integer responseCode = connect.getResponseCode();
                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        result += line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
