package com.example.rajatbhagat.recyclerviewexample;

/**
 * Created by rajatbhagat on 30/1/17.
 */

public class Game {

    private String gameTitle;
    private String gameDetail;

    public Game() {
        // Mandatory empty constructor
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDetail = gameDeveloper;
    }

    public String getGameTitle() { return gameTitle; }

    public String getGameDeveloper() {
        return gameDetail;
    }
}
