package xyz.aakashrivastava.onelabsdemo;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class FullScreenActivity extends AppCompatActivity implements Serializable {

    ImageView showView;
    ArrayList<ImageModel> arrayList = new ArrayList<>();
    ViewPager mPager;
    int position = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        showView = findViewById(R.id.fullShow);

        String imageURL = getIntent().getStringExtra("uuuu");
        position = getIntent().getIntExtra("eeee",1);
        arrayList = getIntent().getParcelableArrayListExtra("qqqq");

        mPager = findViewById(R.id.mpager);
        Picasso.get().load(imageURL).into(showView);
        mPager.setVisibility(View.INVISIBLE);
        mPager.setOffscreenPageLimit(1);
        mPager.setAdapter(new EndSwipeAdapter(this, arrayList, position));
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                mPager.setVisibility(View.VISIBLE);
                showView.setVisibility(View.INVISIBLE);
            }
        },700);

    }
}
