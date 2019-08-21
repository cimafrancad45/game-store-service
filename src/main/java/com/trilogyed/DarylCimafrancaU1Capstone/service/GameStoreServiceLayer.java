package com.trilogyed.DarylCimafrancaU1Capstone.service;


import com.trilogyed.DarylCimafrancaU1Capstone.dao.*;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.Console;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.Game;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.Invoice;
import com.trilogyed.DarylCimafrancaU1Capstone.dto.TShirt;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.ConsoleViewModel;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.GameViewModel;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.InvoiceViewModel;
import com.trilogyed.DarylCimafrancaU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameStoreServiceLayer {

    private GameDao gameDao;
    private ConsoleDao consoleDao;
    private TShirtDao tShirtDao;
    private InvoiceDao invoiceDao;
    private TaxRateDao taxRateDao;
    private ProcessingFeeDao processingFeeDao;

    @Autowired
    public GameStoreServiceLayer(GameDao gameDao, ConsoleDao consoleDao, TShirtDao tShirtDao, InvoiceDao invoiceDao, TaxRateDao taxRateDao, ProcessingFeeDao processingFeeDao) {
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tShirtDao = tShirtDao;
        this.invoiceDao = invoiceDao;
        this.taxRateDao = taxRateDao;
        this.processingFeeDao = processingFeeDao;

    }


    //consoles

    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();

        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setConsoleId(console.getConsoleId());
        console.setQuantity(consoleViewModel.getQuantity());

        console = consoleDao.addConsole(console);

        consoleViewModel.setConsoleId(console.getConsoleId());

        return consoleViewModel;

    }

    public ConsoleViewModel getConsoleById(int id) {
        Console console = consoleDao.getConsole(id);
        if (console == null)
            return null;
        else
            return buildConsoleViewModel(console);
    }

    public List<ConsoleViewModel> getAllConsoles() {
        List<Console> consoles = consoleDao.getAllConsoles();
        List<ConsoleViewModel> cvmList = new ArrayList<>();
        for (Console c : consoles) {
            ConsoleViewModel cvm = buildConsoleViewModel(c);
            cvmList.add(cvm);
        }
        return cvmList;
    }

    public List<ConsoleViewModel> getConsolesByManufacturer(String manufacturer) {
        List<Console> cList = consoleDao.getConsoleByManufacturer(manufacturer);
        List<ConsoleViewModel> cvmList = new ArrayList<>();

        for (Console c : cList) {
            cvmList.add(buildConsoleViewModel(c));
        }
        if (cList.size() == 0)
            return null;
        else
            return cvmList;
    }

    public void updateConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setConsoleId(consoleViewModel.getConsoleId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleDao.updateConsole(console);
    }

    public void deleteConsole(int id) {
        consoleDao.deleteConsole(id);
    }


    //Games

    public GameViewModel addGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setErsbRating(gameViewModel.getErsbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        game = gameDao.addGame(game);

        gameViewModel.setGameId(game.getGameId());

        return gameViewModel;
    }

    public GameViewModel getGameById(int id) {
        Game game = gameDao.getGame(id);
        return buildGameViewModel(game);
    }

    public List<GameViewModel> getAllGames() {
        List<Game> gList = gameDao.getAllGames();
        List<GameViewModel> gvmList = new ArrayList<>();

        for (Game g : gList) {
            GameViewModel gvm = buildGameViewModel(g);
            gvmList.add(gvm);
        }

        return gvmList;

    }

    public List<GameViewModel> getGamesByStudio(String studio) {
        List<Game> gList = gameDao.findGamesByStudio(studio);
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game g : gList) {
            GameViewModel gvm = buildGameViewModel(g);
            gvmList.add(gvm);
        }
        return gvmList;
    }

    public List<GameViewModel> getGamesByTitle(String title) {
        List<Game> gList = gameDao.findGamesByTitle(title);
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game g : gList) {
            GameViewModel gvm = buildGameViewModel(g);
            gvmList.add(gvm);
        }
        return gvmList;
    }

    public List<GameViewModel> getGamesByErsbRating(String ersbRating) {
        List<Game> gList = gameDao.findGamesByERSB(ersbRating);
        List<GameViewModel> gvmList = new ArrayList<>();
        for (Game g : gList) {
            GameViewModel gvm = buildGameViewModel(g);
            gvmList.add(gvm);
        }
        return gvmList;
    }


    public void updateGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setErsbRating(gameViewModel.getErsbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        gameDao.updateGame(game);
    }

    public void deleteGame(int id) {
        gameDao.deleteGame(id);


    }


    //T-Shirts

    public TShirtViewModel addTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();

        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirt = tShirtDao.addTShirt(tShirt);

        tShirtViewModel.settShirtId(tShirt.getTShirtId());

        return tShirtViewModel;
    }

    public TShirtViewModel getTShirt(int id) {
        TShirt tshirt = tShirtDao.getTShirt(id);
            return buildTShirtViewModel(tshirt);
    }

    public List<TShirtViewModel> getAllTShirts(){
        List<TShirt> tList= tShirtDao.getAllTShirts();
        List<TShirtViewModel> tvmList = new ArrayList<>();

        for (TShirt t : tList){
            TShirtViewModel tvm = buildTShirtViewModel(t);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public List<TShirtViewModel> getTShirtsByColor(String color){
        List<TShirt> tList= tShirtDao.getTShirtsByColor(color);
        List<TShirtViewModel> tvmList = new ArrayList<>();

        for (TShirt t : tList){
            TShirtViewModel tvm = buildTShirtViewModel(t);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public List<TShirtViewModel> getTShirtsBySize(String size){
        List<TShirt> tList= tShirtDao.getTShirtsBySize(size);
        List<TShirtViewModel> tvmList = new ArrayList<>();

        for (TShirt t : tList){
            TShirtViewModel tvm = buildTShirtViewModel(t);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public void updateTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(tShirtViewModel.getTShirtId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirtDao.updateTShirt(tShirt);
    }

    public void deleteTShirt(int id) {
        tShirtDao.deleteTShirt(id);
    }


    //Invoice

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {

        //Persist Invoice Dto
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setQuantity(invoiceViewModel.getQuantity());

        // Getting Item Information
        // 4. The order process logic must properly update the quantity on hand for the item in the order.
        // 6. Order quantity must be less than or equal to the number of items on hand in inventory.

        BigDecimal itemPrice;

        if (invoice.getItemType().equals("Consoles")) {
            Console item = consoleDao.getConsole(invoice.getItemId());
            int itemQuantity = item.getQuantity();
            itemPrice = item.getPrice();
            // 6. Order quantity must be less than or equal to the number of items on hand in inventory.
            if (invoice.getQuantity() > itemQuantity) {
                throw new IllegalArgumentException("Not enough items on hand.");
            } else {
                item.setQuantity(itemQuantity - invoice.getQuantity());
                consoleDao.updateConsole(item);
            }
        } else if (invoice.getItemType().equals("T-Shirts")) {
            TShirt item = tShirtDao.getTShirt(invoice.getItemId());
            int itemQuantity = item.getQuantity();
            itemPrice = item.getPrice();
            // 6. Order quantity must be less than or equal to the number of items on hand in inventory.
            if (invoice.getQuantity() > itemQuantity) {
                throw new IllegalArgumentException("Not enough items on hand.");
            } else {
                item.setQuantity(itemQuantity - invoice.getQuantity());
                tShirtDao.updateTShirt(item);
            }
        } else if (invoice.getItemType().equals("Games")) {
            Game item = gameDao.getGame(invoice.getItemId());
            int itemQuantity = item.getQuantity();
            itemPrice = item.getPrice();
            // 6. Order quantity must be less than or equal to the number of items on hand in inventory.
            if (invoice.getQuantity() > itemQuantity) {
                throw new IllegalArgumentException("Not enough items on hand.");
            } else {
                item.setQuantity(itemQuantity - invoice.getQuantity());
                gameDao.updateGame(item);
            }
        } else {
            throw new IllegalArgumentException("Item type not correct! Please choose [Games], [T-Shirts], or [Consoles].");
        }

        // Setting Unit Price
        invoice.setUnitPrice(itemPrice.setScale(2));

        //Getting Tax_Rate and processing_Fees
        BigDecimal taxRate = taxRateDao.getTax(invoice.getState());
        BigDecimal processingFee = processingFeeDao.getProcessingFee(invoice.getItemType());

        // Calculating Subtotal
        invoice.setSubtotal(BigDecimal.valueOf(invoice.getQuantity()).multiply(invoice.getUnitPrice()).setScale(2));

        // Calculating Tax
        invoice.setTax(taxRate.multiply(invoice.getSubtotal()).setScale(2, RoundingMode.HALF_UP));

        // Calculating Processing Fee
        if (invoice.getQuantity() > 10) {
            invoice.setProcessingFee(new BigDecimal("15.49").setScale(2).add(processingFee).setScale(2));
        } else {
            invoice.setProcessingFee(processingFee.setScale(2));
        }

        // Calculating Total
        invoice.setTotal(invoice.getSubtotal().add(invoice.getTax()).add(invoice.getProcessingFee()).setScale(2));

        //Add to DB
        invoice = invoiceDao.addInvoice(invoice);

        return buildInvoiceViewModel(invoice);
    }

    // ------------------------------------------------------------------------------

    //builds
    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {

        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;

    }

    private GameViewModel buildGameViewModel(Game game) {

        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setErsbRating(game.getErsbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;

    }

    private TShirtViewModel buildTShirtViewModel(TShirt tShirt) {

        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.settShirtId(tShirt.getTShirtId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;

    }


}
