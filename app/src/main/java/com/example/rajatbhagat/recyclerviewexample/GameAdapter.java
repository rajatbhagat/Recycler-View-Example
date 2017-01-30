package com.example.rajatbhagat.recyclerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rajatbhagat on 30/1/17.
 */

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> gameList;

    public GameAdapter(List<Game> gameList) { this.gameList = gameList; }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);

        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.gameDetailTextView.setText(game.getGameTitle());
        holder.gameTitleTextView.setText(game.getGameDeveloper());
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {

        private TextView gameTitleTextView;
        private TextView gameDetailTextView;

        public GameViewHolder(View itemView) {
            super(itemView);

            gameTitleTextView = (TextView) itemView.findViewById(R.id.card_view_recycler_text_view_title);
            gameDetailTextView = (TextView) itemView.findViewById(R.id.card_view_recycler_text_view_details);
        }
    }
}
