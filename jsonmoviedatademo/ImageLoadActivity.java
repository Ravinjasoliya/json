package com.example.vaibhav.jsonmoviedatademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageLoadActivity extends AppCompatActivity {

    ImageView imageViewMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);

        imageViewMovie = (ImageView) findViewById(R.id.imageViewMovie);
        Intent intent = getIntent();
        imageViewMovie.setImageDrawable(intent.getBooleanExtra("imageKey"));
    }
}
