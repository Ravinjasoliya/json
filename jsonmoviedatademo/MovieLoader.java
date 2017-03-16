package com.example.vaibhav.jsonmoviedatademo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Vaibhav on 06-Sep-16.
 */
public class MovieLoader extends AsyncTask<Void, Void, String> {

    Context context;
    String url;
    ProgressDialog progressDialog;
    MovieResult movieResult;

    public MovieLoader(Context context, String url, MovieResult movieResult){
        this.context = context;
        this.url = url;
        this.movieResult = movieResult;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Data is Loading");
        progressDialog.show();

    }

    @Override
    protected String doInBackground(Void... params) {

        String movie = "";
        try {
            URL urla = new URL(url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) urla.openConnection();
            if(httpURLConnection.getResponseCode() == 200) {
                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String data;

                while ((data = bufferedReader.readLine()) != null) {
                    movie += data;
                }
            }
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();
        movieResult.movieResult(s);

    }
}
