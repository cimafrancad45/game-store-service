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
public class InvoiceDaoTest {
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    TShirtDao tshirtDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TaxRateDao taxRateDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;

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

        List<Invoice> iList = invoiceDao.getAllInvoices();
        for (Invoice i: iList) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteInvoice(){

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

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertNull(invoice2);


    }

    @Test
    public void getAllInvoices() {

        Invoice invoice = new Invoice();
        invoice.setName("Justin");
        invoice.setStreet("24 Lotus Ave");
        invoice.setCity("Princeton Junction");
        invoice.setState("NJ");
        invoice.setZipcode("08550");
        invoice.setItemType("Consoles");
        invoice.setItemId(5);
        invoice.setUnitPrice(new BigDecimal("499.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(invoice.getUnitPrice().
                add(invoice.getUnitPrice().multiply(taxRateDao.getTax(invoice.getState())))
                .multiply(new BigDecimal(invoice.getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP));
        invoice.setTax(taxRateDao.getTax(invoice.getState()));
        invoice.setProcessingFee(processingFeeDao.getProcessingFee(invoice.getItemType()));
        invoice.setTotal(invoice.getSubtotal().
                add(invoice.getProcessingFee()).setScale(2, BigDecimal.ROUND_HALF_UP));


        invoiceDao.addInvoice(invoice);


        Invoice invoice2 = new Invoice();
        invoice2.setName("Daryl");
        invoice2.setStreet("39 Magical Ave");
        invoice2.setCity("Princeton Junction");
        invoice2.setState("NJ");
        invoice2.setZipcode("08550");
        invoice2.setItemType("Consoles");
        invoice2.setItemId(39);
        invoice2.setUnitPrice(new BigDecimal("299.99"));
        invoice2.setQuantity(1);
        invoice2.setSubtotal(invoice2.getUnitPrice().
                add(invoice.getUnitPrice().multiply(taxRateDao.getTax(invoice.getState())))
                .multiply(new BigDecimal(invoice.getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP));
        invoice2.setTax(taxRateDao.getTax(invoice.getState()));
        invoice2.setProcessingFee(processingFeeDao.getProcessingFee(invoice.getItemType()));
        invoice2.setTotal(invoice.getSubtotal().
                add(invoice.getProcessingFee()).setScale(2, BigDecimal.ROUND_HALF_UP));


        invoiceDao.addInvoice(invoice2);

        assertEquals( invoiceDao.getAllInvoices().size(), 2);

    }

    @Test
    public void updateInvoice(){
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


        invoiceDao.addInvoice(invoice);

        invoice.setName("Daryl");
        invoice.setStreet("39 Magical St.");
        invoice.setCity("Princeton Junction");
        invoice.setState("NJ");
        invoice.setZipcode("08550");
        invoice.setItemType("Consoles");
        invoice.setItemId(39);
        invoice.setUnitPrice(new BigDecimal("299.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(invoice.getUnitPrice().
                add(invoice.getUnitPrice().multiply(taxRateDao.getTax(invoice.getState())))
                .multiply(new BigDecimal(invoice.getQuantity())).setScale(2, BigDecimal.ROUND_HALF_UP));
        invoice.setTax(taxRateDao.getTax(invoice.getState()));
        invoice.setProcessingFee(processingFeeDao.getProcessingFee(invoice.getItemType()));
        invoice.setTotal(invoice.getSubtotal().
                add(invoice.getProcessingFee()).setScale(2, BigDecimal.ROUND_HALF_UP));

        invoiceDao.updateInvoice(invoice);

        Invoice invoice2 = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice2, invoice);

    }
}
