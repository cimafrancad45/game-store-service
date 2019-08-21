package com.trilogyed.DarylCimafrancaU1Capstone.dao;

import com.trilogyed.DarylCimafrancaU1Capstone.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoTest {
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void initializeData(){
        List<Console> cList = consoleDao.getAllConsoles();
        for(Console c : cList){
            consoleDao.deleteConsole(c.getConsoleId());
        }

        List<Game> gList = gameDao.getAllGames();
        for (Game g : gList){
            gameDao.deleteGame(g.getGameId());
        }

        List<TShirt> tList = tShirtDao.getAllTShirts();
        for (TShirt t: tList){
            tShirtDao.deleteTShirt(t.getTShirtId());
        }

    }

    @Test
    public void addGetDeleteTShirt(){
        TShirt shirt = new TShirt();
        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        shirt = tShirtDao.addTShirt(shirt);

        TShirt shirt2 = tShirtDao.getTShirt(shirt.getTShirtId());

        assertEquals(shirt, shirt2);

        tShirtDao.deleteTShirt(shirt.getTShirtId());

        shirt2 = tShirtDao.getTShirt(shirt.getTShirtId());

        assertNull(shirt2);

    }

    @Test
    public void getAllTShirts() {
        List<TShirt> tList = new ArrayList<>();


        TShirt shirt = new TShirt();
        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        tShirtDao.addTShirt(shirt);

        tList.add(shirt);


        TShirt shirt2 = new TShirt();
        shirt2.setSize("L");
        shirt2.setColor("Red");
        shirt2.setDescription("A Mario T-shirt");
        shirt2.setPrice(new BigDecimal("9.99"));
        shirt2.setQuantity(2);

        tShirtDao.addTShirt(shirt2);

        tList.add(shirt2);

        List<TShirt> tList2 = tShirtDao.getAllTShirts();

        assertEquals(tList.size(), tList2.size());





    }

    @Test
    public void updateTShirt(){

        TShirt shirt = new TShirt();
        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        shirt = tShirtDao.addTShirt(shirt);


        shirt.setSize("L");
        shirt.setColor("Red");
        shirt.setDescription("A Mario T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(2);

        tShirtDao.updateTShirt(shirt);

        assertEquals(shirt, tShirtDao.getTShirt(shirt.getTShirtId()));

    }

    @Test
    public void getTShirtsBySize(){

        TShirt shirt = new TShirt();
        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        tShirtDao.addTShirt(shirt);


        TShirt shirt2 = new TShirt();
        shirt2.setSize("L");
        shirt2.setColor("Red");
        shirt2.setDescription("A Mario T-shirt");
        shirt2.setPrice(new BigDecimal("9.99"));
        shirt2.setQuantity(2);

        tShirtDao.addTShirt(shirt2);

        TShirt shirt3 = new TShirt();
        shirt3.setSize("L");
        shirt3.setColor("Black");
        shirt3.setDescription("A Mortal Kombat T-ishirt");
        shirt3.setPrice(new BigDecimal("12.99"));
        shirt3.setQuantity(5);

        tShirtDao.addTShirt(shirt3);

        List<TShirt> mShirts = tShirtDao.getTShirtsBySize("M");
        List<TShirt> lShirts = tShirtDao.getTShirtsBySize("L");

        assertEquals(mShirts.size(), 1);
        assertEquals(lShirts.size(), 2);


    }

    @Test
    public void getTShirtsByColor(){

        TShirt shirt = new TShirt();
        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        tShirtDao.addTShirt(shirt);


        TShirt shirt2 = new TShirt();
        shirt2.setSize("L");
        shirt2.setColor("Red");
        shirt2.setDescription("A Mario T-shirt");
        shirt2.setPrice(new BigDecimal("9.99"));
        shirt2.setQuantity(2);

        tShirtDao.addTShirt(shirt2);

        TShirt shirt3 = new TShirt();
        shirt3.setSize("XL");
        shirt3.setColor("Blue");
        shirt3.setDescription("A Street Fighter T-Shirt");
        shirt3.setPrice(new BigDecimal("9.99"));
        shirt3.setQuantity(5);

        tShirtDao.addTShirt(shirt3);

        List<TShirt> redShirts = tShirtDao.getTShirtsByColor("Red");
        List<TShirt> blueShirts = tShirtDao.getTShirtsByColor("Blue");

        assertEquals(redShirts.size(), 1);
        assertEquals(blueShirts.size(), 2);

    }

}
