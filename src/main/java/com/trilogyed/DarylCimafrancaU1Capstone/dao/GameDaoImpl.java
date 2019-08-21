package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao{
    //jdbc template
    JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //Set Statements
    private final String INSERT_GAME_SQL =
            "insert into game (title, ersb_rating, description, price, studio, quantity) values (?,?,?,?,?,?)";
    private final String SELECT_GAME_SQL =
            "select * from game where game_id = ?";
    private final String SELECT_ALL_GAMES_SQL =
            "select * from game";
    private final String SELECT_GAMES_BY_TITLE =
            "select * from game where title = ?";
    private final String SELECT_GAMES_BY_STUDIO =
            "select * from game where studio = ?";
    private final String SELECT_GAMES_BY_ERSB =
            "select * from game where ersb_rating = ?";
    private final String UPDATE_GAME_SQL =
            "update game set title = ?, ersb_rating = ?, description = ?, price = ?, studio = ?, quantity = ? where game_id = ?";
    private final String DELETE_GAME =
            "delete from game where game_id = ?";

    @Override
    public Game addGame(Game game){
        jdbcTemplate.update(INSERT_GAME_SQL,
                game.getTitle(),
                game.getErsbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        game.setGameId(id);
        return game;
    }

    @Override
    public Game getGame(int id){
        try {
            return jdbcTemplate.queryForObject(SELECT_GAME_SQL, this::mapGameToRow, id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(SELECT_ALL_GAMES_SQL, this::mapGameToRow);
    }

    @Override
    public List<Game> findGamesByTitle(String title){
        return jdbcTemplate.query(SELECT_GAMES_BY_TITLE, this::mapGameToRow, title);
    }

    public List<Game> findGamesByStudio(String studio){
        return jdbcTemplate.query(SELECT_GAMES_BY_STUDIO, this::mapGameToRow, studio);
    }

    public List<Game> findGamesByERSB(String ersb){
        return jdbcTemplate.query(SELECT_GAMES_BY_ERSB, this::mapGameToRow, ersb);
    }

    public void updateGame(Game game){
        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getErsbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity(),
                game.getGameId());
    }

    public void deleteGame(int id){
        jdbcTemplate.update(DELETE_GAME, id);
    }

    private Game mapGameToRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("game_id"));
        game.setTitle(rs.getString("title"));
        game.setErsbRating(rs.getString("ersb_rating"));
        game.setDescription(rs.getString("description"));
        game.setPrice(rs.getBigDecimal("price"));
        game.setStudio(rs.getString("studio"));
        game.setQuantity(rs.getInt("quantity"));

        return game;
    }
}
