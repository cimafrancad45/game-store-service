package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.Game;

import java.util.List;

public interface GameDao {

    Game addGame(Game game);

    Game getGame(int id);

    List<Game> getAllGames();

    List<Game> findGamesByTitle(String title);

    List<Game> findGamesByStudio(String studio);

    List<Game> findGamesByERSB(String esrb);

    void updateGame(Game game);

    void deleteGame(int id);


}
