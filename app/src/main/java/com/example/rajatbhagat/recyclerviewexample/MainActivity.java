package com.example.rajatbhagat.recyclerviewexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Game> gameList = new ArrayList<>();
    private GameAdapter gameAdapter = new GameAdapter(gameList);

    private String[] gameTitles = {
            "Poke'mon Go",
            "Far Cry 3",
            "Metal Gear Solid",
            "Resident Evil",
            "FIFA 17",
            "Alto's Adventure"
    };
    private String[] gameDetails = {
            "Niantic",
            "Square Enix",
            "Dont Know",
            "This also I dont know",
            "EA Sports",
            "Some Android Developer"
    };

    private RequestQueue requestQueue;
    private ProgressDialog progressDialog;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Game List");

        gameList = fillGameData();
        gameAdapter = new GameAdapter(gameList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_test);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(gameAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, GameDetails.class);
                        intent.putExtra("title", gameTitles[position]);
                        intent.putExtra("details", gameDetails[position]);
                        startActivity(intent);
                    }
                })
        );
    }

    private List<Game> fillGameData() {
        List<Game> gameTempList = new ArrayList<>();

        for(int i = 0; i < gameTitles.length; i++) {
            Game game = new Game();
            game.setGameTitle(gameTitles[i]);
            game.setGameDeveloper(gameDetails[i]);
            gameTempList.add(game);
        }
        gameAdapter.notifyDataSetChanged();
        return gameTempList;
    }
}
