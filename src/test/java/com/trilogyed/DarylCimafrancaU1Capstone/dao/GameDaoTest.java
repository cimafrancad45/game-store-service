package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.Console;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.Game;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoTest {

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TShirtDao tshirtDao;

    @Before
    public void setUp() {

        List<Game> gList = gameDao.getAllGames();
        for (Game g : gList) {
            gameDao.deleteGame(g.getGameId());
        }

        List<Console> cList = consoleDao.getAllConsoles();
        for (Console c : cList) {
            consoleDao.deleteConsole(c.getConsoleId());
        }

        List<TShirt> tList = tshirtDao.getAllTShirts();
        for (TShirt t : tList) {
            tshirtDao.deleteTShirt(t.getTShirtId());
        }
    }

    @Test
    public void addGetDeleteGame(){
        Game game = new Game();
        game.setTitle("Samurai Showdown");
        game.setErsbRating("M");
        game.setDescription("A return of an arcade classic fighting game!");
        game.setStudio("SNK");
        game.setPrice(new BigDecimal ("59.99"));
        game.setQuantity(10);

        game = gameDao.addGame(game);

        Game game2 = gameDao.getGame(game.getGameId());

        assertEquals(game2, game);

        gameDao.deleteGame(game.getGameId());

        game2 = gameDao.getGame(game.getGameId());

        assertNull(game2);

    }

    @Test
    public void getAllGames() {

        Game game = new Game();
        game.setTitle("Samurai Showdown");
        game.setErsbRating("M");
        game.setDescription("A return of an arcade classic fighting game!");
        game.setStudio("SNK");
        game.setPrice(new BigDecimal ("59.99"));
        game.setQuantity(10);

        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setTitle("Hatsune Miku: Project Diva Future Tone DX");
        game2.setErsbRating("T");
        game2.setDescription("A port of the arcade music game Project Future Tone featuring Japan's #1 virtual idol.");
        game2.setStudio("Sega");
        game2.setPrice(new BigDecimal ("49.99"));
        game2.setQuantity(39);

        gameDao.addGame(game2);

        Game game3 = new Game();
        game3.setTitle("Final Fantasy XIV: Shadowbringers");
        game3.setErsbRating("T");
        game3.setDescription("The latest expansion of Square Enix's popular MMORPG.");
        game3.setStudio("Square Enix");
        game3.setPrice(new BigDecimal ("39.99"));
        game3.setQuantity(14);

        gameDao.addGame(game3);

        assertEquals(gameDao.getAllGames().size(), 3);


    }

    @Test
    public void updateGame(){


        Game game = new Game();
        game.setTitle("Samurai Showdown");
        game.setErsbRating("M");
        game.setDescription("A return of an arcade classic fighting game!");
        game.setStudio("SNK");
        game.setPrice(new BigDecimal ("59.99"));
        game.setQuantity(10);

        game = gameDao.addGame(game);

        game.setTitle("Street Fighter V: Arcade Edition");
        game.setErsbRating("T");
        game.setDescription("The latest addition to Capcom's legendary fighting game series, Street Fighter.");
        game.setStudio("Capcom");
        game.setPrice(new BigDecimal ("19.99"));
        game.setQuantity(5);

        gameDao.updateGame(game);

        Game game2 = gameDao.getGame(game.getGameId());

        assertEquals(game2, game);



    }

    @Test
    public void getGamesByStudio(){


        Game game = new Game();
        game.setTitle("Monster Hunter Worlds");
        game.setErsbRating("T");
        game.setDescription("A brand new Monster Hunter game.");
        game.setStudio("Capcom");
        game.setPrice(new BigDecimal ("39.99"));
        game.setQuantity(13);

        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setTitle("Hatsune Miku: Project Diva Future Tone DX");
        game2.setErsbRating("T");
        game2.setDescription("A port of the arcade music game Project Future Tone featuring Japan's #1 virtual idol.");
        game2.setStudio("Sega");
        game2.setPrice(new BigDecimal ("49.99"));
        game2.setQuantity(39);

        gameDao.addGame(game2);

        Game game3 = new Game();
        game3.setTitle("Devil May Cry: V");
        game3.setErsbRating("M");
        game3.setDescription("The newest sequel to Capcom's Devil May Cry series.");
        game3.setStudio("Capcom");
        game3.setPrice(new BigDecimal ("59.99"));
        game3.setQuantity(14);

        gameDao.addGame(game3);

        assertEquals(gameDao.findGamesByStudio("Capcom").size() , 2);



    }

    @Test
    public void getGamesByESRB(){


        Game game = new Game();
        game.setTitle("Monster Hunter Worlds");
        game.setErsbRating("T");
        game.setDescription("A brand new Monster Hunter game.");
        game.setStudio("Capcom");
        game.setPrice(new BigDecimal ("39.99"));
        game.setQuantity(13);

        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setTitle("Hatsune Miku: Project Diva Future Tone DX");
        game2.setErsbRating("T");
        game2.setDescription("A port of the arcade music game Project Future Tone featuring Japan's #1 virtual idol.");
        game2.setStudio("Sega");
        game2.setPrice(new BigDecimal ("49.99"));
        game2.setQuantity(39);

        gameDao.addGame(game2);

        Game game3 = new Game();
        game3.setTitle("Devil May Cry: V");
        game3.setErsbRating("M");
        game3.setDescription("The newest sequel to Capcom's Devil May Cry series.");
        game3.setStudio("Capcom");
        game3.setPrice(new BigDecimal ("59.99"));
        game3.setQuantity(14);

        gameDao.addGame(game3);


        assertEquals(gameDao.findGamesByERSB("T").size(), 2);

    }

    @Test
    public void getGamesByTitle(){


        Game game = new Game();
        game.setTitle("Samurai Showdown");
        game.setErsbRating("M");
        game.setDescription("A return of an arcade classic fighting game!");
        game.setStudio("SNK");
        game.setPrice(new BigDecimal ("59.99"));
        game.setQuantity(10);

        gameDao.addGame(game);

        Game game2 = new Game();
        game2.setTitle("Final Fantasy XV");
        game2.setErsbRating("T");
        game2.setDescription("An expansion of Square Enix's popular MMORPG.");
        game2.setStudio("Square Emix");
        game2.setPrice(new BigDecimal ("19.99"));
        game2.setQuantity(13);

        gameDao.addGame(game2);

        Game game3 = new Game();
        game3.setTitle("Final Fantasy XIV: Shadowbringers");
        game3.setErsbRating("T");
        game3.setDescription("The latest expansion of Square Enix's popular MMORPG.");
        game3.setStudio("Square Enix");
        game3.setPrice(new BigDecimal ("39.99"));
        game3.setQuantity(14);

        gameDao.addGame(game3);

        List<Game> ffxiv = gameDao.findGamesByTitle("Final Fantasy XIV: Shadowbringers");
        List<Game> samuraiShowdown = gameDao.findGamesByTitle("Samurai Showdown");

        assertEquals(1, ffxiv.size());
        assertEquals(samuraiShowdown.size(), 1);

    }
}
