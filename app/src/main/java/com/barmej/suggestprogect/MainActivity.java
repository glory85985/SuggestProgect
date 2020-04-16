package com.barmej.suggestprogect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.Random;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Random random = new Random();
    int[] placePictures = {
            R.drawable.beach,
            R.drawable.bike,
            R.drawable.football,
            R.drawable.museum,
            R.drawable.park,
            R.drawable.restaurant,
            R.drawable.running,
            R.drawable.shop,
            R.drawable.swimming,
            R.drawable.walking};
    String[] namePictures;
    private static final String BUNDLE_CURRENT_INDEX = "BUNDLE_CURRENT_INDEX";
    private int mcurrentindex = -1;
    ImageView placeImageView;
    TextView placeTextView;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeImageView = findViewById(R.id.imageView);

        placeTextView = new TextView(this);
        placeTextView = (TextView) findViewById(R.id.textView);
        namePictures = getResources().getStringArray(R.array.namePictures1);
    }


    public void show(int i) {
        mcurrentindex = i;
        Drawable placeDrawable = ContextCompat.getDrawable(this, placePictures[i]);
        placeImageView.setImageDrawable(placeDrawable);

        placeTextView.setText(namePictures[i]);
        Log.i(TAG, String.valueOf(i));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_CURRENT_INDEX, mcurrentindex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            mcurrentindex = savedInstanceState.getInt(BUNDLE_CURRENT_INDEX);
            if (mcurrentindex != -1) {
                show(mcurrentindex);
            }
        }
    }

    public void random(View view) {
        int randomplacePictures = random.nextInt(10);
        show(randomplacePictures);
    }

    public void back(View view) {
        if ((mcurrentindex == -1) || (mcurrentindex == 0)) {
            mcurrentindex = 9;
            show(mcurrentindex);
        } else if (mcurrentindex < placePictures.length) {
            --mcurrentindex;
            show(mcurrentindex);
        }
    }

    public void next(View view) {
        mcurrentindex++;
        if (mcurrentindex >= placePictures.length) {
            mcurrentindex = 0;
        }
        show(mcurrentindex);

    }
}