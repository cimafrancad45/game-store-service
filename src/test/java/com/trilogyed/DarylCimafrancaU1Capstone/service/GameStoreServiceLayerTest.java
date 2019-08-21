package com.trilogyed.DarylCimafrancaU1Capstone.service;

import com.trilogyed.DarylCimafrancaU1Capstone.dao.*;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.*;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.InvoiceViewModel;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.TShirtViewModel;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GameStoreServiceLayerTest {
    GameDao gameDao;
    ConsoleDao consoleDao;
    TShirtDao tShirtDao;
    InvoiceDao invoiceDao;
    GameStoreServiceLayer service;
    TaxRateDao taxRateDao;
    ProcessingFeeDao processingFeeDao;

    @Before
    public void setUp() throws Exception{

        setUpTaxRateDaoMock();
        setUpProcessingFeeDaoMock();
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpTShirtDaoMock();
        setUpInvoiceDaoMock();

        service = new GameStoreServiceLayer(gameDao,consoleDao,tShirtDao,invoiceDao, taxRateDao, processingFeeDao);
    }

    @Test
    public void saveFindInvoice(){
        Invoice invoice = new Invoice();

        invoice.setName("Justin");
        invoice.setStreet("24 Lotus Ave");
        invoice.setCity("Princeton Junction");
        invoice.setState("NJ");
        invoice.setZipcode("08550");
        invoice.setItemType("Consoles");
        invoice.setItemId(39);
        invoice.setUnitPrice(new BigDecimal("499.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(invoice.getUnitPrice().
                add(invoice.getUnitPrice().multiply(taxRateDao.getTax(invoice.getState())))
                .multiply(new BigDecimal(invoice.getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP));
        invoice.setTax(taxRateDao.getTax(invoice.getState()));
        invoice.setProcessingFee(processingFeeDao.getProcessingFee(invoice.getItemType()));
        invoice.setTotal(invoice.getSubtotal().
                add(invoice.getProcessingFee()).setScale(2, BigDecimal.ROUND_HALF_UP));


        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice2, invoice);

    }

    @Test
    public void saveFindConsole(){
        Console console = new Console();

        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setProcessor("ARM processor");
        console.setMemoryAmount("32GB");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        console = consoleDao.addConsole(console);

        Console console2 = consoleDao.getConsole(console.getConsoleId());

        assertEquals(console, console2);

    }

    @Test
    public void getConsolesByManufacturer(){

        Console console = new Console();

        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setProcessor("ARM processor");
        console.setMemoryAmount("32GB");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        consoleDao.addConsole(console);

        List<Console> sonyList = consoleDao.getConsoleByManufacturer("Sony");

        TestCase.assertEquals(sonyList.size(), 1);
    }

    @Test
    public void saveFindGame(){
        Game game = new Game();

        game.setTitle("Samurai Showdown");
        game.setErsbRating("M");
        game.setDescription("A return of an arcade classic fighting game!");
        game.setStudio("SNK");
        game.setPrice(new BigDecimal ("59.99"));
        game.setQuantity(10);

        game = gameDao.addGame(game);

        Game game2 = gameDao.getGame(game.getGameId());

        assertEquals(game, game2);

    }

    @Test
    public void addGetTShirt(){
        TShirtViewModel shirt = new TShirtViewModel();

        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        shirt = service.addTShirt(shirt);
        TShirtViewModel fromService = service.getTShirt(shirt.getTShirtId());

        assertEquals(fromService, shirt);

    }

    //
    //Mockito Tests
    //

    private void setUpTaxRateDaoMock() {
        taxRateDao = mock(TaxRateDaoImpl.class);

        TaxRate taxRate = new TaxRate();
        taxRate.setState("NJ");
        taxRate.setRate(new BigDecimal("0.05").setScale(2));

        doReturn(taxRate).when(taxRateDao).getTax("NJ");
    }

    private void setUpProcessingFeeDaoMock() {
        processingFeeDao = mock(ProcessingFeeDaoImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("T-Shirts");
        processingFee.setFee(new BigDecimal("1.98").setScale(2));

        doReturn(processingFee).when(processingFeeDao).getProcessingFee("T-Shirts");
    }

    private void setUpConsoleDaoMock(){

        consoleDao = mock(ConsoleDaoImpl.class);

        Console console = new Console();

        console.setConsoleId(4);
        console.setModel("Playstation 4");
        console.setManufacturer("Sony");
        console.setProcessor("ARM processor");
        console.setMemoryAmount("32GB");
        console.setPrice(new BigDecimal("499.99"));
        console.setQuantity(25);

        Console console2 = new Console();

        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setProcessor("ARM processor");
        console.setMemoryAmount("16GB");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(24);

        List<Console> cList = new ArrayList<>();

        cList.add(console);

        doReturn(console).when(consoleDao).addConsole(console);
        doReturn(console).when(consoleDao).getConsole(4);
        doReturn(cList).when(consoleDao).getAllConsoles();

    }

    private void setUpGameDaoMock(){

        gameDao = mock(GameDaoImpl.class);


        Game game = new Game();

        game.setGameId(3);
        game.setTitle("Samurai Showdown");
        game.setErsbRating("M");
        game.setDescription("A return of an arcade classic fighting game!");
        game.setStudio("SNK");
        game.setPrice(new BigDecimal ("59.99"));
        game.setQuantity(10);

        Game game2 = new Game();

        game2.setTitle("Street Fighter V: Arcade Edition");
        game2.setErsbRating("T");
        game2.setDescription("The latest addition to Capcom's legendary fighting game series, Street Fighter.");
        game2.setStudio("Capcom");
        game2.setPrice(new BigDecimal ("19.99"));
        game2.setQuantity(5);

        List<Game> gList = new ArrayList<>();
        gList.add(game);

        doReturn(game).when(gameDao).addGame(game);
        doReturn(game).when(gameDao).getGame(3);
        doReturn(gList).when(gameDao).getAllGames();

    }


    private void setUpTShirtDaoMock(){
        tShirtDao = mock(TShirtDaoImpl.class);

        TShirt shirt = new TShirt();
        shirt.settShirtId(65);
        shirt.setSize("M");
        shirt.setColor("Blue");
        shirt.setDescription("A Sonic T-shirt");
        shirt.setPrice(new BigDecimal("9.99"));
        shirt.setQuantity(4);

        TShirt shirt2 = new TShirt();
        shirt2.setSize("M");
        shirt2.setColor("Blue");
        shirt2.setDescription("A Sonic T-shirt");
        shirt2.setPrice(new BigDecimal("9.99"));
        shirt2.setQuantity(4);

        List<TShirt> tList = new ArrayList<>();
        tList.add(shirt);

        doReturn(shirt).when(tShirtDao).addTShirt(shirt);
        doReturn(shirt).when(tShirtDao).getTShirt(65);
        doReturn(tList).when(tShirtDao).getAllTShirts();
    }

    private void setUpInvoiceDaoMock(){

        invoiceDao = mock(InvoiceDaoImpl.class);


        Invoice invoice = new Invoice();
        invoice.setInvoiceId(3);
        invoice.setName("Justin");
        invoice.setStreet("24 Lotus Ave");
        invoice.setCity("Princeton Junction");
        invoice.setState("NJ");
        invoice.setZipcode("08550");
        invoice.setItemType("Consoles");
        invoice.setItemId(29);
        invoice.setUnitPrice(new BigDecimal("499.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("499.99"));
        invoice.setTax(new BigDecimal("0.05"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(invoice.getSubtotal().
                add(invoice.getProcessingFee()).setScale(2, BigDecimal.ROUND_HALF_UP));

        Invoice invoice2 = new Invoice();
        invoice2.setName("Daryl");
        invoice2.setStreet("39 Magical Ln");
        invoice2.setCity("Princeton Junction");
        invoice2.setState("NJ");
        invoice2.setZipcode("08550");
        invoice2.setItemType("Consoles");
        invoice2.setItemId(39);
        invoice2.setUnitPrice(new BigDecimal("299.99"));
        invoice2.setQuantity(1);
        invoice2.setSubtotal(new BigDecimal("299.99"));
        invoice2.setTax(new BigDecimal("0.05"));
        invoice2.setProcessingFee(new BigDecimal("14.99"));
        invoice2.setTotal(invoice2.getSubtotal().
                add(invoice2.getProcessingFee()).setScale(2, BigDecimal.ROUND_HALF_UP));

        List<Invoice> ivcList = new ArrayList<>();
        ivcList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(3);
        doReturn(ivcList).when(invoiceDao).getAllInvoices();

    }

}
