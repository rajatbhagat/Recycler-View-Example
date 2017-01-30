package com.example.rajatbhagat.recyclerviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class GameDetails extends AppCompatActivity {

    private TextView titleTextView;
    private TextView detailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);

        titleTextView = (TextView) findViewById(R.id.text_view_title);
        detailTextView = (TextView) findViewById(R.id.text_view_details);

        titleTextView.setText(getIntent().getStringExtra("title"));
        detailTextView.setText(getIntent().getStringExtra("details"));
    }
}
