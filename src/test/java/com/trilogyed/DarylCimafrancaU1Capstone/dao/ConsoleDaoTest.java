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
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleDaoTest {

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
    public void addGetDeleteConsole(){

        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setProcessor("ARM processor");
        console.setMemoryAmount("32GB");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        console = consoleDao.addConsole(console);

        Console console2 = consoleDao.getConsole(console.getConsoleId());

        assertEquals(console2, console);

        consoleDao.deleteConsole(console.getConsoleId());

        console2 = consoleDao.getConsole(console.getConsoleId());

        assertNull(console2);

    }

    @Test
    public void getAllConsoles() {

        List<Console> consoleList = new ArrayList<>();
        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("32GB");
        console.setProcessor("ARM processor");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        consoleDao.addConsole(console);

        Console console2 = new Console();
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setProcessor("ARM processor");
        console.setMemoryAmount("16GB");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(24);

        consoleDao.addConsole(console2);

        consoleList.add(console);
        consoleList.add(console2);

        assertEquals(consoleList.size(), consoleDao.getAllConsoles().size());


    }

    @Test
    public void updateConsole(){

        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("32GB");
        console.setProcessor("ARM processor");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        consoleDao.addConsole(console);

        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("16GB");
        console.setProcessor("ARM processor");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(24);

        consoleDao.updateConsole(console);

        Console console2 = consoleDao.getConsole(console.getConsoleId());

        assertEquals(console2, console);

    }

    @Test
    public void getConsoleByManufacturer(){


        Console console = new Console();
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("32GB");
        console.setProcessor("ARM processor");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        consoleDao.addConsole(console);

        Console console2 = new Console();
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console.setMemoryAmount("16GB");
        console2.setProcessor("ARM processor");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(24);

        consoleDao.addConsole(console2);

        Console console3 = new Console();
        console3.setModel("3DS");
        console3.setManufacturer("Nintendo");
        console.setMemoryAmount("8GB");
        console3.setProcessor("ARM processor");
        console3.setPrice(new BigDecimal("149.99"));
        console3.setQuantity(4);

        consoleDao.addConsole(console3);

        List<Console> sonyList = consoleDao.getConsoleByManufacturer("Sony");
        List<Console> nintendoList = consoleDao.getConsoleByManufacturer("Nintendo");

        assertEquals(sonyList.size(), 1);
        assertEquals(nintendoList.size(), 2);

    }

}
