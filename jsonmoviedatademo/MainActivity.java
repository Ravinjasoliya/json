package com.example.vaibhav.jsonmoviedatademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listViewMovie;
    ArrayList<HashMap<String, String>> arrayListMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMovie = (ListView) findViewById(R.id.listViewMovie);
        arrayListMovie = new ArrayList<>();

        MovieLoader movieLoader = new MovieLoader(MainActivity.this, "http://api.androidhive.info/json/movies.json", new MovieResult() {
            @Override
            public void movieResult(String result) {
                try {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = jsonArray.getJSONObject();

                    for (int i = 0; i < jsonArray.length(); i++) {

                        jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("title", jsonObject.getString("title"));
                        final String movieImage = hashMap.put("image", jsonObject.getString("image"));
                        hashMap.put("rating", jsonObject.getString("rating"));
                        hashMap.put("releaseYear", jsonObject.getString("releaseYear"));
                        arrayListMovie.add(hashMap);

                        listViewMovie.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(listViewMovie.getId() == v.getId()){
                                    Intent intent = new Intent(MainActivity.this, ImageLoadActivity.class);
                                    intent.putExtra("imageKey",movieImage);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                    String[] list = {"title", "rating", "releaseYear"};
                    int[] setData = {R.id.textTitle, R.id.ratingMovie, R.id.textYear};
                    SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, arrayListMovie, R.layout.layout, list, setData);
                    listViewMovie.setAdapter(simpleAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        movieLoader.execute();
    }
}
