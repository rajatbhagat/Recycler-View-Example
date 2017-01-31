package com.example.rajatbhagat.recyclerviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

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
            "Alto's Adventure",
            "Doom",
            "Pro Evolution Soccer"
    };
    private String[] gameDetails = {
            "Niantic",
            "Square Enix",
            "Konami",
            "Konami",
            "EA Sports",
            "Some Android Developer",
            "ID",
            "Konami"
    };

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

        SwipeableRecyclerViewTouchListener cardSwipeListener = new SwipeableRecyclerViewTouchListener(
                recyclerView,
                new SwipeableRecyclerViewTouchListener.SwipeListener() {

                    @Override
                    public boolean canSwipeLeft(int position) {
                        return true;
                    }

                    @Override
                    public boolean canSwipeRight(int position) {
                        return true;
                    }

                    @Override
                    public void onDismissedBySwipeLeft(final RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            gameList.remove(position);
                            Snackbar snackbar = Snackbar.make(recyclerView, "Card Removed : " + position + " by swiping left", Snackbar.LENGTH_SHORT)
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(MainActivity.this, "To be implemented", Toast.LENGTH_LONG).show();
                                        }
                                    });
                            snackbar.show();
                            gameAdapter.notifyItemRemoved(position);
                        }
                        gameAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            gameList.remove(position);
                            Snackbar snackbar = Snackbar.make(recyclerView, "Card Removed : " + position + " by swiping right", Snackbar.LENGTH_LONG)
                                    .setAction("UNDO", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Toast.makeText(MainActivity.this, "To be implemented", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            snackbar.show();
                            gameAdapter.notifyItemRemoved(position);
                        }
                        gameAdapter.notifyDataSetChanged();
                    }
                }
        );
        recyclerView.addOnItemTouchListener(cardSwipeListener);
    }

    private List<Game> fillGameData() {
        List<Game> gameTempList = new ArrayList<>();

        for (int i = 0; i < gameTitles.length; i++) {
            Game game = new Game();
            game.setGameTitle(gameTitles[i]);
            game.setGameDeveloper(gameDetails[i]);
            gameTempList.add(game);
        }
        gameAdapter.notifyDataSetChanged();
        return gameTempList;
    }
}
